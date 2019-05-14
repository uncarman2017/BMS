package com.ibs.middleware.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.logging.Logger;

/**
 * 大端 byte 工具类
 */
public class BigByteUtil {

    //static Logger logger = Logger.getLogger(BigByteUtil.class.getName());

    /**
     * short 转 byte[]
     * 大端
     * @param data
     * @return
     */
    public static byte[] getShortBytes(short data) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putShort(data);
        byte[] bytes = buffer.array();
        return bytes;
    }

    /**
     * chart 转 byte[]
     * 大端
     * @param data
     * @return
     */
    public static byte[] getCharBytes(char data) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putChar(data);
        byte[] bytes = buffer.array();
        return bytes;
    }

    /**
     * int 转 byte[]
     * 大端
     * @param data
     * @return
     */
    public static byte[] getIntBytes(int data) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putInt(data);
        byte[] bytes = buffer.array();
        return bytes;
    }

    /**
     * long 转 byte[]
     * 大端
     * @param data
     * @return
     */
    public static byte[] getLongBytes(long data) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putLong(data);
        byte[] bytes = buffer.array();
        return bytes;
    }

    /**
     * float 转 byte[]
     * 大端
     * @param data
     * @return
     */
    public static byte[] getFloatBytes(float data) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putFloat(data);
        byte[] bytes = buffer.array();
        return bytes;
    }

    /**
     * double 转 byte[]
     * 大端
     * @param data
     * @return
     */
    public static byte[] getDoubleBytes(double data) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putDouble(data);
        byte[] bytes = buffer.array();
        return bytes;
    }

    /**
     * String 转 byte[]
     *
     * @param data
     * @param charsetName
     * @return
     */
    public static byte[] getStringBytes(String data, String charsetName) {
        Charset charset = Charset.forName(charsetName);
        byte[] bytes = data.getBytes(charset);
        return bytes;
    }

    /**
     * String 转 byte[]
     *
     * @param data
     * @return
     */
    public static byte[] getStringBytes(String data) {
        byte[] bytes = null;
        if(data != null){
            bytes = data.getBytes();
        }else{
            bytes = new byte[0];
        }
        return bytes;
    }

    /*****************************************************************************************************************************/

    /**
     * byte[] 转short
     * 大端
     * @param bytes
     * @return
     */
    public static short getShort(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(bytes);
        short result = buffer.getShort(0);
        return result;
    }

    /**
     * byte[] 转 char
     * 大端
     * @param bytes
     * @return
     */
    public static char getChar(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(bytes);
        char result = buffer.getChar(0);
        return result;
    }

    /**
     * byte[] 转 int
     * 大端
     * @param bytes
     * @return
     */
    public static int getInt(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(bytes);
        int result = buffer.getInt(0);
        return result;
    }

    /**
     * byte[] 转 long
     *
     * @param bytes
     * @return
     */
    public static long getLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(bytes);
        long result = buffer.getLong(0);
        return result;
    }

    /**
     * byte[] 转 float
     *
     * @param bytes
     * @return
     */
    public static float getFloat(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(bytes);
        float result = buffer.getFloat(0);
        return result;
    }

    /**
     * byte[] 转 double
     *
     * @param bytes
     * @return
     */
    public static double getDouble(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(bytes);
        double result = buffer.getDouble(0);
        return result;
    }

    /**
     * byte[] 转 String
     *
     * @param bytes
     * @param charsetName
     * @return
     */
    public static String getString(byte[] bytes, String charsetName) {
        String result = new String(bytes, Charset.forName(charsetName));
        return result;
    }

    /**
     * byte[] 转 String
     *
     * @param bytes
     * @return
     */
    public static String getString(byte[] bytes) {
        String result = new String(bytes);
        return result;
    }

    /**
     * 验证测试
     */
    private static void verifiTest(){

        short s = 1111;
        int i = 2222;
        long l = 333333;
        char c = 'c';
        float f = 444.44f;
        double d = 555.55;
        String string = "测试字符串666";

        System.out.println(s);
        System.out.println(i);
        System.out.println(l);
        System.out.println(c);
        System.out.println(f);
        System.out.println(d);
        System.out.println(string);

        System.out.println("**************");

        System.out.println(getShort(getShortBytes(s)));
        System.out.println(getInt(getIntBytes(i)));
        System.out.println(getLong(getLongBytes(l)));
        System.out.println(getChar(getCharBytes(c)));
        System.out.println(getFloat(getFloatBytes(f)));
        System.out.println(getDouble(getDoubleBytes(d)));
        System.out.println(getString(getStringBytes(string)));

    }


    public static void main(String[] args) {
        verifiTest();

        System.out.println("finished ... ");
    }

}
