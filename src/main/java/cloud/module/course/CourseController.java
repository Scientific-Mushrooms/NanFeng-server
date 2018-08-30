package cloud.module.course;


import cloud.common.BaseController;
import cloud.common.Result;
import cloud.common.image.Image;
import cloud.common.image.ImageService;
import cloud.module.course.courseComment.CourseComment;
import cloud.module.course.courseComment.CourseCommentService;
import cloud.module.instructor.InstructorService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
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

        Pageable pageable =new PageRequest(0, 5);

        Page<Course> courses = courseRepository.findAll(pageable);

        return new Result("success", "all courses", courses);

    }

    @PostMapping("/course/all/page")
    public Result page(HttpServletRequest request) {

        String strPage = request.getParameter("page");
        String strSize = request.getParameter("size");

        if (isEmpty(strPage) || isEmpty(strSize)) {
            return new Result("fail", "page parameter cannot be empty");
        }

        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));

        Pageable pageable =new PageRequest(page, size);

        Page<Course> courses = courseRepository.findAll(pageable);

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

        String strPage = request.getParameter("page");
        String strSize = request.getParameter("size");

        if (isEmpty(strPage) || isEmpty(strSize)) {
            strPage = "0";
            strSize = "20";
        }

        int page = Integer.parseInt(strPage);
        int size = Integer.parseInt(strSize);

        Pageable pageable =new PageRequest(page, size);

        String name = request.getParameter("name");
        String campus = request.getParameter("campus");
        String faculty = request.getParameter("faculty");
        String type = request.getParameter("type");
        String credit = request.getParameter("credit");

        Page<Course> courses = courseService.searchByAll(name, type, campus, faculty, credit, pageable);

        return new Result("success", "auto complete", courses);

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

    @PostMapping("/course/deleteNull")
    public Result deleteNull() {
        Iterable<Course> all = courseRepository.findAll();
        for (Course c : all) {
            String name = c.getName();
            if (name == null || name.equals("")) {
                courseRepository.deleteByCourseId(c.getCourseId());
            }
        }
        return new Result("success", "delete null");
    }

    @PostMapping("/course/updateByCode")
    public Result updateByCode(HttpServletRequest request) {

        String code = request.getParameter("code");
        String introduction = request.getParameter("introduction");

        if (isEmpty(code)) {
            return new Result("fail", "code cannot be empty");
        }

        courseRepository.updateIntroductionByCode(introduction, code);

        return new Result("success", "update course");

    }

    @PostMapping("/course/easy")
    public Result easy() {

        Iterable<Course> courses = courseRepository.findTop20OByOrderByEasyNumAsc();

        return new Result("success", "easy", courses);

    }

    @PostMapping("/course/useful")
    public Result useful() {

        Iterable<Course> courses = courseRepository.findTop20OByOrderByUsefulNumAsc();

        return new Result("success", "easy", courses);

    }

    @PostMapping("/course/enjoy")
    public Result enjoy() {

        Iterable<Course> courses = courseRepository.findTop20OByOrderByEnjoyNumAsc();

        return new Result("success", "easy", courses);

    }




}
