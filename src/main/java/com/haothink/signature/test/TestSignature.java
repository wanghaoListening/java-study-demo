package com.haothink.signature.test;

import org.junit.Test;

import com.haothink.signature.sign.HaoDsa;
import com.haothink.signature.sign.HaoECDSA;
import com.haothink.signature.sign.HaoRsa;


public class TestSignature {
	
	@Test
	public void testRSA(){
		byte[] by = HaoRsa.executeRSA("haothink123");
		boolean b= HaoRsa.verifyRSA(by);
		System.out.println(b);
	}
	
	@Test
	public void testDSA(){
		byte[] by = HaoDsa.executeDSA("haothink123");
		boolean b= HaoDsa.verifyDSA(by);
		System.out.println(b);
	}
	
	@Test
	public void testEC(){
		byte[] by = HaoECDSA.executeEC("haothink123");
		boolean b= HaoECDSA.verifyEC(by);
		System.out.println(b);
	}
}
