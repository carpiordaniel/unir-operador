package com.Operador.operador.facade;

import com.Operador.operador.Entidad.model.response.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component

@Slf4j
public class PeliculaFacade {
    @Value("${orderProduct.url}")
    private String orderProductUrl;


    @Autowired
    private  RestTemplate restTemplate;

    public ProductResponse getProductDetails(Integer id) {
        try {
            String url = String.format(orderProductUrl, id);
            ProductResponse productResponse=restTemplate.getForObject(url, ProductResponse.class);
            return productResponse;
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        }
    }
}
