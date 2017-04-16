import java.util.*;

public class AntTester {
	public static void main (String [] args) {
		Scanner in = new Scanner (System.in);
		
		int N = 0, pos = 0, direction = 0;
		Ant a = new Ant ();
		
		System.out.print ("Enter the value of N (the length of the side of the square Board): ");
		N = in.nextInt ();
		a.setBoardToUse (N);
		
		if (a.getBoardLength () > 7)
			a.setLifeline ((a.getBoardLength ()*a.getBoardLength ())/8);
		
		System.out.print ("Enter the starting position of the Ant (1 - " + N*N + "): ");
		pos = in.nextInt ();
		while ((pos < 1) || (pos > N*N)) {
			System.out.print ("Enter the starting position of the Ant (1 - " + N*N + "): ");
			pos = in.nextInt ();
		}
		a.surprisesToEncounter ();
		a.setPosition (pos);
		a.displayPosition ();
		a.checkTileRewardOrTrap ();
		
		while (a.getLifeline() > 0 && a.getLifeline() < (N*N)/4) {
			a.resetSurprise();
			a.surprisesToEncounter ();
			System.out.print ("\nEnter 8 (up), 2, (down), 4 (left) or 6 (right): ");
			direction = in.nextInt ();
			
			if (direction == 8)
				a.moveUp();
			else if (direction == 2)
				a.moveDown();
			else if (direction == 4)
				a.moveLeft();
			else if (direction == 6)
				a.moveRight();
			
			if (!a.isLocked()) {
				System.out.println("Ant is now at Tile " + a.getPosition());
				a.displayPosition ();
				a.checkTileRewardOrTrap ();
			}
		}
		if (a.getLifeline() >= ((N*N)/4)) {
			System.out.print ("\nYou win\n");
			System.exit(0);
		}
		else if (a.getLifeline() < 1) {
			System.out.print ("\nYou lose\n");
			System.exit(0);
		}
	}
}