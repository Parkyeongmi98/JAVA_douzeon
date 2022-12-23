package chapter03;

public class ArrayUtil {

	public static double[] intToDouble(int[] is) {
		double[] result = new double[is.length];
		
		for(int i = 0; i < is.length; i++) {
			result[i] = is[i];
		}
		return result;
	}
	
	public static int[] doubleToInt(double[] is) {
		int[] result = new int[is.length];
		
		for(int i = 0; i < is.length; i++) {
			result[i] = (int) is[i];
		}
		return result;
	}

	public static int[] concat(int[] is, int[] is2) {
		int sumResult = is.length + is2.length;
		int[] result = new int[sumResult];
		
		for(int i = 0; i < sumResult; i++) {
			if(i < is.length) {
			result[i] = is[i];
			} else {
				result[i] = is2[i - is.length];
			}
		}
		return result;

	}
}
