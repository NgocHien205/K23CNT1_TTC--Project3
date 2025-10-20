package com.hien.Lesson01_nnh_string_boot.pkg_stream_api;

import java.util.Arrays;
import java.util.List;

public class StreamExample {

    // Phương thức không dùng stream
    public void withoutStream() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Đếm các số chẵn
        int count = 0;
        for (Integer integer : integerList) {
            if (integer % 2 == 0) {
                count++;
            }
        }

        System.out.println("WithoutStream -> Số phần tử chẵn: " + count);
    }

    // Phương thức dùng Stream
    public void withStream() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Đếm các số chẵn bằng stream
        long count = integerList.stream()
                .filter(num -> num % 2 == 0)
                .count();

        System.out.println("WithStream -> Số phần tử chẵn: " + count);
    }

    public static void main(String[] args) {
        System.out.println("=== DEMO STREAM API ===\n");

        StreamExample streamExample = new StreamExample();

        // Demo không dùng stream
        System.out.println("1. Cách truyền thống (không dùng Stream):");
        streamExample.withoutStream();

        // Demo dùng stream
        System.out.println("\n2. Sử dụng Stream API:");
        streamExample.withStream();

        // Demo thêm các thao tác khác với Stream
        System.out.println("\n3. Các thao tác khác với Stream:");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Lọc số chẵn và in ra
        System.out.println("Các số chẵn:");
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);

        // Tính tổng các số
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("\nTổng các số: " + sum);

        // Lấy 5 số đầu tiên
        System.out.println("\n5 số đầu tiên:");
        numbers.stream()
                .limit(5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}
