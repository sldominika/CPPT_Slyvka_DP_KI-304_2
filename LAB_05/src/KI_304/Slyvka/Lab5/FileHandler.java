package KI_304.Slyvka.Lab5;

import java.io.*;

/**
 * Клас {@code FileHandler} реалізує методи запису та читання результатів
 * у текстовому та двійковому форматах.
 *
 * <p>Використовується для збереження результатів обчислення класу {@link ExpressionCalculator}.</p>
 *
 * @author Сливка
 * @version 1.0
 */
public class FileHandler {

    /**
     * Записує результат у текстовий файл.
     *
     * @param fileName ім’я файлу
     * @param x        значення аргументу
     * @param y        результат обчислення
     * @throws IOException якщо виникає помилка під час запису
     */
    public void writeText(String fileName, double x, double y) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(String.format("x = %.6f%n", x));
            writer.write(String.format("y = %.6f%n", y));
        }
    }

    /**
     * Зчитує результат із текстового файлу.
     *
     * @param fileName ім’я файлу
     * @throws IOException якщо виникає помилка під час читання
     */
    public void readText(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("Вміст текстового файлу:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    /**
     * Записує результат у двійковий файл.
     *
     * @param fileName ім’я файлу
     * @param x        значення аргументу
     * @param y        результат обчислення
     * @throws IOException якщо виникає помилка під час запису
     */
    public void writeBinary(String fileName, double x, double y) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName))) {
            out.writeDouble(x);
            out.writeDouble(y);
        }
    }

    /**
     * Зчитує результат із двійкового файлу.
     *
     * @param fileName ім’я файлу
     * @throws IOException якщо виникає помилка під час читання
     */
    public void readBinary(String fileName) throws IOException {
        try (DataInputStream in = new DataInputStream(new FileInputStream(fileName))) {
            double x = in.readDouble();
            double y = in.readDouble();
            System.out.printf("Дані з двійкового файлу: x = %.6f, y = %.6f%n", x, y);
        }
    }
}

