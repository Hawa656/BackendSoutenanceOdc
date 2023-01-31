package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Models.Video;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.Repository.VideoRepository;
import SoutenanceBackend.soutenance.images.Image;
import SoutenanceBackend.soutenance.services.UserService;
import SoutenanceBackend.soutenance.services.VideoService;
import com.fasterxml.jackson.databind.json.JsonMapper;
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
    //private VideoRepository videoRepository;

    public VideoController(VideoService videoService, UserService userService, VideoRepository videoRepository) {
        this.videoService = videoService;
        this.userService = userService;
        //this.videoRepository = videoRepository;

        this.videoRepository = videoRepository;
    }

    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER UNE VIDEO°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireVideo")
    public List<Video> read(){
        return videoService.lire();

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
    public Video update(@PathVariable Long id, @RequestBody Video video){
        return videoService.modifier(id, video);
    }



    //°°°°°°°°°°°°°°°°°°°°°°AJOUTER UNE VIDEO°°°°°°°°°°°°°°°°°°°°°
    @PostMapping("/Ajouter/{idLegumeFruit}")
    public String ajout(@RequestParam(value = "file")MultipartFile file,
                        @PathVariable LegumesFruits idLegumeFruit,
                        @RequestParam(value = "videorecu") String videorecu) throws IOException {
    //recuperation de l
        String nomvideo = StringUtils.cleanPath(file.getOriginalFilename());
        //Image.save(file, );
        //Enregistrement de la la video
        Image.save(file, nomvideo);

        //video.setUser(userRepository.findById(id).get());
        Video video = new JsonMapper().readValue(videorecu, Video.class);
        video.setVideo(nomvideo);
        //recuperation de l'id de l'utilisateur connecté
        User user = userService.hawa();
        video.setUser(user);
        video.setLegumesFruits(idLegumeFruit);


        videoService.creer(video);

            return "vidéo ajouter avec succès";
        }


    }

