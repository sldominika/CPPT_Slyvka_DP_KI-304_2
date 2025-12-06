package KI_304.Slyvka.Lab3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Абстрактний суперклас, що моделює базові властивості пістолета.
 * Містить модель, "магазин" (використовується як резервуар), стан запобіжника,
 * а також логування дій у файл.
 *
 * Підкласи повинні реалізувати специфічну дію {@link #action()} (наприклад, spray()).
 *
 * @author Slyvka
 * @version 1.0
 */
public abstract class AbstractGun {
    /** Назва/модель пістолета */
    protected String model;

    /** Місткість резервуара (макс. одиниць води) */
    protected int capacity;

    /** Поточний рівень води в резервуарі (од.) */
    protected int waterLevel;

    /** Чи увімкнено запобіжник (true — увімкнено) */
    protected boolean safetyOn;

    /** Для логування дій */
    protected PrintWriter logWriter;

    /**
     * Конструктор з параметрами.
     *
     * @param model назва/модель
     * @param capacity місткість резервуара
     */
    public AbstractGun(String model, int capacity) {
        this.model = model;
        this.capacity = Math.max(0, capacity);
        this.waterLevel = 0;
        this.safetyOn = true; // за замовчуванням запобіжник увімкнено
        initLogger();
        log("Створено AbstractGun: " + model + ", місткість: " + this.capacity);
    }

    /**
     * Ініціалізує логер (файл {@code watergun_log.txt}).
     * У випадку помилки — виводить стектрейс.
     */
    private void initLogger() {
        try {
            logWriter = new PrintWriter(new FileWriter("watergun_log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
            logWriter = null;
        }
    }

    /**
     * Запис у лог (якщо доступний).
     *
     * @param msg текст повідомлення
     */
    protected void log(String msg) {
        if (logWriter != null) {
            logWriter.println(msg);
            logWriter.flush();
        }
    }

    /**
     * Коректно закрити лог-файл. Викликати наприкінці роботи з об'єктом.
     */
    public void closeLogger() {
        if (logWriter != null) {
            log("Закриття логу для " + model);
            logWriter.close();
            logWriter = null;
        }
    }

    /**
     * Увімкнути запобіжник.
     */
    public void enableSafety() {
        safetyOn = true;
        log("Запобіжник увімкнено");
    }

    /**
     * Вимкнути запобіжник.
     */
    public void disableSafety() {
        safetyOn = false;
        log("Запобіжник вимкнено");
    }

    /**
     * Додати воду в резервуар (реально — заправка).
     *
     * @param units одиниць води для додавання
     */
    public void refill(int units) {
        if (units <= 0) {
            log("Спроба додати некоректну кількість води: " + units);
            return;
        }
        int before = waterLevel;
        waterLevel = Math.min(capacity, waterLevel + units);
        log("Поповнено воду: +" + (waterLevel - before) + " (запитано " + units + ")");
    }

    /**
     * Віддати поточний рівень води.
     *
     * @return кількість одиниць води в резервуарі
     */
    public int getWaterLevel() {
        return waterLevel;
    }

    /**
     * Віддати максимальну місткість резервуара.
     *
     * @return місткість в одиницях
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Розрядити резервуар (обнулити воду).
     */
    public void drain() {
        waterLevel = 0;
        log("Резервуар повністю спорожнено");
    }

    /**
     * Вивести інформацію про пістолет у консоль та в лог.
     */
    public void showInfo() {
        String info = toString();
        System.out.println(info);
        log("Виведено інформацію: " + info.replace("\n", " | "));
    }

    /**
     * Абстрактна дія, яку має реалізувати підклас (наприклад, spray()).
     * Повинна враховувати стан запобіжника та рівень води і логувати події.
     */
    public abstract void action();

    @Override
    public String toString() {
        return "Пістолет: " + model +
                "\nМісткість резервуара: " + capacity +
                "\nРівень води: " + waterLevel +
                "\nЗапобіжник: " + (safetyOn ? "увімкнено" : "вимкнено");
    }
}
