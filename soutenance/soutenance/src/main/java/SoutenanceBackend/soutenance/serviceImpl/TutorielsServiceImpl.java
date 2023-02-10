package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.Tutoriels;
import SoutenanceBackend.soutenance.Repository.TutorielsRepository;
import SoutenanceBackend.soutenance.services.TutorielsService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TutorielsServiceImpl implements TutorielsService {
    private TutorielsRepository tutorielsRepository;

    public TutorielsServiceImpl(TutorielsRepository tutorielsRepository) {
        this.tutorielsRepository = tutorielsRepository;
    }

    @Override
    public Tutoriels creer(Tutoriels tutoriels) {
        return tutorielsRepository.save(tutoriels);
    }

    @Override
    public List<Tutoriels> lire() {

        return tutorielsRepository.findAll();
    }

    @Override
    public Tutoriels modifier(Long id, Tutoriels tutoriels) {

        return tutorielsRepository.findById(id)
                .map(t -> {
                    t.setTitre(tutoriels.getTitre());
                    t.setEtape1(tutoriels.getEtape1());
                    t.setEspacementEntreGraine(tutoriels.getEspacementEntreGraine());
                    t.setEtatDeLaTerre(tutoriels.getEtatDeLaTerre());
                    t.setEtape2(tutoriels.getEtape2());
                    t.setEtape3(tutoriels.getEtape3());
                    //t.setHauteur(tutoriels.getHauteur());
                    return tutorielsRepository.save(t);
                }).orElseThrow(() -> new RuntimeException("legume ou fruit non trouve"));
    }

    @Override
    public String supprimer(Long id) {
        tutorielsRepository.deleteById(id);

        return "supprimer avec succès";
    }
}
