package cloud.module.course;


import cloud.module.course.courseComment.CourseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void deleteByCourseId(String courseId) {

        courseRepository.deleteByCourseId(courseId);

        courseCommentService.deleteAllByCourseId(courseId);

    }




}
