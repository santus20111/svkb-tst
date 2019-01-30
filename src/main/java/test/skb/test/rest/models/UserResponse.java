package test.skb.test.rest.models;

import lombok.Data;
import test.skb.test.models.User;

@Data
public class UserResponse {
    private String id;
    private String firstLastName;
    private String birthday;
    private Integer bonuses;

    public UserResponse(User user, Integer bonuses) {
        this.id = user.getId();
        this.firstLastName = user.getFirstLastName();
        this.birthday = user.getBirthday().toString();
        this.bonuses = bonuses;

    }
}
