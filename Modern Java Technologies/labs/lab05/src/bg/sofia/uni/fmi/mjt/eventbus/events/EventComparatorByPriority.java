package bg.sofia.uni.fmi.mjt.eventbus.events;

import java.util.Comparator;

public class EventComparatorByPriority<T extends Event<?>> implements Comparator<Event<?>> {

    // Events sorted by their priority in descending order.
    // Events with equal priority are ordered in ascending order of their timestamps.
    @Override
    public int compare(Event<?> o1, Event<?> o2) {
        int priorityComparison = Integer.compare(o1.getPriority(), o2.getPriority());

        if (priorityComparison == 0) {
            return o1.getTimestamp().compareTo(o2.getTimestamp());
        }

        return priorityComparison;
    }
    
}
