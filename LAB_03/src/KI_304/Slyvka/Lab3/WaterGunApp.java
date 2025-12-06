package KI_304.Slyvka.Lab3;

import java.util.Scanner;

/**
 * Драйвер для тестування WaterGun.
 * Демонструє створення, заправку, розпилення, зміну тиску, вивід інформації і закриття логів.
 */
public class WaterGunApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Скільки водяних пістолетів створити? ");
        int n = sc.nextInt();
        sc.nextLine();

        WaterGun[] guns = new WaterGun[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n=== Створення водяного пістолета #" + (i + 1) + " ===");
            System.out.print("Модель (Enter — WaterBlaster): ");
            String model = sc.nextLine();
            if (model.isEmpty()) model = "WaterBlaster";

            System.out.print("Місткість резервуара (Enter — 20): ");
            String capStr = sc.nextLine();
            int cap = capStr.isEmpty() ? 20 : Integer.parseInt(capStr);

            System.out.print("Початковий тиск (Enter — 5): ");
            String pStr = sc.nextLine();
            int pressure = pStr.isEmpty() ? 5 : Integer.parseInt(pStr);

            guns[i] = new WaterGun(model, cap, pressure);
            System.out.println("Створено: " + guns[i].model);
        }

        // Тестування
        for (int i = 0; i < n; i++) {
            WaterGun g = guns[i];
            System.out.println("\n=== Тестування #" + (i + 1) + " (" + g.model + ") ===");

            g.showInfo();

            System.out.println("Заправляємо 10 одиниць...");
            g.refillWater(10);
            System.out.println("Рівень води: " + g.getWaterLevel());

            System.out.println("Вимикаємо запобіжник та робимо 2 spray()");
            g.disableSafety();
            g.spray();
            g.spray();

            System.out.println("Підвищимо pressure на 3 і знову spray()");
            g.changePressure(3);
            g.spray();

            System.out.println("Увімкнемо запобіжник і спробуємо spray()");
            g.enableSafety();
            g.spray();

            System.out.println("Dump tank (спорожнення):");
            g.dumpTank();
            g.showInfo();

            g.shutdown();
        }

        System.out.println("\nТестування завершено. Логи в файлі watergun_log.txt");
        sc.close();
    }
}
