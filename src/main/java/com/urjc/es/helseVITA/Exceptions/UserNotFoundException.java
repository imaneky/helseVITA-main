package com.urjc.es.helseVITA.Exceptions;

//It executes when user don't exists

public class UserNotFoundException extends RuntimeException {

    String username;
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String username) {
        super("User not found: " + username);
        this.username = username;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

