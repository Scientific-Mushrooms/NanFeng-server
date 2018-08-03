package cloud.module.course.courseComment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CourseCommentRepository extends CrudRepository<CourseComment, String> {


    CourseComment findByCourseCommentId(String ratingId);

    @Transactional
    void deleteByUserId(String userId);

    Iterable<CourseComment> findAllByCourseId(String courseId);

    @Transactional
    void deleteAllByCourseId(String courseId);
}
