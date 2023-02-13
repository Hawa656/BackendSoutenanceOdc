package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Conseils;
import SoutenanceBackend.soutenance.Models.LegumesFruits;

import java.util.List;

public interface ConseilsService {
    Conseils creer(Conseils conseils);
    List<Conseils> lire();
    Conseils modifier(Long id, Conseils conseils);
    String supprimer(Long id);
    Conseils RecupereIdConseil(Long idConseil);

}
