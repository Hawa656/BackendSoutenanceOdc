package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Notifications;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.NotificationsRepository;
import SoutenanceBackend.soutenance.services.NotificationsService;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin(origins = "http://localhost:8100")
public class NotificationsController {
    private NotificationsService notificationsService;
    private NotificationsRepository notificationsRepository;
    private UserService userService;

    public NotificationsController(NotificationsService notificationsService, NotificationsRepository notificationsRepository, UserService userService) {
        this.notificationsService = notificationsService;
        this.notificationsRepository = notificationsRepository;
        this.userService = userService;
    }

    @GetMapping("/lireNotification")
    public List<Notifications> read(){
        return notificationsService.lire();

    }
    //°°°°°°°°°°°°°°AFFICHER La liste des NOTIFICATION POUR LE USER CONNECTER QUI A CRERR LA TACHE POUR LA NOTIFICATION°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireNotifUserConecter/{idNot}")
    public ResponseEntity<?> afficherlireNotifUserConecter(@PathVariable Long idNot){



        List<Notifications> notificationsListes = notificationsService.lireNotifUserConecter(idNot);

        if (notificationsListes.isEmpty()){
            return new ResponseEntity<>("Auccune notification pour le moment", HttpStatus.NOT_FOUND);
        }

        System.out.println("ppppppppppppppppppppp"+ notificationsListes);

        return new ResponseEntity<>(notificationsListes, HttpStatus.OK);

    }
}
