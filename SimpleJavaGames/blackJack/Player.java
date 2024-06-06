// Player.java
import java.util.Scanner;

public class Player extends Participant {
    private Scanner scanner;

    public Player() {
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean wantsToHit() {
        System.out.print("Hit or Stand? ");
        String action = scanner.nextLine().toLowerCase();
        return action.equals("hit");
    }
}
