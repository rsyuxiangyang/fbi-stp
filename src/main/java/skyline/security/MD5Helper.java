package skyline.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: zhanrui
 * Date: 12-5-10
 * Time: 上午11:28
 * To change this template use File | Settings | File Templates.
 */
public class MD5Helper {
    final static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    private static MessageDigest messagedigest = null;
    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println(MD5Helper.class.getName() + "MD5初始失败!");
            e.printStackTrace();
        }
    }

    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }
    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        // 取字节中高 4 位的数字转换
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        // 取字节中低 4 位的数字转换
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static void main(String[] args)  {
        String md5 = MD5Helper.getMD5String("1111");
        System.out.println("pwd:1111  md5:" + md5 );  //b59c67bf196a4758191e42f76670ceba
        md5 = MD5Helper.getMD5String("8888");
        System.out.println("pwd:8888  md5:" + md5 );   //cf79ae6addba60ad018347359bd144d2
        md5 = MD5Helper.getMD5String("111111");
        System.out.println("pwd:111111  md5:" + md5 );  //96e79218965eb72c92a549dd5a330112
    }
}
