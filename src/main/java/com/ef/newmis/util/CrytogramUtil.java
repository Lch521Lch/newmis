package com.ef.newmis.util;


import org.apache.commons.codec.binary.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 改造CrytogramUtil
 *
 * @author dirxu
 *
 */
public class CrytogramUtil {
    public static String encrypt(String paramString1, String paramString2) {
        MessageDigest localMessageDigest = null;
        try {
            localMessageDigest = MessageDigest.getInstance(paramString2);
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            localNoSuchAlgorithmException.printStackTrace();
        }
        localMessageDigest.reset();
        byte[] arrayOfByte1 = paramString1.getBytes();
        byte[] arrayOfByte2 = localMessageDigest.digest(arrayOfByte1);
        Base64 localBASE64Encoder = new Base64();
        return localBASE64Encoder.encodeToString(arrayOfByte2);
    }







    public static void main(String[] args) throws  Exception {

        //OrgnizationConfig.CRYPTOGRAM_ALGORITHM
        //System.out.println(CrytogramUtil.encrypt("1qaz!QAZ", "MD5"));
        //System.out.println(CrytogramUtil.encrypt("124中文内容", "ABCDE"));
    }
}

