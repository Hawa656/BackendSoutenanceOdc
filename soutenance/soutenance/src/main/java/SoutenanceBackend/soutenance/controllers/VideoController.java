package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.LegumesFruitsRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.Repository.VideoRepository;
import SoutenanceBackend.soutenance.images.Video;
import SoutenanceBackend.soutenance.services.UserService;
import SoutenanceBackend.soutenance.services.VideoService;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/video")
@CrossOrigin(origins = "http://localhost:8100")
public class VideoController {
    private VideoService videoService;
    private UserService userService;

    private VideoRepository videoRepository;

    private UserRepository userRepository;
    private final LegumesFruitsRepository legumesFruitsRepository;
    //private VideoRepository videoRepository;

    public VideoController(VideoService videoService, UserService userService, VideoRepository videoRepository,
                           LegumesFruitsRepository legumesFruitsRepository) {
        this.videoService = videoService;
        this.userService = userService;
        //this.videoRepository = videoRepository;

        this.videoRepository = videoRepository;
        this.legumesFruitsRepository = legumesFruitsRepository;
    }

    //°°°°°°°°°°°°°°°°°°°°°°FILTRER LES VIDEOS PAR LEGUME ET FRUIT°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/listeVideoLegume/{abasse}")
        public List<SoutenanceBackend.soutenance.Models.Video> filtrerParLegume(@PathVariable String abasse){
        return videoRepository.listVideoLegumes(abasse);
    }

    //°°°°°°°°°°°°°°°°°°°°°°FILTRER LES VIDEOS PAR FRUIT°°°°°°°°°°°°°°°°°°°°°
   /* @GetMapping("/listeVideoFruit")
    public List<SoutenanceBackend.soutenance.Models.Video> filtrerParFruit(){
        return videoRepository.listVideoFruits();
    }*/

    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER UNE VIDEO°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireVideo")
    public List<SoutenanceBackend.soutenance.Models.Video> read(){
        return videoService.lire();
    }


    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER UNE VIDEO°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/videoParLegume/{legumesFruits}")
    public Object reads(LegumesFruits legumesFruits){
        legumesFruits.getTypeLegumeFruit();
         videoRepository.findByLegumesFruits(legumesFruits);
        return   legumesFruits.getTypeLegumeFruit();
    }

    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER UNE VIDEO°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerVideo/{id}")
    public String delete(@PathVariable Long id){

        if (!videoRepository.existsById(id)){
            return "cette video n'existe pas vous ne pouvez donc pas la supprimer";
        }else {
            return videoService.supprimer(id);
        }

    }
    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER UNE VIDEO°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierVideo/{id}")
    public SoutenanceBackend.soutenance.Models.Video update(@PathVariable Long id, @RequestBody SoutenanceBackend.soutenance.Models.Video video){
        return videoService.modifier(id, video);
    }



    //°°°°°°°°°°°°°°°°°°°°°°AJOUTER UNE VIDEO°°°°°°°°°°°°°°°°°°°°°
    @PostMapping("/Ajouter/{idLegumeFruit}")
    public String ajout(@RequestParam(value = "file")MultipartFile file,
                        @PathVariable long idLegumeFruit,
                        @RequestParam(value = "videorecu") String videorecu) throws IOException {
    //recuperation de l
        String nomvideo = StringUtils.cleanPath(file.getOriginalFilename());
        //Image.save(file, );
        //Enregistrement de la la video
        Video.save(file, nomvideo);

        //video.setUser(userRepository.findById(id).get());
        SoutenanceBackend.soutenance.Models.Video video = new JsonMapper().readValue(videorecu, SoutenanceBackend.soutenance.Models.Video.class);
        video.setVideo(nomvideo);
        //recuperation de l'id de l'utilisateur connecté
        User user = userService.hawa();
        video.setUser(user);
        video.setLegumesFruits(new LegumesFruits(idLegumeFruit));

        try {
            videoService.creer(video);

            return "vidéo ajouter avec succès";
        } catch (DataIntegrityViolationException dive) {
            return "Pas de fruit ou legume correspondant";
        }

        }


    //°°°°°°°°°°°°°°°°°°°°°°RECUPERATION DE VIDEO PAR IDLEGUMEFRUIT°°°°°°°°°°°°°°

    
    // Le probleme est que legumeFruit est lier à typeLegumeFruit qui
    // prend que 2 variable fruit ou legume, et video est lier à legumeFruit or on a besoin
    // d'afficher la video d'un legumeFruit donner°°°°°°°
    //°°°°°°°°CETTE METHODE RETOURNE LES INFO SUR LEGUMESFRUITS, VIDEO ,TUTORIELS
    @GetMapping("/videoparIdLegumeFruit/{id_legumesFruits}")
    public SoutenanceBackend.soutenance.Models.Video reads(@PathVariable("id_legumesFruits") Long id_legumesFruits){
        LegumesFruits legumesFruits = legumesFruitsRepository.findById(id_legumesFruits).get();
        return videoRepository.findByLegumesFruits(legumesFruits);
    }

}

