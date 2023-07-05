package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.CustomerMessage;

public interface ISendMessageServicePort {
    void notifyCustomer(CustomerMessage customerMessage);
}
