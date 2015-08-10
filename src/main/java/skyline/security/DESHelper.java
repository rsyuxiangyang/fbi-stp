package skyline.security;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.Security;

/**   
 *@Title:   DES��������㷨��
 *@Description:   
 *@Author:
 *@Create:Jan 27, 2011 3:02:18 PM   
 *@Version:1.1   
 */
public class DESHelper {
	private static String strDefaultKey = "szlcsoft";
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;

    /**
     * ��byte����ת��Ϊ��ʾ16����ֵ���ַ����� �磺byte[]{8,18}ת��Ϊ��0813�� ��public static byte[]
     * hexStr2ByteArr(String strIn) ��Ϊ�����ת������
     * 
     * @param arrB ��Ҫת����byte����
     * @return ת������ַ���
     * @throws Exception �������������κ��쳣�������쳣ȫ���׳�
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // ÿ��byte�������ַ����ܱ�ʾ�������ַ����ĳ��������鳤�ȵ�����
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // �Ѹ���ת��Ϊ����
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // С��0F������Ҫ��ǰ�油0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * ����ʾ16����ֵ���ַ���ת��Ϊbyte���飬 ��public static String byteArr2HexStr(byte[] arrB)
     * ��Ϊ�����ת������
     * 
     * @param strIn
     *            ��Ҫת�����ַ���
     * @return ת�����byte����
     * @throws Exception
     *             �������������κ��쳣�������쳣ȫ���׳�
     * @author <a href="mailto:wangchongan@gmail.com">WangChongAn</a>
     */
    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // �����ַ���ʾһ���ֽڣ������ֽ����鳤�����ַ������ȳ���2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    /**
     * Ĭ�Ϲ��췽����ʹ��Ĭ����Կ
     * 
     * @throws Exception
     */
    public DESHelper() throws Exception {
        this(strDefaultKey);
    }

    /**
     * ָ����Կ���췽��
     * 
     * @param strKey
     *            ָ������Կ
     * @throws Exception
     */
    public DESHelper(String strKey) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Key key = getKey(strKey.getBytes());

        encryptCipher = Cipher.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);

        decryptCipher = Cipher.getInstance("DES");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }

    /**
     * �����ֽ�����
     * 
     * @param arrB
     *            ����ܵ��ֽ�����
     * @return ���ܺ���ֽ�����
     * @throws Exception
     */
    public byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    /**
     * �����ַ���
     * 
     * @param strIn
     *            ����ܵ��ַ���
     * @return ���ܺ���ַ���
     * @throws Exception
     */
    public String encrypt(String strIn) throws Exception {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }

    /**
     * �����ֽ�����
     * 
     * @param arrB
     *            ����ܵ��ֽ�����
     * @return ���ܺ���ֽ�����
     * @throws Exception
     */
    public byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }

    /**
     * �����ַ���
     * 
     * @param strIn
     *            ����ܵ��ַ���
     * @return ���ܺ���ַ���
     * @throws Exception
     */
    public String decrypt(String strIn) throws Exception {
        return new String(decrypt(hexStr2ByteArr(strIn)));
    }

    /**
     * ��ָ���ַ���������Կ����Կ������ֽ����鳤��Ϊ8λ ����8λʱ���油0������8λֻȡǰ8λ
     * 
     * @param arrBTmp
     *            ���ɸ��ַ������ֽ�����
     * @return ���ɵ���Կ
     * @throws Exception
     */
    private Key getKey(byte[] arrBTmp) throws Exception {
        // ����һ���յ�8λ�ֽ����飨Ĭ��ֵΪ0��
        byte[] arrB = new byte[8]; // ��ԭʼ�ֽ�����ת��Ϊ8λ 
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) 
        { 
        	arrB[i] = arrBTmp[i]; 
        } 
        // ������Կ 
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES"); return key; 
    } 
    public static void main(String[] args) throws Exception {
        //ʹ��Ĭ�ϵ���Կ aec504733cfb4112
        System.out.println("ʹ��Ĭ�ϵ���Կ aec504733cfb4112");
    	DESHelper dESHelper = new DESHelper();
    	String e = dESHelper.encrypt("laozhou");
    	System.out.println("laozhou����--------"+e); 
    	String d = dESHelper.decrypt(e);
    	System.out.println("laozhou����--------"+d);

    	//ʹ���Զ�����Կ
        System.out.println("ʹ���Զ�����Կ");
    	DESHelper dESHelper2 = new DESHelper("qingdao_rongshangxinxijishu_youxiangongsi");
    	String e2 = dESHelper2.encrypt("50");
    	System.out.println("13588888888����--------"+e2);
    	String d2 = dESHelper2.decrypt(e2);
    	System.out.println("13588888888����--------"+d2);
    }
}


