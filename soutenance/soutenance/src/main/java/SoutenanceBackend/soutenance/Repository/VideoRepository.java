package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Tutoriels;
import SoutenanceBackend.soutenance.Models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Override
    boolean existsById(Long aLong);
//    AFFICHER LA LISTE DE VIDEOS DES LEGUMES
    @Modifying
    @Transactional
    @Query(value = "SELECT video.titre,video.video \n" +
            "FROM video,legumes_fruits,type_legume_fruit\n" +
            "WHERE video.id_legumes_fruits = legumes_fruits.id\n" +
            "AND legumes_fruits.type_legume_fruit_id = type_legume_fruit.id\n" +
            "AND type_legume_fruit.type='legume';);", nativeQuery = true)
    void listVideoLegumes();
    //    AFFICHER LA LISTE DE VIDEOS DES FRUITS
    @Modifying
    @Transactional
    @Query(value = "SELECT video.titre,video.video \n" +
            "FROM video,legumes_fruits,type_legume_fruit\n" +
            "WHERE video.id_legumes_fruits = legumes_fruits.id\n" +
            "AND legumes_fruits.type_legume_fruit_id = type_legume_fruit.id\n" +
            "AND type_legume_fruit.type='fruit';);", nativeQuery = true)
    void listVideoFruits();
}
