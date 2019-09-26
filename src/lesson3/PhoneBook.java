package lesson3;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {
    String name;
    String phone;
    public Map<String, List<String>> fB;
//конструктор
    public PhoneBook() {
       fB = new HashMap<String,  List<String>>();
    }
//добавление новой записи c несколькими телефонами
    public void add (String name, String[] phone) {
       if (fB.get(name)==null) {
           fB.put(name, new ArrayList<String>(Arrays.asList(phone)));
           System.out.println("Новый абонент успешно добавлен!");
       }
       else
           System.out.println("Абонент "+name+" уже существует! Чтоб добавить тел. используйте строку (не массив строк!)!");
    }
//перегрузка метода - добавление 1 телефона в запись, либо новой записи c 1 телефоном
    public void add (String name, String phone) {
       if (fB.get(name)==null){
            fB.put(name, new ArrayList<String >());
        }
        fB.get(name).add(phone);
    }

    //вывод всей книги
    public void getAll(){
        System.out.println("Список контактов:");
        for (Map.Entry<String, List<String>> ee : fB.entrySet()){
            String kName = ee.getKey();
            List<String> values = ee.getValue();
            System.out.println(kName+" phone: "+values);
        }
    }
    //получение номера телефона по фамилии
    public void get(String name){
        System.out.println("Результат поиска:");
        System.out.println(name+" phone: "+fB.get(name));
    }

}
