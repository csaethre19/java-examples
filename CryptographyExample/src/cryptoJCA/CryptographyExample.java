package cryptoJCA;

import java.security.NoSuchAlgorithmException;
import javax.crypto.*;

import java.security.InvalidKeyException;

public class CryptographyExample {

	public static void main(String[] args) throws NoSuchAlgorithmException, 
		NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		//generate key using AES algorithm
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		SecretKey aesKey = keyGen.generateKey();
		
		//create Cipher instance using the AES algorithm
		Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		
		//intiialize the cipher for encryption
		aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
		
		//clear text example
		byte[] password = "myPassw0rd".getBytes();
		
		//encrypt the clear text
		byte[] ciphertext = aesCipher.doFinal(password);
		
		String encryptedPassword = new String(ciphertext);
		System.out.println("Encrypted password: " + encryptedPassword);
		
		//initialize the same cipher for decryption
		aesCipher.init(Cipher.DECRYPT_MODE, aesKey);
		
		//decrypt cipher message
		byte[] decryptPassword = aesCipher.doFinal(ciphertext);
		String clearPassword = new String(decryptPassword);
		System.out.println("Decrypted password: " + clearPassword);
		
	}

}
