package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Notification;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.services.NotificationService;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private NotificationService notificationService;
    private UserService userService;

    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER UNE NOTIFICATION°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireNotification")
    public List<Notification> read(){
        return notificationService.lire();

    }
    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER UNE NOTIFICATION°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerNotification/{id_Notification}")
    public String delete(@PathVariable Long id_Notification){

        return notificationService.supprimer(id_Notification);
    }
    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER UNE NOTIFICATION°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierNotification/{id_Notification}")
    public Notification update(@PathVariable Long id_Notification, @RequestBody Notification notification){
        return notificationService.modifier(id_Notification,notification);
    }
    //°°°°°°°°°°°°°°°°°°°°°°AJOUTER UNE NOTIFICATION°°°°°°°°°°°°°°°°°°°°°
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/ajouterNotification/{id}")
    public Object create(@RequestBody Notification notification,@PathVariable("id") User id) throws IOException {
//        Notification notification1 = new Notification();
//        notification1.setTitre();
//        notification1.setDate(date);


        notification.setUser(id);
        notificationService.creer(notification);
        return "notification ajouter avec succès";
    }
}
