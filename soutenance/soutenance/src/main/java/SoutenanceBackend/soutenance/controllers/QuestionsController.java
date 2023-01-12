package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Questions;
import SoutenanceBackend.soutenance.Models.Reponses;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.QuestionsRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.QuesionsService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {
    private QuesionsService quesionsService;
    private QuestionsRepository questionsRepository;
    private UserRepository userRepository;

    public QuestionsController(QuesionsService quesionsService, QuestionsRepository questionsRepository, UserRepository userRepository) {
        this.quesionsService = quesionsService;
        this.questionsRepository = questionsRepository;
        this.userRepository = userRepository;
    }

    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER UNE QUESTION°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireQuestion")
    public List<Questions> read(){
        return quesionsService.lire();

    }
    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER UNE QUESTION°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerQuestion/{id_question}")
    public String delete(@PathVariable Long id_question){

        return quesionsService.supprimer(id_question);
    }
    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER UNE QUESTION°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierQuestion/{id_user}")
    public Questions update(@PathVariable Long id_user, @RequestBody Questions questions){
        return quesionsService.modifier(id_user,questions);
    }
    //°°°°°°°°°°°°°°°°°°°°°°AJOUTER UNE QUESTION°°°°°°°°°°°°°°°°°°°°°
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/ajouterQuestion/{id_user}")
    public Object create(@PathVariable User id_user,
            @Param("question") String question) throws IOException {
        Questions questions1 = new Questions();
        questions1.setQuestion(question);

        questions1.setUser(id_user);

        quesionsService.creer(questions1);
        return "Question ajouter avec succès";
    }
}
