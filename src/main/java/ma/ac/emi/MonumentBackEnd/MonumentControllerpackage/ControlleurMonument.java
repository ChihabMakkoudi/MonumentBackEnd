package ma.ac.emi.MonumentBackEnd.MonumentControllerpackage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ma.ac.emi.MonumentBackEnd.APIControllerspackage.IMonumentGetter;
import ma.ac.emi.MonumentBackEnd.Entities.Monument;

public class ControlleurMonument implements IMonumentGetter{

    @Autowired
    private IDBMonumentGetter dbMonumentGetter;

    @Override
    public List<Monument> getMonuments(List<String> motClets) {
        List<Monument> monuments = dbMonumentGetter.getMonuments();
        List<Monument> filtredMonuments = new ArrayList<>();
        for (Monument monument : monuments) {
            for (String motCle : motClets) {
                if (monument.getDescription().contains(motCle)) {
                    filtredMonuments.add(monument);
                    break;
                }
            }
        }
        return filtredMonuments;
    }

    @Override
    public Monument getMonument(String monumentId) {
        return dbMonumentGetter.getMonument(monumentId);
    }
    
}
