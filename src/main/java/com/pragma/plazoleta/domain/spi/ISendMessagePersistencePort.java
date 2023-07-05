package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.CustomerMessage;

public interface ISendMessagePersistencePort {
    void notifyCustomer(CustomerMessage customerMessage);
}
