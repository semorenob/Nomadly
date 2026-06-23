package com.example.Nomadly.repository;

import com.example.Nomadly.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    List<Reporte> findByIdColegio(Long idColegio);

    List<Reporte> findByIdCurso(Long idCurso);
}