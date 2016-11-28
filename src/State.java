import java.util.*;

public class State implements Comparable<State> {
	private City currentCity;
	private int gCost;
	private State prevState;
	private ArrayList<Path> trips;
	
	/**
	 * @constructor
	 * */
	public State(City c,int gCost, State s, ArrayList<Path> trips ){
		this.currentCity=c;
		this.gCost=gCost;
		this.prevState=s;
		this.trips=trips;
	}
	
	public int getGcost(){
		return this.gCost;
	}
	public City getCurrentCity(){
		return this.currentCity;
	}
	public ArrayList<Path> getTrips(){
		return this.trips;
	}
	public State getPrevState(){
		return this.prevState;
	}
	/**
	 * Return a string of passed path
	 * */
	public String outPut(){
		if(prevState==null){
			return "";
		}
		String temp="";
		temp=prevState.outPut()+temp;
		temp=temp+"Trip "+prevState.getCurrentCity().getName()+" to "+currentCity.getName()+"\n";
		return temp;
	}
	
	/**
	 * Same condition stands for has same currentcity and same trips need to travel,do not include fCost.
	 * 
	 * @param s The state need to compare with
	 * 
	 */
	public boolean isSameCondition(State s){  
		 if(this.currentCity==s.getCurrentCity()&&this.trips.size()==s.getTrips().size()){
			 for(Path p1:this.trips){
				 int flag=0;     //stand for , p1 find same direction path in p2
				 for(Path p2:s.getTrips()){
					 if(p1.sameDirection(p2)){
						 flag=1;
						 break;
					 }
				 }
				 if(flag==0){
					 return false;
				 }
			 }
			 return true;
		 }else{
			 return false;
		 }
	}
	
	/**
	 * F=G+H, call City's getHeuristic method
	 * */
	public int getFcost(){
		return this.currentCity.getHeuristic(trips)+this.gCost;
	}
	
	/**
	 * Implement the compareTo() method, which is required by comparable interface
	 * */
	public int compareTo(State s) {
		// TODO 
		return this.getFcost()-s.getFcost();
	}
}
