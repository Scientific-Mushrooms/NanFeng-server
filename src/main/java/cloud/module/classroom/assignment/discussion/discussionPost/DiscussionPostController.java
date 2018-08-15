package cloud.module.classroom.assignment.discussion.discussionPost;


import cloud.common.BaseController;
import cloud.common.Result;
import cloud.module.classroom.assignment.discussion.Discussion;
import cloud.module.student.Student;
import cloud.module.student.StudentService;
import org.aspectj.weaver.ast.ITestVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class DiscussionPostController extends BaseController {


    @Autowired
    private DiscussionPostRepository discussionPostRepository;

    @Resource
    private DiscussionPostService discussionPostService;

    @Resource
    private StudentService studentService;


    @PostMapping("/discussionPost/all")
    public Result all() {

        Iterable<DiscussionPost> discussionPosts = discussionPostRepository.findAll();

        return new Result("success", "all discussion posts", discussionPosts);

    }

    @PostMapping("/discussionPost/deleteAll")
    public Result deleteAll() {

        discussionPostRepository.deleteAll();

        return new Result("success", "delete all discussion posts");

    }

    @PostMapping("/discussionPost/create")
    public Result create(@ModelAttribute DiscussionPost discussionPost) {

        String discussionId = discussionPost.getDiscussionId();
        String studentId = discussionPost.getStudentId();

        if (isEmpty(discussionId)) {
            return new Result("fail", "discussion id cannot be empty");
        }

        if (isEmpty(studentId)) {
            return new Result("fail", "student id cannot be empty");
        }

        discussionPostRepository.save(discussionPost);

        return new Result("success", "create discussion post", discussionPost);

    }

    @PostMapping("/discussionPost/discussionIdToAllDiscussionPosts")
    public Result discussionIdToAllDiscussionPosts(HttpServletRequest request) {

        String discussionId = request.getParameter("discussionId");

        if (isEmpty(discussionId)) {
            return new Result("fail", "discussion id cannot be empty");
        }

        Iterable<DiscussionPost> discussionPosts = discussionPostService.discussionIdToAllDiscussionPosts(discussionId);

        ArrayList<Student> students = new ArrayList<Student>();

        for (DiscussionPost discussionPost : discussionPosts) {
            Student student = studentService.studentIdToStudent(discussionPost.getStudentId());
            students.add(student);
        }

        return new Result("success", "discussion Id To All DiscussionPosts", discussionPosts, students);

    }


}
