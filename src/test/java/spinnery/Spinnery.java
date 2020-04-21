package spinnery;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spinnery.client.BaseScreen;
import spinnery.containers.BackpackContainer;
import spinnery.items.BackpackItem;
import spinnery.registry.NetworkRegistry;
import spinnery.screens.EmptyPanelScreen;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Stream;

import static com.mojang.brigadier.arguments.StringArgumentType.string;

public class Spinnery implements ModInitializer {
    public static final String LOG_ID = "Spinnery";
    public static final String MOD_ID = LOG_ID.toLowerCase();
    public static Logger LOGGER = LogManager.getLogger("Spinnery");

    @Override
    public void onInitialize() {
        LOGGER.warn("Spinnery was started in testing mode.");
        NetworkRegistry.initialize();

        initTests();
    }

    private void initTests() {
        Registry.register(Registry.ITEM, new Identifier("spinnery", "backpack"), new BackpackItem());
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_ID,
                (syncId, id, player, buf) -> new BackpackContainer(buf.readText(), syncId, player.inventory));

        CommandRegistry.INSTANCE.register(false, dispatcher -> {
            dispatcher.register(CommandManager.literal("spinnery")
                    .then(CommandManager.literal("test")
                            .then(CommandManager.argument("screen", string()).suggests(
                                    (context, builder) -> {
                                        String remain = builder.getRemaining().toLowerCase(Locale.ROOT);
                                        for (String s : screens.keySet()) {
                                            if (s.toLowerCase(Locale.ROOT).startsWith(remain)) {
                                                builder.suggest(s);
                                            }
                                        }
                                        return builder.buildFuture();
                                    })
                                    .executes(ctx -> {
                                        Class<? extends BaseScreen> choosen = screens.get(StringArgumentType.getString(ctx, "screen"));
                                        if (choosen != null) {
                                            try {
                                                if (ctx.getSource().getMinecraftServer().isSinglePlayer()) {
                                                    MinecraftClient.getInstance().openScreen(choosen.getDeclaredConstructor().newInstance());
                                                }
                                            } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        return 1;
                                    })
                            )
                    )
            );
        });
    }

    private static HashMap<String, Class<? extends BaseScreen>> screens = new HashMap<>();
    public static final Identifier BACKPACK_ID = new Identifier("spinnery", "backpack");

    static {
        screens.put("empty_panel", EmptyPanelScreen.class);
    }
}
