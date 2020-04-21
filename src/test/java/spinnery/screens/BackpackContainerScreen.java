package spinnery.screens;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import spinnery.common.BaseContainerScreen;
import spinnery.containers.BackpackContainer;
import spinnery.widget.WAbstractWidget;
import spinnery.widget.WInterface;
import spinnery.widget.WPanel;
import spinnery.widget.WSlot;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;

public class BackpackContainerScreen extends BaseContainerScreen<BackpackContainer> {

    public BackpackContainerScreen(BackpackContainer container) {
        super(container.name, container, container.player);

        WInterface mainInterface = getInterface();
        WPanel mainPanel = mainInterface.createChild(WPanel::new, Position.of(0, 0, 0), Size.of(9 * 18 + 8, 3 * 18 + 108)).setParent(mainInterface);
        mainPanel.setLabel(container.name);
        mainPanel.setOnAlign(WAbstractWidget::center);
        mainPanel.center();
        mainInterface.add(mainPanel);
        WSlot.addPlayerInventory(Position.of(mainPanel, ((mainPanel.getWidth()) / 2) - (int) (18 * 4.5f), 3 * 18 + 24, 1), Size.of(18, 18), mainInterface);
        WSlot.addArray(Position.of(mainPanel, 4, 19, 1), Size.of(18, 18), mainInterface, 0, BackpackContainer.BACKPACK_INVENTORY, 9, 3);
    }
}
