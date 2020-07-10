import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {

    public static void main(String[] args) {

        Map<String, Long> collect = new HashMap<>();

        try (FileReader reader = new FileReader("file1.txt")) {
            StringBuilder builder = new StringBuilder();

            int a;
            while ((a = reader.read()) != -1) {
                builder.append((char)a);
            }

            String b = builder.toString().toLowerCase();
            String[] words = b.replaceAll("[,.?:;/]*","").split("\\s+");

            Arrays.sort(words);

            for (String word : words) {
                System.out.println(word);
            }
             collect =
                    Arrays.stream(words).collect(groupingBy(Function.identity(), counting()));
            for(Map.Entry<String, Long> map : collect.entrySet()){

                System.out.println(map);

            }

            System.out.println();
            collect.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .forEach(System.out::println);
        }

        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        List<Map.Entry<String, Long>> list = new ArrayList<>(collect.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<String, Long> result = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

    }

    }

