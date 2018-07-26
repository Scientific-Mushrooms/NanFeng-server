package cloud.module.course;


import cloud.common.BaseController;
import cloud.common.Result;
import cloud.common.image.Image;
import cloud.common.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CourseController extends BaseController {


    @Autowired
    private CourseRepository courseRepository;

    @Resource
    private ImageService imageService;


    @PostMapping("/course/all")
    public Result all() {

        Iterable<Course> courses = courseRepository.findAll();

        return new Result("success", "all courses", courses);

    }

    @PostMapping("/course/delAll")
    public Result delAll() {

        courseRepository.deleteAll();

        return new Result("success");

    }

    @PostMapping("/course/create")
    public Result create(@ModelAttribute Course course, MultipartHttpServletRequest request) {

        MultipartFile avatar = request.getFile("avatar");

        if (avatar != null) {
            Image image = imageService.saveImage(avatar, course.getCourseId(), "avatar");
            course.setAvatarId(image.getImageId());
        }

        courseRepository.save(course);


        return new Result("success", "create course", course);

    }

    @PostMapping("/course/del")
    public Result del(HttpServletRequest request) {

        String courseId = request.getParameter("courseId");

        courseRepository.deleteByCourseId(courseId);
        return new Result("success", "delete course");
    }

    @PostMapping("/course/courseIdToCourse")
    public Result courseIdToCourse(HttpServletRequest request) {

        String courseId = request.getParameter("courseId");

        Course course = courseRepository.findByCourseId(courseId);

        return new Result("success", "create course", course);
    }

}
