package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventData {

//    need a place to put events
    private static final Map<Integer, Event> events = new HashMap<>();

//    get all events
    public static Collection<Event> getAll() {
        return events.values();
    }

//    get a single event
    public static Event getById(Integer id) {
        return events.get(id);
    }

//    add an event
    public static void addEvent(Event event) {
        events.put(event.getId(), event); //(key,value)
    }

//    remove an event
    public static void remove(Integer id) {
        if (events.containsKey(id)) {
            events.remove(id);
        }
    }
}
