package com.consolefire.swing.helper.utils;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.consolefire.swing.helper.enums.MessageType;

public abstract class MessageUtil {
    private static final int INFO_MESSAGE = JOptionPane.INFORMATION_MESSAGE;
    private static final int WARN_MESSAGE = JOptionPane.WARNING_MESSAGE;
    private static final int ERROR_MESSAGE = JOptionPane.ERROR_MESSAGE;

    public static String readString(String displayText) {
        return readFromJOptPane(null, displayText);
    }

    public static String readString(Component parent, String displayText) {
        return readFromJOptPane(parent, displayText);
    }

    private static String readFromJOptPane(Component parent, String displayText) {
        String value = "";
        try {
            value = JOptionPane.showInputDialog(parent, displayText);
        } catch (Exception e) {
            return "";
        }
        return value;
    }

    public static void displayMessage(String displayText) {
        displayMessage(null, displayText);
    }

    public static void displayMessage(Component parent, String displayText) {
        displayMessage(parent, displayText, MessageType.INFO);
    }

    public static void displayMessage(Component parent, String displayText, MessageType displayType) {
        int messageType = -1;
        if (MessageType.INFO.equals(displayType)) {
            messageType = INFO_MESSAGE;
        } else if (MessageType.WARN.equals(displayType)) {
            messageType = WARN_MESSAGE;
        } else if (MessageType.ERROR.equals(displayType)) {
            messageType = ERROR_MESSAGE;
        }
        if (messageType != -1) {
            JOptionPane.showMessageDialog(parent, displayText, "Message...", messageType);
        } else {
            JOptionPane.showMessageDialog(parent, displayText);
        }
    }

    public static int confirmOkCancel(Component parent, String displayText, MessageType displayType) {
        int messageType = -1;
        if (MessageType.INFO.equals(displayType)) {
            messageType = INFO_MESSAGE;
        } else if (MessageType.WARN.equals(displayType)) {
            messageType = WARN_MESSAGE;
        } else if (MessageType.ERROR.equals(displayType)) {
            messageType = ERROR_MESSAGE;
        }
        if (messageType != -1) {
            return JOptionPane.showConfirmDialog(parent, displayText, "Confirm...", JOptionPane.OK_CANCEL_OPTION,
                    messageType);
        }
        return JOptionPane.showConfirmDialog(parent, displayText);
    }
}
