package com.hien.Lesson01_nnh_string_boot.method_ref;

import java.util.Arrays;
import java.util.function.BiFunction;

interface ExecuteFunction {
    int execute(int a, int b);
}

public class MathUtils {

    // Constructor
    public MathUtils() {}

    public MathUtils(String str) {
        System.out.println("MathUtils: " + str);
    }

    // Static methods
    public static int sum(int a, int b) {
        return a + b;
    }

    public static int minus(int a, int b) {
        return a - b;
    }

    // Instance method
    public int multiply(int a, int b) {
        return a * b;
    }

    // Method nhận ExecuteFunction làm tham số
    public static int doAction(int a, int b, ExecuteFunction func) {
        return func.execute(a, b);
    }

    public static void main(String[] args) {
        System.out.println("=== DEMO METHOD REFERENCES ===\n");

        int a = 10;
        int b = 20;

        // 1. Static Method Reference
        System.out.println("1. Static Method Reference:");
        int sum = doAction(a, b, MathUtils::sum);
        System.out.println("Sum: " + a + " + " + b + " = " + sum);

        int minus = doAction(a, b, MathUtils::minus);
        System.out.println("Minus: " + a + " - " + b + " = " + minus);

        // 2. Instance Method Reference
        System.out.println("\n2. Instance Method Reference:");
        MathUtils mathUtils = new MathUtils();
        int multiply = doAction(a, b, mathUtils::multiply);
        System.out.println("Multiply: " + a + " * " + b + " = " + multiply);

        // 3. Reference đến method của một đối tượng cụ thể
        System.out.println("\n3. Reference to Instance Method:");
        String[] stringArray = {"Java", "C++", "PHP", "C#", "JavaScript"};
        System.out.println("Before sort: " + Arrays.toString(stringArray));
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        System.out.println("After sort: " + Arrays.toString(stringArray));
    }
}
