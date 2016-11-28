import java.util.*;

public class ConcreteHStrategy1 implements HeuristicStrategy {

	
	/* Compute the heuristic for a certain city with the given trips need to go
	 * 
	 * @Override
	 * 
	 * @param c Need to compute this city's heuristic
	 * @param trips The trips need to travel
	 * */
	public int computeHeuristic(City c, ArrayList<Path> trips) {
		// TODO 
		int heuristic=0;
		
		//if current city is a from location of any trip,it will certainly travel the trip next round
		int tempCountFromCity=0;
		for(Path p:trips){
			heuristic+=p.getTravelTime();
			heuristic+=p.getFrom().getTransferTime();
			if(c.equals(p.getFrom())){
				tempCountFromCity++;
			}
		}
		if(tempCountFromCity>=1){
			return heuristic;
		}
		
		//if current city isn't a from location of any trip,it will certainly travel to some trip from location next round
		//if a trip's from location is some trip's to location, then we won't go to that location next round
		ArrayList<City> tripsFromCityCanBeIgnored=new ArrayList<City>();
		for(Path p:trips){
			for(Path pTemp:trips){
				if(p.getTo().equals(pTemp.getFrom())){ //this trip's to location is other trips' from location
					tripsFromCityCanBeIgnored.add(p.getTo());
				}
			}
		}
				
		int tempSum=0;
		int tempCount=0;
		for(Path p:trips){
			if(tripsFromCityCanBeIgnored.contains(p.getFrom())){
				continue;
			}else{
				Path tempP=new Path(c,p.getFrom());
				for(Path temp:c.getCityPath()){
					if(temp.sameDirection(tempP)){
						tempSum+=temp.getTravelTime();
						tempCount++;
						break;
					}
				}
			}
		}
		if(tempCount>0){
			heuristic+=(int)(tempSum/tempCount); //get the average cost to go to next from location.
			heuristic+=c.getTransferTime();
		}							
		return heuristic;
	}
	
}
/*	Alternative way to solve problem
 * 
 * int tempCount=0;
ArrayList<City> tripsFromCityCanBeIgnored=new ArrayList<City>();
for(Path pTemp:trips){
	if(p.getTo().equals(pTemp.getFrom())){ //this trip's to location is other trips' from location
		tripsFromCityCanBeIgnored.add(p.getTo());
		//break;
	}				
	if(pTemp.getFrom().equals(p.getFrom())){ //duplicate from location
			tempCount++;
	}
}		
if((!tripsFromCityCanBeIgnored.contains(p.getFrom()))||tempCount>1){
	Path pTemp1 =new Path(c,p.getFrom());
	for(Path pTemp2:c.getCityPath()){  //these pathes have real travelTime
		if(pTemp1.sameDirection(pTemp2)){
			heuristic+=pTemp2.getTravelTime()/(trips.size()-tripsFromCityCanBeIgnored.size());
			//heuristic+=(int)pTemp2.getTo().getTransferTime()/(trips.size()-tripsFromCityCanBeIgnored.size());
			break;
		}
	}
}*/
