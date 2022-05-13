package ma.ac.emi.MonumentBackEnd.userControllerTest;

import ma.ac.emi.MonumentBackEnd.DAO.UserDAO;
import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.ControlleurUtilisateur;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserAdder;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserDeletter;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserGetter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ControlleurUtilisateur.class, UserDAO.class})
public class ControllerUtilisateurTest {
    @Autowired
    private ControlleurUtilisateur userController;
    @Autowired
    private IDBUserAdder userAdder;
    @Autowired
    private IDBUserGetter userGetter;
    @Autowired
    private IDBUserDeletter userDeletter;
    @Test
    public void deletingUser(){
        Utilisateur user1=new Utilisateur("test","king","alaoui","maroc@gmail.com","123");
        userAdder.addUtilisateur(user1);
        assertNotNull(userGetter.getUtilisateur(user1.getId()));
        userController.deleteUtilisateur(user1.getId());
        assertNull(userGetter.getUtilisateur(user1.getId()));
    }
    @Test
    public void checkExistence(){
        Utilisateur user1=new Utilisateur("test","king","alaoui","maroc@gmail.com","123");
        userAdder.addUtilisateur(user1);
        assertTrue(userController.checkExistence(user1.getId()));
        userController.deleteUtilisateur(user1.getId());
        assertFalse(userController.checkExistence(user1.getId()));
    }
    @Test
    public void editUser(){
        Utilisateur user1=new Utilisateur("test","king","alaoui","maroc@gmail.com","123");
        userAdder.addUtilisateur(user1);
        user1.setNom("sidna");
        userController.editUtilisateur(user1);
        assertEquals("sidna",userGetter.getUtilisateur(user1.getId()).getNom());
    }
    @Test
    public void checkUser() throws NoSuchAlgorithmException  {
        Utilisateur user1=new Utilisateur("test","king","alaoui","maroc@gmail.com","123");
        userAdder.addUtilisateur(user1);
        assertTrue(userController.checkUser("test_d4bdb4851b5198118c0d477d7bfce881c98a4089ecd36d90a14077d0839157c6"));
    }
    @Test
    public void checkAdmin() throws NoSuchAlgorithmException  {
        Utilisateur admin=new Utilisateur("0","simo","elalaoui","admin@gmail.com","0001");
        userAdder.addUtilisateur(admin);
        assertTrue(userController.checkAdmin("0_f63bfc9627d4f33ff969386eec2bf1557d328d2c790ba3a4ebe1d8df9a4e42cf"));
        userController.deleteUtilisateur("0");
    }
    @Test
    public void createUser() {
        userDeletter.deleteUtilisateur("a@a.com");
        Utilisateur user1=new Utilisateur("a@a.com","me","pre","a@a.com","123");
        try {
            userController.creatUtilisateur(user1);
        }
        catch (Exception e){
            fail();
        }
        Utilisateur user2  = userGetter.getUtilisateur("a@a.com");
        assertEquals("me", user2.getNom());
    }
    @Test
    public void createExistantUser() {
        userDeletter.deleteUtilisateur("a@a.com");
        Utilisateur user1=new Utilisateur("a@a.com","me","pre","a@a.com","123");
        try {
            userController.creatUtilisateur(user1);
            userController.creatUtilisateur(user1);
            fail();
        }
        catch (Exception e){
        }
    }
    @Test
    public void createAdmin() throws NoSuchAlgorithmException {
        userDeletter.deleteUtilisateur("0");
        Utilisateur user0=new Utilisateur("0","admin","admin","admin@admin.com","admin");
        userAdder.addUtilisateur(user0);
        assertTrue(userController.checkAdmin(user0.getToken()));

    }
}
