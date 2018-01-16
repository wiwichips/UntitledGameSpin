/*
 * Description: Aircraft class for enemy aircraft objects in the game
 * Author:      Will Pringle
 */

package culm.will;

/**
 *
 * @author wipri9236
 */
public class Aircraft {
    // GLOBAL VARIABLES
        // Variables involving posistion
    private double angle; // angle in radians
    private int posistionX; // the horizontal posistion on the screen
    private int posistionY; // the vertical posistion on the screen
        // Variables involving time 
    private int timeAlive; // the time since the aircraft has been spawned
        // Variables involving aircraft aesthetics
    private String colour; // the colour of the aircraft, either "purple" or "yellow"
    
    /**
     * Aircraft Constructor
     * @param angle
     * @param colour 
     */
    public Aircraft(double angle, String colour){
        this.colour=colour;
        
    }
    
}
