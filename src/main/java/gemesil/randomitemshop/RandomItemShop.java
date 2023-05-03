package gemesil.randomitemshop;

import gemesil.randomitemshop.commands.ShopCommand;
import gemesil.randomitemshop.files.SaveData;
import gemesil.randomitemshop.tasks_and_events.RefreshShopTask;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Map;

public final class RandomItemShop extends JavaPlugin {

    private static RandomItemShop plugin;
    RefreshShopTask refreshShopTask;
    final int SHOP_REFRESH_COOLDOWN_IN_TICKS = 864000;

    FileConfiguration config = getConfig();
    String prefix, NoPermissionsMessage, NoBypassMessage;

    @Override
    public void onEnable() {
        // Load shop data from before plugin was disabled
        File shopItemsFile = new File(getDataFolder(), "shopItemsSave.yml");
        if (shopItemsFile.exists()) {
            Map<Material, Double>  shopItems = loadShopItemsFromFile(shopItemsFile);
        }
        // todo

        // Save config settings as default
        config.options().copyDefaults(true);
        saveConfig();

        // Get chat response text from config
        prefix = ChatColor.translateAlternateColorCodes('&',config.getString("prefix"));
        NoPermissionsMessage = ChatColor.translateAlternateColorCodes('&',config.getString("NoPermissionsMessage"));
        NoBypassMessage = ChatColor.translateAlternateColorCodes('&',config.getString("NoBypassMessage"));

        // Register commands
        getCommand("shop").setExecutor(new ShopCommand());

        // Refresh the shop every 12h (864000 ticks)
        refreshShopTask = new RefreshShopTask();
        refreshShopTask.runTaskTimer(this, 0, SHOP_REFRESH_COOLDOWN_IN_TICKS); // (1 seconds = 20 ticks)

    }

    @Override
    public void onDisable() {
        // Save current shop items to file
        File shopItemsFile = new File(getDataFolder(), "prices.yml");
        saveShopItemsToFile(prices, shopItemsFile);
        // todo


    }

    public static RandomItemShop getPlugin() {
        return plugin;
    }
}