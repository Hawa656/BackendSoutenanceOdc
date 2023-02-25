package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.EperiodeNormal;
import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.Tutoriels;
import SoutenanceBackend.soutenance.Models.TypeLegumeFruit;
import SoutenanceBackend.soutenance.Repository.LegumesFruitsRepository;
import SoutenanceBackend.soutenance.Repository.TypeLegumeFruitRepository;
import SoutenanceBackend.soutenance.services.LegumesFruitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegumesFruitsServiceImpl implements LegumesFruitsService {

    private LegumesFruitsRepository legumesFruitsRepository;
    private final TypeLegumeFruitRepository typeLegumeFruitRepository;

    public LegumesFruitsServiceImpl(LegumesFruitsRepository legumesFruitsRepository,
                                    TypeLegumeFruitRepository typeLegumeFruitRepository) {
        this.legumesFruitsRepository = legumesFruitsRepository;
        this.typeLegumeFruitRepository = typeLegumeFruitRepository;
    }

    @Override
    public LegumesFruits creer(LegumesFruits legumesFruits) {

        return legumesFruitsRepository.save(legumesFruits);
    }

    @Override
    public List<LegumesFruits> lire() {
        return legumesFruitsRepository.findAll();
    }

    @Override
    public LegumesFruits modifier(Long id, LegumesFruits legumesFruits) {
        return legumesFruitsRepository.findById(id)
                .map(lf -> {
                    lf.setNom(legumesFruits.getNom());
                    lf.setDescription(legumesFruits.getDescription());
                    lf.setPhoto(legumesFruits.getPhoto());
                    lf.setDureeFloraisaon(legumesFruits.getDureeFloraisaon());
                    lf.setPeriodeNormal(legumesFruits.getPeriodeNormal());
                    lf.setArrosage(legumesFruits.getArrosage());

                    if (legumesFruits.getTutoriels() != null) {
                        lf.setTutoriels(legumesFruits.getTutoriels());
                    }
                    if (legumesFruits.getTypeLegumeFruit() != null) {
                        lf.setTypeLegumeFruit(legumesFruits.getTypeLegumeFruit());
                    }

                    return legumesFruitsRepository.save(lf);
                })
                .orElseThrow(() -> new RuntimeException("LegumesFruits non trouvé avec l'ID: " + legumesFruits.getNom()));
    }


    @Override
    public String supprimer(Long id) {
            legumesFruitsRepository.deleteById(id);
        return "supprimer avec succès";
    }

    @Override
    public LegumesFruits RecupereIdLegume(Long idLegumeFruit) {
        return legumesFruitsRepository.findById(idLegumeFruit).get();
    }
}
