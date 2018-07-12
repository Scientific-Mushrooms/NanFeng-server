package cloud.module.course;


import cloud.common.BaseController;
import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CourseController extends BaseController {


    @Autowired
    private CourseRepository courseRepository;


    @PostMapping("/course/all")
    public Iterable<Course> all(HttpServletRequest request) {
        return courseRepository.findAll();
    }

    @PostMapping("/course/delAll")
    public Result delAll(HttpServletRequest request) {
        courseRepository.deleteAll();
        return new Result("success");
    }

    @PostMapping("/course/create")
    public Result create(HttpServletRequest request) {

        String courseName = request.getParameter("courseName");
        String courseCode = request.getParameter("courseCode");
        String courseProf = request.getParameter("courseProf");
        String courseIntro = request.getParameter("courseIntro");

        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseIntro(courseIntro);
        course.setCourseName(courseName);
        course.setCourseProf(courseProf);
        courseRepository.save(course);

        return new Result("success", "create course", course);

    }

    @PostMapping("/course/del")
    public Result del(HttpServletRequest request) {

        String courseId = request.getParameter("courseId");

        courseRepository.deleteByCourseId(courseId);
        return new Result("success", "delete course");
    }


}
