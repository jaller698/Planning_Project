package shared;

import java.util.ArrayList;
import java.util.Iterator;

public class WorkTimeUnit {
	private AUser user;
	public AUser getUser() {return user;}
	
	private ArrayList<Integer> timeWorked = new ArrayList<Integer>();
	
	public WorkTimeUnit(AUser user) {
		this.user = user;
	}
	
	public void AddTime(int hours) { // adds time to this unit
		timeWorked.add(hours);
	}
	
	public int GetTotalTime() { // returns the total time of this unit
		int totalTime = 0;
		
		for (Integer integer : timeWorked) {
			totalTime += integer;
		}
		
		return totalTime;
	}
}
