package com.ibs.middleware.service;

import java.util.Arrays;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.ip.IpParameters;
import org.springframework.stereotype.Service;

@Service
public class TcpMaster {

    private static ModbusFactory modbusFactory;

    static {
        if (modbusFactory == null) {
            modbusFactory = new ModbusFactory();
        }
    }

    /**
     * 获取master
     * @return master
     */
    public static ModbusMaster getMaster(String host, Integer port, Boolean keepAlive) {
        IpParameters params = new IpParameters();
        params.setHost(host);
        params.setPort(port);
        //params.setEncapsulated(true);
        ModbusMaster master = modbusFactory.createTcpMaster(params, keepAlive);
        try {
            //设置超时时间
            master.setTimeout(3000);
            //设置重连次数
            master.setRetries(3);
            //初始化
            master.init();
        } catch (ModbusInitException e) {
            e.printStackTrace();
        }
        return master;
    }
}