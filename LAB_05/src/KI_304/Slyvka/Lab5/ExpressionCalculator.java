package KI_304.Slyvka.Lab5;

/**
 * Клас {@code ExpressionCalculator} реалізує метод обчислення математичного виразу:
 * <pre>
 *     y = sin(3x - 5) / ctg(2x) = sin(3x - 5) * tan(2x)
 * </pre>
 *
 * <p>Виконується перевірка на ділення на нуль, коли ctg(2x) = 0
 * (тобто cos(2x) ≈ 0 або tan(2x) → ∞).</p>
 *
 * @author Сливка
 * @version 1.2
 */
public class ExpressionCalculator {

    /**
     * Обчислює значення виразу:
     * <pre>
     *     y = sin(3x - 5) / ctg(2x) = sin(3x - 5) * tan(2x)
     * </pre>
     *
     * @param x значення аргументу (у радіанах)
     * @return результат обчислення y
     * @throws ArithmeticException якщо ctg(2x) = 0 (тобто cos(2x) ≈ 0)
     */
    public double calculate(double x) throws ArithmeticException {
        double sin3x5 = Math.sin(3 * x - 5);
        double cos2x = Math.cos(2 * x);
        double tan2x = Math.tan(2 * x);

        // Якщо cos(2x) ≈ 0, то ctg(2x) = 0 → ділення на нуль
        if (Double.isNaN(tan2x) || Double.isInfinite(tan2x) || Math.abs(cos2x) < 1e-14) {
            throw new ArithmeticException("Помилка: ctg(2x) = 0 (cos(2x) ≈ 0), ділення на нуль неможливе.");
        }

        return sin3x5 * tan2x;
    }
}
