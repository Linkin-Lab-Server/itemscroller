package fi.dy.masa.itemscroller.event;

import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import fi.dy.masa.itemscroller.recipes.RecipeStorage;
import fi.dy.masa.itemscroller.villager.VillagerDataStorage;

public class ClientWorldChangeHandler implements fi.dy.masa.malilib.event.ClientWorldChangeHandler
{
    @Override
    public void onPreClientWorldChange(@Nullable WorldClient worldBefore, @Nullable WorldClient worldAfter, Minecraft mc)
    {
        // Quitting to main menu, save the settings before the integrated server gets shut down
        if (worldBefore != null && worldAfter == null)
        {
            RecipeStorage.getInstance().writeToDisk();
            VillagerDataStorage.getInstance().writeToDisk();
        }
    }

    @Override
    public void onPostClientWorldChange(@Nullable WorldClient worldBefore, @Nullable WorldClient worldAfter, Minecraft mc)
    {
        // Logging in to a world, load the data
        if (worldBefore == null && worldAfter != null)
        {
            RecipeStorage.getInstance().readFromDisk();
            VillagerDataStorage.getInstance().readFromDisk();
        }
    }
}
