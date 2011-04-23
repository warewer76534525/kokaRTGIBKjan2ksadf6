package chat.downloader.main;

import org.jivesoftware.smack.XMPPException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) throws XMPPException {
		new ClassPathXmlApplicationContext("cd-context.xml");
	}
}
