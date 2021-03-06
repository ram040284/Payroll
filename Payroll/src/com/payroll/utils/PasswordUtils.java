package com.payroll.utils;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class PasswordUtils {
	 
	 public static String getEncryptedPassword(String pwdText) {
		 String encryped = "";
		 try {
			 PasswordUtils pwdUtis = new PasswordUtils();
			 SecretKeySpec  secKey = pwdUtis.getSecretEncryptionKey();
			 if(pwdText.length() < 8) {
				 String tempPwd = "00000000";
				 pwdText += tempPwd.substring(pwdText.length(), tempPwd.length());
			 }
			 byte[] cipherText = pwdUtis.encryptText(pwdText.trim(), secKey);
			 encryped = bytesToHex(cipherText);
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		 return encryped; 
	 }
	 
	 public static boolean isValidPassword(String pwdText, String encrypted) {
		 boolean pwdMatch = false;
		 try {
			 PasswordUtils pwdUtis = new PasswordUtils();
			 SecretKeySpec  secKey = pwdUtis.getSecretEncryptionKey();
			
			 byte[] encByte = hexToBinary(encrypted);
			 String decryptedText = pwdUtis.decryptText(encByte, secKey);
			 
			 if(pwdText.length() < 8) {
				 String tempPwd = "00000000";
				 pwdText += tempPwd.substring(pwdText.length(), tempPwd.length());
			 }
			 if (pwdText.trim().equals(decryptedText)) {
				 pwdMatch = true;
			 }
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		 return pwdMatch; 
	 }
	 
	    /**
	      * gets the AES encryption key. In your actual programs, this should be safely
	      * stored.
	      * @return
	      * @throws Exception
	      */
	     private SecretKeySpec  getSecretEncryptionKey() throws Exception{
	    	 byte[] key = "F4A96816712F1070E3B2641315CAD04F".getBytes("UTF-8");
	    	 MessageDigest sha = MessageDigest.getInstance("SHA-1");
	    	 key = sha.digest(key);
	    	 key = Arrays.copyOf(key, 16); //use only first 128 bit
	    	 SecretKeySpec secKey = new SecretKeySpec(key, "AES");
	         return secKey;
	     }
	      
	     /**
	      * Encrypts plainText in AES using the secret key
	      * @param plainText
	      * @param secKey
	      * @return
	      * @throws Exception
	      */
	     private byte[] encryptText(String plainText,SecretKey secKey) throws Exception{
	         // AES defaults to AES/ECB/PKCS5Padding in Java 7
	         Cipher aesCipher = Cipher.getInstance("AES");
	         aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
	         byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
	         return byteCipherText;
	     }

	     /**
	      * Decrypts encrypted byte array using the key used for encryption.
	      * @param byteCipherText
	      * @param secKey
	      * @return
	      * @throws Exception
	      */
	     private String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {
	         // AES defaults to AES/ECB/PKCS5Padding in Java 7
	         Cipher aesCipher = Cipher.getInstance("AES");
	         aesCipher.init(Cipher.DECRYPT_MODE, secKey);
	         byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
	         return new String (bytePlainText);
	     }
	      
	     /**
	      * Convert a binary byte array into readable hex form
	      * @param hash
	      * @return
	      */
	     private static String  bytesToHex(byte[] hash) {
	         return DatatypeConverter.printHexBinary(hash);
	     }
	     
	     private static byte[] hexToBinary(String hashString) {
	         return DatatypeConverter.parseHexBinary(hashString);
	     }
}
