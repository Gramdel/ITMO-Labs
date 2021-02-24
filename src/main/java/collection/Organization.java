package collection;

import static core.Main.organizations;

public class Organization {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private Long employeesCount; //Поле может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null

    public Organization(String name, Long annualTurnover, Long employeesCount, OrganizationType type) {
        this.id = organizations.size() + 1;
        this.name = name;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Long getAnnualTurnover() {
        return annualTurnover;
    }

    public Long getEmployeesCount() {
        return employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Organization o = (Organization) obj;
        return name.equals(o.name) && annualTurnover.equals(o.annualTurnover) &&
                employeesCount.equals(o.employeesCount) && type.equals(type);
    }

    @Override
    public String toString() {
        return "{" + id + ", " + name + ", " + annualTurnover + ", " + employeesCount + ", " + type + "}";
    }

    public String toStringForCSV() {
        return name + "," + (annualTurnover == null ? "" : annualTurnover) + "," + (employeesCount == null ? "" : employeesCount) + "," + (type == null ? "" : type) + "\n";
    }
}