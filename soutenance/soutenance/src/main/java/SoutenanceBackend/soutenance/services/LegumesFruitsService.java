package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.LegumesFruits;

import java.util.List;

public interface LegumesFruitsService {
    LegumesFruits creer(LegumesFruits legumesFruits);
    List<LegumesFruits> lire();
    LegumesFruits modifier(Long id, LegumesFruits legumesFruits);
    String supprimer(Long id);
}
