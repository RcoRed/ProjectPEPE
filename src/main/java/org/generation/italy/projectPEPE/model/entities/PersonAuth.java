package org.generation.italy.projectPEPE.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.generation.italy.projectPEPE.model.entities.enums.Role;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
//@SuperBuilder       //annotazione di lombok sperimentale
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person_auth")
public abstract class PersonAuth implements UserDetails{
    @Id
    @Column(name = "id_person_auth")
    @GeneratedValue(generator = "person_auth_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "person_auth_generator", sequenceName = "person_auth_sequence", allocationSize = 1)
    private Long id;
    private String email;
    private String password;

    @Column(name = "role", columnDefinition = "ROLE")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private Role role;
    @OneToMany(mappedBy = "person")
    private List<Token> tokens;


    //questi override servono all' authenticationManager (bisogna studiarli meglio)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
