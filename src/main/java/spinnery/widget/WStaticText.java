package spinnery.widget;

import net.minecraft.text.Text;
import spinnery.client.BaseRenderer;

import java.util.Map;

public class WStaticText extends WWidget implements WClient {
	public static final int TEXT = 7;
	protected int hexColor;
	protected Text text;

	public WStaticText(WAnchor anchor, int positionX, int positionY, int positionZ, Text text, WInterface linkedPanel) {
		setInterface(linkedPanel);

		setAnchor(anchor);

		setAnchoredPositionX(positionX);
		setAnchoredPositionY(positionY);
		setPositionZ(positionZ);

		setSizeX(sizeX);

		setTheme("light");

		setText(text);
	}

	public static WWidget.Theme of(Map<String, String> rawTheme) {
		WWidget.Theme theme = new WWidget.Theme();
		theme.add(TEXT, WColor.of(rawTheme.get("text")));
		return theme;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	@Override
	public void setTheme(String theme) {
		if (getInterface().isClient()) {
			super.setTheme(theme);
		}
	}

	@Override
	public void draw() {
		if (isHidden()) {
			return;
		}

		int x = getPositionX();
		int y = getPositionY();

		BaseRenderer.getTextRenderer().drawWithShadow(getText().asFormattedString(), x, y, getColor(TEXT).RGB);
	}
}
