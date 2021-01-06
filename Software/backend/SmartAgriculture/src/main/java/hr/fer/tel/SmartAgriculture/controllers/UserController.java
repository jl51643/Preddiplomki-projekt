package hr.fer.tel.SmartAgriculture.controllers;

import hr.fer.tel.SmartAgriculture.models.LoginModel;
import hr.fer.tel.SmartAgriculture.models.RegistrationModel;
import hr.fer.tel.SmartAgriculture.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginModel loginRequest) {
        String jwt = userService.login(loginRequest);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegistrationModel registrationModel) {
        String jwt = userService.register(registrationModel);

        return ResponseEntity.ok(jwt);
    }
}
