package testprojet;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ifi.entity.Profile;

public class profilTest {

	@Test
	public void test() {
		Profile p = new Profile("mohcine59@hotmail.fr", "0671539854", "INFORMATIQUES");
		assertEquals(p.getEmail(), "mohcine59@hotmail.fr");
		assertEquals(p.getPhone(), "0671539854");
		assertEquals(p.getCompetences(), "INFORMATIQUES");
	}
	
	

}
