import java.util.ArrayList;
import java.util.PriorityQueue;

public class Map {
	private ArrayList<City> cities;
	
	/**
	 * @constructor
	 * */
	public Map(){
		cities=new ArrayList<City>();
	}
	
	/**
	 * Used during initial period
	 * */
	public void addCity(City c){
		cities.add(c);
	}
	/**
	 * An auxiliary function
	 * 
	 * @param name Target city name
	 * */
	public City getCity(String name){
		for(City c:cities){
			if(c.getName().equals(name)){
				return c;
			}
		}
		return null;
	}
	
	/**	
	 * Implement the astar search algorithm, to calculate the optimal way for traveling
	 * 
	 * @param trips The trips need to travel
	 * */
	public void astarSearch(ArrayList<Path> trips){
		PriorityQueue<State> queue=new PriorityQueue<State>();
		
		City startCity=getCity("London");
		State startState=new State(startCity,0,null,trips);
		queue.add(startState);
		int nodeExpanded=0;
		
		ArrayList<State> stateExpanded=new ArrayList<State>();
		
		while(!queue.isEmpty()){
			nodeExpanded++;
			State s1=queue.poll();
			
			if(s1.getTrips().isEmpty()){    // success
				System.out.println(nodeExpanded + " nodes expanded");
				System.out.println("cost = "+(s1.getGcost()-startCity.getTransferTime()));
				String temp=s1.outPut();
				System.out.println(s1.outPut().trim());
				return;
			}
			
			stateExpanded.add(s1);
			
			//check whether add an child state be added to queue
			for(Path p:s1.getCurrentCity().getCityPath()){
				ArrayList<Path> tempTrips=new ArrayList<Path>(s1.getTrips()); //the trips pass to child state is a new arraylist
				
				//flag will be set if p is a path which required to be traveled
				int travelANeedToTraveledPathFlag=0;
				//flag wiil be set if p's to location is the from location of some trips 
				int toLocationIsTripFromLocationFlag=0;
				for(Path tempP:tempTrips){ 
					if(tempP.sameDirection(p)){ //if from current city to next city is the path that need to travel,
						tempTrips.remove(tempP);
						travelANeedToTraveledPathFlag=1;
						break;
					}
					if(p.getTo().equals(tempP.getFrom())){
						toLocationIsTripFromLocationFlag=1;
						// cannot break here,since still need to check whether have same direction trip with this p
					}
				}
				if(travelANeedToTraveledPathFlag==0&&toLocationIsTripFromLocationFlag==0){
					continue;
				}else{//means (travelANeedToTraveledPathFlag==1||toLocationIsTripFromLocationFlag==1)		
					
					//could precisely generate a proper child state here, since have manipulated the trips arraylist
					State tempState1=new State(p.getTo(),s1.getGcost()+s1.getCurrentCity().getTransferTime()+p.getTravelTime(),s1,tempTrips);
					
					//flag will be set if the state with same condition have been expanded once
					//same condition just means that have same currentcity and trips
					int isExpandedFlag=0;
					for(int i=0;i<stateExpanded.size();i++){
						if(tempState1.isSameCondition(stateExpanded.get(i))){
							isExpandedFlag=1;
							break;
						}
					}
					//check all the flag, and determine whether a state add to queue
					if(isExpandedFlag==0){					
						queue.add(tempState1);
					}
				}
			}
			
		}		
	}
}
