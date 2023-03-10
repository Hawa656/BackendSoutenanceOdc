package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Conseils;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.ConseilsRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.images.Image;
import SoutenanceBackend.soutenance.images.Video;
import SoutenanceBackend.soutenance.services.ConseilsService;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/conseil")
@CrossOrigin(origins = "http://localhost:8100")
public class ConseilController {
    private ConseilsRepository conseilsRepository;
    private UserRepository userRepository;
    private ConseilsService conseilsService;

    public ConseilController(ConseilsRepository conseilsRepository, UserRepository userRepository, ConseilsService conseilsService) {
        this.conseilsRepository = conseilsRepository;
        this.userRepository = userRepository;
        this.conseilsService = conseilsService;
    }

    @GetMapping("RecupererIdConseil/{idConseil}")
    public Conseils RecupererId(@PathVariable("idConseil") Long idConseil){
        return conseilsService.RecupereIdConseil(idConseil);
    }

    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER UN LEGUME OU FRUIT°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireConseil")
    public List<Conseils> read(){
        return conseilsService.lire();

    }
    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER UN LEGUME OU FRUIT°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerConseil/{idConseil}")
    public String delete(@PathVariable Long idConseil){
        return conseilsService.supprimer(idConseil);
    }
    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER UN LEGUME OU FRUIT°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierConseil/{idConseil}")
    public Conseils update(@PathVariable Long idConseil, @RequestBody Conseils conseils){
        return conseilsService.modifier(idConseil, conseils);
    }

    @PostMapping("/Ajouter/{idUser}")
    public String create(@Param("titre") String titre,
                        @Param("description") String description,
                         @Param("file") MultipartFile file,
                         @PathVariable("idUser") Long idUser
                         ) throws IOException {
        Conseils conseils1 = new Conseils();
        conseils1.setTitre(titre);
        conseils1.setDescription(description);

        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        conseils1.setPhoto(Image.save(file, nomfile));

        User user = userRepository.findById(idUser).get();
        conseils1.setUser(user);

//        conseils1.setPhoto(Image.save(file, conseils1.getPhoto()));
        conseilsService.creer(conseils1);
        return "conseil ajouter avec succès";
}
}
