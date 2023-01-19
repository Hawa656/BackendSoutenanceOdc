package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.MonJardin;
import SoutenanceBackend.soutenance.Repository.MonJardinRepository;
import SoutenanceBackend.soutenance.services.MonJardinService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MonJardinServiceImpl implements MonJardinService {
    private MonJardinRepository monJardinRepository;

    public MonJardinServiceImpl(MonJardinRepository monJardinRepository) {
        this.monJardinRepository = monJardinRepository;
    }

    @Override
    public MonJardin creer(MonJardin monJardin) {
        return monJardinRepository.save(monJardin);
    }

    @Override
    public List<MonJardin> lire() {

        return monJardinRepository.findAll();
    }

    @Override
    public MonJardin modifier(Long id, MonJardin monJardin) {

        return monJardinRepository.findById(id)
                .map(monJardin1 ->{
                    monJardin1.setNom(monJardin.getNom());
                    monJardin1.setPhoto(monJardin.getPhoto());

                    return monJardinRepository.save(monJardin1);
                } ).orElseThrow(() -> new RuntimeException("Votre jardin non trouvé"));
    }

    @Override
    public String supprimer(Long id) {

         monJardinRepository.deleteById(id);
        return "jardin supprimer avec succès";
    }
}
