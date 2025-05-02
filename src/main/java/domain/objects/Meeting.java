package domain.objects;

import domain.characters.Person;
import domain.enums.Atmosphere;
import domain.enums.Result;
import domain.interfaces.Observer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Meeting extends Event implements Observer {

    private ArrayList<Person> attendees;
    private Result result = Result.UNDEFINED;

    public Meeting(String name) {
        this.attendees = new ArrayList<>();
    }

    public Meeting(String name, Atmosphere atmosphere) {
        this.attendees = new ArrayList<>();
        this.setAtmosphere(atmosphere);
    }

    @Override
    public void update() {

    }
}
