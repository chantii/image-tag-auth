import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class CipherExample {
	
	 private static void deletefile(String file){
		  File f1 = new File(file);
		  boolean success = f1.delete();
		  if (!success){
		  System.out.println("Deletion failed.");
		  System.exit(0);
		  }else{
		  System.out.println("File deleted.");
		    }
		  }

		public static void encrypt(String file) {
			try {
				String key = "mahesh-naidu"; // needs to be at least 8 characters for DES

				FileInputStream fis = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(file+"env");
				encrypt(key, fis, fos);

				//FileInputStream fis2 = new FileInputStream("dataeny.rar");
				//FileOutputStream fos2 = new FileOutputStream("data.rar");
				//decrypt(key, fis2, fos2);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
		public static void decrypt(String file) {
			try {
				String key = "mahesh-naidu"; // needs to be at least 8 characters for DES

				//FileInputStream fis = new FileInputStream(file);
				//FileOutputStream fos = new FileOutputStream(file);
				//encrypt(key, fis, fos);
				FileInputStream fis2 = new FileInputStream(file);
				file = file.substring(0,file.length() - 3 );
				FileOutputStream fos2 = new FileOutputStream(file);
				decrypt(key, fis2, fos2);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
	/* public static void main(String[] args) {
		try {
			String key = "mahesh-naidu"; // needs to be at least 8 characters for DES

			FileInputStream fis = new FileInputStream("data.rar");
			FileOutputStream fos = new FileOutputStream("dataeny.rar");
			encrypt(key, fis, fos);

			FileInputStream fis2 = new FileInputStream("dataeny.rar");
			FileOutputStream fos2 = new FileOutputStream("data.rar");
			decrypt(key, fis2, fos2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}*/

	public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}

	public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}

	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}

}