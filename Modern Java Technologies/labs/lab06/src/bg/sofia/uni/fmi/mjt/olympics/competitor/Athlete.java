package bg.sofia.uni.fmi.mjt.olympics.competitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Athlete implements Competitor, Comparable<Athlete> {

    private final String identifier;
    private final String name;
    private final String nationality;

    private Map<Medal, Integer> medals;

    public Athlete(String identifier, String name, String nationality) {
        this.identifier = identifier;
        this.name = name;
        this.nationality = nationality;
        this.medals = new HashMap<>();
    }

    public void addMedal(Medal medal) {
        validateMedal(medal);
        medals.put(medal, medals.getOrDefault(medal, 0) + 1);
    }

    private void validateMedal(Medal medal) {
        if (medal == null) {
            throw new IllegalArgumentException("Medal cannot be null");
        }
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNationality() {
        return nationality;
    }

    @Override
    public List<Medal> getMedals() {
        List<Medal> result = new ArrayList<>();

        for (Map.Entry<Medal, Integer> medalIntegerEntry : this.medals.entrySet()) {
            for (int i = 0; i < medalIntegerEntry.getValue(); i++) {
                result.add(medalIntegerEntry.getKey());
            }
        }

        return Collections.unmodifiableList(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return Objects.equals(identifier, athlete.identifier) && Objects.equals(name, athlete.name)
                && Objects.equals(nationality, athlete.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, name, nationality);
    }

    @Override
    public int compareTo(Athlete o) {
        int comparedByMedalsCount = Integer.compare(o.getMedals().size(), this.getMedals().size());

        if (comparedByMedalsCount != 0) {
            return comparedByMedalsCount;
        }

        return this.identifier.compareTo(o.identifier);
    }
}