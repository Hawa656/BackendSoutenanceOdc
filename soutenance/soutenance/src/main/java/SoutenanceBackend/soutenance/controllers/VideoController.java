package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.Video;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.Repository.VideoRepository;
import SoutenanceBackend.soutenance.images.Image;
import SoutenanceBackend.soutenance.services.UserService;
import SoutenanceBackend.soutenance.services.VideoService;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {
    private VideoService videoService;
    private UserService userService;

    private UserRepository userRepository;
    //private VideoRepository videoRepository;

    public VideoController(VideoService videoService, UserService userService) {
        this.videoService = videoService;
        this.userService = userService;
        //this.videoRepository = videoRepository;

}

    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER UNE VIDEO°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireVideo")
    public List<Video> read(){
        return videoService.lire();

    }
    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER UNE VIDEO°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerVideo/{id}")
    public String delete(@PathVariable Long id){

        return videoService.supprimer(id);
    }
    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER UNE VIDEO°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierVideo/{id}")
    public Video update(@PathVariable Long id, @RequestBody Video video){
        return videoService.modifier(id, video);
    }



    //°°°°°°°°°°°°°°°°°°°°°°AJOUTER UNE VIDEO°°°°°°°°°°°°°°°°°°°°°
    @PostMapping("/Ajouter")
    public String ajout(@RequestParam(value = "file")MultipartFile file,
                        @RequestParam(value = "videorecu") String videorecu) throws IOException {
    //recuperation de l
        String nomvideo = StringUtils.cleanPath(file.getOriginalFilename());
        //Image.save(file, );
        //Enregistrement de la la video
        Image.save(file, nomvideo);

        //video.setUser(userRepository.findById(id).get());
        Video video = new JsonMapper().readValue(videorecu, Video.class);
        video.setVideo(nomvideo);



        videoService.creer(video);

            return "Legume ajouter avec succès";
        }


    }

