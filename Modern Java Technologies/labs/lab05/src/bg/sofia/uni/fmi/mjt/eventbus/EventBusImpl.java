package bg.sofia.uni.fmi.mjt.eventbus;

import bg.sofia.uni.fmi.mjt.eventbus.events.Event;
import bg.sofia.uni.fmi.mjt.eventbus.events.EventComparatorByTimestamp;
import bg.sofia.uni.fmi.mjt.eventbus.exception.MissingSubscriptionException;
import bg.sofia.uni.fmi.mjt.eventbus.subscribers.Subscriber;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class EventBusImpl implements EventBus {

    private Map<Class<? extends Event<?>>, Set<Subscriber<?>>> subscribers;
    private Set<Event<?>> eventLogs;

    public EventBusImpl() {
        this.subscribers = new HashMap<>();
        this.eventLogs = new HashSet<>();
    }

    @Override
    public <T extends Event<?>> void subscribe(Class<T> eventType, Subscriber<? super T> subscriber) {
        checkIfEventTypeIsNull(eventType);
        checkIfSubscriberIsNull(subscriber);

        this.subscribers.putIfAbsent(eventType, new HashSet<>());
        this.subscribers.get(eventType).add(subscriber);
    }

    @Override
    public <T extends Event<?>> void unsubscribe(Class<T> eventType, Subscriber<? super T> subscriber)
            throws MissingSubscriptionException {
        checkIfEventTypeIsNull(eventType);
        checkIfSubscriberIsNull(subscriber);

        if (this.subscribers.get(eventType) == null) {
            throw new MissingSubscriptionException("Subscriber is not subscribed to the event type");
        }

        this.subscribers.get(eventType).remove(subscriber);

        if (this.subscribers.get(eventType).isEmpty()) {
            this.subscribers.remove(eventType);
        }
    }

    @Override
    public <T extends Event<?>> void publish(T event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }

        if (this.subscribers.get(event.getClass()) != null) {
            for (Subscriber<?> subscriber : this.subscribers.get(event.getClass())) {
                ((Subscriber<? super T>) subscriber).onEvent(event);
            }
        }

        this.eventLogs.add(event);
    }

    @Override
    public void clear() {
        this.subscribers.clear();
        this.eventLogs.clear();
    }

    @Override
    public Collection<? extends Event<?>> getEventLogs(Class<? extends Event<?>> eventType, Instant from, Instant to) {
        checkIfEventTypeIsNull(eventType);

        if (from == null) {
            throw new IllegalArgumentException("Start timestamp cannot be null");
        } else if (to == null) {
            throw new IllegalArgumentException("End timestamp cannot be null");
        }

        if (this.eventLogs.isEmpty() || from.equals(to)) {
            return List.of();
        }

        Set<Event<?>> events = new TreeSet<>(new EventComparatorByTimestamp<>());

        for (Event<?> eventLog : this.eventLogs) {
            if (eventLog.getClass().equals(eventType) && isBetween(eventLog.getTimestamp(), from, to)) {
                events.add(eventLog);
            }
        }

        return Collections.unmodifiableSet(events);
    }

    private boolean isBetween(Instant timestamp, Instant from, Instant to) {
        return timestamp.compareTo(from) >= 0 && timestamp.compareTo(to) < 0;
    }

    @Override
    public <T extends Event<?>> Collection<Subscriber<?>> getSubscribersForEvent(Class<T> eventType) {
        checkIfEventTypeIsNull(eventType);

        return Collections.unmodifiableSet(this.subscribers.getOrDefault(eventType, new HashSet<>()));
    }

    private static <T extends Event<?>> void checkIfSubscriberIsNull(Subscriber<? super T> subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("Subscriber cannot be null");
        }
    }

    private static <T extends Event<?>> void checkIfEventTypeIsNull(Class<T> eventType) {
        if (eventType == null) {
            throw new IllegalArgumentException("Event type cannot be null");
        }
    }

}
