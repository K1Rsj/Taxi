package ua.finalproject.model.entities.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import ua.finalproject.model.entities.Entity;

@Getter
@Setter
@Builder
public class User implements Entity<Integer> {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private Long moneySpent;
    private Role role;

    public enum Role {
        USER, ADMIN
    }
}
