package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.TypeLegumeFruit;
import SoutenanceBackend.soutenance.Models.Video;
import SoutenanceBackend.soutenance.Repository.TypeLegumeFruitRepository;

import java.util.List;

public interface TypeLegumeFruitService {
    TypeLegumeFruit creer(TypeLegumeFruit typeLegumeFruit);
    List<TypeLegumeFruit> lire();
    TypeLegumeFruit modifier(Long id, TypeLegumeFruit typeLegumeFruit);
    String supprimer(Long id);
}
