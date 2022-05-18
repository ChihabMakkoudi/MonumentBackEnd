package ma.ac.emi.MonumentBackEnd.daoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ma.ac.emi.MonumentBackEnd.DAO.MonumentDAO;
import ma.ac.emi.MonumentBackEnd.DAO.XQueryMonumentsSearch;

@SpringBootTest(classes = {XQueryMonumentsSearch.class, MonumentDAO.class})
public class XqueryTests {
    @Autowired
    private XQueryMonumentsSearch xQueryMonumentsSearch;

    @Test
    public void simpleTest() {
        Boolean c = xQueryMonumentsSearch.containsMotCle(new File("monuments/1.xml"),"raba");

        assertTrue(c);


    }

    
}
