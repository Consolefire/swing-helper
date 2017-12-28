package com.consolefire.swing.helper.views;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class OkCancelOptionDialog extends JDialog {
    private static final long serialVersionUID = 6640319827518779938L;
    private ActionListener cancelListener;
    private ActionListener okListener;
    private final JPanel workerPanel;

    private JButton cancelButton;
    private JPanel containerPanel;
    private JSeparator jSeparator1;
    private JButton okButton;

    public OkCancelOptionDialog(Frame parent, boolean modal, String title, ImageIcon icon, JPanel workerPanel) {
        super(parent, modal);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        if (null == title) {
            title = "OkCancelOptionDialog";
        }
        setTitle(title);
        if (null != icon) {
            setIconImage(icon.getImage());
        }
        this.workerPanel = workerPanel;
        initComponents();
    }

    public void addCancelListener(ActionListener actionListener) {
        if (null != actionListener) {
            this.cancelListener = actionListener;
            cancelButton.addActionListener(cancelListener);
        }
    }

    public void addOkListener(ActionListener actionListener) {
        if (null != actionListener) {
            this.okListener = actionListener;
            okButton.addActionListener(okListener);
        }
    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        containerPanel = new JPanel();
        jSeparator1 = new JSeparator();
        cancelButton = new JButton();
        okButton = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        containerPanel.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

        if (null != workerPanel) {
            containerPanel.add(workerPanel, BorderLayout.CENTER);
        }

        getContentPane().add(containerPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jSeparator1, gridBagConstraints);

        cancelButton.setText("Cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        if (null != cancelListener) {
            cancelButton.addActionListener(cancelListener);
        }
        getContentPane().add(cancelButton, gridBagConstraints);

        okButton.setText("Ok");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        if (null != okListener) {
            okButton.addActionListener(okListener);
        }
        getContentPane().add(okButton, gridBagConstraints);

        pack();
    }

    public JPanel getContainerPanel() {
        return containerPanel;
    }
    
    public JPanel getWorkerPanel() {
        return workerPanel;
    }
}
