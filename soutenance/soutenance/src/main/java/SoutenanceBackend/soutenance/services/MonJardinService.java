package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.MonJardin;


import java.util.List;

public interface MonJardinService {
    MonJardin creer(MonJardin monJardin);
    List<MonJardin> lire();
    MonJardin modifier(Long id, MonJardin monJardin);
    String supprimer(Long id);
}
