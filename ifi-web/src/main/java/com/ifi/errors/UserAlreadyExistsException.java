package com.ifi.errors;

public class UserAlreadyExistsException extends Exception {

    /** The serialVersionUID. */
    private static final long serialVersionUID = 6320824649175253164L;

    String username = null;

    public UserAlreadyExistsException(String username) {
        this.username = username;
    }

    /** {@inheritDoc} */
    @Override
    public String getMessage() {
        return "User \"" + username + "\" already exists";
    }

}
