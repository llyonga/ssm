package com.io.ssm.module.common.utils.cryptography;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @date 2017年6月27日
 * @author lvyong
 * @version 2017-06-27 下午10:47:28
 */
public class CryptographyUtil {

    /**
     * base64加密
     * @param str
     * @return
     */
    public static String encBase64(String str){
        return Base64.encodeToString(str.getBytes());
    }

    /**
     * base64解密
     * @param str
     * @return
     */
    public static String decBase64(String str){
        return Base64.decodeToString(str);
    }

    /**
     * Md5加密
     * @param str
     * @param salt
     * @return
     */
    public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }

    /**
     * 用于登录加密（sha-256）
     * 盐值为username得到
     *
     * @param username
     * @param password
     * @param hashIterations
     * @return
     */
    public static String sha256(String username,String password,int hashIterations) {
        Object salt = ByteSource.Util.bytes(username);
        return new Sha256Hash(password, salt, hashIterations).toHex();
    }

    /**
     * 简单散列加密算法
     * @param hashAlgorithmName
     * @param credentials
     * @param password
     * @param hashIterations
     * @return
     */
    public static String simpleHash(String hashAlgorithmName,String credentials,String password,int hashIterations) {
        Object salt = ByteSource.Util.bytes(hashAlgorithmName);
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toHex();
    }

    public static void main(String[] args) {
        String password="123456";
        System.out.println("Base64加密："+CryptographyUtil.encBase64(password));
        System.out.println("Base64解密："+CryptographyUtil.decBase64(CryptographyUtil.encBase64(password)));

        System.out.println("Md5加密："+CryptographyUtil.md5(password, "java1234"));

        System.out.println("**********************");
        System.out.println(sha256("admin", "admin", 1024)); //40941538609061b2c98b2cc12860cc52a6abab96230e16ad0f0011088fe52dd8
    }
}

