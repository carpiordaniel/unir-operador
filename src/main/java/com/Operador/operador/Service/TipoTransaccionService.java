package com.Operador.operador.Service;

import com.Operador.operador.Entidad.TipoTransaccion;
import com.Operador.operador.Repositorio.TipoTransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoTransaccionService {

    @Autowired
    private TipoTransaccionRepository tipoTransaccionRepository;

    public List<TipoTransaccion> findAll() {
        return tipoTransaccionRepository.findAll();
    }

    public Optional<TipoTransaccion> findById(String id) {
        return tipoTransaccionRepository.findById(id);
    }
    public TipoTransaccion findByIdUni(String id) {
        Optional<TipoTransaccion> optionalTipoTransaccion = tipoTransaccionRepository.findById(id);
            TipoTransaccion tipoTransaccion = optionalTipoTransaccion.get();
            return TipoTransaccion.builder()
                    .ttrId(tipoTransaccion.getTtrId())
                    .ttrDescripcion(tipoTransaccion.getTtrDescripcion())
                    .build();

    }
    public TipoTransaccion save(TipoTransaccion tipoTransaccion) {
        return tipoTransaccionRepository.save(tipoTransaccion);
    }

    public void deleteById(String id) {
        tipoTransaccionRepository.deleteById(id);
    }


}
