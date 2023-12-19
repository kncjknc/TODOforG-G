package EmployeesOf.G.G.services;

import EmployeesOf.G.G.controller.CommonController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    Logger logger = LoggerFactory.getLogger(JwtService.class);


    public static final String SECRETE = "varathan10v042002vengatachalam1977samkossha89329";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

        return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(Integer id,String userName) {
        try {
            return createToken(id, userName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("inside Create Token");
            return "DEFAULT_TOKEN_VALUE";
        }
    }

    private String createToken(Integer id, String userName) {
        return Jwts.builder().claim("userId",id).claim("userName",userName).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

    }

    private Key getSignKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRETE);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
