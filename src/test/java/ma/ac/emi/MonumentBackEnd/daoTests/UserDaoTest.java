package ma.ac.emi.MonumentBackEnd.daoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ma.ac.emi.MonumentBackEnd.DAO.UserDAO;
import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserAdder;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserDeletter;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserGetter;

@SpringBootTest(classes = {UserDAO.class})
public class UserDaoTest {

    @Autowired
    private IDBUserAdder userAdder;
    @Autowired
    private IDBUserDeletter userDeletter;
    @Autowired
    private IDBUserGetter userGetter;

    @Test
    public void addingSimpleUser() throws IOException {
        Utilisateur utilisateur = new Utilisateur("1", "El", "Ahmed", "ahmed@el.com", "123456");
        userAdder.addUtilisateur(utilisateur);
        String results =Files.readString(Path.of("users/1_ahmed@el.com.xml"), StandardCharsets.US_ASCII);
        String expectedResult = new String("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Account id=\"1\"><nom>El</nom><prenom>Ahmed</prenom><email>ahmed@el.com</email><password>123456</password></Account>");
        assertEquals(expectedResult, results);
    }

    @Test
    public void delettingSimpleUser() {
        Utilisateur utilisateur = new Utilisateur("1", "El", "Ahmed", "ahmed@el.com", "123456");
        userAdder.addUtilisateur(utilisateur);
        userDeletter.deleteUtilisateur("1");
        File file = new File("users/1_ahmed@el.com.xml");
        assertFalse(file.exists());

    }
    
    @Test
    public void getUserWithId1() {
        userDeletter.deleteUtilisateur("1");
        Utilisateur utilisateur = new Utilisateur("1", "El", "Ahmed", "ahmed@el.com", "123456");
        userAdder.addUtilisateur(utilisateur);

        Utilisateur result_utilisateur = userGetter.getUtilisateur("1");
        assertNotNull(result_utilisateur);
        
    }
    
}
