package ac.knu.service;

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

    public boolean isEqual(Friend willBeComparedFriend){
        return (this.name == willBeComparedFriend.name && this.age == willBeComparedFriend.age && this.gender == willBeComparedFriend.gender);
    }
}