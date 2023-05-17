package com.example.discordexa.discord.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JwtUtils {


    public String generateJwt(MyUserDetails userDetails){

        Map<String , Object> donnees = new HashMap<>();
        donnees.put("firstname",userDetails.getUtilisateur().getFirstname());
        donnees.put("lastname",userDetails.getUtilisateur().getLastname());
        donnees.put("role",userDetails.getUtilisateur().getRole());

        return Jwts.builder()
                .setClaims(donnees)
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256,"azerty")
                .compact();
    }

    public Claims getData(String jwt){

        return Jwts.parser()
                .setSigningKey("azerty")
                .parseClaimsJws(jwt)
                .getBody();
    }

    public boolean isTokenValide(String jwt){
        try {
            Claims donnes = getData(jwt);
        }catch (SignatureException e){
            return false;
        }
    return true;
    }
}
