package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.ISendMessageServicePort;
import com.pragma.plazoleta.domain.model.CustomerMessage;
import com.pragma.plazoleta.domain.spi.ISendMessagePersistencePort;

public class SendMessageUseCase implements ISendMessageServicePort {
    private final ISendMessagePersistencePort sendMessagePersistencePort;

    public SendMessageUseCase(ISendMessagePersistencePort sendMessagePersistencePort) {
        this.sendMessagePersistencePort = sendMessagePersistencePort;
    }

    @Override
    public void notifyCustomer(CustomerMessage customerMessage) {
        sendMessagePersistencePort.notifyCustomer(customerMessage);
    }
}
