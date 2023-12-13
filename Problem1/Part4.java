package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeSet;


public class Part4 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		NavigableSet<String> tree = new TreeSet<>();
		
		for (String line = r.readLine(); line != null; line = r.readLine()){
			
			if(tree.isEmpty()){
				
				tree.add(line);
				w.println(line);
			}else{
				// ArrayList<String> listNumbers = new ArrayList<>();
				// listNumbers.addAll(tree);
				//System.out.println(listNumbers);
				//contains
				if (tree.contains(line)){
					continue;
				}else if(tree.higher(line) != null){
					if(tree.higher(line).startsWith(line)){
						continue;
					}else{
						w.println(line);
						tree.add(line);
			
				// 	}
				// }else{
				// 	if((listNumbers.get(listNumbers.indexOf(tree.higher(line)) - 1)).startsWith(line) || listNumbers.get(listNumbers.indexOf(tree.higher(line))).startsWith(line)){
				// 		continue;
				// 	}else{
				// 		w.println(line);
				// 		tree.add(line);
				// 	}
				// }
				} 
				}else{
					tree.add(line);
					w.println(line);
				}
			}
		}
	}
		
		
	
	
	
	
			// 		if(sentence.isEmpty()){
	// 			sentence.add(line);
	// 			w.println(line);
	// 		}else{
	// 		for(String v: sentence){
	// 			if(line.equals(v)){
	// 				continue;
	// 			}else if(v.startsWith(line) == false){
	// 				check = true;
	// 			}else{
	// 				check = false;
	// 				break;
	// 			}
	// 		}if(check == true){
	// 			w.println(line);
	// 			sentence.add(line);
	// 		}check = false;
			
	// 		}
	// 	}
	// }

	

			//ods is faster
			//jcf is slower but can handle more


			// {
			// 	NavigableSet<String> tree = new TreeSet<String>();
		  
			// 	tree.add("abcc");
			// 	tree.add("cds");
			// 	tree.add("bbc");
			// 	tree.add("dps");
			// 	tree.add("dmg");
			// 	tree.add("wsp");
			// 	tree.add("axc");
		  
			// 	if (tree.higher("abc") != null){
			// 		ArrayList<String> listNumbers = new ArrayList<>();
			// 		listNumbers.addAll(tree);
			// 		String higherVal = tree.higher("abc");
			// 		String lowerVal = tree.lower("abc");
			// 		if(lowerVal == null){
			// 			tree.add("abc");
			// 		}else if(higherVal == null){
			// 			tree.add("abc");
			// 		}else{
			// 			System.out.println(listNumbers.get(listNumbers.indexOf(higherVal) - 1));
			// 		}
			// 		}
				
			// }
	
	
			
		
		
	

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
