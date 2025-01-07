package pl.storex.storex.user;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String uuid) {
        super("Could not find user " + uuid);
    }

}
