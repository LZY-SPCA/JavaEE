package whu.edu.assignment4.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    String name;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    private List<Role> roles = new ArrayList<>();
}
