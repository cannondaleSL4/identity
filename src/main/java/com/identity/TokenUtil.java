package com.identity;

import com.model.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenUtil {

    //private static final long VALIDITY_TIME_MS = 10 * 24 * 60 * 60 * 1000;// 10 days Validity
    //private static final long VALIDITY_TIME_MS = 10; // it's 1 sec for testing
    private static final long VALIDITY_TIME_MS =  2 * 60 * 60 * 1000; // 2 hours  validity
    private static final String AUTH_HEADER_NAME = "Authorization";

    private String secret="mrin";

    public Optional<Authentication> verifyToken(HttpServletRequest request) {
      final String token = request.getHeader(AUTH_HEADER_NAME);

      if (token != null && !token.isEmpty()){
        final TokenUser user = parseUserFromToken(token.replace("Bearer","").trim());
        if (user != null) {
            return  Optional.of(new UserAuthentication(user));
        }
      }
      return Optional.empty();

    }

    //Get User Info from the Token
    public TokenUser parseUserFromToken(String token){

        Claims claims = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();

        User user = new User();
        user.setId( (Integer) claims.get("userId"));
        user.setRole(((String)claims.get("role")));
        if (user.getId() != null && user.getRoles() != null) {
            return new TokenUser(user);
        } else {
            return null;
        }
    }

    public String createTokenForUser(TokenUser tokenUser) {
      return createTokenForUser(tokenUser.getUser());
    }

    public String createTokenForUser(User user) {
      return Jwts.builder()
        .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS))
        .setSubject(user.getName())
        .claim("userId", user.getId())
        .claim("role", user.getRoles().toString())
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
    }

}
