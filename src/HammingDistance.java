import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class HammingDistance {
	
	static TreeSet<String> treeStation = new TreeSet<String>(); 
	
	public static void main(String[] args) {
		
		//treeStation = new TreeSet<String>();
	}
	
	public void read(String filename) throws IOException 
	{
		BufferedReader file = new BufferedReader(new FileReader(filename)); //Mesonet.csv
		String line = file.readLine();

		while (line != null) //So long as there is another line to be read, the while loop will continue
		{
			String keep = line.substring(0, 4); 
			treeStation.add(keep); 	
			line = file.readLine(); //adds the 4 digit nodes to an array to later be looped through
		}

		file.close(); 
	}
	
	public TreeSet<String> getTreeSet() {
		return treeStation;
		
	}

}
