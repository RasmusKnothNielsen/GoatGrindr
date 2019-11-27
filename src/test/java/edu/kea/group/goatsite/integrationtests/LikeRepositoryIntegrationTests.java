package edu.kea.group.goatsite.integrationtests;

import edu.kea.group.goatsite.model.Gender;
import edu.kea.group.goatsite.model.Goat;
import edu.kea.group.goatsite.model.Like;
import edu.kea.group.goatsite.repository.GoatRepository;
import edu.kea.group.goatsite.repository.LikeRepository;
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
public class LikeRepositoryIntegrationTests {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    GoatRepository goatRepository;

    @Autowired
    LikeRepository likeRepository;

    private Goat adam, eve, troels;

    public LikeRepositoryIntegrationTests() {
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

        troels = new Goat();
        troels.setName("Troels");
        troels.setUsername("TroelsGoat");
        troels.setDob(new Date(1970));
        troels.setEnabled(true);
        troels.setGender(Gender.MALE);
        troels.setLongDescription("Jeg er Troels!");

    }

    @Test
    public void canCreateAndGetLike() {

        goatRepository.save(adam);
        goatRepository.save(eve);

        Iterable<Like> before_likes = likeRepository.findAll();

        while(before_likes.iterator().hasNext()) {
            Like returnedLike = before_likes.iterator().next();
            Assert.assertFalse(
                    returnedLike.getGoatLiker().equals(adam) &&
                            returnedLike.getGoatLiked().equals(eve)
            );
        }

        Like like = new Like();
        like.setGoatLiker(adam);
        like.setGoatLiked(eve);
        likeRepository.save(like);

        boolean isFound = false;
        Iterable<Like> after_likes = likeRepository.findAll();

        while(after_likes.iterator().hasNext()) {

            Like returnedLike = after_likes.iterator().next();

            if (returnedLike.getGoatLiker().equals(adam) &&
                    returnedLike.getGoatLiked().equals(eve)
            ) {
                isFound = true;
                break;
            }

        }
        Assert.assertTrue(isFound);

    }



    @Test
    public void canFindMatch() {

        goatRepository.save(adam);
        goatRepository.save(eve);
        goatRepository.save(troels);

        Like like1 = new Like();
        Like like2 = new Like();

        like1.setGoatLiker(adam);
        like1.setGoatLiked(eve);
        likeRepository.save(like1);

        like2.setGoatLiker(eve);
        like2.setGoatLiked(adam);
        likeRepository.save(like2);

        Iterable<Like> matches = likeRepository.findMatch(adam.getId(), eve.getId());

        if (matches.iterator().hasNext()) {

            Like match = matches.iterator().next();
            Goat goatLiker = match.getGoatLiker();
            Goat goatLiked = match.getGoatLiked();
            Assert.assertEquals(eve, goatLiker);
            Assert.assertEquals(adam, goatLiked);


        }

    }

    @Test
    public void cannotFindNonexistingMatch() {

        goatRepository.save(adam);
        goatRepository.save(eve);
        goatRepository.save(troels);

        Like like1 = new Like();
        Like like2 = new Like();

        like1.setGoatLiker(adam);
        like1.setGoatLiked(eve);
        likeRepository.save(like1);

        like2.setGoatLiker(eve);
        like2.setGoatLiked(adam);
        likeRepository.save(like2);

        Iterable<Like> matches = likeRepository.findMatch(adam.getId(), troels.getId());

        Assert.assertFalse(matches.iterator().hasNext());

    }

}
