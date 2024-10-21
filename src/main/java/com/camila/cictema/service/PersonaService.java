package com.camila.cictema.service;

import com.camila.cictema.model.Persona;
import com.camila.cictema.repository.PersonaRepository;
import com.camila.cictema.specification.PersonaSpecification;
import com.camila.cictema.util.RespuestaPaginada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public RespuestaPaginada<Persona> filtrarPorParametros(Integer id, String nombres, String apellidos, String telefono, Boolean deleted, int page, int size) {

        // Paginacion
        Pageable pageable = PageRequest.of(page, size);

        // Especificacion basada en parametros
        Specification<Persona> specification = PersonaSpecification.filtrarPorParametros(id, nombres, apellidos, telefono, deleted);

        // Retorna el resultado paginado usando el repositorio
        Page<Persona> personaPage = personaRepository.findAll(specification,pageable);

        //Se retorna solo los datos utiles
        return new RespuestaPaginada<Persona>(
                personaPage.getContent(),
                personaPage.getNumber(),
                personaPage.getSize(),
                personaPage.getTotalElements(),
                personaPage.getTotalPages()
        );
    }

    public Persona guardar(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona actualizar(Integer id, Persona persona){
        Optional<Persona> optionalPersona = personaRepository.findById(id);
        if (optionalPersona.isPresent()) {
            Persona existingPersona = optionalPersona.get();
            existingPersona.setNombres(persona.getNombres());
            existingPersona.setApellidos(persona.getApellidos());
            existingPersona.setTelefono(persona.getTelefono());
            existingPersona.setDeleted(persona.getDeleted());
            return personaRepository.save(existingPersona);
        } else {
            throw new RuntimeException("Persona not found with id " + id);
        }
    }

    public void eliminar(Integer id) {
        personaRepository.deleteById(id);
    }

    public Persona softDelete(Integer id){
        Optional<Persona> optionalPersona = personaRepository.findById(id);
        if (optionalPersona.isPresent()) {
            Persona existingPersona = optionalPersona.get();
            existingPersona.setDeleted(true);
            return personaRepository.save(existingPersona);
        } else {
            throw new RuntimeException("Persona not found with id " + id);
        }
    }





}
