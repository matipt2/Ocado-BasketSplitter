package org.example;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BasketSplitter {
   protected Map<String, List<String>> deliveryTypes;

    public BasketSplitter(String absolutePathToConfigFile) {
        loadConfigFromJson(absolutePathToConfigFile);
    }
    public Map<String, List<String>> getDeliveryTypes() {
        return deliveryTypes;
    }

    public Map<String, List<String>> split(List<String> items) {
        if (deliveryTypes == null) {
            throw new IllegalStateException("Delivery types not loaded. Config needs to be loaded before using split method!");
        }

        Map<String, List<String>> result = new HashMap<>();

        List<String> remainingItems = new ArrayList<>(items);

        while (!remainingItems.isEmpty()) {
            String bestDeliveryType = findBestDeliveryType(remainingItems);
            List<String> productsList = result.computeIfAbsent(bestDeliveryType,k -> new ArrayList<>());

            for (Iterator<String> iterator = remainingItems.iterator(); iterator.hasNext();) {
                String item = iterator.next();
                List<String> deliveryOptions = deliveryTypes.get(item);
                if (deliveryOptions != null && deliveryOptions.contains(bestDeliveryType)) {
                    productsList.add(item);
                    iterator.remove();
                }
            }
        }
        return result;
    }

    private String findBestDeliveryType(List<String> items) {
        Map<String, Integer> deliveryCounts = new HashMap<>();

        for (String item : items) {
            List<String> deliveryOptions = deliveryTypes.get(item);
            if (deliveryOptions != null) {
                for (String deliveryType : deliveryOptions) {
                    deliveryCounts.put(deliveryType, deliveryCounts.getOrDefault(deliveryType, 0) + 1);
                }
            }
        }

        while (true) {
            String bestDeliveryType = Collections.max(deliveryCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
            int count = deliveryCounts.getOrDefault(bestDeliveryType, 0);
            if (count > 0) {
                deliveryCounts.put(bestDeliveryType, count - 1);
                return bestDeliveryType;
            } else {
                deliveryCounts.remove(bestDeliveryType);
            }
            if (deliveryCounts.isEmpty()) {
                throw new IllegalStateException("No more delivery options available.");
            }
        }
    }

    public void loadConfigFromJson(String path) {
        try {
            Object obj = new JSONParser().parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;

            this.deliveryTypes = new HashMap<>();

            for (Object key : jsonObject.keySet()) {
                JSONArray jsonArray = (JSONArray) jsonObject.get(key);
                List<String> deliveryOptions = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    deliveryOptions.add((String) jsonArray.get(i));
                }
                this.deliveryTypes.put((String) key, deliveryOptions);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading json: " + path, e);
        } catch (ParseException e) {
            throw new RuntimeException("Error loading json: " + path, e);
        }
    }
}
