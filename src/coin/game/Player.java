/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coin.game;

/**
 *
 * @author Courage
 * 
 * Represents the user in the game.
 * Handles the logic for modifying and retrieving the coin count.
 */
public class Player {
    private int coins; // Private variable to ensure data integrity (Encapsulation)

    public Player() {
        this.coins = 0; // Initialize starting coins
    }

    /** @return current number of coins */
    public int getCoins() {
        return coins;
    }

    /** @param amount The value to add (positive) or subtract (negative) */
    public void updateCoins(int amount) {
        this.coins += amount;
    }

    /** @param factor The number to multiply the current coins by */
    public void multiplyCoins(int factor) {
        this.coins *= factor;
    }

    /** Resets the coin count to zero, usually after a penalty */
    public void resetCoins() {
        this.coins = 0;
    }
}
