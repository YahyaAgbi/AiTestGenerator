package Agbi.Yahya.TestUnitaire.repositories;

import Agbi.Yahya.TestUnitaire.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {
    Projet findByNom(String name);
}
