package com.devstutor.user;

import java.io.Serial;
import java.io.Serializable;

public class RegularUser implements User, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String username;

    public RegularUser(String username){
        this.username = username;
    }
    @Override
    public String getUserName() {
        return username;
    }
}
