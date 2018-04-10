/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programerino3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author owner
 */
public class Programerino3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        MatrixGraph mg = new MatrixGraph();
        mg.build();
        int[][] x = mg.primAlgorithm1();
        int[][] y = mg.kruskalAlgorithm2();
        mg.printMatrix(x, "Prim");
        mg.finalOutput(x, 0);
        mg.printMatrix(y, "Kruskal");
        mg.finalOutput(y, 0);
       // mg.kruskalAlgorithm2();
        mg.floydAlgorithm3();
        //mg.printMatrix(x);
    }
}
