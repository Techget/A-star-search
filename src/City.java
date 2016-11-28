import java.util.*;

public class City {
	private String name;
	private int transferTime;
	private HeuristicStrategy HS; 
	private ArrayList<Path> pathes;
	
	/**
	 * @constructor
	 * */
	public City(int tt,String name){
		this.name=name;
		this.transferTime=tt;
		HS=new ConcreteHStrategy1();
		pathes=new ArrayList<Path>();
	}
	
	/**
	 * @constructor
	 * */
	public City(int tt,String name,HeuristicStrategy HS){
		this.name=name;
		this.transferTime=tt;
		this.HS=HS;
		pathes=new ArrayList<Path>();
	}
	
	public String getName(){
		return this.name;
	}
	public int getTransferTime(){
		return this.transferTime;
	}
	public ArrayList<Path> getCityPath(){
		return this.pathes;
	}

	/**
	 * Used during the initial time
	 * 
	 * @param to The city is the path to location
	 * @param travelTime The path's travel time 
	 * */
	public void addPath(City to,int travelTime){
		pathes.add(new Path(travelTime,this,to));
	}
	
	/**
	 * Call the function from a class which implmented HeuristicStrategy interface
	 * 
	 * @param trips the remaining trips need to travel
	 * */
	public int getHeuristic(ArrayList<Path> trips){ // invoked by State objects
		return HS.computeHeuristic(this, trips);
	}
	
	
}
