package com.example.Nomadly.repository;

import com.example.Nomadly.model.ServicioAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioAdicionalRepository extends JpaRepository<ServicioAdicional, Long> {

    List<ServicioAdicional> findByIdContrato(Long idContrato);

}