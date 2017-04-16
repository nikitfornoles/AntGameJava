import java.util.*;

public class Board {
	private int N;
	private int rewardValue;
	private int trapType;
	private Vector <Integer> tilesWithReward;
	private Vector <Integer> tilesWithTrap;

	public Board () {
		rewardValue = 1;
		trapType = 1;
		tilesWithReward = new Vector <Integer> ();
		tilesWithTrap = new Vector <Integer> ();
	}
	public void setN (int side) {
		N = side;
	}
	public int getN () {
		return N;
	}
	public int getRewardValue () {
		return rewardValue;
	}
	public void setTileWithReward (Integer i) {
		tilesWithReward.addElement (i);
	}
	public void generateTileWithReward (int z) {
		Random r = new Random();
		for (int i = 0; i < ((z*z)/4); i++) {
			int answer = r.nextInt(z*z) + 1;
			if (hasReward (answer))
				i = i-1;
			else
				setTileWithReward (answer);
		}
	}
	public void getTileWithReward () {
		Enumeration reward = tilesWithReward.elements ();
		while (reward.hasMoreElements ())
			System.out.print (reward.nextElement () + ", ");
	}
	public boolean hasReward (Integer i) {
		boolean z = false;
		Enumeration reward = tilesWithReward.elements ();
		while (reward.hasMoreElements ()) {
			if (i == reward.nextElement ())
				z = true;
		}
		return z;
	}
	public void removeAllRewards () {
		tilesWithReward.clear();
	}
	public void setTileWithTrap (Integer j) {
		tilesWithTrap.addElement(j);
	}
	public void generateTileWithTrap (int z) {
		Random t = new Random();
		for (int i = 0; i < ((z*z)/4); i++) {
			int answer = t.nextInt(z*z) + 1;
			if (hasReward (answer))
				i = i-1;
			else if (hasTrap (answer))
				i = i-1;
			else
				setTileWithTrap (answer);
		}
	}
	public void getTileWithTrap () {
		Enumeration trap = tilesWithTrap.elements ();
		while (trap.hasMoreElements ())
			System.out.print (trap.nextElement () + ", ");
	}
	public boolean hasTrap (Integer i) {
		boolean z = false;
		Enumeration trap = tilesWithTrap.elements ();
		while (trap.hasMoreElements ()) {
			if (i == trap.nextElement ())
				z = true;
		}
		return z;
	}
	public void removeAllTraps () {
		tilesWithTrap.clear();
	}
}