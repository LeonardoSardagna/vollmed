package com.volmed.vollmed_api.main.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT m FROM Medico m
            WHERE
            m.ativo = true
            AND
            m.especialidade = :especialidade
            AND
            m.id NOT IN(
                SELECT c.medico.id FROM Consulta c
                WHERE
                c.data = :data
            )
            ORDER BY RAND()
            LIMIT 1
            """)
    Medico escolherMedicoAleatorioDataLivre(Especialidade especialidade, LocalDateTime data);

    @Query("""
            SELECT m.ativo
            FROM Medico m
            WHERE
            m.id = :id
            """)
    Boolean findAtivoById(Long id);
}
