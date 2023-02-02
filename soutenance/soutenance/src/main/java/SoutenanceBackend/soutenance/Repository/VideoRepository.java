package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.Tutoriels;
import SoutenanceBackend.soutenance.Models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Override
    boolean existsById(Long aLong);
//    AFFICHER LA LISTE DE VIDEOS DES LEGUMES

    Video findByLegumesFruits(LegumesFruits legumesFruits);

    // ==========================FILTRAGE===================================
    //====REQUETTE QUI PERMET D'AFFICHER TOUTE LES VIDEO DE FRUIT ET LEGUMES
    //SUIVANT QUE Le TYPE TYPELEGUMEFRUIT SOIT FRUIT OU LEGUME'=================
    @Query(value = "SELECT video.* from video ,legumes_fruits ,type_legume_fruit WHERE video.id_legumes_fruits=legumes_fruits.id and legumes_fruits.type_legume_fruit_id = type_legume_fruit.id and type_legume_fruit.type=:nofr", nativeQuery = true)
    List<Video> listVideoLegumes(String nofr);
    // ==========================END FILTRAGE==============================
    //    AFFICHER LA LISTE DE VIDEOS DES FRUITS
    @Modifying
    @Transactional
    @Query(value = "SELECT video.titre,video.video \n" +
            "FROM video,legumes_fruits,type_legume_fruit\n" +
            "WHERE video.id_legumes_fruits = legumes_fruits.id\n" +
            "AND legumes_fruits.type_legume_fruit_id = type_legume_fruit.id\n" +
            "AND type_legume_fruit.type='fruit'", nativeQuery = true)
    List<Video> listVideoFruits();

    //Video findByLegumesFruits(LegumesFruits id_legumesFruits);
}
