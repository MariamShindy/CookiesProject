package com.UserService.Secuirty.Services.Implementation;




import com.UserService.Secuirty.Dto.JwtAuthenticationResponse;
import com.UserService.Secuirty.Dto.RefreshTokenRequest;
import com.UserService.Secuirty.Dto.SignUpRequest;
import com.UserService.Secuirty.Dto.SigninRequest;
import com.UserService.Secuirty.Services.AuthService;
import com.UserService.Secuirty.Services.JWTService;
import com.UserService.model.Role;
import com.UserService.model.User;
import com.UserService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {
@Autowired
    private UserRepository userRepository;
@Autowired
    private PasswordEncoder passwordEncoder;
@Autowired
private AuthenticationManager authenticationManager;
@Autowired
private JWTService jwtService;


   public User SignUp(SignUpRequest signUpRequest){
    User user = new User();
    user.setEmail(signUpRequest.getEmail());
    user.setFristName(signUpRequest.getFirstname());
    user.setLastName(signUpRequest.getLastname());
    user.setRole(Role.Customer);
    user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
     return  userRepository.save(user);
}
public JwtAuthenticationResponse SignIn(SigninRequest signinRequest){
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));
    var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    var jwt = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(new HashMap<>() , user);
    JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
    jwtAuthenticationResponse.setToken(jwt);
    jwtAuthenticationResponse.setRefreshToken(refreshToken);
    return jwtAuthenticationResponse;
}
public JwtAuthenticationResponse RefreshToken (RefreshTokenRequest refreshTokenRequest){
    String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
    User user = userRepository.findByEmail(userEmail).orElseThrow();
    if (jwtService.isTokenValid(refreshTokenRequest.getToken(),user))
    {
        var jwt = jwtService.generateToken(user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
        return jwtAuthenticationResponse;
    }
    return null;
}


}
