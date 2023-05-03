package org.generation.italy.projectPEPE.model.services.implementations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.generation.italy.projectPEPE.model.abstractions.AbstractTokenRepository;
import org.generation.italy.projectPEPE.model.entities.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {
    private AbstractTokenRepository tokenRepository;

    @Autowired
    public LogoutService(AbstractTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        Token storedToken = tokenRepository.findByToken(jwt)
                .orElseThrow(null);
        if (storedToken != null){
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
            //da cercare cosa fa
            SecurityContextHolder.clearContext();
        }

    }
}
