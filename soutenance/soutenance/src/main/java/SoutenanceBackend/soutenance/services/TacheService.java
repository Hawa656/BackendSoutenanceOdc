package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Notifications;
import SoutenanceBackend.soutenance.Models.Tache;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TacheService {
    Tache creer(Tache tache);
    List<Tache> lire();
    Tache modifier(Long id, Tache tache);
    String supprimer(Long id);
    List<Tache> lireTawheUserConecter(Long idUser);

    //Tache getDateExit(LocalDate date);
}
