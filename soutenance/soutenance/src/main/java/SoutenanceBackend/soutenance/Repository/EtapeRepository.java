package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Etape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtapeRepository extends JpaRepository<Etape, Long> {
}
