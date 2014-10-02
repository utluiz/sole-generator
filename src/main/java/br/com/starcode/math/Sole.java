package br.com.starcode.math;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


/**
 * Represents a System of linear equations: A.x = b
 */
public class Sole {

	protected double[][] a;
	protected double[] x, b;
	protected int size;
	
	public Sole(double[][] a, double[] x, double[] b) {
		if (a == null || x == null || b == null) {
			throw new IllegalArgumentException("Null parameters!");
		}
		if (a.length != b.length || a.length != b.length) {
			throw new IllegalArgumentException("Size of arrays are not consistent!");
		}
		if (a.length == 0) {
			throw new IllegalArgumentException("Size cannot be zero!");
		}
		size = a.length; 
		for (int i = 0; i < size; i++) {
			if (a[i] == null) {
				throw new IllegalArgumentException("Row " + (i+1) + " is null!");
			}
			if (a[i].length != size) {
				throw new IllegalArgumentException("Row " + (i+1) + " has an inconsistent size!");
			}
		}
		this.a = a;
		this.x = x;
		this.b = b;
	}
	
	public double[][] getA() {
		return a;
	}
	
	public double[] getB() {
		return b;
	}
	
	public double[] getX() {
		return x;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		double[][] na = new double[size][size];
		double[] nx = new double[size], nb = new double[size];
		System.arraycopy(b, 0, nb, 0, size);
		System.arraycopy(x, 0, nx, 0, size);
		for (int i = 0; i < size; i++) {
			System.arraycopy(a[i], 0, na[i], 0, size);
		}
		return new Sole(na, nx, nb);
	}
	
	/*
	 * Try to generate a triangular matrix and checks if result is zero
	 * http://www.wyzant.com/resources/lessons/math/precalculus/systems_of_equations/consistent_and_inconsistent_systems 
	 */
	public boolean isConsistent() {
		try {
			Sole s = (Sole) this.clone();
			s.makeTriangular();
			if (s.a[s.size - 1][s.size - 1] == 0.00 && s.b[s.size - 1] != 0.0) {
				return false;
			}
		} catch (CloneNotSupportedException e) {
			//nothing
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Based on:
	 * https://github.com/vkostyukov/la4j/blob/master/src/main/java/org/la4j/matrix/AbstractMatrix.java
	 */
	public void makeTriangular() {
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				double c = a[j][i] / a[i][i];
				for (int k = i; k < size; k++) {
					if (k == i) {
						a[j][k] = 0.0;
					} else {
						a[j][k] -= a[i][k] * c;
					}
				}
				b[j] -= b[i] * c;
			}
		}
	}
	
	public void print() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (j > 0) {
					System.out.print(" ");
				}
				System.out.printf("%+.6f", a[i][j]);
			}
			System.out.print(" = ");
			System.out.println(b[i]);
		}
		System.out.print("(");
		for (int i = 0; i < size; i++) {
			if (i > 0) {
				System.out.print(", ");
			}
			System.out.printf("%+.6f", x[i]);
		}
		System.out.println(")");
	}
	
	private String pad(String s, int l) {
		while (s.length() < l) {
			 s = " " + s;		
		}
		return s;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("0.0#####", dfs);
		
		sb.append(size);
		sb.append('\n');
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (j > 0) {
					sb.append(' ');
				}
				sb.append(pad(df.format(a[i][j]), 10));
			}
			sb.append(" = ");
			sb.append(pad(df.format(b[i]), 10));
			sb.append('\n');
		}
		sb.append('\n');
		for (int i = 0; i < size; i++) {
			if (i > 0) {
				sb.append(' ');
			}
			sb.append(pad(df.format(x[i]), 10));
		}
		sb.append('\n');
		return sb.toString();
	}
	
	public void writeTo(OutputStream os, String charset) throws UnsupportedEncodingException, IOException {
		os.write(toString().getBytes(charset));
	}
	
}
