package cloud.module.course;


import cloud.module.course.courseComment.CourseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Transactional
@Service
public class CourseService {


    @Autowired
    private CourseRepository courseRepository;

    @Resource
    private CourseCommentService courseCommentService;


    public Course courseIdToCourse(String courseId) {

        return courseRepository.findByCourseId(courseId);

    }

    public Course codeToCourse(String code) {

        return courseRepository.findByCode(code);

    }

    public void deleteByCourseId(String courseId) {

        courseRepository.deleteByCourseId(courseId);

        courseCommentService.deleteAllByCourseId(courseId);

    }

    public Page searchByName(String name, Pageable pageable) {

        String newName = "%" + name + "%";

        Page page = courseRepository.findByNameLike(newName, pageable);

        return page;

    }

    public Iterable<Course> searchByName(String name) {

        String newName = "%" + name + "%";

        Iterable<Course> courses = courseRepository.findByNameLike(newName);

        return courses;

    }

    public Page<Course> searchByAll(String name, String type, String campus, String faculty, Pageable pageable) {

        if (type == null) {
            type = "";
        }

        if (name == null) {
            name = "";
        }

        if (faculty == null) {
            faculty = "";
        }

        if (campus == null) {
            campus = "";
        }

        String newName = "%" + name + "%";
        String newType = "%" + type + "%";
        String newCampus = "%" + campus + "%";
        String newFaculty = "%" + faculty + "%";

        Page<Course> courses = courseRepository.findByNameLikeAndTypeLikeAndCampusLikeAndFacultyLike(newName, newType, newCampus, newFaculty, pageable);

        return courses;

    }




}
