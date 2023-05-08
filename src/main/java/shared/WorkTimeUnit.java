package shared;

import java.util.ArrayList;
import java.util.Iterator;

public class WorkTimeUnit {
	private AUser user; // {Written by Perry02}
	public AUser getUser() {return user;}
	
	private ArrayList<Integer> timeWorked = new ArrayList<Integer>();
	
	public WorkTimeUnit(AUser user) { // {Written by Perry02}
		this.user = user;
	}
	
	// adds time to this unit 
	public void AddTime(int hours) { // {Written by Perry02}
		timeWorked.add(hours);
	}
	
	// returns the total time of this unit
	public int GetTotalTime() { // {Written by Perry02}
		int totalTime = 0;
		
		for (Integer integer : timeWorked) {
			totalTime += integer;
		}
		
		return totalTime;
	}
}
