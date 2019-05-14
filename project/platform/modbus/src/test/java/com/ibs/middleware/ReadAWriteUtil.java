package com.ibs.middleware;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.msg.*;
import com.serotonin.modbus4j.sero.util.queue.ByteQueue;

public class ReadAWriteUtil {

    public static void main(String[] args) {
        modbusRTCP("127.0.0.1", 502, 1, 4000, 2);
    }

    public static ModbusMaster initModbus(String ip, int port) {
        ModbusFactory modbusFactory = new ModbusFactory();
        // 设备ModbusTCP的Ip与端口，如果不设定端口则默认为502
        IpParameters params = new IpParameters();
        params.setHost(ip);
        // 设置端口，默认502
        if (502 != port) {
            params.setPort(port);
        }
        ModbusMaster tcpMaster = null;
        // 参数1：IP和端口信息 参数2：保持连接激活
        tcpMaster = modbusFactory.createTcpMaster(params, true);
        try {
            tcpMaster.init();
            System.out.println("=======初始化成功========");
        } catch (ModbusInitException e) {
            System.out.println("初始化异常");
        }
        return tcpMaster;



//        IpParameters params = new IpParameters();
//        params.setHost("localhost");
//        params.setPort(502);
//        params.setEncapsulated(true);
//        ModbusMaster master = modbusFactory.createTcpMaster(params, false);// TCP 协议
//        try {
//            //设置超时时间
//            master.setTimeout(1000);
//            //设置重连次数
//            master.setRetries(3);
//            //初始化
//            master.init();
//        } catch (ModbusInitException e) {
//            e.printStackTrace();
//        }
//        return master;
    }


    /**
     * 批量写数据到保持寄存器
     * @param ip 从站IP
     * @param port modbus端口
     * @param slaveId 从站ID
     * @param start 起始地址偏移量
     * @param values 待写数据
     */
    public static void modbusWTCP(String ip, int port, int slaveId, int start, short[] values) {
        // 参数1：IP和端口信息 参数2：保持连接激活
        ModbusMaster tcpMaster = initModbus(ip, port);
        try {
            WriteRegistersRequest request = new WriteRegistersRequest(slaveId, start, values);
            WriteRegistersResponse response = (WriteRegistersResponse) tcpMaster.send(request);
            if (response.isException()){
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            }else{
                System.out.println("Success");
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }


    public static void modbusRTCP(String ip, int port, int slaveId, int start, int numberOfBits) {
        // 参数1：IP和端口信息 参数2：保持连接激活
        ModbusMaster tcpMaster = initModbus(ip, port);
        try {
            ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(slaveId, start, numberOfBits);
            ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) tcpMaster.send(request);
            if (response.isException()){
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            } else {
                System.out.println("Success");
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }


    /**
     * 读保持寄存器上的内容
     * @param ip 从站IP
     * @param port modbus端口
     * @param start 起始地址偏移量
     * @param readLenth 待读寄存器个数
     * @return
     */
    public static ByteQueue modbusTCP(String ip, int port, int start,int readLenth) {
        ModbusFactory modbusFactory = new ModbusFactory();
        // 设备ModbusTCP的Ip与端口，如果不设定端口则默认为502
        IpParameters params = new IpParameters();
        params.setHost(ip);
        //设置端口，默认502
        if(502!=port){
            params.setPort(port);
        }
        ModbusMaster tcpMaster = null;
        tcpMaster = modbusFactory.createTcpMaster(params, true);
        try {
            tcpMaster.init();
            System.out.println("========初始化成功=======");
        } catch (ModbusInitException e) {
            return null;
        }
        ModbusRequest modbusRequest=null;
        try {
            //功能码03   读取保持寄存器的值
            modbusRequest = new ReadHoldingRegistersRequest(1, start, readLenth);
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }
        ModbusResponse modbusResponse=null;
        try {
            modbusResponse = tcpMaster.send(modbusRequest);
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }
        ByteQueue byteQueue= new ByteQueue(1024);
        modbusResponse.write(byteQueue);
        System.out.println("功能码:"+modbusRequest.getFunctionCode());
        System.out.println("从站地址:"+modbusRequest.getSlaveId());
        System.out.println("收到的响应信息大小:"+byteQueue.size());
        System.out.println("收到的响应信息值:"+byteQueue);
        return byteQueue;
    }
}