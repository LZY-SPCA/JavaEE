package whu.edu.assignment4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import whu.edu.assignment4.entity.User;

@RestController
@CrossOrigin
@RequestMapping("authenticate")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword())
            );
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getName());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(token);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("User Authorized not passed");
        }
    }
}

