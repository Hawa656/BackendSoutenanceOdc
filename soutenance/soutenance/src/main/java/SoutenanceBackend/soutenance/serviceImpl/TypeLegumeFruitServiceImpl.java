package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.TypeLegumeFruit;
import SoutenanceBackend.soutenance.Repository.TypeLegumeFruitRepository;
import SoutenanceBackend.soutenance.services.TypeLegumeFruitService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeLegumeFruitServiceImpl implements TypeLegumeFruitService {
    private TypeLegumeFruitRepository typeLegumeFruitRepository;

    public TypeLegumeFruitServiceImpl(TypeLegumeFruitRepository typeLegumeFruitRepository) {
        this.typeLegumeFruitRepository = typeLegumeFruitRepository;
    }

    @Override
    public TypeLegumeFruit creer(TypeLegumeFruit typeLegumeFruit) {
        return typeLegumeFruitRepository.save(typeLegumeFruit);
    }

    @Override
    public List<TypeLegumeFruit> lire() {

        return typeLegumeFruitRepository.findAll();
    }

    @Override
    public TypeLegumeFruit modifier(Long id, TypeLegumeFruit typeLegumeFruit) {

        return typeLegumeFruitRepository.findById(id)
                .map(typeLegumeFruit1 -> {
                    typeLegumeFruit1.setType(typeLegumeFruit.getType());
                    return typeLegumeFruitRepository.save( typeLegumeFruit);
                }).orElseThrow(() -> new RuntimeException("type legumefruit non trouve"));


    }

    @Override
    public String supprimer(Long id) {
        typeLegumeFruitRepository.deleteById(id);

        return "Type de legumeFruit supprimer avec succès";
    }
}
