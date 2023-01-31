package SoutenanceBackend.soutenance.Repository;


import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.TypeLegumeFruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TypeLegumeFruitRepository extends JpaRepository<TypeLegumeFruit, Long> {

    TypeLegumeFruit findByType(String type);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `type_legume_fruit` (`id`, `type`) VALUES (NULL, 'Legume'), (NULL, 'Fruit');",nativeQuery = true)
    void creerTypeLegumeFruit();

}
