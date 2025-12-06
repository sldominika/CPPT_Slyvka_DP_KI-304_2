package KI_304.Slyvka.Lab2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Клас {@code Gun} моделює пістолет з магазином, типом кулі і запобіжником.
 * <p>
 * Усі дії пістолета (перезаряджання, постріли, зміни конфігурації) записуються
 * у лог-файл {@code gun_log.txt}. Для логування використовується {@link PrintWriter},
 * який коректно закривається методом {@link #closeLogger()}.
 * </p>
 *
 * @author Slyvka
 * @version 1.0
 */
public class Gun {
    /**
     * Модель пістолета (рядок ідентифікації).
     */
    private String model;

    /**
     * Об'єкт кулі, яка використовується пістолетом.
     */
    private Bullet bullet;

    /**
     * Об'єкт магазину пістолета.
     */
    private Magazine magazine;

    /**
     * Стан запобіжника: {@code true} — увімкнено, {@code false} — вимкнено.
     */
    private boolean safetyOn;

    /**
     * Об'єкт для запису логу у файл.
     */
    private PrintWriter logWriter;

    // ---------------------- КОНСТРУКТОРИ ----------------------

    /**
     * Створює пістолет з вказаними параметрами.
     *
     * @param model       назва або модель пістолета
     * @param magCapacity місткість магазину (кількість куль)
     * @param caliber     калібр кулі (наприклад, "9x19 мм")
     * @param speed       початкова швидкість кулі в м/с
     */
    public Gun(String model, int magCapacity, String caliber, int speed) {
        this.model = model;
        this.bullet = new Bullet(caliber, speed);
        this.magazine = new Magazine(magCapacity);
        this.safetyOn = true; // за замовчуванням запобіжник увімкнено
        initLogger();
        log("Створено пістолет: " + model + ", магазин: " + magCapacity + " місць, калібр: " + caliber);
    }

    /**
     * Конструктор за замовчуванням. Створює стандартний пістолет.
     * Модель: "Glock-17", місткість магазину: 15, калібр: "9x19 мм", швидкість: 350 м/с.
     */
    public Gun() {
        this("Glock-17", 15, "9x19 мм", 350);
    }

    // ---------------------- ЛОГУВАННЯ ----------------------

    /**
     * Ініціалізує логер, що записує у файл {@code gun_log.txt}.
     * У разі помилки ініціалізації виводиться стектрейс у стандартний потік помилок.
     */
    private void initLogger() {
        try {
            logWriter = new PrintWriter(new FileWriter("gun_log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
            logWriter = null;
        }
    }

    /**
     * Допоміжний метод для запису повідомлення у лог.
     * Якщо логер не ініціалізовано, нічого не робить.
     *
     * @param msg текст повідомлення для запису
     */
    private void log(String msg) {
        if (logWriter != null) {
            logWriter.println(msg);
            logWriter.flush();
        }
    }

    /**
     * Коректно закриває логер і записує запис про закриття.
     * Викликати завжди наприкінці роботи з об'єктом, щоб гарантувати запис логу.
     */
    public void closeLogger() {
        if (logWriter != null) {
            log("Закриття логу для моделі " + model);
            logWriter.close();
            logWriter = null;
        }
    }

    // ---------------------- ФУНКЦІОНАЛ ----------------------

    /**
     * Перезаряджає магазин заданою кількістю куль.
     * Якщо кількість більше за місткість, магазин заповнюється до максимальної ємності.
     *
     * @param count кількість куль для додавання у магазин
     */
    public void reload(int count) {
        int before = magazine.getCount();
        magazine.load(count);
        int after = magazine.getCount();
        log("Перезаряджено: +" + (after - before) + " (спроба додати " + count + ")");
    }

    /**
     * Виконати постріл — якщо запобіжник вимкнено і магазин не порожній, зменшує
     * кількість куль на 1 та записує подію у лог.
     * Якщо постріл неможливий, у лог записується причина.
     */
    public void shoot() {
        if (safetyOn) {
            log("Спроба пострілу відхилена: запобіжник увімкнено");
            return;
        }
        if (!magazine.hasBullets()) {
            log("Спроба пострілу відхилена: магазин порожній");
            return;
        }
        magazine.fireOne();
        log("Постріл з пістолета " + model + " — калібр: " + bullet.getCaliber() + ", швидкість: " + bullet.getSpeed() + " м/с");
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
     * Розрядити магазин — встановити кількість куль у магазині у 0.
     */
    public void unload() {
        magazine.unload();
        log("Магазин розряджено");
    }

    /**
     * Змінити тип кулі (калібр і швидкість).
     *
     * @param caliber новий калібр (рядок)
     * @param speed   нова швидкість кулі в м/с
     */
    public void changeBulletType(String caliber, int speed) {
        String old = bullet.getCaliber() + "@" + bullet.getSpeed();
        bullet = new Bullet(caliber, speed);
        log("Змінено тип кулі: " + old + " -> " + caliber + "@" + speed);
    }

    /**
     * Повернути кількість куль, що залишилися в магазині.
     *
     * @return кількість куль у магазині
     */
    public int getRemainingBullets() {
        return magazine.getCount();
    }

    /**
     * Отримати інформацію про пістолет у вигляді рядка.
     *
     * @return текстова інформація про модель, кулю, магазин та стан запобіжника
     */
    public String getInfo() {
        return this.toString();
    }

    /**
     * Вивести інформацію про пістолет у консоль та записати подію у лог.
     */
    public void showInfo() {
        System.out.println(this.toString());
        log("Виведено інформацію про пістолет у консоль");
    }

    /**
     * Перевірити, чи увімкнено запобіжник.
     *
     * @return {@code true}, якщо запобіжник увімкнено, інакше {@code false}
     */
    public boolean isSafetyOn() {
        return safetyOn;
    }

    /**
     * Повернути модель пістолета.
     *
     * @return назва або модель пістолета
     */
    public String getModel() {
        return model;
    }

    /**
     * Повернути рядок з коротким описом пістолета.
     *
     * @return короткий опис
     */
    @Override
    public String toString() {
        return "Пістолет: " + model +
                "\nКалібр: " + bullet.getCaliber() +
                "\nШвидкість: " + bullet.getSpeed() + " м/с" +
                "\nМагазин: " + magazine.getCount() + "/" + magazine.getCapacity() +
                "\nЗапобіжник: " + (safetyOn ? "увімкнено" : "вимкнено");
    }

    // ---------------------- ВНУТРІШНІ КЛАСИ ----------------------

    /**
     * Внутрішній клас, що описує кулю (тип/калібр та початкова швидкість).
     */
    public static class Bullet {
        /**
         * Калібр кулі (рядок, наприклад "9x19 мм").
         */
        private String caliber;

        /**
         * Швидкість кулі в метрах за секунду.
         */
        private int speed;

        /**
         * Створює об'єкт кулі.
         *
         * @param caliber калібр кулі
         * @param speed   швидкість кулі в м/с
         */
        public Bullet(String caliber, int speed) {
            this.caliber = caliber;
            this.speed = speed;
        }

        /**
         * Повертає калібр кулі.
         *
         * @return калібр
         */
        public String getCaliber() {
            return caliber;
        }

        /**
         * Повертає швидкість кулі.
         *
         * @return швидкість в м/с
         */
        public int getSpeed() {
            return speed;
        }
    }

    /**
     * Внутрішній клас, що моделює магазин пістолета.
     */
    public static class Magazine {
        /**
         * Максимальна місткість магазину.
         */
        private int capacity;

        /**
         * Поточна кількість куль у магазині.
         */
        private int count;

        /**
         * Створює магазин з вказаною місткістю.
         *
         * @param capacity максимальна кількість куль
         */
        public Magazine(int capacity) {
            this.capacity = capacity;
            this.count = 0;
        }

        /**
         * Додає в магазин задану кількість куль. Якщо кількість перевищує
         * місткість, магазин заповнюється до максимальної ємності.
         *
         * @param bullets кількість куль для додавання
         */
        public void load(int bullets) {
            int before = count;
            count = Math.min(capacity, count + bullets);
            // (не пишемо у лог тут — зовнішні методи викликають log)
        }

        /**
         * Стрільнути однією кулею — зменшити лічильник на 1, якщо є кулі.
         */
        public void fireOne() {
            if (count > 0) {
                count--;
            }
        }

        /**
         * Розрядити магазин (встановити кількість куль = 0).
         */
        public void unload() {
            count = 0;
        }

        /**
         * Перевірити, чи є кулі в магазині.
         *
         * @return {@code true}, якщо кількість > 0
         */
        public boolean hasBullets() {
            return count > 0;
        }

        /**
         * Повернути поточну кількість куль.
         *
         * @return кількість куль
         */
        public int getCount() {
            return count;
        }

        /**
         * Повернути максимальну місткість магазину.
         *
         * @return місткість магазину
         */
        public int getCapacity() {
            return capacity;
        }
    }
}
