package cloud.module.course;


import cloud.common.BaseController;
import cloud.common.Result;
import cloud.common.image.Image;
import cloud.common.image.ImageService;
import cloud.module.course.courseComment.CourseComment;
import cloud.module.course.courseComment.CourseCommentService;
import cloud.module.instructor.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController extends BaseController {


    @Autowired
    private CourseRepository courseRepository;

    @Resource
    private ImageService imageService;

    @Resource
    private CourseCommentService courseCommentService;

    @Resource
    private InstructorService instructorService;

    @Resource
    private CourseService courseService;


    @PostMapping("/course/all")
    public Result all() {

        Iterable<Course> courses = courseRepository.findAll();

        return new Result("success", "all courses", courses);

    }

    @PostMapping("/course/deleteAll")
    public Result delAll() {

        Iterable<Course> courses = courseRepository.findAll();

        for (Course course : courses) {
            courseService.deleteByCourseId(course.getCourseId());
        }

        return new Result("success", "delete all courses");

    }

    @PostMapping("/course/create")
    public Result create(@ModelAttribute Course course, MultipartHttpServletRequest request) {

        MultipartFile avatar = request.getFile("avatar");

        if (avatar != null) {
            Image image = imageService.saveImage(avatar, course.getCourseId(), "avatar");
            course.setAvatarId(image.getImageId());
        }

        if (courseRepository.existsByCode(course.getCode())) {
            return new Result("error", "duplicate course code");
        }

        courseRepository.save(course);


        return new Result("success", "create course", course);

    }

    @PostMapping("/course/deleteByCourseId")
    public Result deleteByCourseId(HttpServletRequest request) {

        String courseId = request.getParameter("courseId");

        courseService.deleteByCourseId(courseId);

        return new Result("success", "delete course");

    }

    @PostMapping("/course/courseIdToCourse")
    public Result courseIdToCourse(HttpServletRequest request) {

        String courseId = request.getParameter("courseId");

        Course course = courseRepository.findByCourseId(courseId);

        Iterable<CourseComment> courseComments = courseCommentService.courseIdToCourseComments(courseId);

        return new Result("success", "create course", course, courseComments);

    }

    @PostMapping("/course/search")
    public Result searchByName(HttpServletRequest request) {

        String name = request.getParameter("name");

        if (name == null || name.equals("")) {

            Iterable<Course> courses = courseRepository.findTop10ByOrderByName();

            return new Result("success", "find top 10", courses);

        }

        Iterable<Course> courses = courseService.searchByName(name);

        return new Result("success", "search", courses);

    }

    @PostMapping("/course/autoComplete")
    public Result autoComplete(HttpServletRequest request) {

        String name = request.getParameter("name");

        Iterable<Course> courses = courseService.searchByName(name);

        List<String> names = new ArrayList();

        for (Course course : courses) {
            names.add(course.getName());
        }

        return new Result("success", "auto complete", names);

    }


}
