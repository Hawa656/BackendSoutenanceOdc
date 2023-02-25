package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.EStatut;
import SoutenanceBackend.soutenance.Models.Notifications;
import SoutenanceBackend.soutenance.Models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache,Long> {
    List<Tache> findByDateAcitivte(LocalDate date);

    //Tache findByEStatut(String eStatut);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `tache`,users WHERE users.id=tache.user_id", nativeQuery = true)
    List<Tache> ListeTache(@PathVariable Long id);
}
