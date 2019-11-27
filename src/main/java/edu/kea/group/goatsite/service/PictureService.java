package edu.kea.group.goatsite.service;

import edu.kea.group.goatsite.model.Goat;
import edu.kea.group.goatsite.model.Picture;
import edu.kea.group.goatsite.repository.GoatRepository;
import edu.kea.group.goatsite.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService {

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    GoatRepository goatRepository;

    private void addPicture(Picture picture) {
        pictureRepository.save(picture);
    }

    private void getPicture(Goat goat) {
        //Long id = goat.getId();
        //String location = pictureRepository.findByGoatId(id);
        //System.out.println(location);
    }
}
