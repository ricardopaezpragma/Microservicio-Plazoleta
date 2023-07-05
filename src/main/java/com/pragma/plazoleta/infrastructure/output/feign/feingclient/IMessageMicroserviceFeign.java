package com.pragma.plazoleta.infrastructure.output.feign.feingclient;

import com.pragma.plazoleta.domain.model.CustomerMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MessageMicroservice", url = "${microservie.url.message}")
public interface IMessageMicroserviceFeign {
    @PostMapping("/send/notify-order")
    void notifyCustomer(@RequestBody CustomerMessage customerMessage);
}
