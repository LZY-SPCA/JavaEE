package whu.edu.assignment4.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 解析和验证JWT令牌的工具类
 */
@Component
public class JwtTokenUtil {

    /**
     * token的过期时间
     */
    public static final long JWT_TOKEN_VALIDITY = 5*60*60*1000;

    /**
     * token的密钥
     */
    @Value("${jwt.secret}")
    private String secret;


    /**
     * 从Token中获得Claims
     * @param token
     * @return
     */
    public Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token).getBody();
    }

    /**
     * 生成Token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();//可以自由加入各种身份信息，如角色
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 对Token进行验证
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        return validateClaim(getClaimFromToken(token),userDetails);
    }

    /**
     * 对Claims进行验证
     * @param claim
     * @param userDetails
     * @return
     */
    public boolean validateClaim(Claims claim,UserDetails userDetails) {
        //可以验证其他信息，如角色
        return userDetails != null &&
                claim.getSubject().equals(userDetails.getUsername())
                && !claim.getExpiration().before(new Date());
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public Boolean isTokenExpired(String token) {
        Claims claim = getClaimFromToken(token);
        return claim.getExpiration().before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }
}
