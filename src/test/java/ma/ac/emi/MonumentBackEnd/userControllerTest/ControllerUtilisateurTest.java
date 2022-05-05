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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ControlleurUtilisateur.class, UserDAO.class})
public class ControllerUtilisateurTest {
    @Autowired
    private ControlleurUtilisateur userController;
    @Autowired
    private IDBUserAdder userAdder;
    @Autowired
    private IDBUserGetter userGetter;
    @Test
    public void deletingUser(){
        Utilisateur user1=new Utilisateur("test","king","alaoui","maroc@gmail.com","123");
        userAdder.addUtilisateur(user1);
        assertNotNull(userGetter.getUtilisateur(user1.getId()));
        userController.deleteUtilisateur(user1.getId());
        assertNull(userGetter.getUtilisateur(user1.getId()));
    }
    @Test
    public void checkUser(){
        Utilisateur user1=new Utilisateur("test","king","alaoui","maroc@gmail.com","123");
        userAdder.addUtilisateur(user1);
        assertTrue(userController.checkUser(user1.getId()));
        userController.deleteUtilisateur(user1.getId());
        assertFalse(userController.checkUser(user1.getId()));
    }
    @Test
    public void editUser(){
        Utilisateur user1=new Utilisateur("test","king","alaoui","maroc@gmail.com","123");
        userAdder.addUtilisateur(user1);
        user1.setNom("sidna");
        userController.editUtilisateur(user1);
        assertEquals("sidna",userGetter.getUtilisateur(user1.getId()).getNom());
    }

}
