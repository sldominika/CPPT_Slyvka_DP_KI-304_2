package KI_304.Slyvka.Lab5;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Клас {@code CalcApp} є програмою-драйвером для демонстрації роботи
 * {@link ExpressionCalculator} та {@link FileHandler}.
 *
 * <p>Програма виконує такі дії:
 * <ul>
 *   <li>Зчитує значення аргументу {@code x} з клавіатури (у радіанах);</li>
 *   <li>Обчислює вираз {@code y = sin(3x - 5) / ctg(2x)};</li>
 *   <li>Записує результати у текстовий та двійковий файли;</li>
 *   <li>Зчитує дані назад із цих файлів.</li>
 * </ul>
 *
 * <p>Під час виконання програми можуть виникати винятки:
 * {@link InputMismatchException}, {@link ArithmeticException}, {@link IOException}.</p>
 *
 * @author Сливка
 * @version 1.1
 */
public class CalcApp {

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpressionCalculator calculator = new ExpressionCalculator();
        FileHandler fileHandler = new FileHandler();

        try {
            System.out.print("Введіть значення x (у радіанах): ");
            double x = sc.nextDouble();

            double y = calculator.calculate(x);
            System.out.printf("Результат: y = %.6f%n", y);

            // --- Запис у файли ---
            fileHandler.writeText("result.txt", x, y);
            fileHandler.writeBinary("result.dat", x, y);

            // --- Зчитування ---
            fileHandler.readText("result.txt");
            fileHandler.readBinary("result.dat");

        } catch (InputMismatchException e) {
            System.out.println("Помилка: введено нечислове значення!");
        } catch (ArithmeticException e) {
            System.out.println("Математична помилка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}

