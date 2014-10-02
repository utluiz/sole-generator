package br.com.starcode.math;

public class SoleBuilder {

	public static Sole generateRandom(int size, double minValue, double maxValue) {
		
		double range = maxValue - minValue;
		double[][] a = new double[size][size];
		double[] x = new double[size], b = new double[size];
		
		for (int i = 0; i < size; i++) {
			x[i] = Math.random() * range + minValue;
			b[i] = 0;
			for (int j = 0; j < size; j++) {
				a[i][j] = Math.random() * range + minValue;
				b[i] += a[i][j] * x[i]; 
			}
		}
		
		return new Sole(a, x, b);
		
	}
	
	public static Sole generateRandomInt(int size, int minValue, int maxValue) {
		
		double range = maxValue - minValue;
		double[][] a = new double[size][size];
		double[] x = new double[size], b = new double[size];
		
		for (int i = 0; i < size; i++) {
			x[i] = Math.round(Math.random() * range + minValue);
			b[i] = 0;
			for (int j = 0; j < size; j++) {
				a[i][j] = Math.round(Math.random() * range + minValue);
				b[i] += a[i][j] * x[i]; 
			}
		}
		
		return new Sole(a, x, b);
		
	}
	
	public static Sole generateRandom(int size) {
		
		return generateRandom(size, -10, 10);
		
	}
	
	public static Sole generateRandomInt(int size) {
		
		return generateRandomInt(size, -10, 10);
		
	}
	
	public static Sole generateRandomConsistent(int size) {
		
		Sole s = null;
		int t = 1;
		do {
			//System.out.println("trying " + t++);
			s = generateRandom(size);
		} while (!s.isConsistent());
		return s;
		
	}
	
	public static Sole generateRandomConsistentInt(int size) {
		
		Sole s = null;
		int t = 1;
		do {
			//System.out.println("trying " + t++);
			s = generateRandomInt(size);
		} while (!s.isConsistent());
		return s;
		
	}
	
	public static Sole generateRandomConsistentInt(int size, int minValue, int maxValue) {
		
		Sole s = null;
		int t = 1;
		do {
			//System.out.println("trying " + t++);
			s = generateRandomInt(size, minValue, maxValue);
		} while (!s.isConsistent());
		return s;
		
	}
	
}
