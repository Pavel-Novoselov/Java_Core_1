package MainWork;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javax.crypto.spec.PSource;

public class HW2Main {
    public static void main(String[] args) {
        //создаем массив
        String[][] arrayS = {{"1","2","3","4"},{"5","6","7","8"},{"9","10","11","12"},{"13","14","16","17"}};

        try {
            System.out.println("Сумма элементов массива " + arrExc(arrayS));
        } catch (MyArraySizeException e){
            System.out.println(e.getMessage());
        }
        catch (MyArrayDataException e1){
            System.out.println(e1.getMessage()+e1.getX()+" "+e1.getY());
        }
        System.out.println("Конец программы");
    }
    //метод принимает строковый 2-мерный массив и отдает сумму элементов
    public static int arrExc (String[][] strArr) throws MyArraySizeException, MyArrayDataException {
        //проверка размера массива
        if (strArr.length>4) throw new MyArraySizeException("Ошибка размера массива. Размер X массива превышен!");
        for (int k=0; k<strArr.length; k++){
            if (strArr[k].length>4)
                throw new MyArraySizeException("Ошибка размера массива. Размер Y массива превышен!");
        }

        int sum=0;

        for (int i=0;i<strArr.length;i++){
            for (int j=0;j<strArr[i].length;j++){
                if(isInt(strArr[i][j])){
                    sum+=Integer.parseInt(strArr[i][j]);
                } else throw new MyArrayDataException("Неверный тип данных в ячейке ", i, j);
            }
        }
        return sum;
    }

    //метод проверяет, спарсилась ли строка в число
    public static boolean isInt (String str){
        try{
            Integer.parseInt(str);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
