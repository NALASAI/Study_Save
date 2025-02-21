package util;

import java.security.MessageDigest;

public class SHA256{
	
	// 이메일 값을 받은 후 해시를 적용한 값을 반환해서 이용하는 유틸
	public static String getSHA256(String input) {
		StringBuffer result = new StringBuffer();
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] salt = "Hello! This is Salt.".getBytes();
			// salt의 경우 SHA256을 적용하게 되면 해커에 의해 해킹을 당할 가능성이 높기 때문에 salt값을 미리 적용한다.
			digest.reset();
			digest.update(salt);
			byte[] chars = digest.digest(input.getBytes("UTF-8"));
			for(int i = 0; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				if(hex.length() == 1) result.append("0");
				// hash값을 적용한 chars의 해당 인덱스를 AND 연산을 통해 한자리 수인 경우를 제외한 나머지에 0을 붙여서 총2자리 값을 가지는 16진수형식으로 출력할 수 있도록 제작
				result.append(hex);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result.toString();
	}
}