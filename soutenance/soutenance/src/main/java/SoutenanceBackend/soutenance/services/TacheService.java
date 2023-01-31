package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Tache;

import java.util.Date;
import java.util.List;

public interface TacheService {
    Tache creer(Tache tache);
    List<Tache> lire();
    Tache modifier(Long id, Tache tache);
    String supprimer(Long id);

    Tache getDateExit(Date date);
}
