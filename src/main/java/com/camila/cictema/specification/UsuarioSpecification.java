package com.camila.cictema.specification;

import com.camila.cictema.model.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioSpecification {
    public static Specification<Usuario> filtrarPorParametros(
            Integer id, String nombres, String apellidos, String telefono, String email,
            String username, Float porcentajeGanancia,  LocalDateTime fechaCreacionDesde, LocalDateTime fechaCreacionHasta, LocalDateTime fechaModificacionDesde, LocalDateTime fechaModificacionHasta, Boolean deleted
    ){

        return(Root<Usuario> root,CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {

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

            if(username!= null && !username.isEmpty()){
                predicates.add(criteriaBuilder.equal(
                            root.get("username"),
                            username
                        )
                );
            }

            if(email!= null && !email.isEmpty()){
                predicates.add(criteriaBuilder.equal(
                                root.get("email"),
                                email
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

            if(porcentajeGanancia!= null){
                predicates.add(
                  criteriaBuilder.equal(
                          root.get("porcentajeGanancia"),
                          porcentajeGanancia
                  )
                );
            }

            if(fechaCreacionDesde != null){
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("fechaCreacion"),
                                fechaCreacionDesde
                        )
                );
            }

            if(fechaCreacionHasta != null){
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get("fechaCreacion"),
                                fechaCreacionHasta
                        )
                );
            }

            if(fechaModificacionDesde != null){
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("fechaModificacion"),
                                fechaModificacionDesde
                        )
                );
            }

            if(fechaModificacionHasta != null){
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get("fechaModificacion"),
                                fechaModificacionHasta
                        )
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };

    }
}
