package home.smart.core.control;

import home.smart.core.game_api.ChangeEvent;

public interface UserEventProcessor {
    /**
     * Handles input changes made by the user
     * @param event change details
     */
    void onInputChange(ChangeEvent event);

    /**
     * Handles output changes made by the user
     * @param event change details
     */
    void onOutputChange(ChangeEvent event);
}
