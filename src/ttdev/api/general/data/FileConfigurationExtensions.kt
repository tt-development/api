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
 * Key-value pairs are converted into `K` and `V` respectively
 * by the functions provided as parameters.
 */
fun <K, V> FileConfiguration.getMap(path: String, keyParser: (String) -> K, valueParser: (Any) -> V): MutableMap<K, V> {

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

    return map
}

fun <K, V> FileConfiguration.setMap(path: String, map: Map<K, V>) {

    createSection(path)

    map.entries.forEach { set("$path.${it.key}", it.value) }
}