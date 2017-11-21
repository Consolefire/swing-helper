package com.consolefire.swing.helper.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public abstract class GraphicsUtil {
    public static String encodeColor(Color color) {
        if (null == color) {
            return "0x000000";
        }

        return "0x" + String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
    }

    public static String encodeColorForCss(Color color) {
        if (null == color) {
            return "#000000";
        }

        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
    }

    public static String encodeColor(int r, int g, int b) {
        return "0x" + String.format("%02x%02x%02x", r, g, b).toUpperCase();
    }

    public static synchronized void addRendererHint(final Graphics2D graphics) {
        if (graphics == null) {
            return;
        }
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.add(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        graphics.setRenderingHints(hints);
    }

}
