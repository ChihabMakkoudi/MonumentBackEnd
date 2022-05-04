package ma.ac.emi.MonumentBackEnd.daoTests;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ma.ac.emi.MonumentBackEnd.DAO.EvaluationDAO;
import ma.ac.emi.MonumentBackEnd.Entities.Editeur;
import ma.ac.emi.MonumentBackEnd.Entities.Evaluation;

@SpringBootTest(classes = {EvaluationDAO.class})
public class EvaluationDaoTest {

    @Autowired
    private EvaluationDAO evaluationDAO;

    @Test
    public void deletingEvaluationFile() throws IOException {
        File file = new File("evaluations/test.xml");
        file.getParentFile().mkdirs();
        file.createNewFile();
        evaluationDAO.deleteEvaluation("test");
        assertFalse(file.exists());
    }

    @Test
    public void addSimpleEvaluation() {
        evaluationDAO.addEvaluation(new Evaluation("test", 4.4, "random text", new Editeur("Editor1","James Bond")));
        


    }
    
}
