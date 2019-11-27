package edu.kea.group.goatsite.integrationtests;

import edu.kea.group.goatsite.model.Gender;
import edu.kea.group.goatsite.model.Goat;
import edu.kea.group.goatsite.repository.GoatRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GoatRepositoryIntegrationTests {

    @Autowired
    TestEntityManager entityManager;

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

    /* Integration test to see if we can change information about a current goat in our system */
    @Test
    public void canChangeGoatInformation() {

        // given the following

        Goat goat = new Goat();
        goat.setName("Ferdinand");
        goat.setUsername("Ferdinandos");
        goat.setDob(new Date(2000));
        goat.setEnabled(true);
        goat.setGender(Gender.MALE);
        goat.setLongDescription("Be baaaaaaahd like me!");
        entityManager.persist(goat);
        entityManager.flush();

        String formerLongDescription = goat.getLongDescription();

        // When we do the following
        Goat goatWithChangedInformation = goatRepository.findByUsername("Ferdinandos");
        goatWithChangedInformation.setLongDescription("Wanna be baaaaaaahd with me?!?");
        entityManager.persist(goatWithChangedInformation);
        entityManager.flush();

        // Then we se the following
        assertThat(formerLongDescription)
                .isNotEqualTo(goatRepository.findByUsername("Ferdinandos").getLongDescription());

    }

}
