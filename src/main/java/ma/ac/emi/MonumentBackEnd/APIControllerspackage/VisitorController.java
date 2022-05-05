package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.ac.emi.MonumentBackEnd.Entities.Monument;

@RestController
public class VisitorController {
    
    @Autowired
    private IMonumentGetter monumentGetter;

    @GetMapping("/monument")
    public Monument getMonument(@RequestParam String id) {
        return monumentGetter.getMonument(id);
    }
    
    @GetMapping("/search")
    public List<Monument> getMonuments(@RequestParam(required = false) List<String> motClets) {
        if (motClets == null) {
            motClets = new ArrayList<>();
            motClets.add("");
        }
        return monumentGetter.getMonuments(motClets);
    }
    

}
