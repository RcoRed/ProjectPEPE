package org.generation.italy.projectPEPE.model.services.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.generation.italy.projectPEPE.auth.AuthRequest;
import org.generation.italy.projectPEPE.auth.AuthResponse;
import org.generation.italy.projectPEPE.auth.RegisterRequest;
import org.generation.italy.projectPEPE.model.abstractions.AbstractPersonRepository;
import org.generation.italy.projectPEPE.model.abstractions.AbstractTokenRepository;
import org.generation.italy.projectPEPE.model.entities.Person;
import org.generation.italy.projectPEPE.model.entities.Token;
import org.generation.italy.projectPEPE.model.entities.enums.Role;
import org.generation.italy.projectPEPE.model.entities.enums.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.Optional;

@Service
public class AuthService {
    /*
    * es
    * private final UserRepository repository;
    *
    * da quanto ho capito se lo rendi final e fai creare il costruttore da lombok (@RequiredArgsConstructor)
    * Spring farà l'autowired s enza che noi lo specifichiamo quindi tipo automaticamente
    * */
    private AbstractPersonRepository personRepository;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private AbstractTokenRepository tokenRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AbstractPersonRepository personRepository, JwtService jwtService, AuthenticationManager authenticationManager, AbstractTokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Person> findByEmail(String email){
        return  personRepository.findByEmail(email);
    }

    public AuthResponse register(RegisterRequest request) {
        //qui nell'esempio si usava il builder al momento non lo sto usando per la questione della classe astratta
        System.out.println("AuthService: " + request);
        //ci salviamo la password cryptata per questioni di sicurezza (il nostro database non saprà mai la password reale dell'utente)
        Person person = new Person(0,request.getEmail(), passwordEncoder.encode(request.getPassword()), Role.USER,
                request.getFirstname(), request.getLastname(), request.getDob(),
                request.getWeight(), request.getHeight(), request.getSex(),
                request.getWork(),request.getDiet(),request.getPhysicalActivity());
        System.out.println("AuthService: " + person + " persona creata");
        Person savedPerson = personRepository.save(person);
        String jwtToken = jwtService.generateToken(person);
        String refreshToken = jwtService.generateRefreshToken(person);
        saveUserToken(savedPerson, jwtToken);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        System.out.println("AuthService/authenticate: " + request + " email: " + request.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Person person = personRepository.findByEmail(request.getEmail())
                .orElseThrow();
        System.out.println("AuthService: findByEmail passed");
        String jwtToken = jwtService.generateToken(person);
        String refreshToken = jwtService.generateRefreshToken(person);
        //in questo modo ci sarà una sola riga token per utente e non verrà generata una nuova riga ogni volta che l'utente fa l'autenticazione (da chiedere se va bene)
        Token token = updateTokenByPerson(person,jwtToken);
        tokenRepository.save(token);
//        revokeAllUserTokens(person);
//        saveUserToken(person,jwtToken);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private Token updateTokenByPerson(Person person,String newToken){
        Token token = tokenRepository.findByPerson(person).orElseThrow();
        token.setToken(newToken);
        return token;
    }

    private void saveUserToken(Person person, String jwtToken) {
        var token = Token.builder()
                .person(person)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    //se va tutto bene non dovrebbe più servire questo metodo
    private void revokeAllUserTokens(Person person) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(person.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if(userEmail != null){
            Person person = personRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, person)){
                String jwtToken = jwtService.generateToken(person);
                Token token = updateTokenByPerson(person,jwtToken);
                tokenRepository.save(token);
//                revokeAllUserTokens(person);
//                saveUserToken(person,jwtToken);
                AuthResponse authResponse = AuthResponse.builder()
                        .accessToken(jwtToken)
                        .refreshToken(refreshToken)
                        .build();
                //non capisco cosa faccia
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
