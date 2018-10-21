import org.bukkit.configuration.file.FileConfiguration

/**
 * Get the map at the specified configuration `path`.
 *
 * The map should be in this format:
 * ```
 * path:
 *   key: value
 *   key: value
 * ```
 * This function will return null if the created map
 * if empty or casting key-value pairs to their preferred
 * type failed.
 */
inline fun <reified K, reified V> FileConfiguration.getMap(path: String, keyParser: (String) -> K, valueParser: (String) -> V): MutableMap<K, V>? {

    val map = mutableMapOf<K, V>()

    val section = getConfigurationSection(path)
    val keySet = section.getKeys(false)

    /* Iterate through all keys of specified
    `path` converting each key-value pair to K
    and V then adding each key-value pair to a
    mutable map of <K, V>
    */
    for (key in keySet) {
        val keyType = keyParser.invoke(key)
        val valueType = valueParser.invoke(section.getString(key))
        map[keyType] = valueType
    }

    return if (map.isEmpty()) null else map
}