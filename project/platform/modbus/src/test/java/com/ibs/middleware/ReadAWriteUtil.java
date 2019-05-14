package com.ibs.middleware;

import com.ibs.middleware.component.Modbus4jReadUtils;
import com.ibs.middleware.component.Modbus4jWriteUtils;
import com.ibs.middleware.service.TcpMaster;
import com.ibs.middleware.util.byteUtil;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.msg.*;
import com.serotonin.modbus4j.sero.util.queue.ByteQueue;

import java.util.Arrays;

public class ReadAWriteUtil {

    public static void main(String[] args) {
        try {
            ModbusMaster master = TcpMaster.getMaster("127.0.0.1", 502, true);
            Modbus4jReadUtils mRead = new Modbus4jReadUtils(master);
            Modbus4jWriteUtils mWrite = new Modbus4jWriteUtils(master);

            // 读取hex数据
            ReadResponse res1 = mRead.readHoldingRegister(1, 4000, 1);
            System.out.print(byteUtil.byteToHex(res1.getData()));

            // 读物short数据
            ReadResponse res2 = mRead.readHoldingRegister(1, 4004, 3);
            System.out.print(Arrays.toString(res2.getShortData()));

            // 写数据
            mWrite.writeHoldingRegister(1, 4004, 101, 2);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

}