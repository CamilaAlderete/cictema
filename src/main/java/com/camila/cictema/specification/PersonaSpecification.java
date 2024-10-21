package com.camila.cictema.specification;

import com.camila.cictema.model.Persona;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PersonaSpecification {
    public static Specification<Persona> filtrarPorParametros( Integer id, String nombres, String apellidos, String telefono, Boolean deleted){

        return (Root<Persona> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(id!=null){
              predicates.add( criteriaBuilder.equal(root.get("id"),id) );
            }

            if(nombres!= null && !nombres.isEmpty()){
                predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("nombres")),
                            "%" + nombres.toLowerCase()+ "%"
                        )
                );
            }

            if (apellidos != null && !apellidos.isEmpty()) {
                predicates.add(
                        criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("apellidos")),
                            "%" + apellidos.toLowerCase() + "%"
                        )
                );
            }

            if(telefono!= null && !telefono.isEmpty()){
                predicates.add(
                  criteriaBuilder.equal(
                          root.get("telefono"),
                          telefono
                  )
                );
            }

            if(deleted!= null){
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("deleted"),
                                deleted
                        )
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
