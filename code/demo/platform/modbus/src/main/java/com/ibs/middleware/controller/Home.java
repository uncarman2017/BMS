package com.ibs.middleware.controller;

import com.ibs.middleware.component.Modbus4jReadUtils;
import com.ibs.middleware.service.ModbusLanucher;
import com.ibs.middleware.service.TcpMaster;
import com.ibs.middleware.util.byteUtil;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.msg.ReadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @RequestMapping("/")
    public String home() {
        try {
            ModbusMaster master = TcpMaster.getMaster("127.0.0.1", 502, true);
            Modbus4jReadUtils mRead = new Modbus4jReadUtils(master);
            ReadResponse res = mRead.readHoldingRegister(1, 4000, 1);
            return byteUtil.byteToHex(res.getData());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "home";
    }
}
