package com.camila.cictema.controller;

import com.camila.cictema.model.Persona;
import com.camila.cictema.service.PersonaService;
import com.camila.cictema.util.CustomResponse;
import com.camila.cictema.util.RespuestaPaginada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    CustomResponse<RespuestaPaginada<Persona>> get(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nombres,
            @RequestParam(required = false) String apellidos,
            @RequestParam(required = false) String telefono,
            @RequestParam(required = false) Boolean deleted,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try {

            RespuestaPaginada<Persona> respuestaPaginada = personaService.filtrarPorParametros(id, nombres, apellidos, telefono, deleted, page, size);
            return new CustomResponse<RespuestaPaginada<Persona>>(respuestaPaginada, true, "Éxito", HttpStatus.OK.value());

        }catch (Exception e){
            return new CustomResponse<>(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }



    @PostMapping
    public CustomResponse<Persona> post(@RequestBody Persona persona){
        try{
            Persona newPersona = personaService.guardar(persona);
            return new CustomResponse<Persona>(newPersona, true, "Éxito", HttpStatus.OK.value());
        }catch (Exception e){
            return new CustomResponse<>(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/{id}")
    public CustomResponse<Persona> put(@PathVariable Integer id ,@RequestBody Persona persona){
        try{
            Persona newPersona = personaService.actualizar(id, persona);
            return new CustomResponse<Persona>(newPersona, true, "Éxito", HttpStatus.OK.value());
        }catch (Exception e){
            return new CustomResponse<>(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @DeleteMapping("/{id}")
    public CustomResponse<Persona> delete(@PathVariable Integer id){
        try{
            personaService.eliminar(id);
            return new CustomResponse<Persona>(null, true, "Éxito", HttpStatus.OK.value());
        }catch (Exception e){
            return new CustomResponse<>(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/soft-delete/{id}")
    public CustomResponse<Persona> softDelete(@PathVariable Integer id){
        try{
            Persona persona = personaService.softDelete(id);
            return new CustomResponse<Persona>(persona, true, "Éxito", HttpStatus.OK.value());
        }catch (Exception e){
            return new CustomResponse<>(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
