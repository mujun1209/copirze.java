package triones;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
/**
*私鈅加密，保证消息机密性
*/
public class TestAES
{
	public static void run(String key,String txt)
		throws Exception
	{
		String lEncode = "GBK";
		byte[] lsTxt = txt.getBytes(lEncode);
		byte[] lsKey = key.getBytes(lEncode);
		//通过KeyGenerator形成一个key
		System.out.println("\nStart generate AES key");
		Key lKey= new SecretKeySpec(lsKey,"AES");
		System.out.println("Finish generating AES key");
		//获得一个私鈅加密类Cipher，ECB是加密方式，PKCS5Padding是填充方法
		//Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
		Cipher cipher=Cipher.getInstance("AES");
		//System.out.println("\n"+cipher.getProvider().getInfo());
		//使用私鈅加密
		System.out.println("\nStart encryption:");
		//cipher.init(Cipher.ENCRYPT_MODE,lKey);
		cipher.init(Cipher.WRAP_MODE,lKey);
		byte[] cipherText=cipher.doFinal(lsTxt);
		System.out.println("Finish encryption:");
		System.out.println("[" + new String(cipherText,lEncode) + "]");
		System.out.println("\nStart decryption:");
		//cipher.init(Cipher.DECRYPT_MODE,lKey);
		cipher.init(Cipher.UNWRAP_MODE,lKey);
		byte[] newPlainText=cipher.doFinal(cipherText);
		System.out.println("Finish decryption:");
		System.out.println("[" + new String(newPlainText,lEncode)+ "]");
	}

}
