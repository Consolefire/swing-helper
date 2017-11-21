package com.consolefire.swing.helper.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class ApplicationEventManager {

    private static ApplicationEventManager instance;
    private Map<Class<?>, List<BaseEventListener>> allListeners;
    
    @Getter
    @Setter
    private ApplicationEventDeligate applicationEventDeligate;

    private ApplicationEventManager() {
        this.allListeners = new HashMap<>();
    }

    public static ApplicationEventManager getInstance() {
        if (null != instance)
            return instance;
        synchronized (ApplicationEventManager.class) {
            if (null == instance) {
                instance = new ApplicationEventManager();
            }
        }
        return instance;
    }

    public synchronized void registerListener(final Class<?> eventType, final BaseEventListener listener) {
        if (null == allListeners.get(eventType)) {
            allListeners.put(eventType, new ArrayList<BaseEventListener>());
        }
        allListeners.get(eventType).add(listener);
    }

    public synchronized void unregisterListener(final Class eventType) {
        allListeners.remove(eventType);
    }

    public synchronized void fireEvent(ApplicationEvent event) {
        if (null == event || null == applicationEventDeligate)
            return;
        List<BaseEventListener> listeners = allListeners.get(event.getClass());
        if (null != listeners) {
            for (BaseEventListener listener : listeners) {
                applicationEventDeligate.doFire(event, listener);
            }
        }
    }



}
