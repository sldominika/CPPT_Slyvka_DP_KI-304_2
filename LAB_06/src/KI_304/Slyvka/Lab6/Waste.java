package KI_304.Slyvka.Lab6;

/**
 * Абстрактний клас {@code Waste} описує базовий тип сміття.
 * Кожен об'єкт має вагу та назву.
 */
public abstract class Waste implements Comparable<Waste> {
    protected String name;
    protected double weight;

    /**
     * Конструктор для створення об'єкта сміття.
     *
     * @param name назва сміття
     * @param weight вага сміття (кг)
     */
    public Waste(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    /** @return назва сміття */
    public String getName() {
        return name;
    }

    /** @return вага сміття */
    public double getWeight() {
        return weight;
    }

    /**
     * Порівнює два об'єкти сміття за вагою.
     *
     * @param other інший об'єкт {@code Waste}
     * @return додатне число, якщо this > other, від’ємне — якщо менше, 0 — якщо рівні
     */
    @Override
    public int compareTo(Waste other) {
        return Double.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return name + " (" + weight + " кг)";
    }
}
