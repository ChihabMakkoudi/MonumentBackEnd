package ma.ac.emi.MonumentBackEnd.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ma.ac.emi.MonumentBackEnd.DAO.EvaluationDAO;
import ma.ac.emi.MonumentBackEnd.DAO.MonumentDAO;
import ma.ac.emi.MonumentBackEnd.Entities.Coordinate;
import ma.ac.emi.MonumentBackEnd.Entities.Editeur;
import ma.ac.emi.MonumentBackEnd.Entities.Evaluation;
import ma.ac.emi.MonumentBackEnd.Entities.Monument;
import ma.ac.emi.MonumentBackEnd.Entities.Ville;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.ControlleurMonument;

@SpringBootTest(classes = {ControlleurMonument.class, EvaluationDAO.class, MonumentDAO.class})
public class ControlleurMonumentTest {
    

    @Autowired
    private ControlleurMonument controlleurMonument;

    @Test 
    public void makeAnEvaluation() {
        Evaluation evaluation = new Evaluation("", 3.5, "was good", new Editeur("Id", "me hi"));
        controlleurMonument.addEvaluation(evaluation, "ABC");

        File file = new File("evaluations/ABC_Id.xml");
        
        assertTrue(file.exists());

    }

    @Test
    public void deleteAnEvaluation() {
        Evaluation evaluation = new Evaluation("", 3.5, "was good", new Editeur("Id", "me hi"));
        controlleurMonument.addEvaluation(evaluation, "ABC");
        controlleurMonument.deleteEvaluation("ABC_Id");
        

        File file = new File("evaluations/ABC_Id.xml");
        assertFalse(file.exists());
    }
    
    @Test
    public void createMonument() {
        Monument monument = new Monument("test1", "Challa", "monument", new Coordinate(2.5, 4.5), List.of("ABC", "WOW"), new Ville("Rabat", "Lights"));
        controlleurMonument.addMonument(monument);
        
        
        File file = new File("monuments/test1.xml");
        assertTrue(file.exists());

    }

    @Test
    public void deleteMonument() {
    
        Monument monument = new Monument("test1", "Challa", "monument", new Coordinate(2.5, 4.5), List.of("ABC", "WOW"), new Ville("Rabat", "Lights"));
        controlleurMonument.addMonument(monument);
        
        controlleurMonument.deleteMonument("test1");
        
        File file = new File("monuments/test1.xml");
        assertFalse(file.exists());

    }



    @Test
    public void getMonumentWithEvaluations() {
        Monument monument = new Monument("test1", "Challa", "monument", new Coordinate(2.5, 4.5), List.of("ABC", "WOW"), new Ville("Rabat", "Lights"));
        controlleurMonument.addMonument(monument);
        Evaluation evaluation = new Evaluation("", 3.5, "was good", new Editeur("Id", "me hi"));
        Evaluation evaluation2 = new Evaluation("", 3.5, "was good", new Editeur("Id2", "me hi"));
        controlleurMonument.addEvaluation(evaluation, "test1");
        controlleurMonument.addEvaluation(evaluation2, "test1");

        Monument resultedMonument = controlleurMonument.getMonument("test1");

        assertNotNull(resultedMonument);
        assertEquals("Challa", resultedMonument.getNom());
        assertEquals(2 , resultedMonument.getEvaluations().size());
        assertEquals(3.5 , resultedMonument.getEvaluations().get(0).getNote());



    }
}
