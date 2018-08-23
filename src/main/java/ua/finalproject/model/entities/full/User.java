package ua.finalproject.model.entities.full;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import ua.finalproject.model.entities.Entity;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users", nullable = false)
    private Integer id;

    @Column(name = "login", nullable = false, length = 45, unique = true)
    private String login;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "email", nullable = false, length = 45, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "second_name", nullable = false, length = 45)
    private String secondName;

    @Column(name = "phone_number", nullable = false, length = 45)
    private String phoneNumber;

    @Column(name = "money_spent", insertable = false, nullable = false)
    private Long moneySpent;

    @Column(name = "role", nullable = false, insertable = false, length = 45)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Order> orders;

    /**
     * User roles
     */
    public enum Role {
        USER, ADMIN
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", moneySpent=" + moneySpent +
                ", role=" + role +
                ", orders=" + orders +
                '}';
    }
}
