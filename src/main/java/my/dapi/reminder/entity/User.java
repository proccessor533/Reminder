package my.dapi.reminder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "telegram")
    private String telegram;

    @Column(name = "email")
    private String email;

    public User(String username, String password, String telegram, String email) {
        this.username = username;
        this.password = password;
        this.telegram = telegram;
        this.email = email;
    }

}
