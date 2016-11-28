import java.util.*;

public interface HeuristicStrategy {
	public int computeHeuristic(City c, ArrayList<Path> trips);
}
