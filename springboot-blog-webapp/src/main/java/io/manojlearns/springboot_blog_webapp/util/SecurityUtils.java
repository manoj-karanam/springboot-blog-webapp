package io.manojlearns.springboot_blog_webapp.util;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

public class SecurityUtils {

    public static User getCurrentUser(){
        //this gives you user details of the currently logged user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof User){
            return (User) principal;
        }

        return null;
    }

    public static String getRole() {

        User user = getCurrentUser();
        Collection<GrantedAuthority> authorities= user.getAuthorities();

        for(GrantedAuthority authority: authorities){
            return authority.getAuthority();
        }

        return null;
    }
}
