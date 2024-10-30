package dianafriptuleac.u5w2d3.repositories;

import dianafriptuleac.u5w2d3.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutoreRepository extends JpaRepository<Autore, Long> {
    Optional<Autore> findByEmail(String email);
}
