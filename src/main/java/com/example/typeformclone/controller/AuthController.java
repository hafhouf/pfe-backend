package com.example.typeformclone.controller;

import com.example.typeformclone.model.Response;
import com.example.typeformclone.model.User;
import com.example.typeformclone.service.ResponseService;
import com.example.typeformclone.service.UserService;
import com.example.typeformclone.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ResponseService responseService;


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (Exception ex) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        User loggedInUser = userService.findByUsername(user.getUsername()).get(); // Fetch the user to get the role

        return ResponseEntity.ok(new AuthenticationResponse(jwt, loggedInUser.getId(), loggedInUser.getRole()));
    }

    static class AuthenticationResponse {
        private String jwt;
        private Long id;
        private String role;

        public AuthenticationResponse(String jwt, Long id, String role) {
            this.jwt = jwt;
            this.id = id;
            this.role = role;
        }

        public String getJwt() {
            return jwt;
        }

        public Long getId() {
            return id;
        }

        public String getRole() {
            return role;
        }

        public void setJwt(String jwt) {
            this.jwt = jwt;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }

    @GetMapping("/{id}/responses")
    public ResponseEntity<List<Response>> getUserResponses(@PathVariable Long id) {
        List<Response> responses = responseService.findByUserId(id);
        return ResponseEntity.ok(responses);
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
}
