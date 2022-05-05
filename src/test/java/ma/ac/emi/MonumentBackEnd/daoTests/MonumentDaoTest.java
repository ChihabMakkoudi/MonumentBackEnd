package ma.ac.emi.MonumentBackEnd.daoTests;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.IOException;
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
        monumentDAO.addMonument(monument);



    }
}
