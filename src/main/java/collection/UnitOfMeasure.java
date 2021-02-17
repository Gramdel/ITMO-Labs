package collection;

public enum UnitOfMeasure {
    KILOGRAMS,
    SQUARE_METERS,
    PCS,
    LITERS,
    MILLILITERS;
    public static UnitOfMeasure fromString(String s){
        if (s != null) {
            for (UnitOfMeasure type : UnitOfMeasure.values()) {
                if (s.equals(type.toString())) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException("No such value");
    }
}