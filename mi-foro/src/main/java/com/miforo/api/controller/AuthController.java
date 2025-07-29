package com.miforo.api.controller;

import com.miforo.api.domain.user.User;
import com.miforo.api.domain.user.UserRepository;
import com.miforo.api.security.TokenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());
        
        Authentication authentication = authenticationManager.authenticate(authToken);
        String token = tokenService.generateToken((User) authentication.getPrincipal());
        
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (userRepository.findByUsername(registerDTO.username()) != null) {
            return ResponseEntity.badRequest().body("Usuario ya existe");
        }

        String encodedPassword = passwordEncoder.encode(registerDTO.password());
        User user = new User(registerDTO.username(), encodedPassword, registerDTO.email());
        userRepository.save(user);

        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    public record LoginDTO(@NotBlank String username, @NotBlank String password) {}
    public record RegisterDTO(@NotBlank String username, @NotBlank String password, @NotBlank String email) {}
    public record TokenDTO(String token) {}
}