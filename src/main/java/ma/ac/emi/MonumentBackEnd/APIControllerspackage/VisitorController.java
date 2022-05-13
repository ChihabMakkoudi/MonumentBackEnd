package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import ma.ac.emi.MonumentBackEnd.Entities.Monument;
import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;
import ma.ac.emi.MonumentBackEnd.Pdf.PdfGeneration;

@CrossOrigin
@RestController
public class VisitorController {
    
    @Autowired
    private IMonumentGetter monumentGetter;
    @Autowired
    private IConnectionController connectionController;

    @RequestMapping(value = "/pdf/{file_name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("file_name") String fileName) {
        try {
            PdfGeneration.convertToPDF(fileName);
        } catch (FOPException | IOException | TransformerException e) {
            e.printStackTrace();
        }
        return new FileSystemResource(PdfGeneration.OUTPUT_FILE); 
    }
    
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
    
    @PostMapping("/auth")
    public String authentification(@RequestParam String email, @RequestParam String password) throws NoSuchAlgorithmException {
        Utilisateur user = connectionController.connection(email, password);
        if (user == null)
            return "fail";
        return user.getToken();
    }

    @PostMapping("/newuser")
    public String createAccount(@RequestParam String nom,
                                @RequestParam String prenom,
                                @RequestParam String email,
                                @RequestParam String password
    ) {

        Utilisateur user = new Utilisateur(email, nom, prenom, email, password);
        try {
            user.setId(user.getMail());
            connectionController.creatUtilisateur(user);
            return user.getToken();
        } catch (Exception e) {
            return "fail";
        }

    }

}
