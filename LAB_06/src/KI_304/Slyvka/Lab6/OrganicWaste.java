package KI_304.Slyvka.Lab6;

/**
 * Клас {@code OrganicWaste} описує органічне сміття.
 */
public class OrganicWaste extends Waste {
    private boolean biodegradable;

    /**
     * @param name назва предмета
     * @param weight вага (кг)
     * @param biodegradable чи є біорозкладним
     */
    public OrganicWaste(String name, double weight, boolean biodegradable) {
        super(name, weight);
        this.biodegradable = biodegradable;
    }

    /** @return чи є предмет біорозкладним */
    public boolean isBiodegradable() {
        return biodegradable;
    }

    @Override
    public String toString() {
        return "Органіка: " + super.toString() + (biodegradable ? " (біорозкладне)" : " (не біорозкладне)");
    }
}
