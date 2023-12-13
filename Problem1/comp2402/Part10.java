package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


public class Part10 {

	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	//dont be afraid of using nested loops, not all run O(n) time
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		ArrayList<String> sentence = new ArrayList<>();
		String biggest = "";
		int index = 0;
		boolean check = true;
		for (String line = r.readLine(); line != null; line = r.readLine()){
			sentence.add(line);
			}
			// sentence = new ArrayList<String>(sentence.subList(index + 1, sentence.size()));
			// biggest = Collections.max(sentence);
			// index = sentence.indexOf(Collections.max(sentence));
			// w.println(biggest);
			while(check == true){
				biggest = Collections.max(sentence);

				w.println(biggest);
				index = sentence.indexOf(biggest);
				sentence = new ArrayList<String>(sentence.subList(index + 1, sentence.size()));
				if (sentence.isEmpty()){
					check = false;
				}
			}
			}
		
	
			// 	if(sentence.isEmpty()){
			// 		sentence.add(line);
			// 	}else if(sentence.get(0).compareTo(line) < 0){
			// 		sentence.clear();
			// 		pastBiggest = line;
			// 		sentence.add(line);
			// 	}else{
			// 			for(int x = sentence.indexOf(pastBiggest); x < sentence.size(); x++){
			// 				if(sentence.get(x).compareTo(line) < 0){
			// 						sentence.remove(x);	
									
			// 				}
			// 			 }
			// 				sentence.add(line);
			// 				//w.println(line);	 
			// 	}
				
			// }
			// // Iterator<String> iter = sentence.iterator();
			// // while(iter.hasNext()){
			// // 	w.println(iter.next());
			// // }
			
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
