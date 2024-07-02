package com.Operador.operador.Service;

import com.Operador.operador.Entidad.TipoTransaccion;
import com.Operador.operador.Entidad.Usuario;
import com.Operador.operador.Entidad.Transaccion;
import com.Operador.operador.Entidad.TransaccionId;
import com.Operador.operador.Entidad.model.request.TransaccionRequest;
import com.Operador.operador.Entidad.model.response.TransaccionResponse;
import com.Operador.operador.Entidad.model.response.ProductResponse;
import com.Operador.operador.Repositorio.TransaccionRepository;
import com.Operador.operador.Repositorio.UsuarioRepository;
import com.Operador.operador.facade.PeliculaFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl {
    @Autowired
    private final PeliculaFacade productsFacade;

    @Autowired
    private final TransaccionRepository transaccionRepository;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final TipoTransaccionService tipoTransaccionRepository;

    public TransaccionResponse createTransaccion(TransaccionRequest orderRequest) {
        TransaccionResponse orderResponse = new TransaccionResponse();

        Usuario usuario = usuarioRepository.findById(orderRequest.getUserId()).orElse(null);
        if (usuario == null) {
            orderResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            orderResponse.setMessage("Usuario no encontrado");
            return orderResponse;
        }

        for (Integer movieId : orderRequest.getMovieIds()) {
                ProductResponse productResponse = productsFacade.getProductDetails(movieId);
            if (productResponse != null) {
                saveTransaccion(orderRequest,usuario, productResponse);
            }
        }

        orderResponse.setStatusCode(HttpStatus.CREATED.value());
        orderResponse.setMessage("Order created successfully");
        return orderResponse;
    }

    private void saveTransaccion(TransaccionRequest orderRequest, Usuario usuario , ProductResponse productResponse) {
        Transaccion transaccion = new Transaccion();
        TransaccionId transaccionId = new TransaccionId();
        transaccionId.setTraId(transaccionRepository.findMaxTransId().orElse(0));
        transaccionId.setTraUser(orderRequest.getUserId());
        transaccionId.setTraFilm(productResponse.getId());
        transaccionId.setTraTipo(orderRequest.getType());
        transaccionId.setTraEstado("COMPLETED");
        transaccion.setId(transaccionId);
        transaccion.setTraFecini(new Date());
        transaccion.setTraFecfin(new Date());
        transaccion.setUsuario(usuario);
        TipoTransaccion tipoTransaccion=tipoTransaccionRepository.findByIdUni(orderRequest.getType());
        transaccion.setTipoTransaccion(tipoTransaccion);
        transaccionRepository.save(transaccion);
    }
}
