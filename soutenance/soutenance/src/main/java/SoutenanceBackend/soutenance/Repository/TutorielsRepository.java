package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.Tutoriels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorielsRepository extends JpaRepository<Tutoriels, Long> {

   Tutoriels findByTitre(String titre);
}
