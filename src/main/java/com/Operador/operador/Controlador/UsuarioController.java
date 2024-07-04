package com.Operador.operador.Controlador;

import com.Operador.operador.Entidad.Usuario;
import com.Operador.operador.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/api/usuarios")
    public List<Usuario> getAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/api/usuarios/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable String id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/api/usuarios/{id}")
    public ResponseEntity<Usuario> update(@PathVariable String id, @RequestBody Usuario usuario) {
        if (!usuarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuario.setTuUserid(id);
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @DeleteMapping("/api/usuarios/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!usuarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
