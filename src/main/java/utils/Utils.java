package utils;

import java.util.Base64;

public class Utils {
	public static String decode64(String encodedStg) {
		Base64.Decoder decoder = Base64.getDecoder();
		return new String(decoder.decode(encodedStg.getBytes()));
	}
}