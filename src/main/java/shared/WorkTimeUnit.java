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
	
	public void AddTime(int hours) {
		timeWorked.add(hours);
	}
	
	public int GetTotalTime() {
		int totalTime = 0;
		
		for (Integer integer : timeWorked) {
			totalTime += integer;
		}
		
		return totalTime;
	}
}
