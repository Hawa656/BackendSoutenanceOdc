package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Notification;
import SoutenanceBackend.soutenance.Models.Questions;

import java.util.List;

public interface NotificationService {
    Notification creer(Notification notification);
    List<Notification> lire();
    Notification modifier(Long id, Notification notification);
    String supprimer(Long id);
}
