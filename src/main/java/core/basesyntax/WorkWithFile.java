package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> fileData = getDataFromFile(fromFileName);
        writeResultToFile(toFileName, fileData);
    }

    private Map<String, Integer> getDataFromFile(String fromFileName) {
        String value;
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((value = bufferedReader.readLine()) != null) {
                String[] arr = value.split(",");
                String fruitName = arr[0];
                int price = Integer.parseInt(arr[1]);

                if (map.containsKey(fruitName)) {
                    Integer sum = map.get(fruitName) + price;
                    map.put(fruitName, sum);
                } else {
                    map.put(fruitName, price);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    private static void writeResultToFile(String toFileName, Map<String, Integer> map) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            int result = map.get("supply") - map.get("buy");
            bufferedWriter.write("supply" + "," + map.get("supply") + System.lineSeparator());
            bufferedWriter.write("buy" + "," + map.get("buy") + System.lineSeparator());
            bufferedWriter.write("result" + "," + result);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file " + toFileName, e);
        }
    }
}
