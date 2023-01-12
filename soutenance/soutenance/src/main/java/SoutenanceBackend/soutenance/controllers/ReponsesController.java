package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Questions;
import SoutenanceBackend.soutenance.Models.Reponses;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.QuestionsRepository;
import SoutenanceBackend.soutenance.Repository.ReponsesRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.ReponsesService;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reponses")
public class ReponsesController {
    private ReponsesRepository reponsesRepository;
    private ReponsesService reponsesService;
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private QuestionsRepository questionsRepository;

    public ReponsesController(ReponsesRepository reponsesRepository, ReponsesService reponsesService, UserRepository userRepository, QuestionsRepository questionsRepository) {
        this.reponsesRepository = reponsesRepository;
        this.reponsesService = reponsesService;
        this.userRepository = userRepository;
        this.questionsRepository = questionsRepository;
    }

    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER UNE REPONSE°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireReponses{id_question}")
    public List<Reponses> read(){
        return reponsesService.lire();

    }
    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER UNE REPONSE°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerReponses/{id_question}")
    public String delete(@PathVariable Long id_question){

        return reponsesService.supprimer(id_question);
    }
    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER UNE REPONSE°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierReponses/{id_question}")
    public Reponses update(@PathVariable Long id_question, @RequestBody Reponses reponses){
        return reponsesService.modifier(id_question, reponses);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/ajouter/{idquestion}")
    public Object create(
                         @PathVariable("idquestion")  Long idquestion,
                         @Param("reponse") String reponse) throws IOException {
        Reponses reponses1 = new Reponses();
        reponses1.setReponse(reponse);


        Questions idQuestion = questionsRepository.getReferenceById(idquestion);
        reponses1.setQuestions(idQuestion);

        User user = userService.hawa();
        reponses1.setUser(user);
        if (idQuestion==null){
            return "Pas de question coreespondante";
        }
        reponsesService.creer(reponses1);
        return "Rponses ajouter avec succès";
    }
}
