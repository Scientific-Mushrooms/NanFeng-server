package cloud.module.course.courseComment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class CourseCommentService {


    @Autowired
    private CourseCommentRepository courseCommentRepository;


    public CourseComment courseCommentIdToCourseComment(String courseCommentId) {

        CourseComment courseComment = courseCommentRepository.findByCourseCommentId(courseCommentId);

        return courseComment;
    }

    public Iterable<CourseComment> courseIdToCourseComments(String courseId) {

        Iterable<CourseComment> courseComments = courseCommentRepository.findAllByCourseId(courseId);

        return courseComments;

    }

    public void deleteAllByCourseId(String courseId) {

        courseCommentRepository.deleteAllByCourseId(courseId);

    }
}
