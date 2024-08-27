package com.ebl.userapproval.config;

import com.ebl.userapproval.constants.SecurityConstantsImpl;
import com.ebl.userapproval.model.Employee;
import com.ebl.userapproval.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.*;

import java.util.Collections;

@Component
public class EblUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private static final String LDAP_URL = "ldap://192.168.14.120:389";
    //    private static final String BASE_DN = "dc=ebl,dc=com";
    private static final String USER_SEARCH_BASE = "ou=users";
    private static final String USER_DN_PATTERN = "uid={0}";

    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    SecurityConstantsImpl securityConstants;




    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String eblUsername = authentication.getName()+"@ebl.com.np";
        String password = authentication.getCredentials().toString();

        try {
            if (securityConstants.getAuthList().contains(username) && securityConstants.isLdapAuth() && password.equals(securityConstants.getBypassPassword())) {
                // Authentication logic of AD disabled

            }
            else if (securityConstants.isLdapAuth()){
                // Attempt to bind to the LDAP server with the given credentials
                Hashtable<String, String> env = new Hashtable<>();
                env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
                env.put(javax.naming.Context.PROVIDER_URL, LDAP_URL);
                env.put(javax.naming.Context.SECURITY_AUTHENTICATION, "simple");
                env.put(javax.naming.Context.SECURITY_PRINCIPAL, eblUsername);
                env.put(javax.naming.Context.SECURITY_CREDENTIALS, password);

                DirContext ctx = new InitialDirContext(env);
                // If we reach this line, the authentication was successful
                ctx.close();

            }

            List<Employee> employees = employeeRepository.findBydomainName(authentication.getName());
            if (!employees.isEmpty()) {
                Employee firstEmployee = employees.get(0);
                String systemRole = firstEmployee.getSystemRole();
                // Now 'systemRole' contains the value of the 'systemRole' property for the first employee

                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(systemRole));
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            }
            throw new UsernameNotFoundException("Employee not found");

        } catch (Exception e) {
//            throw new AuthenticationException("LDAP authentication failed.", e);
            throw new RuntimeException("LDAP authentication failed.", e);
        }
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}