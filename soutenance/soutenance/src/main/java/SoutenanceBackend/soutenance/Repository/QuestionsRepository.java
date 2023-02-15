package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    Questions findQuestionById(Long idQuestion);
//===============LISTES DES QUESTIONS NON REPONDU=========================
    @Modifying
    @Transactional
    @Query(value = "SELECT *\n" +
            "FROM questions\n" +
            "WHERE id NOT IN (\n" +
            "    SELECT DISTINCT questions_id\n" +
            "    FROM reponses\n" +
            ");\n",nativeQuery = true)
    List<Questions> ListeDesQuestionsNonRepondu();
}
