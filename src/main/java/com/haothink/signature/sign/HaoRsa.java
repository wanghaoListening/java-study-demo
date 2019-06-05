package com.haothink.signature.sign;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class HaoRsa {
	public static byte[] byteStr;
	public static KeyPairGenerator generator;
	public static RSAPublicKey rsaPublicKey;
	public static RSAPrivateKey rsaPrivateKey;
	public static KeyFactory keyFactory;
	public static Signature signature;
	static{
		try {

			generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(512);
			KeyPair keypair = generator.generateKeyPair();
		    rsaPublicKey = (RSAPublicKey) keypair.getPublic();
		    rsaPrivateKey = (RSAPrivateKey) keypair.getPrivate();
		} catch (NoSuchAlgorithmException e) {
			
			throw new RuntimeException();
		}
	}

	
	public static byte[] executeRSA(String rsaStr){
		
		try {
			PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey priKeypriKey = keyFactory.generatePrivate(encodedKeySpec);
			signature = Signature.getInstance("MD5withRSA");
			signature.initSign(priKeypriKey);
			byteStr = rsaStr.getBytes();
			signature.update(byteStr);
			return signature.sign();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public static boolean verifyRSA(byte[] sign){
		try {
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			signature = Signature.getInstance("MD5withRSA");
			signature.initVerify(publicKey);
			signature.update(byteStr);
			return signature.verify(sign);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}
