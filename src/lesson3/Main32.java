package lesson3;

import java.util.ArrayList;
import java.util.HashMap;

public class Main32 {
    public static void main(String[] args) {
//   создание экз.класса тел.книга
        PhoneBook phoneBook = new PhoneBook();
//сделал 2 метода add - в пер
//только создание новой записи с любым кол-вом тел.
        phoneBook.add ("Ivanov", new String[]{"123456789", "+7-495-123456789", "951951951"});
        phoneBook.add ("Penrov", new String[]{"+7-123-456-789", "33669823"});
        phoneBook.add ("Vasechkin", new String[]{"8-495-111-22-33", "78787878878"});
        phoneBook.add ("Vasechkin", new String[]{"111"});
//добавление 1 тел. в сущ. запись, либо создаем новую с одним тел.
        phoneBook.add ("Vasechkin", "999");
        phoneBook.add ("Ivanov", "000000");
        phoneBook.add("Sidorov", "+7-955-123-45-78");
        phoneBook.add("Sidorov", "555-22-33");
 //вся книга
        phoneBook.getAll();
        //поиск по фамилии
        phoneBook.get("Ivanov");
        phoneBook.get("Egorov");
    }
}
