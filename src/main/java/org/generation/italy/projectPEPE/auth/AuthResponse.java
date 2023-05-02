package org.generation.italy.projectPEPE.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

//    @JsonProperty("access_token")     //dovrebbe servire solo per rinominare la proprietà nel file Json che genereremo (credo)
    private String accessToken;

//    @JsonProperty("refresh_token")
    private String refreshToken;
}
