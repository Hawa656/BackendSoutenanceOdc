package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.LegumesFruits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LegumesFruitsRepository extends JpaRepository<LegumesFruits, Long> {

    LegumesFruits findByNom(String nom);


    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `legumes_fruits` WHERE legumes_fruits.type_legume_fruit_id=1;",nativeQuery = true)
    List<LegumesFruits> ListeLegumes();


}
