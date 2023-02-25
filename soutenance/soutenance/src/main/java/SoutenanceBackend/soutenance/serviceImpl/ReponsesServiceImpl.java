package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.Questions;
import SoutenanceBackend.soutenance.Models.Reponses;
import SoutenanceBackend.soutenance.Repository.QuestionsRepository;
import SoutenanceBackend.soutenance.Repository.ReponsesRepository;
import SoutenanceBackend.soutenance.services.ReponsesService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReponsesServiceImpl implements ReponsesService {
    private ReponsesRepository reponsesRepository;
    private final QuestionsRepository questionsRepository;

    public ReponsesServiceImpl(ReponsesRepository reponsesRepository,
                               QuestionsRepository questionsRepository) {
        this.reponsesRepository = reponsesRepository;
        this.questionsRepository = questionsRepository;
    }

    @Override
    public Reponses creer(Reponses reponses) {
        return reponsesRepository.save(reponses);
    }

    @Override
    public List<Reponses> lire() {

        return reponsesRepository.findAll();
    }

    @Override
    public Reponses modifier(Long id, Reponses reponses) {

        return reponsesRepository.findById(id)
                .map(r->{
                    r.setReponse(reponses.getReponse());

                    return reponsesRepository.save(r);
                }).orElseThrow(() -> new RuntimeException("question non trouve"));
    }

    @Override
    public String supprimer(Long id) {
        reponsesRepository.deleteById(id);

        return "reponses supprimer avec succès";
    }
}
