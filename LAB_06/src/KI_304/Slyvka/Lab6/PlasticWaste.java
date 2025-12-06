package KI_304.Slyvka.Lab6;

/**
 * Клас {@code PlasticWaste} описує пластикове сміття.
 */
public class PlasticWaste extends Waste {
    private boolean recyclable;

    /**
     * @param name назва предмета
     * @param weight вага (кг)
     * @param recyclable чи підлягає переробці
     */
    public PlasticWaste(String name, double weight, boolean recyclable) {
        super(name, weight);
        this.recyclable = recyclable;
    }

    /** @return чи підлягає предмет переробці */
    public boolean isRecyclable() {
        return recyclable;
    }

    @Override
    public String toString() {
        return "Пластик: " + super.toString() + (recyclable ? " (перероблюється)" : " (не перероблюється)");
    }
}
