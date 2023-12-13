package comp2402a5;
// Thanks to Pat Morin for this file!

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.HashSet;


public class RoadTripGame {
	
	/**
	 * Your code goes here
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		HashMap<String,List<Integer>> firstLetter = new HashMap<String,List<Integer>>();
		HashMap<String,List<Integer>> secondLetter = new HashMap<String,List<Integer>>();

		HashSet<String> whole = new HashSet<String>();

		Graph picture = new AdjacencyLists(0);
		int counter = 0;
		int size = 0;
		for(String line = r.readLine(); line != null; line = r.readLine()){
			
			String letter1 = line.substring(0, 1);
			String letter2 = line.substring(line.length()-1, line.length());
			if(!whole.contains(letter1+letter2)){
				whole.add(letter1+letter2);
                
				if(firstLetter.containsKey(letter2)){
					picture.addVertex();
					for(Integer i: firstLetter.get(letter2)){
						picture.addEdge(counter, i);
					}
				}
				if(!(firstLetter.containsKey(letter1))){
					
					firstLetter.put(letter1, new ArrayList<Integer>());
					firstLetter.get(letter1).add(counter);
					picture.addVertex();
					size++;
				}else{
					firstLetter.get(letter1).add(counter);
					picture.addVertex();
					size++;
				}
				if(secondLetter.containsKey(letter1)){
					picture.addVertex();
					for(Integer i: secondLetter.get(letter1)){
						picture.addEdge(i,counter);
					}
				}
				if(!(secondLetter.containsKey(letter2))){
					secondLetter.put(letter2, new ArrayList<Integer>());
					secondLetter.get(letter2).add(counter);
					picture.addVertex();
				}else{
					secondLetter.get(letter2).add(counter);
					picture.addVertex();
				}
				counter++;
			}
		}
		int x = Algorithms.bfs(picture, 0,size-1);
		//do plus one
		w.println(x+1);

		//create hashmaps<<String>,<List<Integer>>>start character and last character, value is the indexes of the word
		//arraylist<List<index>> of indexs

		//compare the last letter with the first letter hashmap and if it contains, add an eadge to each one of the index conatined in the hash map list value
		//do the same with the first letter of the new word
	}

	/**
	 * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call doIt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);				
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 1e-9 * (stop-start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
