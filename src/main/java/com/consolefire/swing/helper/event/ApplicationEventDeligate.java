package com.consolefire.swing.helper.event;

public interface ApplicationEventDeligate {

    <E extends ApplicationEvent, L extends BaseEventListener> void doFire(E event, L listener);

}
