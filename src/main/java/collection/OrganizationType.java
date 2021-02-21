package collection;

public enum OrganizationType {
    COMMERCIAL,
    GOVERNMENT,
    PRIVATE_LIMITED_COMPANY,
    OPEN_JOINT_STOCK_COMPANY;
    public static OrganizationType fromString(String s){
        if (s != null) {
            for (OrganizationType type : OrganizationType.values()) {
                if (s.equals(type.toString())) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException();
    }
}