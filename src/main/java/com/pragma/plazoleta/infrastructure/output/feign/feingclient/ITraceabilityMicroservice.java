package com.pragma.plazoleta.infrastructure.output.feign.feingclient;

import com.pragma.plazoleta.infrastructure.output.feign.entity.TraceabilityEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "TraceabilityMicroservice", url = "${microservice.url.traceability}")
public interface ITraceabilityMicroservice {
    @PostMapping("/traceability")
    void saveTraceability(@RequestBody TraceabilityEntity traceabilityEntity);
    @GetMapping("/traceability/{customerId}")
    List<TraceabilityEntity> getTraceabilityByCustomerId(@PathVariable int customerId);
    @GetMapping("/traceability/order/{orderId}")
    List<TraceabilityEntity> getTraceabilityByOrderId(@PathVariable int orderId);
}
