package passwordBasedEncrpt;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PBE {
	public static void main(String[] args) throws NoSuchAlgorithmException, 
		InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, 
		InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		PBEKeySpec pbeKeySpec;
		PBEParameterSpec pbeParamSpec;
		SecretKeyFactory keyFac;
		
		//Salt
		byte[] salt = new byte[20]; 
		Random rand = new SecureRandom();
		rand.nextBytes(salt);
		
		//iteration count
		int count = 1000;
		
		//create PBE parameter set
		pbeParamSpec = new PBEParameterSpec(salt, count);

		//Prompt user for encryption password.
		//Collect user password as char array, convert it 
		//into a SecretKey object, using PBE key factory.
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter encryption password: ");
		String pw = sc.nextLine();
		sc.close();
		char[] encrypt = pw.toCharArray();
		pbeKeySpec = new PBEKeySpec(encrypt);
		keyFac = SecretKeyFactory.getInstance("PBEWithHmacSHA256AndAES_256");
		SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
		
		//getEncoded returns the encoded key which in our case is the password
		String s = new String(pbeKey.getEncoded());
		System.out.println("password to be encrypted: " + s);
		
		//Create PBE Cipher
		Cipher cipher = Cipher.getInstance("PBEWithHmacSHA256AndAES_256");
		
		//Initialize the PBE Cipher with the key and parameters
		cipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);
		
		byte[] cypherpw = cipher.doFinal(pbeKey.getEncoded());
		String encrypted = new String(cypherpw);
		System.out.println("cipher password: " + encrypted);
		
		//decrypt password
		cipher.init(Cipher.DECRYPT_MODE, pbeKey);
		byte[] decryptpw = cipher.doFinal(cypherpw);
		String decrypted = new String(decryptpw);
		System.out.println("decrypted password: " + decrypted);
	}
}
