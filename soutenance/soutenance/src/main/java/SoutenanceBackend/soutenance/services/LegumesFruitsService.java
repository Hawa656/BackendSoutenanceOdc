package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.LegumesFruits;

import java.util.List;
import java.util.Optional;

public interface LegumesFruitsService {
    LegumesFruits creer(LegumesFruits legumesFruits);
    List<LegumesFruits> lire();

    LegumesFruits modifier(Long id, LegumesFruits legumesFruits);
    String supprimer(Long id);

    Optional<LegumesFruits> RecupereIdLegume(Long idLegumeFruit);

    //==========================legumeFruit par Id avec tuto et etape associé========================================================
    List<Object[]> getLegumesFruitsWithTutorielAndEtapes(Long id);

    // List<Object[]> getLegumesFruitsWithTutorielAndEtapes(Long id);
}
