package pl.storex.storex.user.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String uuid) {
        super("Could not find user " + uuid);
    }

}
