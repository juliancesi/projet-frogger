package util;


public class Utils {
	
	public static int binaryOperationAND(int... arg) {
		int res = 0;
		for(int i = 0; i < arg.length - 1; i++) {
			res += arg[i] & arg[i + 1];
		}
		return res;
	}

	public static int binaryOperationOR(int... arg) {
		int res = 0;
		for(int i = 0; i < arg.length - 1; i++) {
			res += arg[i] | arg[i + 1];
		}
		return res;
	}

	public static int binaryOperationXOR(int... arg) {
		int res = 0;
		for(int i = 0; i < arg.length - 1; i++) {
			res += arg[i] ^ arg[i + 1];
		}
		return res;
	}

	public static boolean isNull(String s) {
		if(s == null || s.isEmpty()) {
			return true;
		}
		return false;
	}
}
