import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        new WordCounter().maxfrequency();
    }
    // Читаем с файла и возвращаем ArrayList со считанными словами.
    public ArrayList fileReader() throws FileNotFoundException, UnsupportedEncodingException {
        StringBuilder pool = new StringBuilder();

        String separator = File.separator;
        String way = "C:" + separator + "Users" + separator + "qwe" + separator + "Desktop" + separator + "myfile.txt";

        Scanner scan = new Scanner(new InputStreamReader(
                new FileInputStream(way), "windows-1251"));
        while (scan.hasNextLine()) {
            pool.append(scan.nextLine() + " ");
        }

        String s = pool.toString();
        List<String> list = Stream.of(s.split("[^A-Za-zА-Яа-я]+"))
                .map(String::toLowerCase)
                .sorted()
                .collect(Collectors.toList());

        ArrayList list1 = new ArrayList();
        list1.addAll(list);
        return list1;
    }
    // Вычисляем частоту каждого слова и выводим в консоль.
    public LinkedHashMap frequentlyCounter(ArrayList<String> list) throws FileNotFoundException, UnsupportedEncodingException {
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
    public void maxfrequency() throws FileNotFoundException, UnsupportedEncodingException {
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
