package cloud.module.course;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface CourseRepository extends CrudRepository<Course, String> {

    Course findByCourseId(String courseId);

    void deleteByCourseId(String courseId);

    @Modifying
    @Query("update Course c set c.courseCode = ?1 where c.courseId = ?2")
    @Transactional
    void updateCourseCode(String courseCode, String courseId);

    @Modifying
    @Query("update Course c set c.courseName = ?1 where c.courseId = ?2")
    @Transactional
    void updateCourseName(String courseName, String courseId);

    @Modifying
    @Query("update Course c set c.courseIntroduction = ?1 where c.courseId = ?2")
    @Transactional
    void updateCourseIntro(String courseIntroduction, String courseId);

    @Modifying
    @Query("update Course c set c.courseStartDate = ?1 where c.courseId = ?2")
    @Transactional
    void updateStartDate(Date courseStartDate, String courseId);

    @Modifying
    @Query("update Course c set c.courseEndDate = ?1 where c.courseId = ?2")
    @Transactional
    void updateEndDate(Date courseEndDate, String courseId);
}
