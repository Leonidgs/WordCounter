package service;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public class WordCounter {

    // Читаем с файла и возвращаем ArrayList со считанными словами.
    public ArrayList fileReader()  {
        StringBuilder pool = new StringBuilder();

        String separator = File.separator;
        String way = "C:" + separator + "Users" + separator + "qwe" + separator + "Desktop" + separator + "myfile.txt";

        Scanner scan = null;
        try {
            scan = new Scanner(new InputStreamReader(
                    new FileInputStream(way), "windows-1251"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            pool.append(scan.nextLine() + " ");
        }

        String s = pool.toString();
        ArrayList<String> list = Stream.of(s.split("[^A-Za-zА-Яа-я]+"))
                .map(String::toLowerCase)
                .sorted()
                .collect(toCollection(ArrayList::new));

        return list;
    }
    // Вычисляем частоту каждого слова и выводим в консоль.
    public LinkedHashMap frequentlyCounter(ArrayList<String> list) {
        list = new WordCounter().fileReader();
        System.out.println("Отсортированный по алф список " + list);
        int counter = 0;
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    counter++;
                    map.put(list.get(i), counter);
                } else {
                    counter = 0;
                }
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Word " + entry.getKey() + " Frequency " + entry.getValue());
        }
        return map;
    }
    //Вычисляем слова с максимальной частотой и выводим в консоль.
    public void maxfrequency() {
        HashMap<String, Integer> map = new HashMap();
        map = new WordCounter().frequentlyCounter(fileReader());
        int max = Collections.max(map.values());
        HashMap<String, Integer> maxMap = new HashMap<>();
        System.out.println("=======================");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (max == entry.getValue()) {
                maxMap.put(entry.getKey(), entry.getValue());

                System.out.println("Max Frequency " + entry.getValue() + " Word " + entry.getKey());
            }
        }
    }
}
