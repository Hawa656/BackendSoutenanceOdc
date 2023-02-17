package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Conseils;
import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.Questions;

import java.util.List;

public interface QuesionsService {
    Questions creer(Questions questions);
    List<Questions> lire();
    Questions modifier(Long id, Questions questions);
    String supprimer(Long id);
    Questions RecupereIdQuestion(Long idQuestion);
}
