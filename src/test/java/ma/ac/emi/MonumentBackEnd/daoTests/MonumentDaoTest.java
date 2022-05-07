package ma.ac.emi.MonumentBackEnd.daoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ma.ac.emi.MonumentBackEnd.DAO.MonumentDAO;
import ma.ac.emi.MonumentBackEnd.Entities.Coordinate;
import ma.ac.emi.MonumentBackEnd.Entities.Monument;
import ma.ac.emi.MonumentBackEnd.Entities.Ville;

@SpringBootTest(classes = {MonumentDAO.class})
public class MonumentDaoTest {

    @Autowired
    private MonumentDAO monumentDAO;

    @Test
    public void delettingExistantMonument() throws IOException {
        File file = new File("monuments/test.xml");
        file.getParentFile().mkdirs();
        file.createNewFile();
        monumentDAO.deleteMonument("test");
        assertFalse(file.exists());

    }
    
    @Test
    public void addSimpleMonument() throws StreamWriteException, DatabindException, IOException {
        Monument monument = new Monument("test1", "Challa", "monument", new Coordinate(2.5, 4.5), List.of("ABC", "WOW"), new Ville("Rabat", "Lights"));
        //Monument monument = new Monument("test1", "Challa", "monument", new Coordinate(2.5, 4.5), new ArrayList<String>(), new Ville("Rabat", "Lights"));
        monumentDAO.addMonument(monument);



    }


    @Test
    public void readMonumentById() {
        Monument monument = new Monument("test1", "Challa", "monument", new Coordinate(2.5, 4.5), List.of("ABC", "WOW"), new Ville("Rabat", "Lights"));
        monumentDAO.addMonument(monument);
        Monument monument2 = monumentDAO.getMonument("test1");

        assertEquals(monument.getNom(), monument2.getNom());
        assertEquals(monument.getDescription(), monument2.getDescription());


    }
    @Test
    public void readMultipleMonuments() {
        Monument monument = new Monument("test1", "Challa", "monument", new Coordinate(2.5, 4.5), List.of("ABC", "WOW"), new Ville("Rabat", "Lights"));
        Monument monument2 = new Monument("test2", "Challa2", "monument2", new Coordinate(2.5, 4.5), List.of("ABC", "WOW"), new Ville("Rabat", "Lights"));
        monumentDAO.addMonument(monument);
        monumentDAO.addMonument(monument2);
        List<Monument> monuments = monumentDAO.getMonuments();
        assertEquals(2, monuments.size());

        Monument result1 = monuments.get(1);
        Monument result2 = monuments.get(0);
        
        assertEquals(monument.getNom(), result1.getNom());
        assertEquals(monument.getDescription(), result1.getDescription());
        assertEquals(monument2.getNom(), result2.getNom());
        assertEquals(monument2.getDescription(), result2.getDescription());


    }
}
