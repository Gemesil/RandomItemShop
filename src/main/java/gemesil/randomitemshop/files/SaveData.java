package gemesil.randomitemshop.files;

import org.bukkit.Material;

import java.io.*;
import java.util.Map;

import static org.bukkit.Bukkit.getLogger;

public class SaveData {

    public Map<Material, Double> loadShopItemsFromFile() {

        try {
            // Open file for reading
            File file = new File(getDataFolder(), "prices.dat");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            // Read prices map from file
            Map<Material, Double> shop_items = (Map<Material, Double>) ois.readObject();

            // Close streams
            ois.close();
            fis.close();

            if (shop_items != null) {
                return shop_items;
            }

        } catch (IOException | ClassNotFoundException e) {
            getLogger().severe("Failed to load prices from file: " + e.getMessage());
        }

        return null;
    }

    public void saveShopItemsToFile(Map<Material, Double> shop_items) {
        try {
            // Open file for writing
            File file = new File(getDataFolder(), "prices.dat");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Write prices map to file
            oos.writeObject(shop_items);

            // Close streams
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            getLogger().severe("Failed to save prices to file: " + e.getMessage());
        }
    }
}
