package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Etape;
import SoutenanceBackend.soutenance.Models.Tutoriels;

import java.util.List;

public interface EtapeService {
    Etape creer(Etape etape);
    List<Etape> lire();
    Etape modifier(Long id, Etape etape);
    String supprimer(Long id);
}
