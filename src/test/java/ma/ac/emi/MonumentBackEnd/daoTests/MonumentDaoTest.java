package ma.ac.emi.MonumentBackEnd.daoTests;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ma.ac.emi.MonumentBackEnd.DAO.MonumentDAO;

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
    
}
