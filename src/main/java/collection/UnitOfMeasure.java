package collection;

public enum UnitOfMeasure {
    KILOGRAMS,
    SQUARE_METERS,
    PCS,
    LITERS,
    MILLILITERS;
    public static UnitOfMeasure fromString(String s){
        if (s != null) {
            for (UnitOfMeasure unit : UnitOfMeasure.values()) {
                if (s.equals(unit.toString())) {
                    return unit;
                }
            }
        }
        throw new IllegalArgumentException();
    }
}