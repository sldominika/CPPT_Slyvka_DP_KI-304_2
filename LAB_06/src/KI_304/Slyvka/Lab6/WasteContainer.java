package KI_304.Slyvka.Lab6;

import java.util.ArrayList;
import java.util.List;

/**
 * Параметризований клас {@code WasteContainer<T>} — це контейнер для зберігання сміття.
 * @param <T> тип об'єктів, що наслідують {@link Waste}
 */
public class WasteContainer<T extends Waste> {
    private List<T> container;
    private int capacity;

    /**
     * Створює новий контейнер для сміття.
     *
     * @param capacity максимальна кількість об’єктів у контейнері
     */
    public WasteContainer(int capacity) {
        this.capacity = capacity;
        this.container = new ArrayList<>();
    }

    /**
     * Додає елемент у контейнер.
     *
     * @param item об'єкт сміття
     * @throws IllegalStateException якщо контейнер заповнений
     */
    public void addItem(T item) {
        if (container.size() >= capacity)
            throw new IllegalStateException("Контейнер переповнений!");
        container.add(item);
    }

    /**
     * Виймає елемент із контейнера.
     *
     * @return останній доданий елемент
     * @throws IllegalStateException якщо контейнер порожній
     */
    public T removeItem() {
        if (container.isEmpty())
            throw new IllegalStateException("Контейнер порожній!");
        return container.remove(container.size() - 1);
    }

    /**
     * Знаходить максимальний елемент у контейнері за вагою.
     *
     * @return об'єкт сміття з найбільшою вагою
     */
    public T findMax() {
        if (container.isEmpty())
            return null;

        T max = container.get(0);
        for (T item : container) {
            if (item.compareTo(max) > 0)
                max = item;
        }
        return max;
    }

    /** Виводить усі елементи контейнера. */
    public void displayAll() {
        if (container.isEmpty()) {
            System.out.println("Контейнер порожній.");
            return;
        }
        for (T item : container)
            System.out.println(item);
    }

    /** @return поточна кількість елементів у контейнері */
    public int size() {
        return container.size();
    }
}
