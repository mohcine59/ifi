package com.ifi.errors;

public class UserNotFoundException extends Exception {

    /** The serialVersionUID. */
    private static final long serialVersionUID = 7896873639809385352L;

    String username = null;

    public UserNotFoundException(String username) {
        this.username = username;
    }

    /** {@inheritDoc} */
    @Override
    public String getMessage() {
        return "User \"" + username + "\" does not exists";
    }
}
