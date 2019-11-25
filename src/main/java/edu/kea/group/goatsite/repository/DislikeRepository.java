package edu.kea.group.goatsite.repository;

import edu.kea.group.goatsite.model.Dislike;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DislikeRepository extends CrudRepository<Dislike, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dislikes WHERE goat_disliked_id = ? OR goat_disliker_id = ?", nativeQuery = true)
    void deleteAllByGoatId(Long id1, Long id2); //TODO - Avoid repetition.

}
