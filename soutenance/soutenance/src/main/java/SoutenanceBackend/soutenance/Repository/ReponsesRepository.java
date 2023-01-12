package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Questions;
import SoutenanceBackend.soutenance.Models.Reponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponsesRepository extends JpaRepository<Reponses, Long> {
}
