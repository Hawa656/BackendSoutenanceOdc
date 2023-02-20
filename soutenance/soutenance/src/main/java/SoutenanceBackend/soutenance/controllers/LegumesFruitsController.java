package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.*;
import SoutenanceBackend.soutenance.Repository.*;
import SoutenanceBackend.soutenance.images.Image;
import SoutenanceBackend.soutenance.images.Video;
import SoutenanceBackend.soutenance.services.LegumesFruitsService;
import SoutenanceBackend.soutenance.services.TutorielsService;
import SoutenanceBackend.soutenance.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/legumefruit")
@CrossOrigin(origins = "http://localhost:8100")
public class LegumesFruitsController {



    private LegumesFruitsService legumesFruitsService;
    private LegumesFruitsRepository legumesFruitsRepository;

    private TypeLegumeFruitRepository typeLegumeFruitRepository;
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private TutorielsService tutorielsService;

    private TutorielsRepository tutorielsRepository;
    @Autowired
    private EtapeRepository etapeRepository;


    @GetMapping("RecupererIdLegumeFruit/{idlegumefruit}")
    public LegumesFruits RecupererId(@PathVariable("idlegumefruit") Long idlegumefruit){
        return legumesFruitsService.RecupereIdLegume(idlegumefruit);
    }

    public LegumesFruitsController(LegumesFruitsService legumesFruitsService, LegumesFruitsRepository legumesFruitsRepository, TypeLegumeFruitRepository typeLegumeFruitRepository, UserRepository userRepository, TutorielsService tutorielsService, TutorielsRepository tutorielsRepository) {
     //   this.legumesFruits = legumesFruits;
        this.legumesFruitsService = legumesFruitsService;
        this.legumesFruitsRepository = legumesFruitsRepository;
        this.typeLegumeFruitRepository = typeLegumeFruitRepository;
        this.userRepository = userRepository;
        this.tutorielsService = tutorielsService;
        this.tutorielsRepository = tutorielsRepository;
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

    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER La liste des LEGUMES°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireLegumes")
    public List<LegumesFruits> afficherlistelegume(){
        return legumesFruitsRepository.ListeLegumes();

    }
    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER La liste des FRUITS°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireFruits")
    public List<LegumesFruits> afficherlistefruit(){
        return legumesFruitsRepository.ListeFruits();

    }

    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER TUTORIEL ET LEGUME OU FRUIT°°°°°°°°°°°°°°°°°°°°°
//    @DeleteMapping("/supprimerlegumesFruits/{id}/{idTuto}")
//    public String delete(@PathVariable Long id,@PathVariable Long idTuto){
////        tutorielsService.supprimer(idTuto);
////         legumesFruitsService.supprimer(id);
//        LegumesFruits legumesFruits1 = new LegumesFruits();
//        legumesFruitsService.supprimer(id,tutorielsService.supprimer(idTuto), legumesFruits1);
//        return "legume ou fruit et le tutoriel associer avec succès";
//    }

    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER UN LEGUME OU FRUIT AINSI QUE LE TUTORIEL (cascading ajouter au niveau du model legumeFruit permet de le faire)°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerlegumesFruits/{id}")
    public String delete(@PathVariable Long id){
        return legumesFruitsService.supprimer(id);
    }
    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER UN LEGUME OU FRUIT°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierLegumesFruits/{id}")
    public LegumesFruits update(@PathVariable Long id, @RequestBody LegumesFruits legumesFruits){
       return legumesFruitsService.modifier(id, legumesFruits);
    }






    //    MODIFIER AJOUT LEGUME FRUIT++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @PostMapping("/Ajouterajoutfruilegume/{type}/{iduser}")
    public Object ajoutfruilegume(@Param("nom") String nom,
                                  @Param("description") String description,
                                  @Param("arrosage") String arrosage,
                                  @Param("periodeNormal") String periodeNormal,
                                  @Param("dureeFloraisaon") String dureeFloraisaon,
                                  @Param("file") MultipartFile file,
                                  @Param("titre") String titre,
                                  @Param("espacementEntreGraine") String espacementEntreGraine,
                                  @PathVariable("type") String type,
                                  @PathVariable("iduser") Long iduser,
                                  @Param("etapes") String etapes,@Param("file1") MultipartFile  file1) throws JsonProcessingException {

        List<String> etapesStringList=new ArrayList<>();
        List<Etape> etapeDTOList=new ArrayList<>();
        etapesStringList= List.of(etapes.split(";"));

        ObjectMapper mapper= new ObjectMapper();



        // Créer l'objet LegumesFruits
        LegumesFruits legumesFruits1 = new LegumesFruits();
        legumesFruits1.setNom(nom);
        legumesFruits1.setArrosage(arrosage);
        legumesFruits1.setDureeFloraisaon(dureeFloraisaon);
        legumesFruits1.setDescription(description);
        legumesFruits1.setPeriodeNormal(periodeNormal);

        // Vérifier si le LegumesFruits n'existe pas déjà
        if(legumesFruitsRepository.findByNom(nom) == null){

            // Enregistrer la photo
            String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
            legumesFruits1.setPhoto(Image.save(file, nomfile));

            // Récupérer le type de légume/fruits et l'utilisateur associé
            legumesFruits1.setTypeLegumeFruit(typeLegumeFruitRepository.findByType(type));
            User user = userRepository.findById(iduser).get();
            legumesFruits1.setUser(user);

            // Créer l'objet Tutoriels
            Tutoriels tutoriels1 = new Tutoriels();
            tutoriels1.setTitre(titre);
            tutoriels1.setEspacementEntreGraine(espacementEntreGraine);

            // Enregistrer le tutoriel
            legumesFruits1.setTutoriels(tutorielsService.creer(tutoriels1));

            // Enregistrer les étapes

            for (String etape :
                    etapesStringList) {

                Etape etape1=mapper.readValue(etape, Etape.class);
                etape1.setTutoriels(tutoriels1);
                String photo = StringUtils.cleanPath(file1.getOriginalFilename());
                etape1.setPhoto(Image.save(file1, photo));
                etapeRepository.save(etape1);
            }
            /*for (EtapeDTO etapeDTO : etapesDTO){
                Etape etape = new Etape();
                etape.setEtape(etapeDTO.getEtape());
                    etape.setPhoto(etapeDTO.getPhoto());
                etape.setTutoriels(tutoriels1);
                etapeRepository.save(etape);
            }*/

            // Enregistrer le LegumesFruits
            legumesFruitsService.creer(legumesFruits1);

            return "Légume ou fruit ajouté avec succès";
        } else {
            return "Légume ou fruit existe déjà";
        }
    }









}
