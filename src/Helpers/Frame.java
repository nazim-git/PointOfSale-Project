package Helpers;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

public class Frame {
	
	public static Rectangle getScreenBounds() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		System.out.println("Screen Bounds: " + bounds );
		return bounds;
	}
}
