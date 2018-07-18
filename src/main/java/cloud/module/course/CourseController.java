package cloud.module.course;


import cloud.common.BaseController;
import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Result create(@ModelAttribute Course course) {

        course.setEasyNum(0);
        course.setLikeNum(0);
        course.setUsefulNum(0);
        course.setRatingNum(0);

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
