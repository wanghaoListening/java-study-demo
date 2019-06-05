package com.haothink.signature.sign;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author wanghao
 * SHA1withDSA
 * */
public class HaoDsa {
	
	public static byte[] byteStr;
	public static KeyPairGenerator generator;
	public static DSAPublicKey dsaPublicKey;
	public static DSAPrivateKey dsaPrivateKey;
	public static KeyFactory keyFactory;
	public static Signature signature;
	static{
		try {

			generator = KeyPairGenerator.getInstance("DSA");
			generator.initialize(512);
			KeyPair keypair = generator.generateKeyPair();
		    dsaPublicKey = (DSAPublicKey) keypair.getPublic();
		    dsaPrivateKey = (DSAPrivateKey) keypair.getPrivate();
		} catch (NoSuchAlgorithmException e) {
			
			throw new RuntimeException();
		}
	}

	
	public static byte[] executeDSA(String dsaStr){
		
		try {
			PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(dsaPrivateKey.getEncoded());
			keyFactory = KeyFactory.getInstance("DSA");
			PrivateKey priKeypriKey = keyFactory.generatePrivate(encodedKeySpec);
			signature = Signature.getInstance("SHA1withDSA");
			signature.initSign(priKeypriKey);
			byteStr = dsaStr.getBytes();
			signature.update(byteStr);
			return signature.sign();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public static boolean verifyDSA(byte[] sign){
		try {
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(dsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("DSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			signature = Signature.getInstance("SHA1withDSA");
			signature.initVerify(publicKey);
			signature.update(byteStr);
			return signature.verify(sign);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}
