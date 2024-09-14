package me.vitorcsouza.jornada_milhas_api.domain.repository;


import me.vitorcsouza.jornada_milhas_api.domain.model.Destino;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface destinoRepository extends JpaRepository<Destino, Long> {
    @Query("SELECT d FROM Destino d WHERE d.nome LIKE :search%")
    Page<Destino> findBySearch(String search, Pageable pageable);
}
