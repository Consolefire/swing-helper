package com.consolefire.swing.helper;

import javax.swing.JComponent;

public abstract class ComponentBuilder<C extends JComponent> {

    protected final C component;
    
    public ComponentBuilder(C component) {
        this.component = component;
    }

    public C build() {
        return component;
    }
    
}
