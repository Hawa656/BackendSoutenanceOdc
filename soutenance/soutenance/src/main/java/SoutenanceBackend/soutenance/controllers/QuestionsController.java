package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.LegumesFruits;
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
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = {"http://localhost:8100","http://localhost:4200"})
public class QuestionsController {
    private QuesionsService quesionsService;
    private QuestionsRepository questionsRepository;
    private UserRepository userRepository;

    public QuestionsController(QuesionsService quesionsService, QuestionsRepository questionsRepository, UserRepository userRepository) {
        this.quesionsService = quesionsService;
        this.questionsRepository = questionsRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("RecupererIdQuestion/{idQuestion}")
    public Questions RecupererId(@PathVariable("idQuestion") Long idQuestion){
        return quesionsService.RecupereIdQuestion(idQuestion);
    }

    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER UNE QUESTION°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireQuestion")
    public List<Questions> read(){
        return quesionsService.lire();

    }
    //°°°°°°°°°°°°°°°°°°°°°°LISTES DES QUESTIONS NON REPONDU°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/ListeDesQuestionsNonRepondu")
    public List<Questions> ListeDesQuestionsNonRepondu(){
        return questionsRepository.ListeDesQuestionsNonRepondu();

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
    //@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/ajouterQuestion/{id_user}")
    public Object create(@PathVariable User id_user,
                         @Param("question") String question) throws IOException {
        Questions questions1 = new Questions();
        questions1.setQuestion(question);

        questions1.setUser(id_user);
        questions1.setTimestamp(LocalDateTime.now()); // Ajouter la date et l'heure actuelles

        quesionsService.creer(questions1);
        return "Question ajoutée avec succès";
    }

}
