package com.pragma.plazoleta.infrastructure.output.feign.adapter;

import com.pragma.plazoleta.domain.model.CustomerMessage;
import com.pragma.plazoleta.domain.spi.ISendMessagePersistencePort;
import com.pragma.plazoleta.infrastructure.exception.SendMessageException;
import com.pragma.plazoleta.infrastructure.output.feign.feingclient.IMessageMicroserviceFeign;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageMicroserviceAdapter implements ISendMessagePersistencePort {
    private final IMessageMicroserviceFeign messageMicroserviceFeign;
    @Override
    public void notifyCustomer(CustomerMessage customerMessage) {
        try {
            messageMicroserviceFeign.notifyCustomer(customerMessage);
        }catch (Exception e){
            throw new SendMessageException("Messaging microservice has bugs");
        }
    }
}
