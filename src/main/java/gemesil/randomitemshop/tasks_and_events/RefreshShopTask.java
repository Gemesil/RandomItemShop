package gemesil.randomitemshop.tasks_and_events;

import gemesil.randomitemshop.ChatLogger;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static org.bukkit.Bukkit.getLogger;

public class RefreshShopTask extends BukkitRunnable {
    // This action generates a new set of blocks and their costs. By default, it runs on a daily basis.

    private Map<Material, Double> shop_items;
    private Random random;
    final int AMOUNT_OF_SHOP_ITEMS_TO_GENERATE = 5;

    @Override
    public void run() {

        /*
        Generates 5 random blocks/items from the game, for every one of those blocks/items generates a "price" or "cost" of other blocks.
        For example, a randomly chosen oak tree block costs 3 sticks.
        This information is saved after its generated.
        The price of each block will try to match how rare or difficult it is to obtain.
        */

        ChatLogger.sendAlert("The shop has been refreshed!");

        shop_items = new HashMap<>();
        random = new Random();

        // Generate 5 random blocks/items
        List<Material> materials = getRandomMaterials(AMOUNT_OF_SHOP_ITEMS_TO_GENERATE);

        // Generate prices for each material
        for (Material material : materials) {
            double price = getPrice(material);
            shop_items.put(material, price);
            getLogger().info(material.name() + " costs " + price + " to obtain.");
        }
    }

    private List<Material> getRandomMaterials(int count) {
        List<Material> materials = new ArrayList<>();
        while (materials.size() < count) {
            Material material = Material.values()[random.nextInt(Material.values().length)];
            if (!materials.contains(material) && !isUnobtainable(material)) {
                materials.add(material);
            }
        }
        return materials;
    }

    private boolean isUnobtainable(Material material) {
        switch (material) {
            case BARRIER:
            case COMMAND_BLOCK:
            case COMMAND_BLOCK_MINECART:
            case END_GATEWAY:
            case END_PORTAL:
            case END_PORTAL_FRAME:
            case FIRE:
            case JIGSAW:
            case MOVING_PISTON:
            case STRUCTURE_BLOCK:
                return true;
            default:
                return false;
        }
    }

    private double getPrice(Material material) {
        switch (material) {
            case ANCIENT_DEBRIS:
            case NETHERITE_BLOCK:
                return 64.0;
            case DIAMOND_BLOCK:
            case EMERALD_BLOCK:
                return 32.0;
            case DRAGON_EGG:
                return 24.0;
            case BEACON:
                return 12.0;
            case OBSIDIAN:
                return 5.0;
            default:
                return 1.0;
        }
    }
}
