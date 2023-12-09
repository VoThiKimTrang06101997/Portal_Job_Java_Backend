package com.r2s.findInternship.common;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.r2s.findInternship.config.AppProperties;

import com.r2s.findInternship.common.util.oauth.UserPrincipal;
import com.r2s.findInternship.service.RoleService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.r2s.findInternship.service.impl.UserDetailsImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
    @Value("${r2s.jwtSecret}")
    private String jwtSecret;
    @Value("${r2s.jwtExpirationMs}")
    private int jwtExpirationMs;
    @Autowired
    private RoleService roleService;
    private AppProperties appProperties;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(userPrincipal.getUsername());
        claims.put("roles", authentication.getAuthorities().stream()
                .map(item -> new SimpleGrantedAuthority(item.getAuthority())).collect(Collectors.toList()));
        return Jwts.builder()
                .setClaims(claims).setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
    }


    public String createToken(Authentication authentication) {
//        List<Role> roles = new ArrayList<>();
//        roles.add(roleService.findById(3));
//        List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//        System.out.println(authentication.getPrincipal());
//
//        CustomOAuth2User userPrincipal = (CustomOAuth2User) authentication.getPrincipal();
//        Claims claims = Jwts.claims().setSubject(userPrincipal.getUsername());
//        claims.put("roles", userPrincipal.getAuthorities().stream().map(item -> new SimpleGrantedAuthority(item.getAuthority())).collect(Collectors.toList()));

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(userPrincipal.getUsername());
        claims.put("roles", userPrincipal.getAuthorities().stream().map(item -> new SimpleGrantedAuthority(item.getAuthority())).collect(Collectors.toList()));
        return Jwts.builder()
                .setClaims(claims).setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
    }
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    // lấy role từ token
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getRolesFromToken(String token) {
        // ví dụ:
        // "roles": [
        // {
        // "authority" : "Role_Admin"
        // }
        // ]
        return (List<Map<String, String>>) Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody()
                .get("roles");
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {

        }
        return false;

    }

}
