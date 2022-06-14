package kz.alimgaziyev.bankappspringbooth2.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 20)
    private String name;

    public Role(ERole name) {
        this.name = name.name();
    }
}
