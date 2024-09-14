package me.vitorcsouza.jornada_milhas_api.domain.repository;

import me.vitorcsouza.jornada_milhas_api.domain.model.Depoimentos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface depoimentosRepository extends JpaRepository<Depoimentos, Long> {
    @Query(value = "SELECT * FROM depoimentos_tb ORDER BY RAND()", nativeQuery = true)
    Page<Depoimentos> findRandom(Pageable pageable);
}
