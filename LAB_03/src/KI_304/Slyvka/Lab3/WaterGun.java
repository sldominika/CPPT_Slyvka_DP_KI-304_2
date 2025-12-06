package KI_304.Slyvka.Lab3;

/**
 * Конкретний підклас водяного пістолета.
 * Наслідує {@link AbstractGun} та реалізує {@link WaterWeapon}.
 *
 * Містить додаткову властивість: тиск/потужність струменя.
 */
public class WaterGun extends AbstractGun implements WaterWeapon {
    /** Потужність/тиск струменя (умовна одиниця, >=1) */
    private int pressure;

    /**
     * Конструктор з параметрами.
     *
     * @param model назва пістолета
     * @param capacity місткість резервуара (од.)
     * @param pressure початкова потужність струменя
     */
    public WaterGun(String model, int capacity, int pressure) {
        super(model, capacity);
        this.pressure = Math.max(1, pressure);
        log("Створено WaterGun: pressure=" + this.pressure);
    }

    /**
     * Конструктор за замовчуванням: модель WaterBlaster, місткість 20, тиск 5.
     */
    public WaterGun() {
        this("WaterBlaster", 20, 5);
    }

    /**
     * Додати воду у резервуар. Делегує до {@link AbstractGun#refill(int)} і робить лог.
     *
     * @param units одиниць води
     */
    @Override
    public void refillWater(int units) {
        refill(units);
    }

    /**
     * Повернути рівень води.
     *
     * @return одиниці води
     */
    @Override
    public int getWaterLevel() {
        return super.getWaterLevel();
    }

    /**
     * Виконати розпилення води.
     * Якщо запобіжник увімкнено — записує у лог причину неможливості.
     * Якщо немає води — також лог.
     * Якщо все OK — "витрачає" певну кількість води, залежну від pressure, і логгує.
     */
    @Override
    public void spray() {
        if (safetyOn) {
            log("Спроба spray() відхилена: запобіжник увімкнено");
            return;
        }
        if (waterLevel <= 0) {
            log("Спроба spray() відхилена: резервуар порожній");
            return;
        }

        // Витрата води залежить від тиску: наприклад, pressure одиниць за один spray
        int consume = Math.min(waterLevel, pressure);
        waterLevel -= consume;
        log("Виконано spray(): витрачено " + consume + " од., залишок " + waterLevel + " од. (pressure=" + pressure + ")");
    }

    /**
     * Збільшити або зменшити тиск.
     *
     * @param delta зміна тиску (може бути від'ємною)
     */
    public void changePressure(int delta) {
        this.pressure = Math.max(1, this.pressure + delta);
        log("Змінено pressure на " + delta + ", новий pressure=" + this.pressure);
    }

    /**
     * Повернути поточний pressure.
     *
     * @return pressure
     */
    public int getPressure() {
        return pressure;
    }

    /**
     * Швидке спорожнення резервуара (наприклад, для демонстрації).
     */
    public void dumpTank() {
        int was = waterLevel;
        drain();
        log("Dump tank: було " + was + " од., тепер " + waterLevel);
    }

    /**
     * Перевизначений toString для читабельності.
     *
     * @return текстовий опис пістолета
     */
    @Override
    public String toString() {
        return super.toString() + "\nPressure: " + pressure;
    }

    /**
     * Реалізація абстрактного метода action() — делегує на spray().
     */
    @Override
    public void action() {
        spray();
    }

    /**
     * Завершення роботи — закриває лог суперкласу.
     */
    public void shutdown() {
        closeLogger();
    }
}
