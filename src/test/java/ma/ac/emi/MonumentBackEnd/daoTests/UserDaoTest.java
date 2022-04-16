package ma.ac.emi.MonumentBackEnd.daoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ma.ac.emi.MonumentBackEnd.DAO.UserDAO;
import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserAdder;

@SpringBootTest(classes = {UserDAO.class})
public class UserDaoTest {

    @Autowired
    private IDBUserAdder userAdder;

    @Test
    public void addingSimpleUser() throws IOException {
        Utilisateur utilisateur = new Utilisateur("1", "El", "Ahmed", "ahmed@el.com", "123456");
        userAdder.addUtilisateur(utilisateur);

        String results =Files.readString(Path.of("users/1_ahmed@el.com.xml"), StandardCharsets.US_ASCII);
        String expectedResult = new String("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Account id=\"1\"><nom>El</nom><prenom>Ahmed</prenom><email>ahmed@el.com</email><password>123456</password></Account>");

        assertEquals(expectedResult, results);
        
        

    }
    
    
}
