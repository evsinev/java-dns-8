package com.payneteasy.dns8;

import sun.net.spi.nameservice.NameService;
import sun.net.spi.nameservice.NameServiceDescriptor;

public class Dns8NameServiceDescriptor implements NameServiceDescriptor {

    @Override
    public NameService createNameService() throws Exception {
        return new Dns8NameService();
    }

    @Override
    public String getProviderName() {
        return "dns8";
    }

    @Override
    public String getType() {
        return "dns";
    }
}
