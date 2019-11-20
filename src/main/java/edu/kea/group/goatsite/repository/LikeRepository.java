package edu.kea.group.goatsite.repository;

import edu.kea.group.goatsite.model.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {

    @Query(value =
            "SELECT * FROM likes WHERE goat_liked_id = ? AND goat_liker_id = ?",
            nativeQuery = true)
    Iterable<Like> findMatch(Long liker, Long liked);

}
