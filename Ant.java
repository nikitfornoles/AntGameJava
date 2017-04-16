import java.util.*;

public class Ant {
	private int position;
	private int lifeline;
	private int direction;
	private boolean lock;
	protected Board board;
	
	public Ant () {
		lifeline = 1;
		position = 5;
		direction = 4;
		board = new Board ();
		lock = false;
	}
	public void setPosition (int p) {
		position = p;
	}
	public int getPosition () {
		return position;
	}
	public void setLifeline (int l) {
		lifeline = l;
	}
	public int getLifeline () {
		return lifeline;
	}
	public void setDirection (int d) {
		direction = d;
	}
	public int getDirection () {
		return direction;
	}
	public void setLock (boolean l) {
		lock = l;
	}
	public boolean isLocked () {
		return lock;
	}
	public void moveUp () {
		if ((position - getBoardLength()) < 1) {
			System.out.println ("That move is not allowed");
			setLock (true);
		}
		else {
			position = position - getBoardLength();
			setLock (false);
		}
	}
	public void moveDown () {
		if ((position + getBoardLength()) > (getBoardLength()*getBoardLength())) {
			System.out.println ("That move is not allowed");
			setLock (true);
		}
		else {
			position = position + getBoardLength();
			setLock (false);
		}
	}
	public void moveLeft () {
		if ((((position - 1) % getBoardLength()) == 0) || (position == 1)) {
			System.out.println ("That move is not allowed");
			setLock (true);
		}
		else {
			position = position - 1;
			setLock (false);
		}
	}
	public void moveRight () {
		if ((position % getBoardLength()) == 0 || position == getBoardLength()*getBoardLength()) {
			System.out.println ("That move is not allowed");
			setLock (true);
		}
		else {
			position = position + 1;
			setLock (false);
		}
	}
	public void surprisesToEncounter () {
		board.generateTileWithReward(getBoardLength ());
		board.generateTileWithTrap(getBoardLength ());
	}
	public void resetSurprise () {
		board.removeAllRewards ();
		board.removeAllTraps ();
	}
	public void checkTileRewardOrTrap () {
		if (board.hasReward (getPosition ())) {
			setLifeline (getLifeline () + board.getRewardValue());
			System.out.println ("Tile " + position + " has a hidden coin. Lifeline is now " + getLifeline());
		}
		else if (board.hasTrap (getPosition ())) {
			Random r = new Random();
			int trapType = r.nextInt(3) + 1;
			checkTrapType (trapType);
		}
		else
			System.out.println ("There is no hidden reward or trap in this tile");
	}
	public void checkTrapType (int num) {
		if (num == 1) {
			System.out.println ("Tile " + getPosition () + " contains water trap. Ant died immediately.\n");
			setLifeline (0);
		}
		else if (num == 2) {
			System.out.println ("Tile " + getPosition () + " contains mud trap. Ant's lifeline decreases by 3.");
			setLifeline (getLifeline() - 3);
			System.out.println ("Lifeline is now " + getLifeline() + "\n");
		}
		else if (num == 3) {
			System.out.println ("Tile " + getPosition () + " contains hill trap. Ant's lifeline decreases by 1.");
			setLifeline (getLifeline() - 1);
			System.out.println ("Lifeline is now " + getLifeline() + "\n");
		}
	}
	public void setBoardToUse (int length) {
		board.setN(length);
	}	
	public int getBoardLength () {
		return board.getN();
	}
	public void displayPosition () {
		for (int i = 1; i <= getBoardLength ()*getBoardLength (); i++) {
			if (i == position)
				System.out.print ("* ");
			else
				System.out.print ("_ ");
			if (i%getBoardLength () == 0)
				System.out.println();
		}
	}
}