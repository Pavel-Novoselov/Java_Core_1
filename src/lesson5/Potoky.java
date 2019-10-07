package lesson5;

import java.util.ArrayList;
import java.util.List;

public class Potoky {
    static final int THR = 40  ;//кол-во потоков (кратно 2-м!!!)
    static final int SIZE = 10000000;
    static final int HALF = SIZE /THR;//размер нарезанного массива

    float[] arr1 = new float[SIZE];//исх. массив для метода 1
    float[] arr2 = new float[SIZE];//исх. массив для метода 2

//метод-1 без разбиения массива
    public void potok1() {
//заполнение массива единицами
        for (int i = 0; i < SIZE; i++) {
            arr1[i] = 1;
        }

        long a = System.currentTimeMillis();

        arrCalc(arr1, 0);//вызов метода перерасчета массива
        System.out.print("Время пересчета массива в 1-м потоке - ");
        System.out.println(System.currentTimeMillis() - a);
    }

//метод перерасчета массива, sinchronized, для того, чтоб использовать в разных потоках одновременно
    public synchronized float[] arrCalc(float[] arr, int n) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + ((SIZE /THR)*n+i) / 5) * Math.cos(0.2f + ((SIZE /THR)*n+i) / 5) * Math.cos(0.4f + ((SIZE /THR)*n+i) / 2));
        }
        System.out.println("Проверка метода arrCalc (1-й и посл. элементы подмассива "+(n+1)+") = "+arr[0]+" "+arr[arr.length-1]);
        return arr;
    }

//метод-2 - разбивает массив на THR частей и запускает расчеты в THR потоках
    public void potok3() {
        float[] arrT; //временный массив
        Thread tr;//переменная потока
        //build ArrayList для храннения нарезанных кусочков массива
        List<float[]> list = new ArrayList<>();
        for (int i = 0; i < THR; i++) {
            list.add(new float[SIZE/THR]);
        }
//заполняем исходный массив единицами
        for (int i = 0; i < SIZE; i++) {
                arr2[i] = 1;
        }

        long a = System.currentTimeMillis();
//нарезаем исходный массив arr2 на THR массивов и записываем их в ArrayList
        for (int i = 0; i < THR; i++) {
            arrT = list.get(i);
            System.arraycopy(arr2, SIZE/THR*i, arrT, 0, SIZE/THR);
            list.set(i, arrT);
        }
        System.out.print("Время разбиения массива на "+THR+" под-массивов ");
        System.out.println(System.currentTimeMillis() - a);

        a = System.currentTimeMillis();

//в цикле THR-раз(2 или 4,8,10 и т.д) создаем потоки и отдаем на перерасчет под-массивы
        for (int i = 0; i < THR; i++){
            //переменные final чтоб передать параметры в потоке в метод arrCalc() (по другому не дает...)
            final float[] arrK=list.get(i);
            final int n=i;
            tr = new Thread(new Runnable() {
                @Override
                public void run() {
                    arrCalc(arrK, n);
                }
            });
            tr.start();
            try {
                tr.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//записывает пересчитанный под-массив в АррэйЛист
            list.set(i, arrK);
 //проверка - посмотрим что мы записали в Лист)
//            float[] fa=list.get(i);
//            System.out.println("fa= "+fa[0]+"+"+fa[fa.length-1]);
        }
        System.out.print("Время пересчета всех под-массивов в "+THR+" потоках - ");
        System.out.println(System.currentTimeMillis() - a);

        a = System.currentTimeMillis();

//обратная сборка массива
        for (int i = 0; i < THR; i++) {
            arrT = list.get(i);
            System.arraycopy(arrT, 0, arr2, SIZE/THR*i, SIZE/THR);
        }
        System.out.print("Время сборки массива - ");
        System.out.println(System.currentTimeMillis() - a);
//проверка, что же получилось)
        System.out.println(arr2[0] + " " + arr2[SIZE - 1]);
    }
}