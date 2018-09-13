package com.video.app.utils;

import android.util.Base64;

import java.security.Key;
import java.security.MessageDigest;
import java.security.Security;

import javax.crypto.Cipher;

public class EncryptUtil {

    /**
     * MD5加密
     * @param source 需要加密的字段
     * @throws Exception
     * */
    public static String MD5(String source) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.reset();
        md5.update(source.getBytes("UTF-8"));
        byte[] result = md5.digest();
        StringBuilder buf = new StringBuilder(result.length * 2);

        for (int i = 0; i < result.length; i++) {
            int intVal = result[i] & 0xff;
            if (intVal < 0x10) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(intVal));
        }
        return buf.toString();
    }

    /**
     * byte[]转base64
     * */
    public static String byte2Base64String(byte[] bytes){
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    /**
     * base64转byte[]
     * */
    public static byte[] base64String2byte(String base64){
        return Base64.decode(base64, Base64.NO_WRAP);
    }

    /**
     * 加密字符串
     * @param encryptStr 需加密的字符串
     * @param DESKey 加密KEY
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encrypt(String encryptStr, String DESKey) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        try {
            byte[] arrB = new byte[8];
            for (int i = 0; i < DESKey.getBytes().length && i < arrB.length; i++) {
                arrB[i] = DESKey.getBytes()[i];
            }
            Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

            Cipher encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] bytes = encryptCipher.doFinal(encryptStr.getBytes());

            StringBuffer sb = new StringBuffer(bytes.length * 2);
            for (int i = 0; i < bytes.length; i++) {
                int intTmp = bytes[i];
                while (intTmp < 0) {
                    intTmp = intTmp + 256;
                }
                if (intTmp < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toString(intTmp, 16));
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
