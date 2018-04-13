/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consolefire.swing.helper.utils;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author sabuj.das
 */
public class ApplyCancelDialog<I, O> extends javax.swing.JDialog {

    private final I input;
    private final JPanel containerPanel;
    
    
    /**
     * Creates new form ApplyCancelDialog
     */
    public ApplyCancelDialog(java.awt.Frame parent, boolean modal, I input, JPanel containerPanel) {
        super(parent, modal);
        this.input = input;
        this.containerPanel = containerPanel;
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(containerPanel);
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
