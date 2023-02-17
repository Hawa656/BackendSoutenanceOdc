package SoutenanceBackend.soutenance.Models;

import SoutenanceBackend.soutenance.Repository.NotificationsRepository;
import SoutenanceBackend.soutenance.Repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Component
public class PostNotification {

    @Autowired
    private TacheRepository tacheRepository;

    @Autowired
    private NotificationsRepository notificationsRepository;

    //@Scheduled(fixedRate = 86400000)
    @Scheduled(fixedRate = 86400000)
    public void createNotificationsForTasksDueToday() {
        LocalDate today = LocalDate.now();
        List<Tache> alltaches = tacheRepository.findAll();
        for (Tache task : alltaches) {
            int days = task.getNbreJour();
            LocalDate dueDate = today.plusDays(days);
            if (task != null) {
                Notifications notifications = new Notifications();
                notifications.setTitreNotif(task.getTitre());
                notifications.setTache(task);
                notifications.setUser(task.getUser());
                notificationsRepository.save(notifications);
            }
        }
    }


}
