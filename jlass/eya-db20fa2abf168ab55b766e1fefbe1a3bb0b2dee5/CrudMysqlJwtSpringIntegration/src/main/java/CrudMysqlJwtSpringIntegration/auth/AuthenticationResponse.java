package CrudMysqlJwtSpringIntegration.auth;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//Example builder pattern for AuthenticationResponse class
public class AuthenticationResponse {
 private String token;

 public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

private AuthenticationResponse() {}

 public static AuthenticationResponseBuilder builder() {
     return new AuthenticationResponseBuilder();
 }

 

 // Builder class
 public static class AuthenticationResponseBuilder {
     private final AuthenticationResponse response;

     private AuthenticationResponseBuilder() {
         this.response = new AuthenticationResponse();
     }

     public AuthenticationResponseBuilder token(String token) {
         this.response.token = token;
         return this;
     }

     public AuthenticationResponse build() {
         return this.response;
     }
 }
}
