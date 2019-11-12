package by.pvt.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Transient
    private final static long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", initialValue = 5)
    private Long id;


    @Column(length = 30)
    private String name;

    @Column(unique = true, nullable = false, length = 25)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, insertable = false, columnDefinition = "varchar(255) default 'USER'")
    private String role;

    @ManyToMany
    private List<Device> devices;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public List<Device> getDevices() {
        if (devices == null) return new ArrayList<>();
        return devices;
    }
}
