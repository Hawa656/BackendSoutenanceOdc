package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Tutoriels;
import SoutenanceBackend.soutenance.Models.Video;

import java.util.List;

public interface VideoService {
    Video creer(Video video);
    List<Video> lire();
    Video modifier(Long id, Video video);
    String supprimer(Long id);
}
