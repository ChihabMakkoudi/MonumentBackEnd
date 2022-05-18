package ma.ac.emi.MonumentBackEnd.MonumentControllerpackage;

import java.util.List;

import ma.ac.emi.MonumentBackEnd.Entities.Monument;

public interface IDBMonumentFilter {


    public List<Monument> getMonumentsByMotCle(List<String> motcles);
}
