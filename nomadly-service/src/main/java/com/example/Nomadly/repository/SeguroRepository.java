package com.example.Nomadly.repository;

import com.example.Nomadly.model.Seguro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguroRepository extends JpaRepository<Seguro, Long> {

    List<Seguro> findByIdContrato(Long idContrato);

}