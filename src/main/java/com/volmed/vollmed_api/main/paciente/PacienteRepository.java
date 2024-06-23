package com.volmed.vollmed_api.main.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT p.ativo
            FROM Paciente p
            WHERE
            p.id = :id
            """)
    Boolean findAtivoById(Long id);
}
