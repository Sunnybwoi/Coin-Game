package coin.game;
import javax.swing.JOptionPane;

/**
 *
 * @author Courage
 * 
 * Controls the game flow using UI buttons instead of text input.
 * Includes logic to handle exit confirmations.
 */
public class GameEngine {
    private Player player;

    public GameEngine() {
        this.player = new Player();
    }

    public void start() {
        showWelcome();
        
        // Door 1
        if (!processDoor("Door 1", "You found 3 coins!", 3, 0)) return;
        
        // Door 2
        if (!processDoor("Door 2", "Oh no! You lost 2 coins.", -2, 0)) return;
        
        // Door 3
        if (!processDoor("Door 3", "Jackpot! Coins multiplied by 4!", 0, 4)) return;
        
        // Door 4: Goblin risk
        if (askToOpen("Door 4", "A mysterious door...")) {
            player.resetCoins();
            JOptionPane.showMessageDialog(null, "A goblin attacked! Game Over.");
            return;
        }

        // Door 5
        if (!processDoor("Door 5", "Final blessing! You found 10 coins!", 10, 0)) return;
        
        showFinalScore();
    }

    /**
     * Creates a button-based dialog. 
     * Returns true if a choice was made, false if the user wants to quit.
     */
    private boolean processDoor(String title, String successMsg, int change, int multiplier) {
        String[] options = {"Open", "Pass"};
        
        // showOptionDialog returns an int: 0 for "Open", 1 for "Pass", -1 for X/Cancel
        int selection = JOptionPane.showOptionDialog(null, 
                "You reached " + title + ". What will you do?", 
                title, 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, options, options[0]);

        if (selection == -1) { // User clicked X or Cancel
            return handleExitRequest();
        }

        if (selection == 0) { // User clicked "Open"
            if (multiplier > 0) player.multiplyCoins(multiplier);
            else player.updateCoins(change);
            JOptionPane.showMessageDialog(null, successMsg + "\nCoins: " + player.getCoins());
        } else {
            JOptionPane.showMessageDialog(null, "You safely passed. \nCoins: " + player.getCoins());
        }
        
        return true;
    }

    /** Simple version for the Goblin door */
    private boolean askToOpen(String title, String message) {
        String[] options = {"Open", "Pass"};
        int selection = JOptionPane.showOptionDialog(null, message, title, 
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
                null, options, options[0]);
        
        if (selection == -1) {
            if (!handleExitRequest()) System.exit(0);
        }
        return selection == 0;
    }

    /**
     * Confirmation logic for quitting.
     * @return true to continue playing, false to stop.
     */
    private boolean handleExitRequest() {
        int quit = JOptionPane.showConfirmDialog(null, 
                "Do you want to quit the game?", 
                "Quit?", 
                JOptionPane.YES_NO_OPTION);
        
        if (quit == JOptionPane.YES_OPTION) {
            System.exit(0); 
            return false;
        }
        return true; // User wants to stay
    }

    private void showWelcome() {
        JOptionPane.showMessageDialog(null, "Welcome, Gamer! \nReady to collect some coins?");
    }

    private void showFinalScore() {
        JOptionPane.showMessageDialog(null, "Total Coins: " + player.getCoins() + "\nWell done!");
    }
}