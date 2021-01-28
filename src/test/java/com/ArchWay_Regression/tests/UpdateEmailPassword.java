package com.ArchWay_Regression.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class UpdateEmailPassword {

	public static void main(String[] args) throws IOException {
		FileInputStream in = new FileInputStream("C:\\Users\\APoyil\\Git\\New folder\\ArchWay_Regression\\src\\main\\java\\com\\ArchWay_Regression\\BaseClass\\Confidential.properties");
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream("C:\\Users\\APoyil\\Git\\New folder\\ArchWay_Regression\\src\\main\\java\\com\\ArchWay_Regression\\BaseClass\\Confidential.properties");
		props.setProperty("email", "EmailToChange");
		props.setProperty("password", "PasswordToChange*");
		System.out.println("Updated");
		props.store(out, null);
		out.close();

	}

}
