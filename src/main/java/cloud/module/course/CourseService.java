package cloud.module.course;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CourseService {


    @Autowired
    private CourseRepository courseRepository;


    public Course courseIdToCourse(String courseId) {
        return courseRepository.findByCourseId(courseId);
    }


}
