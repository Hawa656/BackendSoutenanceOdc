package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Conseils;
import SoutenanceBackend.soutenance.Models.Reponses;

import java.util.List;

public interface ReponsesService {
    Reponses creer(Reponses reponses);
    List<Reponses> lire();
    Reponses modifier(Long id, Reponses reponses);
    String supprimer(Long id);
}
