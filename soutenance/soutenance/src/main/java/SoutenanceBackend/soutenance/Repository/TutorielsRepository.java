package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Etape;
import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.Tutoriels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorielsRepository extends JpaRepository<Tutoriels, Long> {

   Tutoriels findByTitre(String titre);
}
