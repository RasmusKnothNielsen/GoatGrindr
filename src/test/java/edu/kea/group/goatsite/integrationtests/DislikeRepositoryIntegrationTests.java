package edu.kea.group.goatsite.integrationtests;

import edu.kea.group.goatsite.model.Dislike;
import edu.kea.group.goatsite.model.Gender;
import edu.kea.group.goatsite.model.Goat;
import edu.kea.group.goatsite.repository.DislikeRepository;
import edu.kea.group.goatsite.repository.GoatRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DislikeRepositoryIntegrationTests {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    GoatRepository goatRepository;

    @Autowired
    DislikeRepository dislikeRepository;

    private Goat adam, eve;

    public DislikeRepositoryIntegrationTests() {
        adam = new Goat();
        adam.setName("Adam");
        adam.setUsername("AdamGoat");
        adam.setDob(new Date(0));
        adam.setEnabled(true);
        adam.setGender(Gender.MALE);
        adam.setLongDescription("First!");

        eve = new Goat();
        eve.setName("Eve");
        eve.setUsername("EveGoat");
        eve.setDob(new Date(0));
        eve.setEnabled(true);
        eve.setGender(Gender.FEMALE);
        eve.setLongDescription("Second!");
    }

    @Test
    public void canCreateAndGetDislike() {

        goatRepository.save(adam);
        goatRepository.save(eve);

        Iterable<Dislike> before_dislikes = dislikeRepository.findAll();

        while(before_dislikes.iterator().hasNext()) {
            Dislike returnedDislike = before_dislikes.iterator().next();
            Assert.assertFalse(
                    returnedDislike.getGoatDisliker().equals(adam) &&
                            returnedDislike.getGoatDisliked().equals(eve)
            );
        }

        Dislike dislike = new Dislike();
        dislike.setGoatDisliker(adam);
        dislike.setGoatDisliked(eve);
        dislikeRepository.save(dislike);

        boolean isFound = false;
        Iterable<Dislike> after_dislikes = dislikeRepository.findAll();

        while(after_dislikes.iterator().hasNext()) {

            Dislike returnedDislike = after_dislikes.iterator().next();

            if (returnedDislike.getGoatDisliker().equals(adam) &&
                    returnedDislike.getGoatDisliked().equals(eve)
            ) {
                isFound = true;
                break;
            }

        }
        Assert.assertTrue(isFound);

    }

}
