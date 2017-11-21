package com.consolefire.swing.helper.utils;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.JDialog;

public abstract class WindowUtil {

    /**
     * This method changes the location of the component to move it to the center of the screen.
     * 
     * @param component
     */
    public static void bringToCenter(final Component component) {
        if (null == component) {
            return;
        }
        Dimension originalSize = component.getSize();
        Dimension scrResolution = Toolkit.getDefaultToolkit().getScreenSize();
        Point location = new Point((int) ((scrResolution.getWidth() / 2) - (originalSize.getWidth() / 2)),
                (int) ((scrResolution.getHeight() / 2) - (originalSize.getHeight() / 2)));
        component.setLocation(location);
    }

    /**
     * Changes the location of the child component according to the parent component to make the
     * child component center to the parent component. <br>
     * <i>Note: </i>The component can be a Frame or Dialog.
     * 
     * @param child
     * @param parent
     */
    public static void bringCenterTo(final Component child, final Component parent) {
        if (null == child || null == parent) {
            return;
        }
        Dimension childOriginalSize = child.getSize();
        Dimension parentSize = parent.getSize();
        Point parentLocation = parent.getLocation();
        Point location = new Point(parentLocation.x + (parentSize.width / 2 - childOriginalSize.width / 2),
                parentLocation.y + (parentSize.height / 2 - childOriginalSize.height / 2));
        child.setLocation(location);
    }

    /**
     * Enables/Disables all the components based on the input.
     *
     * @param components
     */
    public static void enableComponents(final boolean enable, final Component... components) {
        if (null == components) {
            return;
        }
        if (components.length <= 0) {
            return;
        }
        for (Component component : components) {
            if (null != component) {
                component.setEnabled(enable);
            }
        }
    }

    /**
     * Enables/Disables all the components of a Frame based on the input.
     *
     * @param enable
     * @param parent
     */
    public static void enableAllComponents(final boolean enable, final Frame parent) {
        if (null != parent) {
            Component[] components = parent.getComponents();
            if (null == components) {
                return;
            }
            if (components.length <= 0) {
                return;
            }
            for (Component component : components) {
                if (null != component) {
                    component.setEnabled(enable);
                }
            }
        }
    }

    /**
     * Enables/Disables all the components of a Frame except the specified components based on the
     * input.
     *
     * @param enable
     * @param parent
     * @param components
     */
    public static void enableAllComponentsExcept(final boolean enable, final Frame parent,
            final Component... components) {
        if (null != parent) {
            Component[] allComponents = parent.getComponents();
            if (null == allComponents) {
                return;
            }
            if (allComponents.length <= 0) {
                return;
            }
            for (Component component : allComponents) {
                if (null != component && components != null) {
                    if (contains(component, components)) {
                        continue;
                    }
                    component.setEnabled(enable);
                }
            }
        }
    }

    public static void visibleComponents(final boolean visible, final Component... components) {
        if (null == components) {
            return;
        }
        if (components.length <= 0) {
            return;
        }
        for (Component component : components) {
            if (null != component) {
                component.setVisible(visible);
            }
        }
    }

    public static void visibleAllComponents(final boolean visible, final Frame parent) {
        if (null != parent) {
            Component[] components = parent.getComponents();
            if (null == components) {
                return;
            }
            if (components.length <= 0) {
                return;
            }
            for (Component component : components) {
                if (null != component) {
                    component.setVisible(visible);
                }
            }
        }
    }

    public static void visibleAllComponentsExcept(final boolean visible, final Frame parent,
            final Component... components) {
        if (null != parent) {
            Component[] allComponents = parent.getComponents();
            if (null == allComponents) {
                return;
            }
            if (allComponents.length <= 0) {
                return;
            }
            for (Component component : allComponents) {
                if (null != component && components != null) {
                    if (contains(component, components)) {
                        continue;
                    }
                    component.setVisible(visible);
                }
            }
        }
    }

    /**
     * Enables/Disables all the components of a Frame based on the input.
     *
     * @param enable
     * @param parent
     */
    public static void enableAllComponents(final boolean enable, final JDialog parent) {
        if (null != parent) {
            Component[] components = parent.getRootPane().getComponents();
            if (null == components) {
                return;
            }
            if (components.length <= 0) {
                return;
            }
            for (Component component : components) {
                if (null != component) {
                    component.setEnabled(enable);
                }
            }
        }
    }

    /**
     * Enables/Disables all the components of a Frame except the specified components based on the
     * input.
     *
     * @param enable
     * @param parent
     * @param components
     */
    public static void enableAllComponentsExcept(final boolean enable, final JDialog parent,
            final Component... components) {
        if (null != parent) {
            Component[] allComponents = parent.getRootPane().getComponents();
            if (null == allComponents) {
                return;
            }
            if (allComponents.length <= 0) {
                return;
            }
            for (Component component : allComponents) {
                if (null != component && components != null) {
                    if (contains(component, components)) {
                        continue;
                    }
                    component.setEnabled(enable);
                }
            }
        }
    }

    public static TrayIcon minimizeToTray(final Frame frame, final Image image, ActionListener exitListener) {
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(image, frame.getTitle(), popup);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                trayIcon.displayMessage("Action Event", "An Action Event Has Been Performed!",
                        TrayIcon.MessageType.INFO);
            }
        };
        if (SystemTray.isSupported()) {
            // get the SystemTray instance
            final SystemTray tray = SystemTray.getSystemTray();
            MouseListener mouseListener = new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    if (MouseEvent.BUTTON1 == e.getButton() && e.getClickCount() == 2) {
                        tray.remove(trayIcon);
                        frame.setVisible(true);
                    }
                }

                public void mouseEntered(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse entered!");
                }

                public void mouseExited(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse exited!");
                }

                public void mousePressed(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse pressed!");
                }

                public void mouseReleased(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse released!");
                }
            };



            MenuItem restoreItem = new MenuItem("Restore");
            restoreItem.setShortcut(new MenuShortcut('R'));
            restoreItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tray.remove(trayIcon);
                    frame.setVisible(true);
                }
            });
            popup.add(restoreItem);

            MenuItem exitItem = new MenuItem("Exit");
            exitItem.addActionListener(exitListener);
            popup.add(exitItem);

            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(actionListener);
            trayIcon.addMouseListener(mouseListener);

            try {
                tray.add(trayIcon);
                frame.setVisible(false);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }

            return trayIcon;
        } else {
            // disable tray option in your application or
            // perform other actions
        }
        return null;
    }

    /**
     * 
     * @param item
     * @param items
     * @return
     */
    public static <T> boolean contains(T item, T... items) {
        if (null == item || null == items) {
            return false;
        }
        if (items.length <= 0) {
            return false;
        }
        return Arrays.asList(items).contains(item);
    }
}
