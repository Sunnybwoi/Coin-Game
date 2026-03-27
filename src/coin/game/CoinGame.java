
package coin.game;

/**
 * @author Courage
 */
public class CoinGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Instantiate and run the game engine
        GameEngine game = new GameEngine();
        game.start();
        
        // Ensure the Java Virtual Machine shuts down correctly
        System.exit(0);
        
    }
    
}
