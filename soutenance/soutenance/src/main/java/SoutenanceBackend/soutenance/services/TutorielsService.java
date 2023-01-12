package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Tutoriels;

import java.util.List;

public interface TutorielsService {
    Tutoriels creer(Tutoriels tutoriels);
    List<Tutoriels> lire();
    Tutoriels modifier(Long id, Tutoriels tutoriels);
    String supprimer(Long id);
}
