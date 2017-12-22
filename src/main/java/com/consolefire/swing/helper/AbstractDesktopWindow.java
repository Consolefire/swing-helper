package com.consolefire.swing.helper;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import com.consolefire.swing.helper.utils.OsFeatureUtil;
import com.consolefire.swing.helper.utils.WindowUtil;

public class AbstractDesktopWindow extends JFrame {

    private static final long serialVersionUID = -2261316425068181046L;
    private static final WindowManager WINDOW_MANAGER = WindowManager.getManager();
    private static final Dimension DEFAULT_PREFERED_SIZE = new Dimension(800, 600);
    private static final Dimension DEFAULT_MINIMUM_SIZE = new Dimension(480, 300);
    
    private final JMenuBar desktopMenuBar;
    private final JDesktopPane desktopPane;
    private final JToolBar toolBar;
    private final JToolBar statusBar;
    private final JLabel windowManagerLabel;
    private final JPopupMenu windowManagerPopupMenu;


    public AbstractDesktopWindow() {
        desktopMenuBar = new JMenuBar();
        desktopPane = new JDesktopPane();
        toolBar = new JToolBar();
        statusBar = new JToolBar();
        windowManagerLabel = new JLabel("");
        windowManagerPopupMenu = new JPopupMenu();
        init();
    }

    public AbstractDesktopWindow(JMenuBar desktopMenuBar, JDesktopPane desktopPane, JToolBar toolBar, JToolBar statusBar) {
        this.desktopMenuBar = desktopMenuBar;
        this.desktopPane = desktopPane;
        this.toolBar = toolBar;
        this.statusBar = statusBar;
        windowManagerLabel = new JLabel("");
        windowManagerPopupMenu = new JPopupMenu();
        init();
    }

    private void init() {
        OsFeatureUtil.enableOSXFullscreen(this);
        setPreferredSize(DEFAULT_PREFERED_SIZE);
        setMinimumSize(DEFAULT_MINIMUM_SIZE);
        setSize(DEFAULT_PREFERED_SIZE);
        
        desktopPane.setComponentPopupMenu(windowManagerPopupMenu);
        
        setJMenuBar(desktopMenuBar);
        getContentPane().setLayout(new BorderLayout(2, 2));
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(desktopPane, BorderLayout.CENTER);
        getContentPane().add(statusBar, BorderLayout.SOUTH);
        pack();
        
        WINDOW_MANAGER.init(windowManagerLabel, desktopPane, windowManagerPopupMenu);
        WindowUtil.bringToCenter(this);
    }

    public void addMenu(JMenu menu) {
        this.desktopMenuBar.add(menu);
    }

    public void addMenuItem(final JMenu menu, JMenuItem menuItem) {

    }


}
