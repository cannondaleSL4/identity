package com.identity;

import com.model.user.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class TokenUser extends org.springframework.security.core.userdetails.User {
    private User user;

    public TokenUser(User user) {
        super( user.getId().toString(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRoles().toString()  )  );
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getRole() {
        return user.getRoles().toString();
    }
}
