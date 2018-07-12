package cloud.module.course;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, String> {

    Course findByCourseId(String courseId);

    void deleteByCourseId(String courseId);
}
