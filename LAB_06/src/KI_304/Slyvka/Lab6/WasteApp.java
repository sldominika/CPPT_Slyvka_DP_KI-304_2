package KI_304.Slyvka.Lab6;

/**
 * Клас {@code WasteApp} є програмою-драйвером для демонстрації роботи {@link WasteContainer}.
 */
public class WasteApp {
    public static void main(String[] args) {
        WasteContainer<Waste> bin = new WasteContainer<>(5);

        PlasticWaste bottle = new PlasticWaste("Пляшка", 0.3, true);
        PlasticWaste bag = new PlasticWaste("Пакет", 0.1, false);
        OrganicWaste apple = new OrganicWaste("Яблучний огризок", 0.2, true);
        OrganicWaste leaf = new OrganicWaste("Листя", 0.5, true);

        bin.addItem(bottle);
        bin.addItem(bag);
        bin.addItem(apple);
        bin.addItem(leaf);

        System.out.println("Вміст контейнера:");
        bin.displayAll();

        System.out.println("\nНайважчий предмет:");
        System.out.println(bin.findMax());

        System.out.println("\nВиймаємо елемент: " + bin.removeItem());
        System.out.println("Після вилучення:");
        bin.displayAll();
    }
}
