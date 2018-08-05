package cloud.module.instructor;

import cloud.common.BaseController;
import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
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

    @PostMapping("/instructor/deleteAll")
    public Result deleteAll(HttpServletRequest request) {

        instructorRepository.deleteAll();

        return new Result("success", "delete all instructors");

    }

    @PostMapping("/instructor/deleteByInstructorId")
    public Result del(HttpServletRequest request) {

        String instructorId = request.getParameter("instructorId");

        instructorService.deleteByInstructorId(instructorId);

        return new Result("success", "delete instructor by id");

    }

    @PostMapping("/instructor/create")
    public Result create(@ModelAttribute Instructor instructor) {

        // validation
        if (instructor.getUserId() == null) {
            return new Result("fail", "user id cannot be null");
        }
        if (instructor.getRealName() == null || instructor.getCode() == null) {
            return new Result("fail", "cannot be empty");
        }

        instructorRepository.save(instructor);

        return new Result("success", "create", instructor);

    }

    @PostMapping("/instructor/instructorIdToInstructor")
    public Result instructorIdToInstructor(HttpServletRequest request) {

        String instructorId = request.getParameter("instructorId");

        Instructor instructor = instructorRepository.findByInstructorId(instructorId);

        return new Result("success", "instructor id to instructor", instructor);

    }

    @PostMapping("/instructor/userIdToInstructor")
    public Result userIdToInstructor(HttpServletRequest request) {

        String userId = request.getParameter("userId");

        Instructor instructor = instructorRepository.findByUserId(userId);

        return new Result("success", "user id to instructor", instructor);

    }

    @PostMapping("/instructor/updateByInstructorId")
    public Result updateByInstructorId(HttpServletRequest request) {

        String instructorId = request.getParameter("instructorId");
        String realName = request.getParameter("realName");
        String code = request.getParameter("code");

        Instructor oldInstructor = instructorService.instructorIdToInstructor(instructorId);

        if (oldInstructor == null) {
            return new Result("fail", "instructor not exist");
        }

        if (realName != null && !realName.equals("")) {
            instructorRepository.updateRealNameByInstructorId(realName, instructorId);
        }

        if (code != null && realName.equals("")) {
            instructorRepository.updateCodeByInstructorId(code, instructorId);
        }

        Instructor newInstructor = instructorService.instructorIdToInstructor(instructorId);

        return new Result("success", "update instructor", newInstructor);

    }
}
