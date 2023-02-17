package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Questions;
import SoutenanceBackend.soutenance.Models.Reponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReponsesRepository extends JpaRepository<Reponses, Long> {
    @Query(value = "SELECT * FROM `reponses` WHERE reponses.questions_id=:id", nativeQuery = true)
    List<Reponses> AfficherReponseParQuestion(Long id);
}
