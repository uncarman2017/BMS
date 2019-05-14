package com.ibs.middleware.service;


import com.ibs.middleware.component.Modbus4jReadUtils;
import com.ibs.middleware.component.Modbus4jWriteUtils;
import com.ibs.middleware.util.byteUtil;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.ReadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ModbusLanucher {

    private static final Logger logger = LoggerFactory.getLogger(ModbusLanucher.class);

    @Autowired
    private Modbus4jReadUtils mRead;

    @Autowired
    private Modbus4jWriteUtils mWrite;

    @Scheduled(initialDelay = 5*1000, fixedRate =5*1000)
    public void run() {
        try {
            // 读 hex 字符串
            ReadResponse response = mRead.readHoldingRegister(1, 4000, 3);
            System.out.print(byteUtil.byteToHex(response.getData()));
            System.out.print("\r\n");

            // 读 signed 数字
            int val = 0;
            ReadResponse response2 = mRead.readHoldingRegister(1, 4004, 4);
            System.out.print(Arrays.toString(response2.getShortData()));
            short[] list = response2.getShortData();
            val = list[0];
            System.out.print("\r\n");

            // 写入数字
            mWrite.writeHoldingRegister(1, 4004, val + 1, 2);

        } catch (ModbusTransportException ex) {
            logger.error("TcpModbus lancher connecting error ", ex);
        } catch (ErrorResponseException ex) {
            logger.error("TcpModbus lancher data grap error ", ex);
        } catch (ModbusInitException ex) {
            logger.error("TcpModbus lancher init error ", ex);
        } catch (Exception ex) {
            logger.error("TcpModbus lancher unkown error ", ex);
        }
    }

}
