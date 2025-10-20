package com.hien.Lesson01_nnh_string_boot.method_ref;

import java.util.Arrays;
import java.util.List;

public class DemoMethodRef {

    public static void main(String[] args) {
        System.out.println("=== DEMO NHIỀU CÁCH SỬ DỤNG METHOD REFERENCES ===\n");

        int a = 10;
        int b = 20;

        // 1. So sánh Lambda vs Method Reference
        System.out.println("1. Lambda Expression vs Method Reference:");

        // Cách 1: Lambda Expression
        int sum = MathUtils.doAction(a, b, (x, y) -> MathUtils.sum(x, y));
        System.out.println("Lambda - Sum: " + sum);

        // Cách 2: Method Reference (ngắn gọn hơn)
        int sum2 = MathUtils.doAction(a, b, MathUtils::sum);
        System.out.println("Method Ref - Sum: " + sum2);


        // 2. Tham chiếu đến instance method
        System.out.println("\n2. Instance Method Reference:");
        MathUtils mathUtils = new MathUtils();
        int multiply = MathUtils.doAction(a, b, mathUtils::multiply);
        System.out.println("Multiply result: " + multiply);


        // 3. Sử dụng với forEach
        System.out.println("\n3. Method Reference với forEach:");
        List<String> languages = Arrays.asList("Java Spring", "C++",
                "NetCore API", "PHP Laravel",
                "Javascript");

        System.out.println("Sử dụng biểu thức Lambda:");
        languages.forEach(lang -> System.out.println(lang));

        System.out.println("\nSử dụng method reference:");
        languages.forEach(System.out::println);


        // 4. Sử dụng với sorting
        System.out.println("\n4. Method Reference với Sorting:");
        String[] stringArray = {"Java", "C++", "PHP", "C#", "Javascript"};

        System.out.println("Trước khi sắp xếp: ");
        for (String str : stringArray) {
            System.out.print(str + " ");
        }

        Arrays.sort(stringArray, String::compareToIgnoreCase);

        System.out.println("\n\nSau khi sắp xếp: ");
        for (String str : stringArray) {
            System.out.print(str + " ");
        }
        System.out.println();
    }
}
