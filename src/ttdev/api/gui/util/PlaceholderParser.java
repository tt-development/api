package ttdev.api.gui.util;


import org.bukkit.entity.Player;
import ttdev.api.gui.GUIAPI;

import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author Tre Logan
 */
public class PlaceholderParser {
    
    public static String parse(String str, Player player) {

        PlaceholderManager placeholderManager = GUIAPI.getPlaceholderManager();

        Map<String, Function<Player, String>> placeholderMap = placeholderManager.getPlaceholderMap();
        for (String placeholder : placeholderMap.keySet()) {

            if (!str.contains(placeholder)) {
                continue;
            }

            Function<Player, String> function = placeholderMap.get(placeholder);
            str = str.replace(placeholder, function.apply(player));

        }
        
        return str;
    }
    
}
