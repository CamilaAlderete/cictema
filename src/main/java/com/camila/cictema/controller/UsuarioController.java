package com.camila.cictema.controller;

import com.camila.cictema.model.Usuario;
import com.camila.cictema.service.UsuarioService;
import com.camila.cictema.util.CustomResponse;
import com.camila.cictema.util.RespuestaPaginada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    CustomResponse<RespuestaPaginada<Usuario>> get(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nombres,
            @RequestParam(required = false) String apellidos,
            @RequestParam(required = false) String telefono,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Boolean deleted,
            @RequestParam(required = false) Float porcentajeGanancia,
            @RequestParam(required = false) String fechaCreacionDesde,
            @RequestParam(required = false) String fechaCreacionHasta,
            @RequestParam(required = false) String fechaModificacionDesde,
            @RequestParam(required = false) String fechaModificacionHasta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try{
            RespuestaPaginada<Usuario> respuestaPaginada = usuarioService.filtrarPorParametros(id,nombres, apellidos, telefono, email, username, porcentajeGanancia, fechaCreacionDesde, fechaCreacionHasta, fechaModificacionDesde, fechaModificacionHasta, deleted, page, size);
            return new CustomResponse<>(respuestaPaginada, true, "Éxito", HttpStatus.OK.value());
        }catch (Exception e){
            return new CustomResponse<>(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping
    public CustomResponse<Usuario> post(@RequestBody Usuario usuario){
        try{
            Usuario newUsuario = usuarioService.guardar(usuario);
            return new CustomResponse<Usuario>(newUsuario, true, "Éxito", HttpStatus.OK.value());
        }catch (Exception e){
            return new CustomResponse<>(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/{id}")
    public CustomResponse<Usuario> put(@PathVariable Integer id ,@RequestBody Usuario usuario){
        try{
            Usuario newUsuario = usuarioService.actualizar(id, usuario);
            return new CustomResponse<Usuario>(newUsuario, true, "Éxito", HttpStatus.OK.value());
        }catch (Exception e){
            return new CustomResponse<>(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @DeleteMapping("/{id}")
    public CustomResponse<Usuario> delete(@PathVariable Integer id){
        try{
            usuarioService.eliminar(id);
            return new CustomResponse<Usuario>(null, true, "Éxito", HttpStatus.OK.value());
        }catch (Exception e){
            return new CustomResponse<>(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/soft-delete/{id}")
    public CustomResponse<Usuario> softDelete(@PathVariable Integer id){
        try{
            Usuario usuario = usuarioService.softDelete(id);
            return new CustomResponse<Usuario>(usuario, true, "Éxito", HttpStatus.OK.value());
        }catch (Exception e){
            return new CustomResponse<>(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
