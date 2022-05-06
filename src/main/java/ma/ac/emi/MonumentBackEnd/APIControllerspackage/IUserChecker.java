package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface IUserChecker {
    public boolean checkUser(String token) throws NoSuchAlgorithmException, InvalidKeyException;
}
