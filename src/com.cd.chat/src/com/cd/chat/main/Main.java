package com.cd.chat.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("cd-context.xml");
	}
}
