/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author willd
 * @param <T>
 * @param <String>
 */
public class DirectedGraph<T> {
    private ArrayList<LinkedList<Integer>> adjList = new ArrayList<>();
    private int vertexCounter = 0;
    private T fileName;
    private T className;
    private Scanner scannerIn;
    private Scanner lineScanner;
    private String vertex = null;
    // the vertexTracker arraylist provides a way for recording the 
    // index of each class since these are not easily accessible through
    // the HashMap
    private ArrayList<String> vertexTracker = new ArrayList<>();
    // the HashMap stores each class with its index as its key value
    private HashMap<Integer, String> hmap = new HashMap<>(); 
    private Stack<Integer> topoStack = new Stack<>();
    private boolean[] indexVisited = new boolean[20];
    private Stack<String> stack = new Stack<>();
    
    public DirectedGraph(T fileName) {
        this.fileName = fileName;
        File file = new File(""+this.fileName);
        try {
            scannerIn = new Scanner(file);
            addEdge();
            JOptionPane.showMessageDialog(null, "Graph Built Successfully");
        } catch(FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(null, "File Did Not Open");
        } // end try catch
    } // end constructor
    
    public void addEdge() {
        while (scannerIn.hasNextLine()) {
            LinkedList<Integer> indexList = new LinkedList<>();
            String line = scannerIn.nextLine();
            lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                // vertex holds the String value of the next class name
                vertex = (String) lineScanner.next();
                // add vertices to both hmap and vertexTracker to record
                // both class name and index number
                if (!hmap.containsValue((vertex))) {
                    hmap.put(vertexCounter, vertex);
                    vertexTracker.add(vertex);
                    vertexCounter++;
                } // end if
                // this statement adds the appropriate index number
                // for each vertex to the indexList LinkedList
                for (int i = 0; i < vertexTracker.size(); i++) {
                    if (vertex.equalsIgnoreCase(vertexTracker.get(i))) {
                        indexList.add(i);
                    } // end if
                } // end for
            } // end while                
            // insertIndex stores the head value of each LinkedList
            // so that the value can be dropped before it is added to the 
            // adjList ArrayList
            int insertIndex = indexList.peek();
            // remove first index from the linkedlist since it will now 
            // correspond with the index in the arraylist
            indexList.removeFirst();
            if (adjList.size() >= insertIndex) {
                adjList.add(insertIndex, indexList);
                if (adjList.size() > 1) {
                    adjList.remove(insertIndex + 1);
                } // end if
            } else {
                while (adjList.size() < insertIndex) {
                    adjList.add(new LinkedList<>());
                } // end while
                adjList.add(insertIndex, indexList);
            } // end if else
        } // end while
    } // end method
    
    // This method performs a topological sort using
    // a depth first search
    public Stack topologicalSort(T className) throws Exception {
        this.className = className;
        int vertexIndex = 0;       
        // marker indicating whether or not the class name exists in the graph
        int classNameFound = 0;
        for (int i = 0; i<vertexTracker.size(); i++) {
            if (vertexTracker.get(i).equalsIgnoreCase((String) this.className)) {
                vertexIndex = i;
                classNameFound+=1;
            } // end if
        } // end for
        // throw exception for invalid class name user entry
        if (classNameFound<1)
            throw new InvalidClassNameException(); // end if        
        for (int i = 0; i<adjList.get(vertexIndex).size(); i++) {
            if (adjList.get(vertexIndex).get(i)==vertexIndex) {
                // throws exception for case with cycle
                throw new CycleException();
            } // end if
        } // end for
        // this boolean array marks an index as true once that 
        // index has been visited
        indexVisited[vertexIndex] = true;
        for (int i = 0; i<adjList.get(vertexIndex).size(); i++) {
            if (!indexVisited[adjList.get(vertexIndex).get(i)]) {
                topologicalSort((T) hmap.get(adjList.get(vertexIndex).get(i)));
            } // end if
        } // end for
        topoStack.push(vertexIndex);
        return topoStack;
    } // end method
    
    // This method uses hash map to convert stack of integers to string output
    public String convertIndexToString(Stack stack) {
        StringBuilder indexToString = new StringBuilder();
        this.stack = stack;
        while (!this.stack.isEmpty()) {
            indexToString.append(hmap.get(this.stack.pop())).append(" ");
        } // end while
        return indexToString.toString();
    } // end method
    
    // This method resets the markers for visited indexes to false
    // this allows new depth first searches to run correctly
    public void resetIndexVisited() {
        for (int i = 0; i<indexVisited.length; i++) {
            indexVisited[i] = false;
        } // end for
    } // end method
} // end class
