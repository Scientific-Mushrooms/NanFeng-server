package cloud.common;

import cloud.common.User.UserRepository;
import cloud.common.User.UserService;
import cloud.common.User.User;
import cloud.module.instructor.Instructor;
import cloud.module.instructor.InstructorService;
import cloud.module.student.Student;
import cloud.module.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class SecurityController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @Resource
    private UserService userService;

    @Resource
    private InstructorService instructorService;

    @Resource
    private StudentService studentService;

    @PostMapping("/security/signIn")
    public Result login(HttpServletRequest request) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userRepository.findByEmail(email);

        if (user == null) {
            return new Result("fail", "email not exist");
        }

        if (!user.getPassword().equals(password)) {
            return new Result("fail", "wrong password");
        }

        Instructor instructor = instructorService.userIdToInstructor(user.getUserId());
        Student student = studentService.userIdToStudent(user.getUserId());

        return new Result("success", "sign in", user, instructor, student);

    }

    @PostMapping("/security/signup")
    public Result signup(HttpServletRequest request) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");


        return new Result("success", "good");
    }

    @GetMapping("/user/logout")
    public String Logout(@RequestParam(value="name", defaultValue="World") String name) {

        return "111111111";
    }
}
