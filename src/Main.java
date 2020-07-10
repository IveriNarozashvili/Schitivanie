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
                builder.append((char) a);
            }

            String b = builder.toString().toLowerCase();
            String[] words = b.replaceAll("[,.?:;/]*", "").split("\\s+");

            Arrays.sort(words);

            for (String word : words) {
                System.out.println(word);
            }
            collect =
                    Arrays.stream(words).collect(groupingBy(Function.identity(), counting()));
            for (Map.Entry<String, Long> map : collect.entrySet()) {

                System.out.println(map);

            }

            System.out.println();

            long maxvalue = Collections.max(collect.values());
            collect.entrySet().stream().filter(e -> e.getValue() == maxvalue).forEach(e -> System.out.println(e.getKey()));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

