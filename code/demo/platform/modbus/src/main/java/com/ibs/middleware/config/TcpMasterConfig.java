package com.ibs.middleware.config;

import com.ibs.middleware.service.TcpMaster;
import com.serotonin.modbus4j.ModbusMaster;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TcpMasterConfig {

    @Value("${spring.modbus.host}")
    private String host;

    @Value("${spring.modbus.port}")
    private int port;

    @Value("${spring.modbus.keepAlive}")
    private boolean keepAlive;

    @Bean
    public ModbusMaster TcpMaster() {
        return TcpMaster.getMaster(host, port, keepAlive);
    }

}
