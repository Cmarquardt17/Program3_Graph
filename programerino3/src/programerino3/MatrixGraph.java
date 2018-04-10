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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Christian Marquardt, Will Dean, Michael Muffoletto
 * Date: 4/9/2018
 * Overview: Here we are working with graphs and implementing common graph algorithms
 * Christian worked on the the Prim Algorithm and helper functions
 * Michael worked on the Kruskal's Algorithm
 * Will worked on the Floyd Warshall's Algorithm 
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

    public void printMatrix(int[][] x, String algName) {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) { //Works with any size graph rect. or square
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("This is the " + algName + " Alogrithm");
    }
    //printing out the connections
      public void finalOutput(int[][] x, int startVertex)
    {
        for(int i =0; i < x.length; i++)
        {
            if(x[startVertex][i] != 0)
            {
                //first vertex
                char print1 = (char) (startVertex + 65);
                //second vertex
                char print2 = (char) (i + 65);
                //print Statement showing that
                System.out.print(print1 + " --> " + print2 + ", ");
                //recursively calls the method on the ending vertex to look for multiple connection
                finalOutput(x, i);
                
            }
            
        }
    }

    public int[][] primAlgorithm1() {
        //starting vertex
        int svertex = 0;
        //array with the length of graph
        int[] dvertices = new int[graph.length];
        //start vertex intially is 0
        dvertices[svertex] = 0;
        for (int i = 1; i < dvertices.length; i++) {
            //initializes everything as infinity 
            dvertices[i] = Integer.MAX_VALUE;
        }
        //the graph that will have the connections we want
        int[][] newGraph = new int[graph.length][graph.length]; 
        //All the known Nodes/vertices with edges that we have gone through
        ArrayList<Integer> knownNodes = new ArrayList<>();
        //must add the vertex to have a starting point
        knownNodes.add(svertex);
        //These are all possible connections
        ArrayList<int[]> connection = new ArrayList<int[]>();

        for (int i = 0; i < graph.length; i++) {
            //When the certain spot in the graph read in is not infinity
            if (graph[svertex][i] != Integer.MAX_VALUE) {
                int[] temp = new int[3]; //creating a new edge
                temp[0] = svertex; //step 1 our start
                temp[1] = i; //step 2 our end
                temp[2] = graph[svertex][i]; //step 3 weight(Value)
                connection.add(temp); //we can the add all of the components as a legal connection
            }
        }
        //We will want to sort after everything is added (Helper Function)
        connection = Sort(connection);
        //While there is still connections
        while (connection.size() != 0) {
               //newGraphs start and end equal their weight of connectivity
            newGraph[connection.get(0)[0]][connection.get(0)[1]] = connection.get(0)[2];
            knownNodes.add(connection.get(0)[1]); //storing the Node we are pointing to (end)
            //Creating the graph that was once intialized as infinity into all edges
            connection = newAdd(connection, connection.get(0)[1], knownNodes);
            connection.remove(0); //Removing at spot zero like popping it
            //Sorting the connections that have been added
            connection = Sort(connection);
            //Checking the validity of connection compared to knownNodes we have passed by
            connection = checkValidity(connection, knownNodes);
        }

        return newGraph;
    }

    //adding connections to arrayList
    public ArrayList<int[]> newAdd(ArrayList<int[]> originalConnect, int startEdge, ArrayList<Integer> knownNodes) {
        for (int i = 0; i < graph.length; i++) {
            //When it doesnt equal infinity and does not contain knownNodes(i) we creat new edges
           if (graph[startEdge][i] != Integer.MAX_VALUE && !knownNodes.contains(i))
            {
                int[] temp = new int[3]; //creating a new edge
                temp[0] = startEdge; //step 1 our start
                temp[1] = i; //step 2 our end
                temp[2] = graph[startEdge][i]; //step 3 weight(Value)
                originalConnect.add(temp); //just like how we initialized it above
            }
                }
        //return the orginial ArrayList
        return originalConnect;
    }

    public ArrayList<int[]> Sort(ArrayList<int[]> originalConnect) {
        //This is just a catch if nothing is in OriginalConnect
        if (originalConnect == null || originalConnect.size() == 0) {
            return originalConnect;
        }
        //can use this for many other things
        //Think of what is the easiest way to break this assumption
        boolean passingThrough = false; //We assume that it needs to be sorted
        while (!passingThrough) { //while its not false
            passingThrough = true; //assume its true that its sorted
            for (int i = 0; i < originalConnect.size() - 1; i++) { //We then do a bubble sort
                if (originalConnect.get(i)[2] > originalConnect.get(i + 1)[2]) {
                    //creating a temp to hold what we are about to remove
                    int[] temp; 
                    //storing a certain spot in the temp
                    temp = originalConnect.get(i);
                    //now we want to remove it so we can swap
                    originalConnect.remove(i);
                    //adding the temp in the specific index of the ArrayList
                    originalConnect.add(i + 1, temp); 
                    //shows that it needs to be sorted still
                    passingThrough = false; 
                }
            }
        }
        //returning the arrayList
        return originalConnect;
    }

    public ArrayList<int[]> checkValidity(ArrayList<int[]> originalConnect, ArrayList<Integer> knownNodes) {
        //going through the size of the ArrayList in the parameter
        for (int i = 0; i < originalConnect.size(); i++) {
            //if this is contained we dont want it since it is not the best approach to getting a minimum spanning tree
            if (knownNodes.contains(originalConnect.get(i)[1])) {
                //we dont want the connection
                originalConnect.remove(i);
            }
        }
        //returning the ArrayList
        return originalConnect;
    }
  

    public int[][] kruskalAlgorithm2() { 
        int[][] graph2; //create graph that is going to hold final Output
        graph2 = new int[graph.length][graph.length];
        ArrayList<Integer> knownNodes = new ArrayList<>(); //arrayList that holds the knownNodes.
        ArrayList<int[]> edgesGraph = new ArrayList<int[]>(); //ArrayList that holds the edgesGraph.    
        int max = Integer.MAX_VALUE; //max variable that holds the max value of an integer.

        for(int i = 0; i < graph.length; i++){ //scans the x collumn of graph.
            for(int j = 0; j < graph[0].length; j++){ //scans the y collumn of graph.
                if(graph[i][j] != max){ //if the current position is not infinity add the position into edgesGraph.
                    int newEdge[] = new int[3]; //new int that holds every variable of edgesGraph.
                    newEdge[0] = i; //holds the x coordinate of the newEdge.
                    newEdge[1] = j; //holds the y value of the newEdge.
                    newEdge[2] = graph[i][j]; //holds the weight of the newEdge.
                    edgesGraph.add(newEdge); //adds all these variables of the newEdge into the edgesGraph.
                }
            }
        }
        edgesGraph = Sort(edgesGraph); //calls the sort method to sort the edgesGraph.
        knownNodes.add(edgesGraph.get(0)[0]); //initialize the first position of edgesGraph.
        while(knownNodes.size() != graph2.length){ //slowly popualte graph2 till its complete
            graph2[edgesGraph.get(0)[0]][edgesGraph.get(0)[1]] = edgesGraph.get(0)[2];  //find the current position and weight of the edge.
            knownNodes.add(edgesGraph.get(0)[1]); //add current posiion to knownNodes
            edgesGraph.remove(0); //removes the current position of edgesGraph.
            checkValidity(edgesGraph, knownNodes); //calls the checkValidity method to edgesGraph and knownNodes.

        }
        return graph2; //returns final graph
    }

    public void floydAlgorithm3() {

    }
}
