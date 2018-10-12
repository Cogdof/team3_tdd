package ac.knu;

import lombok.Data;

@Data
public class Friend {

    private String name;
    private int age;
    private Gender gender;

    public Friend(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public enum Gender{
        MALE, FEMALE
    }
}