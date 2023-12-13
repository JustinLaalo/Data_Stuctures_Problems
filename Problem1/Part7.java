package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;

public class Part7 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		Stack<ArrayList<String>> list = new Stack<>();
		ArrayList<String> block = new ArrayList<>();
		int read_lines = 0;
		int k = 1;
		int l = 0;
		for (String line = r.readLine(); line != null; line = r.readLine()){
			block.add(line);
			read_lines++;
		if(line.equals("***reset***")){
				list.add(new ArrayList<>());
				list.get(l).addAll(block);
				block.clear();
				k = 1;
				read_lines = 0;	
				l++;
		}else if(read_lines == k){
			list.add(new ArrayList<>());
			list.get(l).addAll(block);
			block.clear();
			k++;
			read_lines = 0;
			l++;	
		}

		
		}if(block.size() > 0){
			list.add(new ArrayList<>());
			list.get(l).addAll(block);
			block.clear();
		}
		// System.out.println(list);
		Collections.reverse(list);
		// System.out.println(list);
		for(ArrayList<String> all_blocks: list){
			for(String text: all_blocks){
				w.println(text);
			}

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
