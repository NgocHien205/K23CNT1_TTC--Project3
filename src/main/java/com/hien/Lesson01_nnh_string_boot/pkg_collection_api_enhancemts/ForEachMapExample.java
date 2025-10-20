package com.hien.Lesson01_nnh_string_boot.pkg_collection_api_enhancemts;

import java.util.HashMap;
import java.util.Map;

public class ForEachMapExample {

    public static void main(String[] args) {
        System.out.println("=== VÍ DỤ: SỬ DỤNG forEach VỚI MAP ===\n");

        // Tạo HashMap với các ngôn ngữ lập trình
        Map<Integer, String> hmap = new HashMap<>();
        hmap.put(1, "Java Spring");
        hmap.put(2, "Javascript");
        hmap.put(3, "PHP Laravel");
        hmap.put(4, "C# NetCore");

        // Hiển thị tất cả item
        System.out.println("1. Hiển thị tất cả entry trong Map:");
        hmap.forEach((key, value) -> System.out.println("   " + key + " - " + value));

        // Cách 2: Sử dụng entrySet (cách truyền thống)
        System.out.println("\n2. Sử dụng entrySet (cách truyền thống):");
        for (Map.Entry<Integer, String> entry : hmap.entrySet()) {
            System.out.println("   Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // Cách 3: Chỉ lấy keys
        System.out.println("\n3. Hiển thị tất cả keys:");
        hmap.keySet().forEach(key -> System.out.println("   Key: " + key));

        // Cách 4: Chỉ lấy values
        System.out.println("\n4. Hiển thị tất cả values:");
        hmap.values().forEach(value -> System.out.println("   " + value));

        // Demo: forEach với xử lý phức tạp
        System.out.println("\n5. forEach với xử lý phức tạp:");
        hmap.forEach((key, value) -> {
            if (key % 2 == 0) {
                System.out.println("   ID chẵn: " + key + " -> " + value);
            } else {
                System.out.println("   ID lẻ: " + key + " -> " + value);
            }
        });

        // Demo: Tính tổng các keys
        System.out.println("\n6. Tính tổng các keys:");
        final int[] sum = {0}; // Sử dụng mảng để có thể thay đổi trong lambda
        hmap.forEach((key, value) -> sum[0] += key);
        System.out.println("   Tổng các keys: " + sum[0]);
    }
}
