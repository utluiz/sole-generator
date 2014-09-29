package br.com.starcode.math;

import java.io.FileOutputStream;

import org.junit.Assert;
import org.junit.Test;

public class SoleTest {
	
	@Test
	public void write() throws Exception {
		
		Sole s = SoleBuilder.generateRandomConsistentInt(32, -10, 10);
		s.writeTo(new FileOutputStream("target/matrix1.txt"), "UTF-8");
		System.out.println("-------------");
		
	}
	
	@Test
	public void generateConsistentInt() throws Exception {
		
		Sole s = SoleBuilder.generateRandomConsistentInt(12, -10, 10);
		s.print();
		System.out.println("-------------");
		
	}

	@Test
	public void consistent() throws Exception {
		
		//Sole s = SoleBuilder.generateRandom(3, -10, 10);
		Sole s = new Sole(new double[][] {
				{ 1.0,  3.0,  6.0 },
				{ 3.0, -2.0,  3.0 },
				{ 2.0,  6.0, 12.0 }
			}, 
			new double[] { -64.0/5.0, 3.0, -34.0/15.0 }, 
			new double[] { -3.0, 8.0, -6.0 } 
		);
		s.print();
		s.makeTriangular();
		s.print();
		Assert.assertTrue(s.isConsistent());
		System.out.println("-------------");
		
	}
	
	@Test
	public void inconsistent() throws Exception {
		
		Sole s = new Sole(new double[][] {
				{  1.0,  2.0,  6.0 },
				{ -1.0,  1.0, -2.0 },
				{  1.0, -4.0, -2.0 }
			}, 
			new double[] { 0.0, 0.0, 0.0 }, 
			new double[] { 5.0, 3.0, 1.0 } 
		);
		s.print();
		s.makeTriangular();
		s.print();
		Assert.assertFalse(s.isConsistent());
		System.out.println("-------------");
		
	}
	
}
