package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.ac.emi.MonumentBackEnd.Entities.Monument;

@RestController
public class VisitorController {
    
    @Autowired
    private IMonumentGetter monumentGetter;

    @GetMapping("/monument")
    public Monument getMonument(String monumentId) {
        return monumentGetter.getMonument(monumentId);
    }
    
    @GetMapping("/search")
    public List<Monument> getMonuments(List<String> motClets) {
        return monumentGetter.getMonuments(motClets);
    }

}
