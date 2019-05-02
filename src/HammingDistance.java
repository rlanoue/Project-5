import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
/**
 * 
 * @author LanoueAdmin
 * HammingDistance is calculated and lists are read in this class
 */
public class HammingDistance {
/**
 * treeSet initialized
 */
	public TreeSet<String> treeStation = new TreeSet<String>();
	
	/**
	 * read method to enter all the stations into a primary treeSet
	 * @param filename
	 * @throws IOException
	 */
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
/**
 * 
 * @return treeSet 
 */
	public TreeSet<String> getTreeSet() {
		return treeStation;
	}

	/**
	 * calls other method in this class to determine the number of the slider
	 * and then if the given string matches the slider it gets added to the 
	 * treeset
	 * @param guiHamm
	 * @param given
	 * @throws IOException
	 */
	public void addGUIHamm(String guiHamm, String given) throws IOException
	{
		int sliderValue = GUI.slider.getValue();
		int found = findHammingDistance(guiHamm, given); 

		if (found == sliderValue){
			newTree.add(guiHamm); 
		}
	}
	/**
	 * finds the hamming distance between two stations and returns
	 * @param station1
	 * @param station2
	 * @return
	 * @throws IOException
	 */
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

	//to be initialized later
	private int[] node1Distances;  
	private TreeSet<String> newTree; 

	/**
	 * finds the distance between nodes and or single letters or numbers 
	 * @param station1
	 * @throws IOException
	 */
	public void findNodeDistance(String station1) throws IOException
	{
		//is now initialized 
		node1Distances = new int[5];
		newTree = new TreeSet<String>(); 

		int hammingDistanceNode1 = 0;	

		//loops through with an iterator and goes though so long as there is
		//another provided
		for (Iterator<String> given = treeStation.iterator(); given.hasNext();)
		{
			String a = given.next();
			hammingDistanceNode1 = findHammingDistance(station1, a);
			{
				if (hammingDistanceNode1 == 0)
				{
					node1Distances[0] = 1;
				}
				else if (hammingDistanceNode1 == 1)
				{
					node1Distances[1]++;
				}
				else if (hammingDistanceNode1 == 2)
				{
					node1Distances[2]++;
				}
				else if (hammingDistanceNode1 == 3)
				{
					node1Distances[3]++;
				}
				else if (hammingDistanceNode1 == 4) 
				{
					node1Distances[4]++; 
				}
			}
			addGUIHamm(a, station1);
		}
	}
	/**
	 * 
	 * @return treeSet
	 */
	public TreeSet<String> getStations(){
		return this.newTree; 
	}
	
	/**
	 * @return array 
	 */
	public int[] getNodes(){
		return this.node1Distances; 
	}
	
	/**
	 * @return the treeSet 
	 */
	public TreeSet<String> getTreeStations(){
		return this.treeStation; 
	}
}