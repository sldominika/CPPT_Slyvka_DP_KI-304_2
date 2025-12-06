package KI_304.Slyvka.Lab4;

import java.util.Scanner;

/**
 * Головний клас-програма для демонстрації роботи FunctionCalculator.
 */
public class Lab4App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {


            System.out.print("Введіть значення x: ");
            double x = scanner.nextDouble();

            double y = FunctionCalculator.calculate(x);
            System.out.printf("Результат: y = %.6f%n", y);

            FunctionCalculator.saveResultToFile(y, "result.txt");
        } catch (ArithmeticException e) {
            System.err.println("Помилка обчислення: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Невідома помилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
