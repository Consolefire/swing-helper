package com.consolefire.swing.helper.utils;

import java.awt.Window;
import java.lang.reflect.Method;

import javax.swing.UIManager;

import org.apache.commons.lang3.SystemUtils;

public abstract class OsFeatureUtil {

	public enum OsType {
		WINDOWS, MAC, LINUX, UNKNOWN
	}

	public static OsType identifyOsType() {
		if (SystemUtils.IS_OS_MAC) {
			return OsType.MAC;
		}
		if (SystemUtils.IS_OS_LINUX) {
			return OsType.LINUX;
		}
		if (SystemUtils.IS_OS_WINDOWS) {
			return OsType.WINDOWS;
		}
		return OsType.UNKNOWN;
	}

	public static void applyOsFeatures() {
		applyOsFeatures(identifyOsType());
	}

	public static void applyOsFeatures(OsType osType) {
		try {
			switch (osType) {
			case MAC:
				System.setProperty("apple.laf.useScreenMenuBar", "true");
				System.setProperty("apple.awt.brushMetalLook", "true");
				System.setProperty("apple.awt.fileDialogForDirectories", "true");
				/*
				 * https://developer.apple.com/library/mac/documentation/Java/
				 * Reference/Java_PropertiesRef/Articles/JavaSystemProperties.
				 * html#//apple_ref/doc/uid/TP40008047
				 */
				System.setProperty("apple.awt.graphics.OptimizeShapes", "true");
				System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
				System.setProperty("apple.awt.graphics.EnableDeferredUpdates", "true");
				System.setProperty("apple.awt.graphics.UseQuartz", "true");
				break;
			case WINDOWS:
				break;
			case LINUX:
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void enableOSXFullscreen(Window window) {
		if (null == window) {
			return;
		}
		try {
			Class fullScreenUtilitiesClass = Class.forName("com.apple.eawt.FullScreenUtilities");
			Class params[] = new Class[] { Window.class, Boolean.TYPE };
			Method method = fullScreenUtilitiesClass.getMethod("setWindowCanFullScreen", params);
			method.invoke(fullScreenUtilitiesClass, window, true);
		} catch (Exception e) {
			// ignore
		}
	}

}
