package com.ebl.userapproval.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityConstantsImpl implements SecurityConstants {

    @Value("${app.jwt.key}")
    private String jwtKey;

    @Value("${app.jwt.header}")
    private String jwtHeader;

    @Value("${app.ad.auth}")
    private boolean ldapAuth;

    @Value("${app.ebl.auth.bypass}")
    private List<String> authList;

    @Value("${app.ebl.auth.bypass.password}")
    private String bypassPassword;


    @Value("${app.jwt.expiration}")
    private String jwtExpiration;

    public SecurityConstantsImpl() {
        // If needed, you can set defaults here or leave it empty.
    }

    // Getter methods if needed
    public String getJwtKey() {
        return jwtKey;
    }

    public String getJwtHeader() {
        return jwtHeader;
    }

    public String getBypassPassword(){
        return  bypassPassword;
    }

    public String getJwtExpiration()
    {
        return jwtExpiration;
    }

    public List<String> getAuthList(){
        {return authList;}
    }

    public boolean isLdapAuth() {
        return ldapAuth;
    }
}