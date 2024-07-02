package com.Operador.operador.Controlador;


import com.Operador.operador.Entidad.Transaccion;
import com.Operador.operador.Entidad.TransaccionId;
import com.Operador.operador.Entidad.model.request.TransaccionRequest;
import com.Operador.operador.Entidad.model.response.TransaccionResponse;
import com.Operador.operador.Service.TransaccionServiceImpl;
import com.Operador.operador.Service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transacciones")

public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;
    @Autowired
    private TransaccionServiceImpl ordersService;

    @PostMapping("/transacciones")
    public ResponseEntity<TransaccionResponse> createOrder(@RequestBody TransaccionRequest orderRequest) {
        TransaccionResponse orderResponse = ordersService.createTransaccion(orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.valueOf(orderResponse.getStatusCode()));
    }
    @GetMapping
    public List<Transaccion> getAll() {
        return transaccionService.findAll();
    }

    @GetMapping("/api/transacciones/{id}")
    public ResponseEntity<Transaccion> getById(@PathVariable TransaccionId id) {
        Optional<Transaccion> transaccion = transaccionService.findById(id);
        return transaccion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transaccion create(@RequestBody Transaccion transaccion) {
        return transaccionService.save(transaccion);
    }

    @PutMapping("/api/transacciones/{id}")
    public ResponseEntity<Transaccion> update(@PathVariable TransaccionId id, @RequestBody Transaccion transaccion) {
        if (!transaccionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        //transaccion.setTraId(id);
        return ResponseEntity.ok(transaccionService.save(transaccion));
    }

    @DeleteMapping("/api/transacciones/{id}")
    public ResponseEntity<Void> delete(@PathVariable TransaccionId id) {
        if (!transaccionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        transaccionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
