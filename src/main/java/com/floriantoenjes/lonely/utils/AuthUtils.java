package com.floriantoenjes.lonely.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {

    public static String getUsernameFromAuth() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
