package com.haothink.ThreadDemo.concurrent.process;

import java.io.IOException;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) throws IOException  {
		String cmd = "cmd "+"/c "+"ipconfig/all";
		Process process = Runtime.getRuntime().exec(cmd);
		Scanner scanner = new Scanner(process.getInputStream());

		while(scanner.hasNextLine()){
			System.out.println(scanner.nextLine());
		}
		scanner.close();
	}
}