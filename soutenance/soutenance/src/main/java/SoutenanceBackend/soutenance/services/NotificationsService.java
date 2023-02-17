package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Conseils;
import SoutenanceBackend.soutenance.Models.Notifications;

import java.util.List;

public interface NotificationsService {
    List<Notifications> lire();


    List<Notifications> lireNotifUserConecter(Long idNot);
}
