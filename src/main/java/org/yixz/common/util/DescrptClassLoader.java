package org.yixz.common.util;

import java.io.*;
import java.security.*;
import java.lang.reflect.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DescrptClassLoader extends ClassLoader
{
    // 这些对象在构造函数中设置，以后loadClass()方法将利用它们解密类
    private SecretKey key;
    private Cipher cipher;

    // 构造函数：设置解密所需要的对象
    public DescrptClassLoader(SecretKey key) throws GeneralSecurityException, IOException {
        this.key = key;

        String algorithm = "DES";
        SecureRandom sr = new SecureRandom();
        System.err.println("[DecryptStart: creating cipher]");
        cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
    }

    // main过程：在这里读入密匙，创建DecryptStart的实例，它就是定制ClassLoader。
    // 设置好ClassLoader以后，用它装入应用实例，
    // 最后，通过Java Reflection API调用应用实例的main方法
    public static void main(String args[]) throws Exception {
        String keyFilename = args[0];
        String appName = args[1];

        // 传递给应用本身的参数
        String realArgs[] = new String[args.length-2];
        System.arraycopy( args, 2, realArgs, 0, args.length-2 );

        // 读取密匙
        System.err.println( "[DecryptStart: reading key]" );
        byte rawKey[] = EncryptUtil.readFile(keyFilename);
        DESKeySpec dks = new DESKeySpec(rawKey);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);

        // 创建解密的ClassLoader
        DescrptClassLoader dr = new DescrptClassLoader(key);

        // 创建应用主类的一个实例，通过ClassLoader装入它
        System.err.println("[DecryptStart: loading "+appName+"]");
        Class clasz = dr.loadClass(appName);

        // 最后通过Reflection API调用应用实例的main()方法

        // 获取一个对main()的引用
        String proto[] = new String[1];
        Class mainArgs[] = { (new String[1]).getClass() };
        Method main = clasz.getMethod("main", mainArgs);

        // 创建一个包含main()方法参数的数组
        Object argsArray[] = { realArgs };
        System.err.println("[DecryptStart: running "+appName+".main()]");

        // 调用main()
        main.invoke(null, argsArray);
    }

    public Class loadClass(String name, boolean resolve) {
        try {
            Class clasz = null;
            clasz = findLoadedClass(name);
            if (clasz != null) {
                return clasz;
            }
            //读取经过加密的类文件
            byte classData[] = EncryptUtil.readFile(name+".class");
            if(classData != null){
                //解密
                byte decryptedClassData[] = cipher.doFinal(classData);
                // 再把它转换成一个类
                clasz = defineClass( name, decryptedClassData, 0, decryptedClassData.length);
            }

            if (clasz == null) {
                clasz = findSystemClass(name);
            }
            if (resolve && clasz != null) {
                resolveClass(clasz);
            }
            return clasz;
        } catch(Exception ie) {

        }
        return null;
    }
}