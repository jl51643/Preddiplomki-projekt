package hr.fer.tel.SmartAgriculture.services;

import hr.fer.tel.SmartAgriculture.entities.User;
import hr.fer.tel.SmartAgriculture.models.LoginModel;
import hr.fer.tel.SmartAgriculture.models.RegistrationModel;
import hr.fer.tel.SmartAgriculture.repositories.UserRepository;
import hr.fer.tel.SmartAgriculture.security.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public UserService(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    public String register(RegistrationModel registrationModel) {
        User user = new User();

        user.setEmail(registrationModel.getEmail());
        user.setUsername(registrationModel.getUsername());
        user.setPassword(encoder.encode(registrationModel.getPassword()));

        userRepository.save(user);

        return login(new LoginModel(registrationModel.getUsername(), registrationModel.getPassword()));
    }

    public String login(LoginModel loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateJwtToken(authentication);
    }

}
