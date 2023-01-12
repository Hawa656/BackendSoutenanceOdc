package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Conseils;
import SoutenanceBackend.soutenance.Repository.ConseilsRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.images.Image;
import SoutenanceBackend.soutenance.services.ConseilsService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/conseil")
public class ConseilController {
    private ConseilsRepository conseilsRepository;
    private UserRepository userRepository;
    private ConseilsService conseilsService;

    public ConseilController(ConseilsRepository conseilsRepository, UserRepository userRepository, ConseilsService conseilsService) {
        this.conseilsRepository = conseilsRepository;
        this.userRepository = userRepository;
        this.conseilsService = conseilsService;
    }

    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER UN LEGUME OU FRUIT°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireConseil{id_user}")
    public List<Conseils> read(){
        return conseilsService.lire();

    }
    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER UN LEGUME OU FRUIT°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerConseil/{id_user}")
    public String delete(@PathVariable Long id_user){
        return conseilsService.supprimer(id_user);
    }
    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER UN LEGUME OU FRUIT°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierConseil/{id_user}")
    public Conseils update(@PathVariable Long id_user, @RequestBody Conseils conseils){
        return conseilsService.modifier(id_user, conseils);
    }

    @PostMapping("/Ajouter")
    public String create(@Param("titre") String titre,
                        @Param("description") String description,
                         @Param("file") MultipartFile file,
                         @Param("photo" )String photo) throws IOException {
        Conseils conseils1 = new Conseils();
        conseils1.setTitre(titre);
        conseils1.setDescription(description);

        conseils1.setPhoto(Image.save(file, conseils1.getPhoto()));
        conseilsService.creer(conseils1);
        return "conseil ajouter avec succès";
}
}
