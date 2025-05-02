package domain.objects;

import domain.characters.Person;
import domain.enums.Atmosphere;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Meeting extends Event {

    private ArrayList<Person> attendees;

    public Meeting(String name) {
        this.attendees = new ArrayList<>();
    }

    public Meeting(String name, Atmosphere atmosphere) {
        this.attendees = new ArrayList<>();
        this.setAtmosphere(atmosphere);
    }
}
