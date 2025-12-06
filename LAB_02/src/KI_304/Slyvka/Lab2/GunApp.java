package KI_304.Slyvka.Lab2;

import java.util.Scanner;

/**
 * Клас-драйвер для тестування класу Gun.
 */
public class GunApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Скільки пістолетів створити? ");
        int n = sc.nextInt();
        sc.nextLine(); // очищення буфера

        Gun[] guns = new Gun[n];

        // --- Створення пістолетів ---
        for (int i = 0; i < n; i++) {
            System.out.println("\n=== Створення пістолета #" + (i + 1) + " ===");

            System.out.print("Введіть модель (Enter — Glock-17): ");
            String model = sc.nextLine();
            if (model.isEmpty()) model = "Glock-17";

            System.out.print("Введіть місткість магазину (Enter — 15): ");
            String capInput = sc.nextLine();
            int cap = capInput.isEmpty() ? 15 : Integer.parseInt(capInput);

            System.out.print("Введіть калібр (Enter — 9x19 мм): ");
            String cal = sc.nextLine();
            if (cal.isEmpty()) cal = "9x19 мм";

            System.out.print("Введіть швидкість кулі (м/с, Enter — 350): ");
            String spdInput = sc.nextLine();
            int speed = spdInput.isEmpty() ? 350 : Integer.parseInt(spdInput);

            guns[i] = new Gun(model, cap, cal, speed);
            System.out.println("Пістолет створено!");
        }

        // --- Тестування ---
        for (int i = 0; i < n; i++) {
            Gun g = guns[i];
            System.out.println("\n=== Тестування пістолета #" + (i + 1) + " ===");

            g.disableSafety();
            g.reload(10);
            g.shoot();
            g.shoot();
            g.enableSafety();

            System.out.print("Введіть новий калібр: ");
            String newCal = sc.nextLine();
            System.out.print("Введіть нову швидкість кулі (м/с): ");
            int newSpeed = sc.nextInt();
            sc.nextLine();

            g.changeBulletType(newCal, newSpeed);
            g.unload();
            g.showInfo();
            g.closeLogger();
        }

        System.out.println("\n=== Тестування завершено. Лог збережено у gun_log.txt ===");
        sc.close();
    }
}
