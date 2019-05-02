import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class HammingDistance {

	private static TreeSet<String> treeStation = new TreeSet<String>(); 
	public static void main(String[] args) {
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

	public void addGUIHamm(String guiHamm, String given) throws IOException
	{
		int sliderValue = GUI.slider.getValue();
		int toast = findHammingDistance(guiHamm, given); 

		if (toast == sliderValue){
			newTree.add(guiHamm); 
		}
	}
	public int findHammingDistance(String station1, String station2) throws IOException
	{
		int hammingDistance = 0;
		for (int i = 0; i < station1.length(); i++)
		{
			if (station1.charAt(i) != station2.charAt(i))
			{
				hammingDistance++; 
			}
		}
		return hammingDistance;
	}

	int[] node1Distances = new int[4];
	private TreeSet<String> newTree; 

	public void findNodeDistance(String station1) throws IOException
	{
		newTree = new TreeSet<String>(); 

		int hammingDistanceNode1 = 0;	

		for (Iterator<String> given = treeStation.iterator(); given.hasNext();)
		{
			hammingDistanceNode1 = findHammingDistance(station1, given.next());
			{
				if (hammingDistanceNode1 == 1)
				{
					node1Distances[0]++;
				}
				else if (hammingDistanceNode1 == 2)
				{
					node1Distances[1]++;
				}
				else if (hammingDistanceNode1 == 3)
				{
					node1Distances[2]++;
				}
				else if (hammingDistanceNode1 == 4) 
				{
					node1Distances[3]++; 
				}
			}
			addGUIHamm(given.next(), station1);
		}
	}

	public TreeSet<String> getStations(){
		return this.newTree; 
	}
}