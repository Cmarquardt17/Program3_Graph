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
        Charset text = Charset.forName("UTF-8");
        Path base = Paths.get("Input/Input.txt");
        String line = "";
        String splitBy = ",";
        int[][] graph;
        try (BufferedReader reader = Files.newBufferedReader(base, text)) {
            String firstLine = reader.readLine();
            String[] firstLineArray = firstLine.split(splitBy);
            graph = new int[firstLineArray.length][firstLineArray.length];
            //double embedded for loop but slightly different
            int row =0;
            while ((line = reader.readLine()) != null) { 
               String[] holder = line.split(splitBy);
            for(int i = 0; holder.length > i; i++)
            {
                //infinity sign = 8734
                //i is the spot we are at for looping in the columns
                if((int)holder[i].charAt(0) == 8734)
                {
                    graph[row][i] = Integer.MAX_VALUE;
                }
                else{
                    graph[row][i] = Integer.parseInt(holder[i]);
                }
            } 
            row++;
            }
    }
    
}
}
