package hr.fer.tel.SmartAgriculture.controllers;

import hr.fer.tel.SmartAgriculture.models.LoginModel;
import hr.fer.tel.SmartAgriculture.models.RegistrationModel;
import hr.fer.tel.SmartAgriculture.models.TokenModel;
import hr.fer.tel.SmartAgriculture.services.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
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

        return ResponseEntity.ok(new TokenModel(jwt, loginRequest.getUsername()));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegistrationModel registrationModel) {
        try {
            String jwt = userService.register(registrationModel);
            return ResponseEntity.ok(new TokenModel(jwt, registrationModel.getUsername()));
        }
        catch (DataIntegrityViolationException e) {
            String message = e.getMostSpecificCause().getMessage();
            if (message.contains("Users_username_key")) {
                return ResponseEntity.badRequest().body("Non unique username.");
            } else if (message.contains("Users_email_key")) {
                return ResponseEntity.badRequest().body("Non unique email");
            }
            return ResponseEntity.badRequest().build();
        }
    }
}
