package cloud.module.course.instructor.instructorComment;


import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class InstructorCommentController {


    @Autowired
    private InstructorCommentRepository instructorCommentRepository;

    @Resource
    private InstructorCommentService instructorCommentService;


    @PostMapping("/instructorComment/all")
    public Result all() {

        Iterable<InstructorComment> comments = instructorCommentRepository.findAll();

        return new Result("success", "find all instructor comments");

    }

    @PostMapping("/instructorComment/deleteAll")
    public Result deleteAll() {

        instructorCommentRepository.deleteAll();

        return new Result("success", "delete all instructor comments");

    }

    @PostMapping("/instructorComment/deleteByInstructorCommentId")
    public Result deleteByInstructorCommentId(HttpServletRequest request) {

        String instructorCommentId = request.getParameter("instructorCommentId");

        instructorCommentRepository.deleteByInstructorCommentId(instructorCommentId);

        return new Result("success", "delete by instructor comment id");

    }

    @PostMapping("/instructorComment/deleteAllByInstructorId")
    public Result deleteAllByInstructorId(HttpServletRequest request) {

        String instructorCommentId = request.getParameter("instructorId");

        instructorCommentService.deleteAllByInstructorId(instructorCommentId);

        return new Result("success", "delete all instructor comments by instructor id");

    }

}
