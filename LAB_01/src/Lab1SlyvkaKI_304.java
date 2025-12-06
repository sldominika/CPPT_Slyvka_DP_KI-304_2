import java.io.*;
import java.util.*;

/**
 * Клас Lab1ПрізвищеГрупа реалізує роботу програми до лабораторної роботи №1.
 * Програма будує квадратну матрицю із заштрихованою областю (правий верхній трикутник).
 */
public class Lab1SlyvkaKI_304 {
    /**
     * Головний метод Lab1SlyvkaKI_304 є точкою входу в програму.
     *
     * @param args Аргументи командного рядка.
     * @throws FileNotFoundException Якщо файл не може бути створений.
     */
    public static void main(String[] args) throws FileNotFoundException {
        int nRows;
        char[][] arr;
        String filler;
        Scanner in = new Scanner(System.in);
        File dataFile = new File("MyFile.txt"); // вихідний файл
        PrintWriter fout = new PrintWriter(dataFile);

        System.out.print("Введіть розмір квадратної матриці: ");
        nRows = in.nextInt();
        in.nextLine();

        arr = new char[nRows][nRows];

        System.out.print("\nВведіть один символ-заповнювач: ");
        filler = in.nextLine();

        if (filler.isEmpty()) {
            System.out.println("Не введено символ заповнювач");
            return;
        }
        if (filler.length() > 1) {
            System.out.println("Забагато символів заповнювачів");
            return;
        }

        char symbol = filler.charAt(0);

        // будуємо масив: заштрихована область справа вгорі (j >= i)
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nRows; j++) {
                if (j >= i) {
                    arr[i][j] = symbol; // заштрихована область
                } else {
                    arr[i][j] = ' ';    // порожня частина
                }
                System.out.print(arr[i][j] + " ");
                fout.print(arr[i][j] + " ");
            }
            System.out.println();
            fout.println();
        }

        fout.flush();
        fout.close();
    }
}
