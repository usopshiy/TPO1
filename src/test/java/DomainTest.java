import domain.Main;
import domain.characters.Leader;
import domain.enums.Race;
import domain.enums.Result;
import domain.enums.Status;
import domain.objects.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainTest {

    @Test
    void cruiserAmmunitionLimitTest() {
        Cruiser cruiser = new Cruiser("Cruiser");
        ElectricWeapon gun = new ElectricWeapon("nopew", 0);
        ElectricWeapon gun2 = new ElectricWeapon("pew", 1);
        ElectricWeapon gun3 = new ElectricWeapon("pewpew", 2);
        ElectricWeapon gun4 = new ElectricWeapon("pewpewpwe", 3);

        assertAll(
                () -> {assertDoesNotThrow(() -> {cruiser.addWeapon(gun);});},
                () -> {assertDoesNotThrow(() -> {cruiser.addWeapon(gun2);});},
                () -> {assertDoesNotThrow(() -> {cruiser.addWeapon(gun3);});},
                () -> {assertThrows(IllegalStateException.class, () -> {cruiser.addWeapon(gun4);});}
        );
    }

    @Test
    void testFleetUnion() {
        Galaxy galaxy = new Galaxy("test", 1);
        Fleet fleet = new Fleet("Fleet", galaxy);
        Fleet fleet2 = new Fleet("Fleet2", galaxy);
        Cruiser cruiser = new Cruiser("Cruiser");
        Cruiser cruiser2 = new Cruiser("Cruiser");
        Cruiser cruiser3 = new Cruiser("Cruiser");

        fleet.getComposition().add(cruiser);
        fleet2.getComposition().add(cruiser2);
        fleet2.getComposition().add(cruiser3);

        fleet.unite(fleet2);
        assertEquals(3, fleet.getComposition().size());
        assertNotEquals("Fleet", fleet.getName());
    }

    @Test
    void observerChainTest() {
        Meeting meet = new Meeting("meeting");
        Leader l1 = new Leader("1", Race.VLHURG);
        Leader l2 = new Leader("2", Race.GGUGVUNTT);
        meet.getAttendees().add(l1);
        meet.getAttendees().add(l2);

        Galaxy galaxy = new Galaxy("test", 1);
        Fleet fleet = new Fleet("Fleet", galaxy);
        Fleet fleet2 = new Fleet("Fleet2", galaxy);

        fleet.setCurrentTarget(fleet2);
        l2.getObservers().add(l1);
        l1.attach(fleet);
        l1.attach(meet);

        //Good ending
        l2.say("smth");
        assertEquals(Status.FINE, galaxy.getStatus());
        assertEquals(Result.PEACE, meet.getResult());

        //Bad ending
        l2.say("smth with проблемы pupupu");
        assertEquals(Status.IN_RUINS, galaxy.getStatus());
        assertEquals(Result.WAR, meet.getResult());
    }

    @Test
    void testRunnable() {
        assertDoesNotThrow(() -> {Main.main(null);});
    }
}
