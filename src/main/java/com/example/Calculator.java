package com.example;

import java.util.Scanner;

public class Calculator {

    public static double squareRoot(double x) {
        if (x < 0)
            throw new IllegalArgumentException("Cannot calculate square root of negative number.");
        return Math.sqrt(x);
    }

    public static long factorial(int x) {
        if (x < 0)
            throw new IllegalArgumentException("Factorial not defined for negative numbers.");
        if (x > 20)
            throw new IllegalArgumentException("Value too large. May cause overflow.");

        long result = 1;
        for (int i = 2; i <= x; i++)
            result *= i;
        return result;
    }

    public static double naturalLog(double x) {
        if (x <= 0)
            throw new IllegalArgumentException("Natural log defined only for positive numbers.");
        return Math.log(x);
    }

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Scientific Calculator ===");
            System.out.println("1. Square Root");
            System.out.println("2. Factorial");
            System.out.println("3. Natural Log");
            System.out.println("4. Power");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter number: ");
                        double num = scanner.nextDouble();
                        System.out.println("Result: " + squareRoot(num));
                        break;

                    case 2:
                        System.out.print("Enter integer: ");
                        int factNum = scanner.nextInt();
                        System.out.println("Result: " + factorial(factNum));
                        break;

                    case 3:
                        System.out.print("Enter number: ");
                        double logNum = scanner.nextDouble();
                        System.out.println("Result: " + naturalLog(logNum));
                        break;

                    case 4:
                        System.out.print("Enter base: ");
                        double base = scanner.nextDouble();
                        System.out.print("Enter exponent: ");
                        double exp = scanner.nextDouble();
                        System.out.println("Result: " + power(base, exp));
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
