/**
 * 
 */
package com.group66.game.input;

import com.badlogic.gdx.Input.TextInputListener;
import com.group66.game.settings.DynamicSettings;


public class NameInputListener implements TextInputListener {
    private DynamicSettings dynamicSettings;
    /**
     * 
     */
    public NameInputListener(DynamicSettings dynamicSettings) {
        this.dynamicSettings = dynamicSettings;
    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.Input.TextInputListener#input(java.lang.String)
     */
    @Override
    public void input(String text) {
        dynamicSettings.setName(text);

    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.Input.TextInputListener#canceled()
     */
    @Override
    public void canceled() {
        
    }

}
