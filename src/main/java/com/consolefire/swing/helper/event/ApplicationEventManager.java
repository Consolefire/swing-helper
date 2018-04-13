package com.consolefire.swing.helper.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class ApplicationEventManager {

	private static volatile ApplicationEventManager instance;
	private Map<Class<?>, List<? extends BaseEventListener>> allListeners;

	@Getter
	@Setter
	private ApplicationEventDeligate applicationEventDeligate;

	private ApplicationEventManager() {
		this.allListeners = new HashMap<>();
	}

	public static ApplicationEventManager getInstance() {
		synchronized (ApplicationEventManager.class) {
			if (null == instance) {
				synchronized (ApplicationEventManager.class) {
					instance = new ApplicationEventManager();
				}
			}
		}
		return instance;
	}

	public synchronized <L extends BaseEventListener> void registerListener(final Class<?> eventType, final L listener) {
		if (null == allListeners.get(eventType)) {
			allListeners.put(eventType, new ArrayList<L>());
		}
		List<L> list = (List<L>) allListeners.get(eventType);
		list.add(listener);
	}

	public synchronized void unregisterListener(final Class<?> eventType) {
		allListeners.remove(eventType);
	}

	public synchronized <L extends BaseEventListener> void fireEvent(ApplicationEvent event) {
		if (null == event || null == applicationEventDeligate)
			return;
		List<L> listeners = (List<L>) allListeners.get(event.getClass());
		if (null != listeners) {
			for (BaseEventListener listener : listeners) {
				applicationEventDeligate.doFire(event, listener);
			}
		}
	}

}
