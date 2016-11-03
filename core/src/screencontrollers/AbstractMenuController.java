/**
 * 
 */
package screencontrollers;

import com.badlogic.gdx.Screen;

public abstract class AbstractMenuController {

    /* The instance of the screen it controls. */
    Screen screen;
    
    /**
     * 
     */
    public AbstractMenuController(Screen screen) {
        this.screen = screen;
    }

}
