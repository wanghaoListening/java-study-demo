package com.haothink.signature.sign;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 微软产品的序列号验证用的ECDSA
 * 椭圆曲线数字签名算法
 * 速度快，强度高，签名短
 * */
public class HaoECDSA {
	
	public static byte[] byteStr;
	public static KeyPairGenerator generator;
	public static ECPublicKey ecPublicKey;
	public static ECPrivateKey ecPrivateKey;
	public static KeyFactory keyFactory;
	public static Signature signature;
	static{
		try {
			//1.初始化密钥
			generator = KeyPairGenerator.getInstance("EC");
			generator.initialize(256);
			KeyPair keypair = generator.generateKeyPair();
		    ecPublicKey = (ECPublicKey) keypair.getPublic();
		    ecPrivateKey = (ECPrivateKey) keypair.getPrivate();
		} catch (NoSuchAlgorithmException e) {
			
			throw new RuntimeException();
		}
	}

	
	public static byte[] executeEC(String ecStr){
		
		try {
			PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
			keyFactory = KeyFactory.getInstance("EC");
			PrivateKey priKeypriKey = keyFactory.generatePrivate(encodedKeySpec);
			signature = Signature.getInstance("SHA1withECDSA");
			signature.initSign(priKeypriKey);
			byteStr = ecStr.getBytes();
			signature.update(byteStr);
			return signature.sign();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public static boolean verifyEC(byte[] sign){
		try {
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("EC");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			signature = Signature.getInstance("SHA1withECDSA");
			signature.initVerify(publicKey);
			signature.update(byteStr);
			return signature.verify(sign);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}
