package ttdev.api.user.inventory.xml;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import ttdev.api.user.inventory.AInventory;
import ttdev.api.user.inventory.fill.*;
import ttdev.api.user.items.Item;

import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Tre Logan
 */
public class GUILoader {

    private static final String MENU_ELEMENT = "menu";
    private static final String ITEM_ELEMENT = "item";
    private static final String ID_ATTRIBUTE = "id";

    private Class<?> clazz;
    private String path;

    public GUILoader(Class<?> clazz, String path) {
        this.clazz = clazz;
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Item loadItemFromXML(String id) {
        XMLStreamReader reader = setupStream();
        Item item = null;

        try {
            while (reader.hasNext()) {
                int code = reader.next();

                if (code != XMLStreamConstants.START_ELEMENT) {
                    continue;
                }

                String elementName = reader.getName().toString();
                int count = reader.getAttributeCount();

                if (elementName.equals(ITEM_ELEMENT)) {
                    String itemVar = getAttribute(reader, ID_ATTRIBUTE).getValue();
                    if (!itemVar.equals(id)) {
                        continue;
                    }

                    item = parseXMLItemAttributes(reader, count);
                }
            }

            reader.close();

        } catch (XMLStreamException ex) {
            ex.printStackTrace();
            return null;
        }

        return item;
    }

    public AInventory loadMenuFromXML(String id) {
        XMLStreamReader reader = setupStream();
        AInventory inventory = null;

        try {
            while (reader.hasNext()) {
                int code = reader.next();

                if (code != XMLStreamConstants.START_ELEMENT) {
                    continue;
                }

                String elementName = reader.getName().toString();
                int count = reader.getAttributeCount();

                if (elementName.equals(MENU_ELEMENT)) {
                    String menuVar = getAttribute(reader, ID_ATTRIBUTE).getValue();
                    if (!menuVar.equals(id)) {
                        continue;
                    }

                    inventory = parseXMLMenuAttributes(reader, count);
                }
            }

            reader.close();

        } catch (XMLStreamException ex) {
            ex.printStackTrace();
            return null;
        }

        return inventory;
    }

    private XMLStreamReader setupStream() {
        InputStream stream = clazz.getResourceAsStream(path);
        XMLStreamReader reader;
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            reader = factory.createXMLStreamReader(stream);
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
            return null;
        }

        return reader;
    }

    private AInventory parseXMLMenuAttributes(XMLStreamReader reader, int count) {
        AInventory inventory = new AInventory("", 6);

        for (int i = 0; i < count; i++) {

            String name = reader.getAttributeName(i).toString();
            String value = reader.getAttributeValue(i);

            switch (name) {
                case "title":
                    inventory.setName(ChatColor.translateAlternateColorCodes('^', value));
                    break;
                case "rows":
                    inventory.setRows(Integer.parseInt(value));
                    break;
                case "unifill":
                    FillColor color = FillColor.getFillColor(value);
                    inventory.fill(new UniFill(color));
                    break;
                case "bifill": {
                    String[] vals = value.split(",");
                    FillColor colorOne = FillColor.getFillColor(vals[0].trim());
                    FillColor colorTwo = FillColor.getFillColor(vals[1].trim());
                    inventory.fill(new BiFill(colorOne, colorTwo));
                }
                break;
                case "trifill": {
                    String[] vals = value.split(",");
                    FillColor colorOne = FillColor.getFillColor(vals[0].trim());
                    FillColor colorTwo = FillColor.getFillColor(vals[1].trim());
                    FillColor colorThree = FillColor.getFillColor(vals[2].trim());
                    inventory.fill(new TriFill(colorOne, colorTwo, colorThree));
                }
                break;
                case "quadfill": {
                    String[] vals = value.split(",");
                    FillColor colorOne = FillColor.getFillColor(vals[0].trim());
                    FillColor colorTwo = FillColor.getFillColor(vals[1].trim());
                    FillColor colorThree = FillColor.getFillColor(vals[2].trim());
                    FillColor colorFour = FillColor.getFillColor(vals[3].trim());
                    inventory.fill(new QuadFill(colorOne, colorTwo, colorThree, colorFour));
                }
                break;
            }
        }

        return inventory;
    }

    private Item parseXMLItemAttributes(XMLStreamReader reader, int count) {
        Item item = new Item(new ItemStack(Material.AIR));

        for (int i = 0; i < count; i++) {

            String name = reader.getAttributeName(i).toString();
            String value = reader.getAttributeValue(i);

            switch (name) {
                case "material":
                    item.setMaterial(Material.getMaterial(value.toUpperCase()));
                    break;
                case "clearName":
                    item.setName("");
                    break;
                case "name":
                    item.setName(ChatColor.translateAlternateColorCodes('^', value));
                    break;
                case "amount":
                    item.setAmount(Integer.parseInt(value));
                    break;
                case "durability":
                    item.setDurability(Short.parseShort(value));
                    break;
                case "magic":
                    item.setMagic(Boolean.parseBoolean(value));
                    break;
                case "flags": {
                    String[] parts = value.split(",");
                    Stream<ItemFlag> flagStream = Arrays.stream(parts).map(ItemFlag::valueOf);
                    Set<ItemFlag> flagList = flagStream.collect(Collectors.toSet());
                    item.addItemFlags(flagList);
                }
                break;
                case "lore": {
                    String[] parts = value.split(",");
                    Stream<String> partStream = Arrays.stream(parts)
                            .map(String::trim)
                            .map(s -> ChatColor.translateAlternateColorCodes('^', s));
                    List<String> partList = partStream.collect(Collectors.toList());
                    item.setLore(partList);
                }
                break;
                default:
            }
        }

        return item;
    }

    private Attribute getAttribute(XMLStreamReader reader, String name) {
        int count = reader.getAttributeCount();
        XMLEventFactory eventFactory = XMLEventFactory.newFactory();

        for (int i = 0; i < count; i++) {
            String attName = reader.getAttributeName(i).toString();
            String value = reader.getAttributeValue(i);
            if (attName.equals(name)) {
                return eventFactory.createAttribute(name, value);
            }
        }

        return null;
    }

}
