package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Conseils;
import SoutenanceBackend.soutenance.Models.LegumesFruits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConseilsRepository extends JpaRepository<Conseils, Long> {
}
