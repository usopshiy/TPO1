import domain.characters.Leader;
import domain.enums.Race;
import domain.enums.Status;
import domain.objects.Cruiser;
import domain.objects.ElectricWeapon;
import domain.objects.Fleet;
import domain.objects.Galaxy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainTest {

    @Test
    void cruiserAmmunitionTest() {
        Cruiser cruiser = new Cruiser("Cruiser");
        assertEquals(0, cruiser.getWeapons().size());

        ElectricWeapon gun = new ElectricWeapon("pew", 1);
        assertDoesNotThrow(() -> {cruiser.addWeapon(gun);});
        assertEquals(1, cruiser.getWeapons().size());
        assertEquals(gun, cruiser.getWeapons().get(0));

        ElectricWeapon gun2 = new ElectricWeapon("pew", 1);
        ElectricWeapon gun3 = new ElectricWeapon("pew", 1);
        ElectricWeapon gun4 = new ElectricWeapon("pew", 1);
        assertDoesNotThrow(() -> {cruiser.addWeapon(gun2);});
        assertDoesNotThrow(() -> {cruiser.addWeapon(gun3);});
        assertThrows(IllegalStateException.class, () -> {cruiser.addWeapon(gun4);});
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

        assertEquals("Fleet", fleet.getName());
        fleet.unite(fleet2);
        assertEquals(3, fleet.getComposition().size());
        assertNotEquals("Fleet", fleet.getName());
    }

    @Test
    void observerChainTest() {
        Leader l1 = new Leader("1", Race.VLHURG);
        Leader l2 = new Leader("2", Race.GGUGVUNTT);
        Galaxy galaxy = new Galaxy("test", 1);
        Fleet fleet = new Fleet("Fleet", galaxy);
        Cruiser cruiser = new Cruiser("Cruiser");
        Fleet fleet2 = new Fleet("Fleet2", galaxy);

        fleet.setCurrentTarget(fleet2);
        fleet.getComposition().add(cruiser);
        l2.getObservers().add(l1);
        l1.attach(fleet);

        l2.say("smth");
        assertEquals(Status.FINE, galaxy.getStatus());

        l2.say("smth with проблемы pupupu");
        assertEquals(Status.IN_RUINS, galaxy.getStatus());
    }
}
