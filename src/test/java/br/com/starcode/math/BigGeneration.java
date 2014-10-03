package br.com.starcode.math;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.Test;
import org.la4j.LinearAlgebra;
import org.la4j.linear.LinearSystemSolver;
import org.la4j.matrix.Matrix;
import org.la4j.matrix.sparse.CRSMatrix;
import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

public class BigGeneration {
	
	@Test
	public void write() throws Exception {
		
		new File("target/generated").mkdirs();
		for (int i = 1024; i <= 1024; i += 2) {
			
			for (int j = 0; j < 40; j++) {
				
				final int ii = i, jj = j;
				final File f = new File("target/generated/m" + ii + "-" + (jj+1) + ".txt");
				if (f.exists()) continue;
				Thread t = new Thread() {
					
					@Override
					public void run() {
						System.out.println(" " + ii + " " + f.getName());
						Sole s = SoleBuilder.generateRandomConsistentInt(ii, -10, 10);
						Matrix a = new CRSMatrix(s.getA());
						Vector b = new BasicVector(s.b);
						LinearSystemSolver solver = a.withSolver(LinearAlgebra.JACOBI);
						solver.solve(b, LinearAlgebra.SPARSE_FACTORY);
						try {
							s.writeTo(new FileOutputStream(f), "UTF-8");
							System.out.println("ok " + f.getName());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				t.start();
			}
			
		}
		Thread.sleep(15000);
		
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
