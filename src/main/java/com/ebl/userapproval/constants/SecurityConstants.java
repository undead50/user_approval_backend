package com.ebl.userapproval.constants;


import org.springframework.stereotype.Component;

@Component
public interface SecurityConstants {

    public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";

    public static final String JWT_HEADER = "Authorization";
    public static final boolean LADAP_AUTH = false;
}
