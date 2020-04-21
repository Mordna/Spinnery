package spinnery.screens;

import spinnery.client.BaseScreen;
import spinnery.widget.WAbstractWidget;
import spinnery.widget.WInterface;
import spinnery.widget.WPanel;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;

public class EmptyPanelScreen extends BaseScreen {
    public EmptyPanelScreen() {
        super();

        WInterface mainInterface = getInterface();
        WPanel mainPanel = mainInterface.createChild(WPanel::new, Position.of(0, 0, 0),
                Size.of(9 * 18 + 8, 3 * 18 + 108))
                .setParent(mainInterface);
        mainPanel.setLabel("Test Empty Panel");
        mainPanel.setOnAlign(WAbstractWidget::center);
        mainPanel.center();
        mainInterface.add(mainPanel);

    }
}
