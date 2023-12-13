package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Collections;


public class Part3 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	//5000
	//2400
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
        LinkedList <String> sentence = new LinkedList<>();
		int counter = 0;
		for (String line = r.readLine(); line != null; line = r.readLine()){
			if(counter >= 2402){
				sentence.add(line);
				sentence.remove();
				counter++;
			
			}else{
				sentence.add(line);
				counter++;
			}
		}
	
		Collections.sort(sentence);
		if(counter < 1000){
			
		}else if(2402 > counter && counter >= 1000){
			w.println(sentence.get(999));
			
		}else{
			w.println(sentence.get(999));			
		}
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
