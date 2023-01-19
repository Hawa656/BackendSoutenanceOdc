package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.EperiodeNormal;
import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Repository.LegumesFruitsRepository;
import SoutenanceBackend.soutenance.services.LegumesFruitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegumesFruitsServiceImpl implements LegumesFruitsService {

    private LegumesFruitsRepository legumesFruitsRepository;

    public LegumesFruitsServiceImpl(LegumesFruitsRepository legumesFruitsRepository) {
        this.legumesFruitsRepository = legumesFruitsRepository;
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
                .map(lf ->{
                    lf.setNom(legumesFruits.getNom());
                    lf.setDescription(legumesFruits.getDescription());
                    lf.setPhoto(legumesFruits.getPhoto());
                    lf.setDuréeFloraisaon(legumesFruits.getDuréeFloraisaon());
                    lf.setPeriodeNormal(EperiodeNormal.Mars);
                    lf.setArrosage(legumesFruits.getArrosage());

                    return legumesFruitsRepository.save(lf);
                } ).orElseThrow(() -> new RuntimeException("legume ou fruit non trouve"));

    }

    @Override
    public String supprimer(Long id) {
            legumesFruitsRepository.deleteById(id);
        return "supprimer avec succès";
    }
}
