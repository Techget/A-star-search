
public class Path {
	private City from;
	private City to;
	private int travelTime;
	
	/**
	 * used in create Trips
	 * 
	 * @constructor
	 * */
	public Path(City c1,City c2){ 
		this.from=c1;
		this.to=c2;
		this.travelTime=0;
	}
	/**
	 * used in create pathes between cities
	 * 
	 * @constructor
	 * */
	public Path(int tt,City c1,City c2){ 
		this.from=c1;
		this.to=c2;
		this.travelTime=tt;
	}
	
	public City getFrom(){
		return this.from;
	}
	public City getTo(){
		return this.to;
	}
	public int getTravelTime(){
		return this.travelTime;
	}

	/**
	 * Check whether two path is in same direction
	 * */
	public boolean sameDirection(Path p){ 
		if(p.getFrom().equals(this.from)&& p.getTo().equals(this.to)){
			return true;
		}
		return false;
	}
}
