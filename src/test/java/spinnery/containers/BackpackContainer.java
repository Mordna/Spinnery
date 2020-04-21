package spinnery.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import spinnery.common.BaseContainer;
import spinnery.common.BaseInventory;
import spinnery.util.InventoryUtilities;
import spinnery.widget.WInterface;
import spinnery.widget.WSlot;

public class BackpackContainer extends BaseContainer {

    public static final int BACKPACK_INVENTORY = 1;
    public PlayerEntity player;
    public Text name;
    ItemStack backpackStack;

    public BackpackContainer(Text name, int synchronizationID, PlayerInventory playerInventory) {
        super(synchronizationID, playerInventory);
        this.player = playerInventory.player;
        this.name = name;

        WInterface mainInterface = getInterface();
        BaseInventory backpackInventory = new BaseInventory(27);

        backpackStack = player.getMainHandStack();
        if(backpackStack.hasTag()) {
            backpackInventory = InventoryUtilities.read(backpackStack.getTag());
        }
        backpackInventory.addListener(sender -> backpackStack.setTag(InventoryUtilities.write(getInventory(BACKPACK_INVENTORY))));

        getInventories().put(BACKPACK_INVENTORY, backpackInventory);
        WSlot.addHeadlessArray(mainInterface, 0, BACKPACK_INVENTORY, 9, 3);
        WSlot.addHeadlessPlayerInventory(mainInterface);
    }

    @Override
    public void close(PlayerEntity player) {
        backpackStack.setTag(InventoryUtilities.write(getInventory(BACKPACK_INVENTORY)));
        super.close(player);
    }
}
