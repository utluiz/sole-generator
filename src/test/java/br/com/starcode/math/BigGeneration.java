package br.com.starcode.math;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.Test;

public class BigGeneration {
	
	@Test
	public void write() throws Exception {
		
		new File("target/generated").mkdirs();
		for (int i = 2; i <=32; i += 2) {
			
			for (int j = 0; j < 5; j++) {
				
				Sole s = SoleBuilder.generateRandomConsistent(i, -20, 20);
				s.writeTo(new FileOutputStream("target/generated/m" + i + "-" + (j+1) + ".txt"), "UTF-8");
				//System.out.println("-------------");
				
			}
			
		}
		
		/*double m5[][] = {
				{5, 0, 0, -3, -1},
				{-1, 4, 0, 0, -1},
				{0, 0, 2, -1, 0},
				{-1, 0, 0, 4, -2 },
				{0, 0, 0, -1, -2},
		};
		double b[] = {2, 3, -1, 0, -1};*/
		
		
	}
	
}
