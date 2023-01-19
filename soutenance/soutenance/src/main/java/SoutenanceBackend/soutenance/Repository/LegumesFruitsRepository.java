package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.LegumesFruits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegumesFruitsRepository extends JpaRepository<LegumesFruits, Long> {

    LegumesFruits findByNom(String nom);


}
