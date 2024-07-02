package com.Operador.operador.Repositorio;

import com.Operador.operador.Entidad.TipoTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoTransaccionRepository extends JpaRepository<TipoTransaccion, String> {
}
