package com.camila.cictema.service;

import com.camila.cictema.model.Persona;
import com.camila.cictema.model.Usuario;
import com.camila.cictema.repository.UsuarioRepository;
import com.camila.cictema.specification.UsuarioSpecification;
import com.camila.cictema.util.FechaUtils;
import com.camila.cictema.util.HashUtil;
import com.camila.cictema.util.RespuestaPaginada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public RespuestaPaginada<Usuario> filtrarPorParametros(
            Integer id, String nombres, String apellidos, String telefono, String email, String username,
            Float porcentajeGanancia, String fechaCreacionDesdeStr, String fechaCreacionHastaStr,
            String fechaModificacionDesdeStr, String fechaModificacionHastaStr, Boolean deleted,
            int page, int size
    ){
        LocalDateTime fechaCreacionDesde = null, fechaCreacionHasta = null,fechaModificacionDesde= null, fechaModificacionHasta = null;

        if(fechaCreacionDesdeStr!= null && !fechaCreacionDesdeStr.isEmpty()){
            fechaCreacionDesde = FechaUtils.convertirStringAFechaInicioDia(fechaCreacionDesdeStr);
        }

        if(fechaCreacionHastaStr!= null && !fechaCreacionHastaStr.isEmpty()){
            fechaCreacionHasta = FechaUtils.convertirStringAFechaFinDia(fechaCreacionHastaStr);
        }

        if(fechaModificacionDesdeStr!= null && !fechaModificacionDesdeStr.isEmpty()){
            fechaModificacionDesde = FechaUtils.convertirStringAFechaInicioDia(fechaModificacionDesdeStr);
        }

        if(fechaModificacionHastaStr!= null && !fechaModificacionHastaStr.isEmpty()){
            fechaModificacionHasta = FechaUtils.convertirStringAFechaFinDia(fechaModificacionHastaStr);
        }

        Pageable pageable = PageRequest.of(page, size);
        Specification<Usuario> specification = UsuarioSpecification.filtrarPorParametros(id,nombres, apellidos, telefono, email, username, porcentajeGanancia, fechaCreacionDesde, fechaCreacionHasta, fechaModificacionDesde, fechaModificacionHasta, deleted);
        Page<Usuario> usuarioPage = usuarioRepository.findAll(specification, pageable);

        return new RespuestaPaginada<Usuario>(
                usuarioPage.getContent(),
                usuarioPage.getNumber(),
                usuarioPage.getSize(),
                usuarioPage.getTotalElements(),
                usuarioPage.getTotalPages()
        );

    }

    public Usuario guardar(Usuario usuario){
        usuario.setPassword(HashUtil.hashPassword(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Integer id, Usuario usuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario existingUsuario = usuarioOptional.get();
            existingUsuario.setNombres(usuario.getNombres());
            existingUsuario.setApellidos(usuario.getApellidos());
            existingUsuario.setTelefono(usuario.getTelefono());
            existingUsuario.setDeleted(usuario.getDeleted());
            existingUsuario.setEmail(usuario.getEmail());
            existingUsuario.setUsername(usuario.getUsername());
            existingUsuario.setPorcentajeGanancia(usuario.getPorcentajeGanancia());
            existingUsuario.setPassword(HashUtil.hashPassword(usuario.getPassword()));
            existingUsuario.setFechaModificacion(LocalDateTime.now());
            return usuarioRepository.save(existingUsuario);
        } else {
            throw new RuntimeException("Usuario not found with id " + id);
        }
    }

    public void eliminar(Integer id){
        usuarioRepository.deleteById(id);
    }

    public Usuario softDelete(Integer id){
        Optional<Usuario> usuarioOptional =  usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario existingUsuario = usuarioOptional.get();
            existingUsuario.setDeleted(true);
            return usuarioRepository.save(existingUsuario);
        } else {
            throw new RuntimeException("Usuario not found with id " + id);
        }
    }

}
