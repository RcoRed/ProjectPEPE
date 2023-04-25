package org.generation.italy.projectPEPE.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.generation.italy.projectPEPE.model.entities.enums.Role;

import java.util.List;

@Getter
@Setter
//@SuperBuilder       //annotazione di lombok sperimentale
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person_auth")
public abstract class PersonAuth {
    @Id
    @Column(name = "id_person_auth")
    @GeneratedValue(generator = "person_auth_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "person_auth_generator", sequenceName = "person_auth_sequence", allocationSize = 1)
    private Long id;
    private String mail;
    private String password;
    private Role role;
    @OneToMany(mappedBy = "personAuth")
    private List<Token> tokens;

}
