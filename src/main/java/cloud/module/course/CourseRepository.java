package cloud.module.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {

    Page<Course> findAll(Pageable pageable);

    Course findByCourseId(String courseId);

    Iterable<Course> findTop10ByOrderByName();

    @Transactional
    void deleteByCourseId(String courseId);

    Page<Course> findByNameLike(String name, Pageable pageable);

    Iterable<Course> findByNameLike(String name);

    Page<Course> findByNameLikeAndTypeLikeAndCampusLikeAndFacultyLike(String name, String type, String campus, String faculty, Pageable pageable);

    boolean existsByCode(String code);
}
