package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `notifications`,users,tache WHERE users.id=notifications.user_id AND tache.id=notifications.tache_id AND users.id=:id", nativeQuery = true)
    List<Notifications> ListeNotifications(@PathVariable Long id);

}
