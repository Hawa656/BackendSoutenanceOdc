package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.EStatut;
import SoutenanceBackend.soutenance.Models.Tache;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.TacheRepository;
import SoutenanceBackend.soutenance.services.TacheService;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/Tâche")
@CrossOrigin(origins = "http://localhost:8100")
public class TacheController {
    private TacheService tacheService;
    private UserService userService;

    private TacheRepository tacheRepository;

    public TacheController(TacheService tacheService, UserService userService, TacheRepository tacheRepository) {
        this.tacheService = tacheService;
        this.userService = userService;
        this.tacheRepository = tacheRepository;
    }

    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER UNE TÂCHE°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireTâche")
    public List<Tache> read(){
        return tacheService.lire();

    }
    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER UNE TÂCHE°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerTâche/{id_Notification}")
    public String delete(@PathVariable Long id_Tâche){

        return tacheService.supprimer(id_Tâche);
    }
    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER UNE TÂCHE°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierTâche/{id_Tâche}")
    public Tache update(@PathVariable Long id_Tâche, @RequestBody Tache tache){
        return tacheService.modifier(id_Tâche, tache);
    }
    //°°°°°°°°°°°°°°°°°°°°°°AJOUTER UNE TÂCHE°°°°°°°°°°°°°°°°°°°°°
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/ajouterTâche/{id}")
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
