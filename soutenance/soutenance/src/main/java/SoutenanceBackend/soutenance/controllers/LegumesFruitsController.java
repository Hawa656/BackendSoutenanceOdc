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
   /* @PostMapping("/Ajouterajoutfruilegume/{type}/{iduser}")
    public Object ajoutfruilegume(@Param("nom") String nom,
                                  @Param("description") String description,
                                  @Param("arrosage") String arrosage,
                                  @Param("periodeNormal") String periodeNormal,
                                  @Param("dureeFloraisaon") String dureeFloraisaon,
                                  @Param("file") MultipartFile file,
                                  @Param("titre") String titre,
                                  @Param("espacementEntreGraine") String espacementEntreGraine,
                                  @PathVariable("type") String type,
                                  @PathVariable("iduser") Long iduser) throws JsonProcessingException {


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

            // Enregistrer le LegumesFruits
            legumesFruitsService.creer(legumesFruits1);

            return "Légume ou fruit ajouté avec succès";
        } else {
            return "Légume ou fruit existe déjà";
        }
    }

*/


    //Modifier LegumeFruitEtTuto


    //  ++++++++++++  MODIFIER LEGUMEFRUIT ET TUTORIEL++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    /*@PutMapping("modifierLegumesFruitsEtTuto/{id}")
    public LegumesFruits update(@PathVariable Long id, @RequestBody LegumesFruits legumesFruits){

        LegumesFruits legumesFruits1 = new LegumesFruits();


        Tutoriels tutoriels1 = new Tutoriels();
        tutoriels1.
        return legumesFruitsService.modifier(id, legumesFruits);
    }*/

