/**
 * @author xuan Tong, z5089812
 * 
 * Runtime analysis of heuristic function: Every invocation need to loop trips list twice.
 * The first loop, check whether current city is a from location of some trips, if it is, next round, will certainly 
 * go along one of those trips
 * The second loop, filter some from location , and then get average value from the sum of current city to trip from 
 * location.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class TripPlanner {
	private Map map;
	private ArrayList<Path> trips;
	
	public TripPlanner(){
		map=new Map();
		trips=new ArrayList<Path>();
	}
	
	/**
	 * Read the input test file, execute the command line by line
	 * 
	 * @param file an absolute URL giving the base location of the test file
	 * */
	public void readInput(String file){
		  Scanner sc = null;
		  try
		  {
		      sc = new Scanner(new FileReader(file));   
		      while(sc.hasNextLine()){
		    	  String command=sc.nextLine();
		    	  command=command.trim();     // remove the initial space if it has, 
		    	  String parameter[]=command.split(" ",2);  
		    	  String cmd=parameter[0];
		    	  String pm=parameter[1];
		    	  switch(cmd){
		    	  	case "Transfer":
		    	  		cmdTransfer(pm); break;
		    	  	case "Time":
		    	  		cmdTime(pm); break;
		    	  	case "Trip":
		    	  		cmdTrip(pm); break;
		    	  	default:
		    	  		System.out.println("invalid command");
		    	  }		    	  
		      }
		  }
		  catch (FileNotFoundException e) {}
		  finally
		  {
		      if (sc != null) sc.close();
		  }
	}
	/**
	 * Execute the Transfer command
	 * 
	 * @param parameter A string object contains city name and transfer time
	 * */	
	private void cmdTransfer(String parameter){
		String[] pm=parameter.split(" ", 2);
		City c=new City(Integer.parseInt(pm[0]),pm[1]);
		map.addCity(c);
	}
	/**
	 * Execute the Time command
	 * 
	 * @param parameter A string object contains two city names which belong to a path and travel time
	 * */	

	private void cmdTime(String parameter){
		String[] pm=parameter.split(" ",3);
		City c1=map.getCity(pm[1]);
		City c2=map.getCity(pm[2]);
		c1.addPath(c2, Integer.parseInt(pm[0]));
		c2.addPath(c1, Integer.parseInt(pm[0]));
	}
	/**
	 * Execute the Trip command
	 * 
	 * @param parameter A string object contains two city names which belong to a path
	 * */	
	private void cmdTrip(String parameter){
		String[] pm=parameter.split(" ",2);
		City c1=map.getCity(pm[0]);
		City c2=map.getCity(pm[1]);
		Path tripTemp=new Path(c1,c2);
		for(Path p:c1.getCityPath()){
			if(tripTemp.sameDirection(p)){
				Path trip=new Path(p.getTravelTime(),c1,c2);
				trips.add(trip);
				break;
			}
		}
	}
	
	/**
	 * Invoke Map's astarSearch function
	 * 
	 * */	

	public void astarSearchPlan(){
		map.astarSearch(trips);
	}
	
	public static void main(String[] args) {
		// TODO 
		TripPlanner tp=new TripPlanner();
		tp.readInput(args[0]);
		tp.astarSearchPlan();
	}

}
