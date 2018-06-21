package ttdev.api.gui;


import org.bukkit.event.Listener;
import ttdev.api.API;
import ttdev.api.gui.util.PlaceholderManager;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tre Logan
 */
public class GUIAPI {
    
    private static Map<String, Listener> menuListeners = new HashMap<>();
    private static PlaceholderManager placeholderManager = new PlaceholderManager();
    
    public static void addMenuListener(String id, Listener listener) {
        Listener previous = menuListeners.put(id, listener);
        if (previous == null) {
            registerListener(listener);
        }
        
    }
    
    private static void registerListener(Listener listener) {
        API apiInstance = API.getInstance();
        apiInstance.getServer().getPluginManager().registerEvents(listener, apiInstance);
    }

    public static PlaceholderManager getPlaceholderManager() {
        return placeholderManager;
    }

}
