package org.generation.italy.projectPEPE.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import org.generation.italy.projectPEPE.model.entities.enums.TokenType;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token")
public class Token {

    @Id
    @Column(name = "id_token")
    @GeneratedValue(generator = "token_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "token_generator", sequenceName = "token_sequence", allocationSize = 1)
    private Long id;

    private String token;

    @Column(name = "token_type", columnDefinition = "TOKEN_TYPE")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private TokenType tokenType = TokenType.BEARER;

    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_person_auth")
    private Person person;
}
