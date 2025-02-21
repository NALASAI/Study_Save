package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


// Authenticator을 상속받는데 이때 java.net이 아닌 javax.mail을 상속받아야 한다.
public class Gmail extends Authenticator{	
	
	// 사용자에게 메일을 전송할 관리자의 Gmail 계정의 아이디와 비밀번호를 넣어야한다.
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("warriger@gmail.com", "@kahan1996");
	}
}
