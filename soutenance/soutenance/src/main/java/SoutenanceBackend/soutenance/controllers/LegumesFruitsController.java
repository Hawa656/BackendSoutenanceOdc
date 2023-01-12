package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.*;
import SoutenanceBackend.soutenance.Repository.LegumesFruitsRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.images.Image;
import SoutenanceBackend.soutenance.services.LegumesFruitsService;
import antlr.StringUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/legumefruit")
public class LegumesFruitsController {



    private LegumesFruitsService legumesFruitsService;
    private LegumesFruitsRepository legumesFruitsRepository;

    private UserRepository userRepository;

    public LegumesFruitsController(LegumesFruitsService legumesFruitsService, LegumesFruitsRepository legumesFruitsRepository, UserRepository userRepository) {
     //   this.legumesFruits = legumesFruits;
        this.legumesFruitsService = legumesFruitsService;
        this.legumesFruitsRepository = legumesFruitsRepository;
        this.userRepository = userRepository;
    }

   /* @PostMapping("/AjouterLegumesFruits/{id_user}")
    public String create(@RequestBody LegumesFruits legumesFruits, @PathVariable("id_user") Long id_user){
     //   Optional<User> user = userRepository.findById(id_user);


        if(userRepository.findById(id_user) == null){
            return "Utiisateur non trouvé ";
        }
        else{
                legumesFruitsService.creer(legumesFruits);
                return "Legume ajouter avec succès";

        }


    }*/
    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER UN LEGUME OU FRUIT°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireLegumesFruits")
    public List<LegumesFruits> read(){
        return legumesFruitsService.lire();

    }
    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER UN LEGUME OU FRUIT°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerlegumesFruits/{id}")
    public String delete(@PathVariable Long id){
        return legumesFruitsService.supprimer(id);
    }
    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER UN LEGUME OU FRUIT°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierLegumesFruits/{id}")
    public LegumesFruits update(@PathVariable Long id, @RequestBody LegumesFruits legumesFruits){
       return legumesFruitsService.modifier(id, legumesFruits);
    }



    //°°°°°°°°°°°°°°°°°°°°°°AJOUTER UN LEGUME OU FRUIT°°°°°°°°°°°°°°°°°°°°°
    @PostMapping("/Ajouter/{id_user}/{id_type}/{id_tutoriel}/{id_video}")
    public String ajout(@Param("nom") String nom,
                        @Param("description") String description,
                        @Param("arrosage") String arrosage,
                        @Param("periodeNormal") String periodeNormal,
                        @Param("duréeFloraisaon") String duréeFloraisaon,
                        @PathVariable("id_user") User id_user,
                        @PathVariable("id_type") TypeLegumeFruit id_type,
                        @PathVariable("id_tutoriel") Tutoriels id_tutoriel,
                        @PathVariable("id_video") Video id_video,
                        @Param("file") MultipartFile file,
                        @Param("photo") String photo) throws IOException {

       LegumesFruits legumesFruits1 = new LegumesFruits();
       legumesFruits1.setNom(nom);
        legumesFruits1.setArrosage(arrosage);
        legumesFruits1.setDuréeFloraisaon(duréeFloraisaon);
        legumesFruits1.setPeriodeNormal(periodeNormal);
        legumesFruits1.setDescription(description);


        if(legumesFruitsRepository.findByNom(nom) == null){


            legumesFruits1.setPhoto(Image.save(file, legumesFruits1.getPhoto()));

            legumesFruits1.setTutoriels(id_tutoriel);
            legumesFruits1.setVideo(id_video);
            legumesFruits1.setTypeLegumeFruit(id_type);
            legumesFruits1.setUser(id_user);
            legumesFruitsService.creer(legumesFruits1);
            return "Legume ajouter avec succès";
        }
        else{
            return "Legume existe déja";

        }


    }
}
