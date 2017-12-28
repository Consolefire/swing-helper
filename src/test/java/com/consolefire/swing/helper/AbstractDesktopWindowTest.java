package com.consolefire.swing.helper;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import com.consolefire.swing.helper.ComponentBuilderFactory.JMenuBuilder;
import com.consolefire.swing.helper.ComponentBuilderFactory.JMenuItemBuilder;
import com.consolefire.swing.helper.ComponentBuilderFactory.KeyStrokeGenerator;
import com.consolefire.swing.helper.utils.OsFeatureUtil;

public class AbstractDesktopWindowTest {


    public static void main(String[] args) {
        OsFeatureUtil.applyOsFeatures();
        final AbstractDesktopWindow window = new AbstractDesktopWindow() {
            @Override
            protected void initComponents() {
                // TODO Auto-generated method stub

            }
        };
        JMenu menu = JMenuBuilder.create().withLabel("File")
                .withItem(JMenuItemBuilder.create().withLabel("Open")
                        .withKeyStroke(KeyStrokeGenerator.create(KeyEvent.VK_O)
                                .withModifier(Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()).generate())
                        .withAction(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showConfirmDialog(window, "Open");
                            }
                        }).build())
                .withSeparator()
                .withItem(JMenuItemBuilder.create().withLabel("Extract")
                        .withKeyStroke(KeyStrokeGenerator.create(KeyEvent.VK_X)
                                .withModifier(Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()).generate())
                        .withAction(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showConfirmDialog(window, "Extract");
                            }
                        }).build())
                .withItem(JMenuItemBuilder.create().withLabel("Extract To..")
                        .withKeyStroke(KeyStrokeGenerator.create(KeyEvent.VK_X)
                                .withModifier(
                                        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() | ActionEvent.SHIFT_MASK)
                                .generate())
                        .withAction(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showConfirmDialog(window, "Extract To");
                            }
                        }).build())

                .build();
        window.addMenu(menu);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    }

}
