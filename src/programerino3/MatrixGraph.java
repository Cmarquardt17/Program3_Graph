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
import java.util.PriorityQueue;

/**
 *
 * @author owner
 */
public class MatrixGraph {

    int[][] graph;

    public MatrixGraph() {

    }

    public void build() throws IOException {
        Charset text = Charset.forName("UTF-8");
        Path base = Paths.get("Input/Input.txt");
        String line = "";
        String splitBy = ",";

        try (BufferedReader reader = Files.newBufferedReader(base, text)) {
            String firstLine = reader.readLine();
            String[] firstLineArray = firstLine.split(splitBy);
            graph = new int[firstLineArray.length][firstLineArray.length];
            //double embedded for loop but slightly different
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] holder = line.split(splitBy);
                for (int i = 0; holder.length > i; i++) {
                    //infinity sign = 8734
                    //i is the spot we are at for looping in the columns
                    if ((int) holder[i].charAt(0) == 8734) {
                        graph[row][i] = Integer.MAX_VALUE;
                    } else {
                        graph[row][i] = Integer.parseInt(holder[i]);
                    }

                }
                row++;

            }
        }

    }

    public void printMatrix() {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void primAlgorithm1() {
        int svertex = 0;
        int[] dvertices = new int[graph.length];
        
        for (int i = 1; i < dvertices.length; i++) {
            dvertices[i] = Integer.MAX_VALUE;
        }
        String output = null; //Eventual output with result
        PriorityQueue<Integer> connection = new PriorityQueue(); //Prioirity Queue initialized
        for (int i = 0; i < dvertices[i]; i++) {
            connection.add(dvertices[i]);    
        }
        while (!connection.isEmpty()) {
            Integer u = connection.remove(); //how do i get min? like a pop
            //u = Integer.parseInt(output); //converting string to an int?
            for (int i = 0; i < dvertices.length; i++) {
                if(graph[u][i] < dvertices[i])
                {
                    dvertices[i] =  graph[u][i];
                }
                
            }
        }
       // return output;
    }

    public void kruskalAlgorithm2() {

    }

    public void floydAlgorithm3() {

    }
}
