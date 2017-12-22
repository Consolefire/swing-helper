package com.consolefire.swing.helper;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class ComponentBuilderFactory {

    private static final String DEFAULT_IMAGE_PATH = "/defaults/images/";
    public static final ImageIcon _ICON;
    public static final ImageIcon HIDE_ME_ICON;
    public static final ImageIcon EXIT_ICON;

    static {
        _ICON = new ImageIcon(ComponentBuilderFactory.class.getResource(DEFAULT_IMAGE_PATH + "").getFile());
        EXIT_ICON = new ImageIcon(ComponentBuilderFactory.class.getResource(DEFAULT_IMAGE_PATH + "exit.png").getFile());
        HIDE_ME_ICON = new ImageIcon(ComponentBuilderFactory.class.getResource(DEFAULT_IMAGE_PATH + "hide.png").getFile());
    }


    public static class JMenuBuilder extends ComponentBuilder<JMenu> {

        public JMenuBuilder() {
            super(new JMenu());
        }

        public static JMenuBuilder create() {
            return new JMenuBuilder();
        }

        public JMenuBuilder withLabel(String label) {
            component.setText(label);
            return this;
        }

        public JMenuBuilder withItem(JMenuItem menuItem) {
            component.add(menuItem);
            return this;
        }

        public JMenuBuilder withSeparator() {
            component.addSeparator();
            return this;
        }

    }


    public static class JMenuItemBuilder extends ComponentBuilder<JMenuItem> {

        public JMenuItemBuilder() {
            super(new JMenuItem());
        }

        public static JMenuItemBuilder create() {
            return new JMenuItemBuilder();
        }

        public JMenuItemBuilder withLabel(String label) {
            component.setText(label);
            return this;
        }

        public JMenuItemBuilder withKeyStroke(KeyStroke keyStroke) {
            component.setAccelerator(keyStroke);
            return this;
        }

        public JMenuItemBuilder withAction(ActionListener actionListener) {
            component.addActionListener(actionListener);
            return this;
        }

        public JMenuItemBuilder withIcon(ImageIcon imageIcon) {
            component.setIcon(imageIcon);
            return this;
        }
    }

    public static class KeyStrokeGenerator {
        private final int keyCode;
        private int keyMask;

        public static KeyStrokeGenerator create(final int keyCode) {
            return new KeyStrokeGenerator(keyCode);
        }

        public KeyStrokeGenerator(int keyCode) {
            this.keyCode = keyCode;
        }

        public KeyStroke generate() {
            return KeyStroke.getKeyStroke(keyCode, keyMask);
        }

        public KeyStrokeGenerator withModifier(int mask) {
            this.keyMask = mask;
            return this;
        }

    }
}
