package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.*;
import SoutenanceBackend.soutenance.Repository.MonJardinRepository;
import SoutenanceBackend.soutenance.images.Image;
import SoutenanceBackend.soutenance.services.MonJardinService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/monJardin")
@CrossOrigin(origins = "http://localhost:8100")
public class MonJardinController {
    private MonJardinService monJardinService;
    private final MonJardinRepository monJardinRepository;

    public MonJardinController(MonJardinService monJardinService,
                               MonJardinRepository monJardinRepository) {
        this.monJardinService = monJardinService;
        this.monJardinRepository = monJardinRepository;
    }

    //°°°°°°°°°°°°°°°°°°°°°°AJOUTER UN JARDIN°°°°°°°°°°°°°°°°°°°°°
    @PostMapping("/Ajouter/{id_user}")
    public String ajout(@Param("nom") String nom,
                        @PathVariable("id_user") User id_user,
                        @Param("file") MultipartFile file,
                        @Param("photo") String photo) throws IOException {

        MonJardin monJardin1 = new MonJardin();
        monJardin1.setNom(nom);


        if(monJardinRepository.findByNom(nom) == null){


            monJardin1.setPhoto(Image.save(file, monJardin1.getPhoto()));


            monJardin1.setUser(id_user);
            monJardinService.creer(monJardin1);
            return "Votre jardin a été ajouter avec succès";
        }
        else{
            return "ce jardin existe déjà existe déja";

        }


    }
    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER UNE QUESTION°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerjardin/{id_jardin}")
    public String delete(@PathVariable Long id_jardin){

        return monJardinService.supprimer(id_jardin);
    }
    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER UNE QUESTION°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierQuestion/{id_jardin}")
    public MonJardin update(@PathVariable Long id_jardin, @RequestBody MonJardin monJardin){
        return monJardinService.modifier(id_jardin,monJardin);
    }
}


