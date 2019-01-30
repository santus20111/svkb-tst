package test.skb.test.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String id;
    private String firstLastName;
    private LocalDateTime birthday;

    public User(String id, String firstLastName, LocalDateTime birthday) {
        this.id = id;
        this.firstLastName = firstLastName;
        this.birthday = birthday;
    }
}
