package SoutenanceBackend.soutenance.Repository;


import SoutenanceBackend.soutenance.Models.TypeLegumeFruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeLegumeFruitRepository extends JpaRepository<TypeLegumeFruit, Long> {
}
