package bg.sofia.uni.fmi.mjt.eventbus.events;

import java.util.Comparator;

public class EventComparatorByTimestamp<T extends Event<?>> implements Comparator<Event<?>> {

    // Events sorted by their timestamps in ascending order.
    // Events with equal timestamps are ordered in descending order of their priorities.
    @Override
    public int compare(Event<?> o1, Event<?> o2) {
        int timestampComparison = o1.getTimestamp().compareTo(o2.getTimestamp());

        if (timestampComparison != 0) {
            return timestampComparison;
        }

        return Integer.compare(o2.getPriority(), o1.getPriority());
    }
    
}
