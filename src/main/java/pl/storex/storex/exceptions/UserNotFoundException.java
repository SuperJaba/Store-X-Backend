package pl.storex.storex.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String uuid) {
        super("Could not find user " + uuid);
    }

}
