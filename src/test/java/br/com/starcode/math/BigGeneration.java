package br.com.starcode.math;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.Test;

public class BigGeneration {
	
	@Test
	public void write() throws Exception {
		
		new File("target/generated").mkdirs();
		for (int i = 4; i <=1024; i *= 2) {
			
			for (int j = 0; j < 5; j++) {
				
				Sole s = SoleBuilder.generateRandomConsistentInt(i, -20, 20);
				s.writeTo(new FileOutputStream("target/generated/m" + i + "-" + (j+1) + ".txt"), "UTF-8");
				System.out.println("-------------");
				
			}
			
		}
		
		
	}
	
}
