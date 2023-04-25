package org.generation.italy.projectPEPE.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.generation.italy.projectPEPE.model.entities.enums.TokenType;

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

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private PersonAuth personAuth;
}
