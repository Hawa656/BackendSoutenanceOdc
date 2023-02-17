package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.Questions;
import SoutenanceBackend.soutenance.Repository.QuestionsRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.QuesionsService;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuesionsServiceImpl implements QuesionsService {
    private QuestionsRepository questionsRepository;
    private UserRepository userRepository;


    public QuesionsServiceImpl(QuestionsRepository questionsRepository, UserRepository userRepository
                               ) {
        this.questionsRepository = questionsRepository;
        this.userRepository = userRepository;

    }

    @Override
    public Questions creer(Questions questions) {
        return questionsRepository.save(questions);
    }

    @Override
    public List<Questions> lire() {

        return questionsRepository.findAll();
    }

    @Override
    public Questions modifier(Long id, Questions questions) {

        return questionsRepository.findById(id)
                .map(q ->{
                    q.setQuestion(questions.getQuestion());

                    return questionsRepository.save(q);
                } ).orElseThrow(() -> new RuntimeException("question non trouve"));
    }

    @Override
    public String supprimer(Long id) {

         questionsRepository.deleteById(id);
        return "Question supprimer avec sucès";
    }

    @Override
    public Questions RecupereIdQuestion(Long idQuestion) {
        return questionsRepository.findById(idQuestion).get();
    }
}
