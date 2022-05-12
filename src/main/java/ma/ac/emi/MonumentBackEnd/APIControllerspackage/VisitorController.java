package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.ac.emi.MonumentBackEnd.Entities.Monument;
import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;

@CrossOrigin
@RestController
public class VisitorController {
    
    @Autowired
    private IMonumentGetter monumentGetter;
    @Autowired
    private IConnectionController connectionController;

    @GetMapping("/monument")
    public Monument getMonument(@RequestParam String id) {
        return monumentGetter.getMonument(id);
    }
    
    @GetMapping("/search")
    public List<Monument> getMonuments(@RequestParam(required = false) List<String> motClets) {
        if (motClets == null) {
            motClets = new ArrayList<>();
            motClets.add("");
        }
        return monumentGetter.getMonuments(motClets);
    }
    
    @PostMapping("/auth")
    public String authentification(@RequestParam String email, @RequestParam String password) throws NoSuchAlgorithmException {
        Utilisateur user = connectionController.connection(email, password);
        if (user == null)
            return "fail";
        return user.getToken();
    }

    @PostMapping("/newuser")
    public String createAccount(@RequestParam String nom,
                                @RequestParam String prenom,
                                @RequestParam String email,
                                @RequestParam String password
    ) {

        Utilisateur user = new Utilisateur(email, nom, prenom, email, password);
        try {
            user.setId(user.getMail());
            connectionController.creatUtilisateur(user);
            return user.getToken();
        } catch (Exception e) {
            return "fail";
        }

    }

}
