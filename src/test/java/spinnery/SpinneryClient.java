package spinnery;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spinnery.registry.NetworkRegistry;
import spinnery.registry.ResourceRegistry;
import spinnery.registry.WidgetRegistry;
import spinnery.screens.BackpackContainerScreen;

public class SpinneryClient implements ClientModInitializer {
	public static final String LOG_ID = "Spinnery";
	public static final Identifier MOD_ID = new Identifier(LOG_ID.toLowerCase());
	public static Logger LOGGER = LogManager.getLogger("Spinnery");

	@Override
	public void onInitializeClient() {
		LOGGER.warn("Spinnery was started in testing mode.");
		NetworkRegistry.initializeClient();
		WidgetRegistry.initialize();
		ResourceRegistry.initialize();

		initTests();
	}

	private void initTests() {
		ScreenProviderRegistry.INSTANCE.registerFactory(Spinnery.BACKPACK_ID, BackpackContainerScreen::new);
	}
}
