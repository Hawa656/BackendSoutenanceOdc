package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.Etape;
import SoutenanceBackend.soutenance.Repository.EtapeRepository;
import SoutenanceBackend.soutenance.services.EtapeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EtapeServiceImpl implements EtapeService {
    private EtapeRepository etapeRepository;

    public EtapeServiceImpl(EtapeRepository etapeRepository) {
        this.etapeRepository = etapeRepository;
    }

    @Override
    public Etape creer(Etape etape) {
        return etapeRepository.save(etape);
    }

    @Override
    public List<Etape> lire() {
        return etapeRepository.findAll();
    }

    @Override
    public Etape modifier(Long id, Etape etape) {
        return null;
    }

    @Override
    public String supprimer(Long id) {
        return null;
    }
}
