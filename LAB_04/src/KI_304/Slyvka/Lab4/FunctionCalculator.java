package KI_304.Slyvka.Lab4;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Клас FunctionCalculator реалізує обчислення виразу
 * y = sin(3x - 5) / ctg(2x).
 * <p>
 * Використовується механізм виключень для обробки помилок.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class FunctionCalculator {

    /**
     * Метод для обчислення значення функції y = sin(3x - 5) / ctg(2x)
     *
     * @param x змінна x
     * @return значення y
     * @throws ArithmeticException якщо значення ctg(2x) = 0
     */
    public static double calculate(double x) throws ArithmeticException {
        double denominator = Math.tan(2 * x); // 1 / ctg(2x)

        if (Math.abs(denominator) < 1e-10) {
            throw new ArithmeticException("Помилка: значення ctg(2x) = 0, ділення на нуль!");
        }

        return Math.sin(3 * x - 5) * denominator;
    }

    /**
     * Метод для запису результату у файл
     *
     * @param result значення, яке потрібно записати
     * @param fileName ім'я файлу для збереження
     */
    public static void saveResultToFile(double result, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Результат обчислення: " + result + "\n");
            System.out.println("Результат успішно записано у файл " + fileName);
        } catch (IOException e) {
            System.err.println("Помилка при записі у файл: " + e.getMessage());
        }
    }
}
