package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.Conseils;
import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Repository.ConseilsRepository;
import SoutenanceBackend.soutenance.services.ConseilsService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConseilsServiceImpl implements ConseilsService {
    private ConseilsRepository conseilsRepository;

    public ConseilsServiceImpl(ConseilsRepository conseilsRepository) {
        this.conseilsRepository = conseilsRepository;
    }

    @Override
    public Conseils creer(Conseils conseils) {
        return conseilsRepository.save(conseils);
    }

    @Override
    public List<Conseils> lire() {

        return conseilsRepository.findAll();
    }

    @Override
    public Conseils modifier(Long id, Conseils conseils) {

        return conseilsRepository.findById(id)
                .map(c ->{
                    c.setTitre(conseils.getTitre());
                    c.setDescription(conseils.getDescription());
                    c.setPhoto(conseils.getPhoto());
                    return conseilsRepository.save(c);
                } ).orElseThrow(() -> new RuntimeException("conseil non trouve"));
    }

    @Override
    public String supprimer(Long id) {

         conseilsRepository.deleteById(id);
         return "supprimer avec succès";
    }

    @Override
    public Conseils RecupereIdConseil(Long idConseil) {
        return conseilsRepository.findById(idConseil).get();
    }
}
