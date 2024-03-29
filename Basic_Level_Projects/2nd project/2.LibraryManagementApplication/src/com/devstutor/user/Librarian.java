package com.devstutor.user;

import java.io.Serial;
import java.io.Serializable;

public class Librarian implements User, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String username;

    public Librarian(String name){
        username = name;
    }
    @Override
    public String getUserName() {
        return username;
    }
}
