package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.LegumesFruits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LegumesFruitsRepository extends JpaRepository<LegumesFruits, Long> {

    LegumesFruits findByNom(String nom);

    //==========AFFICHER LISTE DES LEGUMES==================
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `legumes_fruits` WHERE legumes_fruits.type_legume_fruit_id=1;",nativeQuery = true)
    List<LegumesFruits> ListeLegumes();
    //==========AFFICHER LISTE DES FRUITS==================
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `legumes_fruits` WHERE legumes_fruits.type_legume_fruit_id=2;",nativeQuery = true)
    List<LegumesFruits> ListeFruits();


    @Query(value = "SELECT l.arrosage, l.description, l.duree_floraisaon, l.nom, l.photo as lf_photo, l.periode_normal, t.espacement_entre_graine, t.titre, e.etape, e.photo as e_photo, e.titre as e_titre FROM etapes e, tutoriels t, legumes_fruits l WHERE l.id_tutoriel = t.id AND t.id = e.tutoriels_id AND l.id = :id", nativeQuery = true)
    List<Object[]> findLegumesFruitsByIdWithTutorielAndEtapes(Long id);



}
