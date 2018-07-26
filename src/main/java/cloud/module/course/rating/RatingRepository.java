package cloud.module.course.rating;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RatingRepository extends CrudRepository<Rating, String> {


    Rating findByRatingId(String ratingId);

    @Transactional
    void deleteByUserId(String userId);

    @Transactional
    void deleteByCourseId(String courseId);

    Iterable<Rating> findAllByCourseId(String courseId);


}
