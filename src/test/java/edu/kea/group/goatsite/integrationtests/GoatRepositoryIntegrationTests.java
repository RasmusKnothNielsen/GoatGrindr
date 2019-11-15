package edu.kea.group.goatsite.integrationtests;

import edu.kea.group.goatsite.model.Goat;
import edu.kea.group.goatsite.repository.GoatRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GoatRepositoryIntegrationTests {

    @Autowired
    GoatRepository goatRepository;

    @Test
    public void canGetAllGoats() {
        Iterable<Goat> goats = goatRepository.findAll();
    }

    @Test
    public void canCreateAndGetGoatByName() {

        // create a goat
        Goat goat = new Goat();
        goat.setName("Ibrahim");
        Date dob = new Date(2000);
        goat.setDob(dob);
        goat.setEnabled(true);
        goat.setLongDescription("Testgoat long description");


        // add goat to H2
        goatRepository.save(goat);

        // get the goat by it's name
        Iterable<Goat> returnGoats = goatRepository.findAllByName("Ibrahim");

        // assert that we found the goat by it's name
        if (returnGoats.iterator().hasNext()) {
            Goat returnGoat = returnGoats.iterator().next();
            Assert.assertEquals(goat, returnGoat);
        }

    }

}
