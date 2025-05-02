package domain.characters;

import domain.enums.Race;
import domain.interfaces.Observer;
import domain.objects.Fleet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class Leader extends Person {

    private ArrayList<Fleet> fleets;
    private ArrayList<Person> observers;

    public Leader(String name, Race race) {
        this.setName(name);
        this.setRace(race);
        this.fleets = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void say(String message) {
        for (Person person : observers) {
            person.listen(message);
        }
    }

    @Override
    public void attach(Observer observer) {
        fleets.add((Fleet) observer);
    }

    @Override
    public void detach(Observer observer) {
        fleets.remove((Fleet) observer);
    }

    @Override
    public void notifyObservers() {
        fleets.forEach(Fleet::update);
    }

    @Override
    public void listen(String phrase) {
        if (phrase.contains("проблемы")) {
            notifyObservers();
        }
    }

}
