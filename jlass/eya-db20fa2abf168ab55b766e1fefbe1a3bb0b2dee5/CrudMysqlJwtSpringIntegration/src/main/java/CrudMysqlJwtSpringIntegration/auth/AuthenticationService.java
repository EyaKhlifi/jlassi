package CrudMysqlJwtSpringIntegration.auth;

import CrudMysqlJwtSpringIntegration.security.config.JwtService;
import CrudMysqlJwtSpringIntegration.user.Role;
import CrudMysqlJwtSpringIntegration.user.User;
import CrudMysqlJwtSpringIntegration.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.Builder;
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository = null;
    private final JwtService jwtService = new JwtService();
    private final PasswordEncoder passwordEncoder=null;
    private final AuthenticationManager authenticationManager = null;
    

//    public AuthenticationResponse register(RegisterRequest request) {
//        var user = CrudMysqlJwtSpringIntegration.user.User.builder()
//                .username(request.getUsername())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER)
//                .build();
//        repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder().token(jwtToken).build();
//    }
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//
//         authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(
//                         request.getEmail(),
//                         request.getPassword()
//                 )
//         );
//
//        var user = repository.findByEmail(request.getEmail())
//                .orElseThrow();
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder().token(jwtToken).build();
//    }
//}

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}