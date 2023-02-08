package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

}
