package com.ebl.userapproval.filter;


import com.ebl.userapproval.constants.SecurityConstantsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class JWTTokenGeneratorFilter extends OncePerRequestFilter {


    private final SecurityConstantsImpl securityConstants;

    public JWTTokenGeneratorFilter(SecurityConstantsImpl securityConstants) {
        this.securityConstants = securityConstants;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            Integer expiration = Integer.parseInt(securityConstants.getJwtExpiration());
            SecretKey key = Keys.hmacShaKeyFor(securityConstants.getJwtKey().getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().issuer("EBL Bank").subject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .issuedAt(new Date())
                    .expiration(new Date((new Date()).getTime() +  expiration))
                    .signWith(key).compact();
            response.setHeader(securityConstants.getJwtHeader(), jwt);
            System.out.println("token:gen"+jwt);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        List<String> pathsToExclude = Arrays.asList("/api/post-login", "/api/register");
        return !pathsToExclude.contains(request.getServletPath());
//        return !request.getServletPath().equals("/api/post-login");
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }

}
