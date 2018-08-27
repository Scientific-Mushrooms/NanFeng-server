package cloud.module.course.courseComment;

import cloud.common.BaseController;
import cloud.common.Result;
import cloud.common.User.User;
import cloud.common.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class CourseCommentController extends BaseController {

    @Autowired
    private CourseCommentRepository courseCommentRepository;

    @Resource
    private CourseCommentService courseCommentService;

    @Resource
    private UserService userService;

    @PostMapping("/courseComment/all")
    public Result all(HttpServletRequest request) {

        Iterable<CourseComment> courseComments = courseCommentRepository.findAll();

        return new Result("success", "all courseComment", courseComments);

    }

    @PostMapping("/courseComment/deleteAll")
    public Result deleteAll(HttpServletRequest request) {

        courseCommentRepository.deleteAll();

        return new Result("success", "delete all course comments");

    }

    @PostMapping("/courseComment/deleteAllByUserId")
    public Result deleteAllByUserId(HttpServletRequest request) {

        String userId = request.getParameter("userId");

        courseCommentRepository.deleteByUserId(userId);

        return new Result("success", "delete all course comments by user id");

    }

    @PostMapping("/courseComment/deleteAllByCourseId")
    public Result deleteAllByCourseId(HttpServletRequest request) {

        String courseId = request.getParameter("courseId");

        courseCommentRepository.deleteAllByCourseId(courseId);

        return new Result("success", "delete all course comments by course id");

    }

    @PostMapping("/courseComment/courseCommentIdToCourseComment")
    public Result courseCommentIdToCourseComment(HttpServletRequest request) {

        String courseCommentId = request.getParameter("courseCommentId");

        CourseComment courseComment = courseCommentService.courseCommentIdToCourseComment(courseCommentId);

        return new Result("success", "ratingID to CourseComment", courseComment);

    }

    @PostMapping("/courseComment/courseIdToCourseComments")
    public Result courseIdToCourseComment(HttpServletRequest request) {

        String courseId = request.getParameter("courseId");

        Iterable<CourseComment> courseComments = courseCommentRepository.findAllByCourseId(courseId);

        List<User> users = new ArrayList();

        for (CourseComment comment : courseComments) {

            User user = userService.userIdToUser(comment.getUserId());

            users.add(user);

        }

        return new Result("success", "ratingID to CourseComment", courseComments, users);

    }

    @PostMapping("/courseComment/create")
    public Result create(@ModelAttribute CourseComment courseComment) {

        // verify parameters
        if (courseComment.getCourseId() == null || courseComment.getUserId() == null) {
            return new Result("fail", "course id or user id can not be empty");
        }

        courseComment.setDate(new Date());

        courseCommentRepository.save(courseComment);

        return new Result("success", "create courseComment", courseComment);

    }

    /*@PostMapping("/courseCommend/recommend/enjoy")
    public Result recommendByEnjoy(HttpServletRequest request) {

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

        Page<Course> courses = courseService.searchByAll(name, type, campus, faculty, pageable);

        return new Result("success", "auto complete", courses);

    }*/


}
