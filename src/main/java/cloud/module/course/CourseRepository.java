package cloud.module.course;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface CourseRepository extends CrudRepository<Course, String> {

    Course findByCourseId(String courseId);

    @Transactional
    void deleteByCourseId(String courseId);

}
