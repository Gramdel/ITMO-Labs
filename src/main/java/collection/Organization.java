package collection;

public class Organization {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private Long employeesCount; //Поле может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null

    public Organization(String name,Long annualTurnover,Long employeesCount,OrganizationType type){
        this.name = name;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.type = type;
    }

    @Override
    public String toString() {
        return "{"+id+", "+name+", "+annualTurnover+", "+employeesCount+", "+type+"}";
    }
}