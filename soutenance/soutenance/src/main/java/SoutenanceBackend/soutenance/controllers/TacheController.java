package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.EStatut;
import SoutenanceBackend.soutenance.Models.PostNotification;
import SoutenanceBackend.soutenance.Models.Tache;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.NotificationsRepository;
import SoutenanceBackend.soutenance.Repository.TacheRepository;
import SoutenanceBackend.soutenance.services.TacheService;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.Notification;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/tache")
@CrossOrigin(origins = "http://localhost:8100")
public class TacheController {
    private TacheService tacheService;
    private UserService userService;

    private TacheRepository tacheRepository;
    private NotificationsRepository notificationsRepository;
    private PostNotification postNotification;

    public TacheController(TacheService tacheService, UserService userService, TacheRepository tacheRepository, NotificationsRepository notificationsRepository, PostNotification postNotification) {
        this.tacheService = tacheService;
        this.userService = userService;
        this.tacheRepository = tacheRepository;
        this.notificationsRepository = notificationsRepository;
        this.postNotification = postNotification;
    }





//===========================AJOUTER UNE TACHE
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/ajouttache/{id}")
    public Object creer(@RequestBody Tache tache, @PathVariable("id") User id) throws IOException {
        tache.setUser(id);
        tache.setEStatut(EStatut.valueOf("Encours"));

         tacheService.creer(tache);
        return "Tâche planifiée avec succès";
    }

    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER UNE TÂCHE°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireTache")
    public List<Tache> read(){
        return tacheService.lire();

    }
    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER UNE TÂCHE°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerTache/{id_Notification}")
    public String delete(@PathVariable Long id_Tâche){

        return tacheService.supprimer(id_Tâche);
    }
    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER UNE TÂCHE°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierTache/{id_Tache}")
    public Tache update(@PathVariable Long id_Tache, @RequestBody Tache tache){
        return tacheService.modifier(id_Tache, tache);
    }
    //°°°°°°°°°°°°°°°°°°°°°°AJOUTER UNE TÂCHE°°°°°°°°°°°°°°°°°°°°°
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/ajouterTache/{id}")
    public Object create(@RequestBody Tache tache, @PathVariable("id") User id) throws IOException {
//        Notification notification1 = new Notification();
//        notification1.setTitre();
//        notification1.setDate(date);


        tache.setUser(id);
        tache.setEStatut(EStatut.valueOf("Encours"));

        tacheService.creer(tache);
        return "Tâche planifiée avec succès";
    }


    @PostMapping("/updateDate")
    public Tache updateDate(@RequestBody Tache date){
        Tache tache1 = tacheService.getDateExit(date.getDate());

        Date date1 = new Date();

        if (tache1.getDate().after(date1)){
            tache1.setEStatut(EStatut.valueOf("Terminee"));
            return tacheService.creer(tache1);
        }
        tache1.setEStatut(EStatut.valueOf("Encours"));
        return tacheService.creer(tache1);
    }

}
