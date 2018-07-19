package cloud.module.course.instructor;

import cloud.common.BaseController;
import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


public class InstructorController extends BaseController {


    @Autowired
    private InstructorRepository instructorRepository;

    @Resource
    private InstructorService instructorService;


    @PostMapping("/instructor/all")
    public Result all(HttpServletRequest request) {

        Iterable<Instructor> instructors = instructorRepository.findAll();

        return new Result("success", "all instructors", instructors);

    }

    @PostMapping("/instructor/delAll")
    public Result delAll(HttpServletRequest request) {

        instructorRepository.deleteAll();

        return new Result("success", "delete all instructors");

    }

    @PostMapping("/instructor/del")
    public Result del(HttpServletRequest request) {

        String instructorId = request.getParameter("instructorId");

        instructorRepository.deleteByInstructorId(instructorId);

        return new Result("success", "delete instructor by id");

    }

    @PostMapping("/instructor/create")
    public Result create(@ModelAttribute Instructor instructor) {

        // validation
        if (instructor.getRealName() == null || instructor.getCode() == null) {
            return new Result("fail", "cannot be empty");
        }

        instructorRepository.save(instructor);

        return new Result("success", "create", instructor);

    }
}
