package spinnery.items;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import spinnery.Spinnery;

public class BackpackItem extends Item {
    public BackpackItem() {
        super(new Settings().maxCount(1).group(ItemGroup.MISC));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ContainerProviderRegistry.INSTANCE.openContainer(Spinnery.BACKPACK_ID, user, (buffer) -> {
                buffer.writeText(new LiteralText("BACKPACK (TEST)"));
            });
        }
        return TypedActionResult.success(user.getMainHandStack());
    }
}