//    MODIFIER AJOUT LEGUME FRUIT++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    /*@PostMapping("/Ajouterajoutfruilegume/{type}/{iduser}")
    public Object ajoutfruilegume(@Param("nom") String nom,
                                  @Param("description") String description,
                                  @Param("arrosage") String arrosage,
                                  @Param("periodeNormal") String periodeNormal,
                                  @Param("dureeFloraisaon") String dureeFloraisaon,
                                  @Param("file") MultipartFile file,
                                  @Param("titre") String titre,
                                  *//*@Param("etape1") String etape1,
                                  @Param("etape2") String etape2,
                                  @Param("etape3") String etape3,
                                  @Param("etape4") String etape4,*//*
                                  //@Param("etatDeLaTerre") String etatDeLaTerre,
                                  @Param("espacementEntreGraine") String espacementEntreGraine,
                                  @PathVariable("type") String type,
                                  @PathVariable("iduser") Long iduser){


        LegumesFruits legumesFruits1 = new LegumesFruits();
        legumesFruits1.setNom(nom);
        legumesFruits1.setArrosage(arrosage);
        legumesFruits1.setDureeFloraisaon(dureeFloraisaon);
        legumesFruits1.setDescription(description);
//        Boolean rtrr = Boolean.valueOf(semis);
//        Boolean ettwy = Boolean.valueOf(bouture);

        legumesFruits1.setPeriodeNormal(periodeNormal);

        if(legumesFruitsRepository.findByNom(nom) == null){

            String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
            legumesFruits1.setPhoto(Image.save(file, nomfile));



            legumesFruits1.setTypeLegumeFruit(typeLegumeFruitRepository.findByType(type));

            //recuperation de l'id de l'utilisateur connecté
            User user = userRepository.findById(iduser).get();
            legumesFruits1.setUser(user);



            Tutoriels tutoriels1 = new Tutoriels();
            tutoriels1.setTitre(titre);
//            //tutoriels1.setEtape1(etape1);
            //tutoriels1.setEtatDeLaTerre(etatDeLaTerre);
           *//* tutoriels1.setEtape2(etape2);
            tutoriels1.setEtape3(etape3);
            tutoriels1.setEtape4(etape4);*//*
            tutoriels1.setEspacementEntreGraine(espacementEntreGraine);
            //Pour enregistrer le tutoriel
            legumesFruits1.setTutoriels(tutorielsService.creer(tutoriels1));


            legumesFruitsService.creer(legumesFruits1);
            List<Etape> etapes = new ArrayList<>();
            for (Etape etape : etapes){
                etape.setTutoriels(tutoriels1);
                etapeRepository.save(etape);
            }
            return "Legume ou fruit ajouter avec succès";
        }
        else{
            return "Legume ou fruit existe déja";

        }


    }*/
    //   FIN MODIFIER AJOUT LEGUME FRUIT++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //°°°°°°°°°°°°°°°°°°°°°°AJOUTER UN LEGUME OU FRUIT ET LE TUTORIEL °°°°°°°°°°°°°°°°°°°°°
    //@PreAuthorize(" hasRole('ADMIN')")
    @PostMapping("/Ajouterajoutfruilegume/{type}/{iduser}")
    public Object ajoutfruilegume(@Param("nom") String nom,
                                  @Param("description") String description,
                                  @Param("arrosage") String arrosage,
                                  @Param("periodeNormal") String periodeNormal,
                                  @Param("dureeFloraisaon") String dureeFloraisaon,
                                  @Param("file") MultipartFile file,
                                  @PathVariable("type") String type,
                                  @PathVariable("iduser") Long iduser){


        LegumesFruits legumesFruits1 = new LegumesFruits();
        legumesFruits1.setNom(nom);
        legumesFruits1.setArrosage(arrosage);
        legumesFruits1.setDureeFloraisaon(dureeFloraisaon);
        legumesFruits1.setDescription(description);
        legumesFruits1.setPeriodeNormal(periodeNormal);

        if(legumesFruitsRepository.findByNom(nom) == null){

            String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
            legumesFruits1.setPhoto(Image.save(file, nomfile));



            legumesFruits1.setTypeLegumeFruit(typeLegumeFruitRepository.findByType(type));

            //recuperation de l'id de l'utilisateur connecté
            User user = userRepository.findById(iduser).get();
            legumesFruits1.setUser(user);

            legumesFruitsService.creer(legumesFruits1);
            return "Legume ajouter avec succès";
        }
        else{
            return "Legume existe déja";

        }


    }




    //°°°°°°°°°°°°°°°°°°°°°°AJOUTER UN LEGUME OU FRUIT°°°°°°°°°°°°°°°°°°°°°
    /*@PostMapping("/Ajouter/{id_user}/{id_type}/{id_tutoriel}/{id_video}")
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
        legumesFruits1.setDescription(description);


        if(periodeNormal.equals("Janvier")){
            legumesFruits1.setPeriodeNormal(EperiodeNormal.Janvier);
        }else if(periodeNormal.equals("Fevrier")){
            legumesFruits1.setPeriodeNormal(EperiodeNormal.Fevrier);
        }else if(periodeNormal.equals("Mars")){
            legumesFruits1.setPeriodeNormal(EperiodeNormal.Mars);
        }else if(periodeNormal.equals("Avril")){
            legumesFruits1.setPeriodeNormal(EperiodeNormal.Avril);
        }else if(periodeNormal.equals("Mai")){
            legumesFruits1.setPeriodeNormal(EperiodeNormal.Mai);
        }else if(periodeNormal.equals("Juin")){
            legumesFruits1.setPeriodeNormal(EperiodeNormal.Juin);
        }else if(periodeNormal.equals("Juillet")){
            legumesFruits1.setPeriodeNormal(EperiodeNormal.Juillet);
        }else if(periodeNormal.equals("Août")){
            legumesFruits1.setPeriodeNormal(EperiodeNormal.Août);
        }else if(periodeNormal.equals("Septembre")){
            legumesFruits1.setPeriodeNormal(EperiodeNormal.Septembre);
        }else if(periodeNormal.equals("Octobre")){
            legumesFruits1.setPeriodeNormal(EperiodeNormal.Octobre);
        }else if(periodeNormal.equals("Novembre")){
            legumesFruits1.setPeriodeNormal(EperiodeNormal.Novembre);
        }else {periodeNormal.equals("Decembre");
            legumesFruits1.setPeriodeNormal(EperiodeNormal.Decembre);
        }



        if(legumesFruitsRepository.findByNom(nom) == null){


            legumesFruits1.setPhoto(Image.save(file, legumesFruits1.getPhoto()));

            legumesFruits1.setTutoriels(id_tutoriel);
            //legumesFruits1.setVideo(id_video);
            legumesFruits1.setTypeLegumeFruit(id_type);
            legumesFruits1.setUser(id_user);
            legumesFruitsService.creer(legumesFruits1);
            return "Legume ajouter avec succès";
        }
        else{
            return "Legume existe déja";

        }


    }*/






}
