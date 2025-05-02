package domain.characters;

import domain.enums.Race;
import domain.enums.Result;
import domain.interfaces.Observer;
import domain.objects.Fleet;
import domain.objects.Meeting;
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
    private ArrayList<Meeting> meetings;

    public Leader(String name, Race race) {
        this.setName(name);
        this.setRace(race);
        this.fleets = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.meetings = new ArrayList<>();
    }

    public void say(String message) {
        for (Person person : observers) {
            person.listen(message);
        }
    }

    @Override
    public void attach(Observer observer) {
        if (observer instanceof Fleet) {
            fleets.add((Fleet) observer);
        }
        else {
            meetings.add((Meeting) observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        if (observer instanceof Fleet) {
            fleets.remove((Fleet) observer);
        }
        else {
            meetings.remove((Meeting) observer);
        }
    }

    @Override
    public void notifyObservers() {
        fleets.forEach(Fleet::update);
    }

    @Override
    public void listen(String phrase) {
        if (phrase.contains("проблемы")) {
            notifyObservers();
            for (Meeting meeting : meetings) {
                meeting.setResult(Result.WAR);
            }
        }
        else {
            for (Meeting meeting : meetings) {
                meeting.setResult(Result.PEACE);
            }
        }
    }

}
