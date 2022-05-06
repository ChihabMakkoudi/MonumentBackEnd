package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import java.security.NoSuchAlgorithmException;

public interface IAdminChecker {
    public boolean checkAdmin(String token) throws NoSuchAlgorithmException;
}
