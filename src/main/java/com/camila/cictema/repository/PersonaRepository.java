package com.camila.cictema.repository;

import com.camila.cictema.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>, JpaSpecificationExecutor<Persona> {

    // Spring permite la construccion de consultas basadas en propiedades de entidades
    Optional<Persona> findPersonaById(Integer id);


}
