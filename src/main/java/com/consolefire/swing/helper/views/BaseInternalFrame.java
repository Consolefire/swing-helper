/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consolefire.swing.helper.views;

import com.consolefire.swing.helper.WindowManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author sabuj.das
 */
public class BaseInternalFrame extends JInternalFrame {

    public static final Dimension DEFAULT_SIZE = new Dimension(580, 350);
    private final JPanel workerPanel;
    private final JPanel containerPanel;

    public BaseInternalFrame(JPanel workerPanel) {
        this.workerPanel = workerPanel;
        this.containerPanel = new JPanel();
        initComponents();
        addInternalFrameListener(WindowManager.getManager());
        if (null != workerPanel) {
            containerPanel.add(workerPanel, BorderLayout.CENTER);
        }
        containerPanel.updateUI();
    }

    private void initComponents() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Base");
        setMinimumSize(DEFAULT_SIZE);
        setPreferredSize(DEFAULT_SIZE);
        setSize(DEFAULT_SIZE);
        containerPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(containerPanel, java.awt.BorderLayout.CENTER);

        pack();
    }

}
