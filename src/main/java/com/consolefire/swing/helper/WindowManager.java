package com.consolefire.swing.helper;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class WindowManager implements InternalFrameListener {

    protected static final int DEFAULT_FRAME_DISTANCE = 10;
    protected static final int DEFAULT_FRAME_START_X = 10;
    protected static final int DEFAULT_FRAME_START_Y = 10;
    private static volatile int frameCount = 0;
    private static volatile boolean initialized = false;
    private static volatile WindowManager manager;


    private WindowManager() {
        internalFrameMap = new ConcurrentHashMap<>();
        hiddenFrameMap = new LinkedHashMap<>();
    }

    public static WindowManager getManager() {
        if (null == manager) {
            synchronized (WindowManager.class) {
                if (null == manager) {
                    manager = new WindowManager();
                }
            }
        }
        return manager;
    }

    private final Map<String, JInternalFrame> internalFrameMap;
    private final Map<String, JInternalFrame> hiddenFrameMap;

    private JLabel windowManagerLabel;
    private JDesktopPane baseDesktopPane;
    private JPopupMenu windowManagerPopupMenu;

    public final void init(final JLabel windowManagerLabel, final JDesktopPane baseDesktopPane,
            final JPopupMenu windowManagerPopupMenu) {
        synchronized (WindowManager.class) {
            if (null == windowManagerLabel || null == baseDesktopPane || null == windowManagerPopupMenu) {
                throw new WindowManagerException("All required components are not initialized....");
            }
            this.windowManagerLabel = windowManagerLabel;
            this.baseDesktopPane = baseDesktopPane;
            this.windowManagerPopupMenu = windowManagerPopupMenu;
            initialized = true;
        }
    }

    protected void addIFrame(JInternalFrame internalFrame) {
        isInitialized();
        if (internalFrameMap.containsKey(internalFrame.getTitle())) {
            showFrame(internalFrame);
            return;
        }
        internalFrame.setLocation(DEFAULT_FRAME_START_X + (DEFAULT_FRAME_DISTANCE * frameCount),
                DEFAULT_FRAME_START_Y + (DEFAULT_FRAME_DISTANCE * frameCount));
        frameCount++;
        baseDesktopPane.add(internalFrame);
        internalFrameMap.put(internalFrame.getTitle(), internalFrame);

        internalFrame.setVisible(true);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    private static void isInitialized() {
        if (!initialized) {
            throw new WindowManagerException("All required components are not initialized....");
        }
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        isInitialized();
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        isInitialized();
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        isInitialized();
        JInternalFrame iFrame = e.getInternalFrame();
        if (null != iFrame) {
            internalFrameMap.remove(iFrame.getTitle());
            hiddenFrameMap.remove(iFrame.getTitle());
            removePopupMenu(iFrame.getTitle());
        }
        frameCount--;
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
        isInitialized();
        JInternalFrame iFrame = e.getInternalFrame();
        if (null != iFrame) {
            iFrame.hide();
            hiddenFrameMap.put(iFrame.getTitle(), iFrame);
            addPopupMenus(iFrame);
        }
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
        isInitialized();
        JInternalFrame iFrame = e.getInternalFrame();
        if (null != iFrame) {
            iFrame.setVisible(true);
            iFrame.setEnabled(true);
            try {
                iFrame.setSelected(true);
            } catch (PropertyVetoException ex) {

            }
            hiddenFrameMap.remove(iFrame.getTitle());
            removePopupMenu(iFrame.getTitle());
        }
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        isInitialized();
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
        isInitialized();
    }

    private void addPopupMenus(final JInternalFrame iFrame) {
        isInitialized();
        if (null != iFrame) {
            JMenuItem menuItem = new JMenuItem(iFrame.getTitle());
            menuItem.setIcon(iFrame.getFrameIcon());
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (iFrame.isIcon()) {
                        iFrame.show();
                        baseDesktopPane.getDesktopManager().deiconifyFrame(iFrame);
                        baseDesktopPane.getDesktopManager().activateFrame(iFrame);
                        try {
                            iFrame.setSelected(true);
                        } catch (PropertyVetoException ex) {

                        }
                        iFrame.updateUI();
                    }
                    baseDesktopPane.moveToFront(iFrame);
                    hiddenFrameMap.remove(iFrame.getTitle());
                    removePopupMenu(iFrame.getTitle());
                }
            });
            windowManagerPopupMenu.add(menuItem);
            windowManagerPopupMenu.updateUI();
        }
        if (hiddenFrameMap.size() > 0) {
            windowManagerLabel.setEnabled(true);
        } else {
            windowManagerLabel.setEnabled(false);
        }
    }

    private void removePopupMenu(String title) {
        isInitialized();
        Component[] components = windowManagerPopupMenu.getComponents();
        if (null != components && components.length > 0) {
            for (int i = 0; i < components.length; i++) {
                Component component = components[i];
                if (component instanceof JMenuItem) {
                    JMenuItem menuItem = (JMenuItem) component;
                    if (title.equals(menuItem.getText())) {
                        windowManagerPopupMenu.remove(component);
                    }
                }
            }
        }
        windowManagerPopupMenu.updateUI();
        if (hiddenFrameMap.size() > 0) {
            windowManagerLabel.setEnabled(true);
        } else {
            windowManagerLabel.setEnabled(false);
        }
    }

    protected boolean containsFrame(String frameTitle) {
        return internalFrameMap.containsKey(frameTitle);
    }

    protected void showFrame(String frameTitle) {
        showFrame(internalFrameMap.get(frameTitle));
    }

    private void showFrame(JInternalFrame internalFrame) {
        isInitialized();
        if (null == internalFrame) {
            return;
        }
        if (internalFrame.isIcon()) {
            internalFrame.show();
            baseDesktopPane.getDesktopManager().deiconifyFrame(internalFrame);

            baseDesktopPane.moveToFront(internalFrame);
            hiddenFrameMap.remove(internalFrame.getTitle());
            removePopupMenu(internalFrame.getTitle());
        } else {
            baseDesktopPane.moveToFront(internalFrame);
            try {
                internalFrame.setSelected(true);
            } catch (PropertyVetoException ex) {
            }
        }

    }

    public void cascadeWindows(JDesktopPane desktopPane) {
        isInitialized();
        JInternalFrame[] frames = desktopPane.getAllFrames();
        if (frames.length == 0) {
            return;
        }

        cascadeWindows(frames, desktopPane.getBounds(), 24);
    }

    public void cascadeWindows(JDesktopPane desktopPane, int layer) {
        isInitialized();
        JInternalFrame[] frames = desktopPane.getAllFramesInLayer(layer);
        if (frames.length == 0) {
            return;
        }

        cascadeWindows(frames, desktopPane.getBounds(), 24);
    }

    private void cascadeWindows(JInternalFrame[] frames, Rectangle dBounds, int separation) {
        isInitialized();
        int margin = frames.length * separation + separation;
        int width = dBounds.width - margin;
        int height = dBounds.height - margin;
        for (int i = 0; i < frames.length; i++) {
            frames[i].setBounds(separation + dBounds.x + i * separation, separation + dBounds.y + i * separation, width,
                    height);
        }
    }

    public void tileWindows(JDesktopPane desktopPane) {
        isInitialized();
        // How many frames do we have?
        JInternalFrame[] allframes = desktopPane.getAllFrames();
        int count = allframes.length;
        if (count == 0) {
            return;
        }

        // Determine the necessary grid size
        int sqrt = (int) Math.sqrt(count);
        int rows = sqrt;
        int cols = sqrt;
        if (rows * cols < count) {
            cols++;
            if (rows * cols < count) {
                rows++;
            }
        }

        // Define some initial values for size & location.
        Dimension size = desktopPane.getSize();

        int w = size.width / cols;
        int h = size.height / rows;
        int x = 0;
        int y = 0;

        // Iterate over the frames, deiconifying any iconified frames and then
        // relocating & resizing each.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols && ((i * cols) + j < count); j++) {
                JInternalFrame f = allframes[(i * cols) + j];

                if (!f.isClosed() && f.isIcon()) {
                    try {
                        f.setIcon(false);
                    } catch (PropertyVetoException ignored) {
                    }
                }

                desktopPane.getDesktopManager().resizeFrame(f, x, y, w, h);
                x += w;
            }
            y += h; // start the next row
            x = 0;
        }
    }

    public void showAllFrames() {
        isInitialized();
        JInternalFrame[] frames = baseDesktopPane.getAllFrames();
        if (null != frames && frames.length > 0) {
            for (int i = 0; i < frames.length; i++) {
                JInternalFrame iFrame = frames[i];
                if (iFrame.isIcon()) {
                    iFrame.show();
                    baseDesktopPane.getDesktopManager().deiconifyFrame(iFrame);
                    baseDesktopPane.getDesktopManager().activateFrame(iFrame);
                    baseDesktopPane.moveToFront(iFrame);
                    iFrame.updateUI();
                    hiddenFrameMap.remove(iFrame.getTitle());
                    removePopupMenu(iFrame.getTitle());
                    try {
                        iFrame.setSelected(true);
                    } catch (PropertyVetoException ex) {

                    }
                }
            }
        }
    }
}
