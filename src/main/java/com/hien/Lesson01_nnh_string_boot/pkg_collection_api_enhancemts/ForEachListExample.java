package com.hien.Lesson01_nnh_string_boot.pkg_collection_api_enhancemts;

import java.util.Arrays;
import java.util.List;

public class ForEachListExample {

    public static void main(String[] args) {
        System.out.println("=== VÍ DỤ: SỬ DỤNG forEach VỚI LIST ===\n");

        // Tạo danh sách các ngôn ngữ lập trình
        List<String> languages = Arrays.asList(
                "Java Spring",
                "C++",
                "NetCore API",
                "PHP Laravel",
                "Javascript"
        );

        // Cách 1: Sử dụng vòng lặp truyền thống
        System.out.println("1. Sử dụng vòng lặp truyền thống:");
        for (int i = 0; i < languages.size(); i++) {
            System.out.println("   " + languages.get(i));
        }

        // Cách 2: Sử dụng enhanced for loop
        System.out.println("\n2. Sử dụng enhanced for loop:");
        for (String lang : languages) {
            System.out.println("   " + lang);
        }

        // Cách 3: Sử dụng biểu thức Lambda
        System.out.println("\n3. Sử dụng biểu thức Lambda:");
        languages.forEach(lang -> System.out.println("   " + lang));

        // Cách 4: Sử dụng method reference
        System.out.println("\n4. Sử dụng method reference:");
        System.out.println("   Sử dụng System.out::println");
        languages.forEach(System.out::println);

        // Demo thêm: forEach với xử lý phức tạp hơn
        System.out.println("\n5. forEach với xử lý phức tạp:");
        languages.forEach(lang -> {
            String upperLang = lang.toUpperCase();
            int length = lang.length();
            System.out.println("   " + upperLang + " (độ dài: " + length + ")");
        });
    }
}
