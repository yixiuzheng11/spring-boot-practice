package org.yixz.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年11月29日 16:47
 */
public class EncryptUtil {
    static public void main(String args[]) throws Exception {
        String keyFilename = args[0];
        String algorithm = "DES";

        // 生成密匙
        SecureRandom sr = new SecureRandom();
        byte rawKey[] = readFile(keyFilename);
        DESKeySpec dks = new DESKeySpec(rawKey);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( algorithm );
        SecretKey key = keyFactory.generateSecret(dks);

        // 创建用于实际加密操作的Cipher对象
        Cipher ecipher = Cipher.getInstance(algorithm);
        ecipher.init(Cipher.ENCRYPT_MODE, key, sr);

        // 加密指定的每一个类
        for (int i=1; i<args.length; ++i) {
            String filename = args[i];
            //读入类文件
            byte classData[] = readFile(filename);
            //加密
            byte encryptedClassData[] = ecipher.doFinal(classData);
            // 保存加密后的内容
            writeFile(filename, encryptedClassData);
            System.out.println("加密之后的文件 "+filename);
        }
    }

    public static byte[] readFile(String fileName) throws Exception {
        File file = new File(fileName);
        long len = file.length();
        byte data[] = new byte[(int)len];
        FileInputStream fin = new FileInputStream(file);
        int r = fin.read(data);
        if (r != len)
            throw new IOException("Only read "+r+" of "+len+" for "+file);
        fin.close();
        return data;
    }

    // 把byte数组写出到文件
    public static void writeFile(String filename, byte data[]) throws IOException {
        FileOutputStream fout = new FileOutputStream(filename);
        fout.write(data);
        fout.close();
    }
}
