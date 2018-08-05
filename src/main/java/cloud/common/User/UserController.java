package cloud.common.User;


import cloud.common.BaseController;
import cloud.common.Result;
import cloud.common.image.Image;
import cloud.common.image.ImageService;
import cloud.module.instructor.Instructor;
import cloud.module.instructor.InstructorService;
import cloud.module.student.Student;
import cloud.module.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController extends BaseController {


    @Autowired
    private UserRepository userRepository;

    @Resource
    private UserService userService;

    @Resource
    private ImageService imageService;

    @Resource
    private InstructorService instructorService;

    @Resource
    private StudentService studentService;


    @PostMapping("/user/all")
    public Iterable<User> all(HttpServletRequest request) {
        return userRepository.findAll();
    }

    @PostMapping("/user/deleteAll")
    public Result deleteAll(HttpServletRequest request) {
        userRepository.deleteAll();
        return new Result("success", "delete all users");
    }

    @PostMapping("/user/userIdToUser")
    public Result userIdToUser(HttpServletRequest request) {

        String userId = request.getParameter("userId");

        User user = userService.userIdToUser(userId);
        Instructor instructor = instructorService.userIdToInstructor(user.getUserId());
        Student student = studentService.userIdToStudent(user.getUserId());

        return new Result("success", "user id to user", user, instructor, student);

    }

    @PostMapping("/user/create")
    public Result create(@ModelAttribute User user ,HttpServletRequest request) {

        // validation
        if (user.getEmail() == null || user.getPassword() == null) {
            return new Result("fail", "can not be empty");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return new Result("fail", "duplicate email");
        }

        user.setAvatarId("e5bbf508-a037-4a0f-8f4f-1ead05555eef");

        userRepository.save(user);

        return new Result("success", "create user", user);
    }

    @PostMapping("/user/deleteByUserId")
    public Result deleteByUserId(HttpServletRequest request) {

        String userId = request.getParameter("userId");

        userRepository.deleteByUserId(userId);

        return new Result("success", "delete by user id");

    }


    @PostMapping("/user/update")
    public Result update(@ModelAttribute User user) {

        String id = user.getUserId();

        if (user.getNickName() != null) {
            userService.updateNickName(id, user.getNickName());
        }

        if (user.getEmail() != null) {
            userService.updateEmail(id, user.getEmail());
        }

        if (user.getPassword() != null) {
            userService.updatePassword(id, user.getEmail());
        }

        User newUser = userService.userIdToUser(id);

        return new Result("success", "update user", newUser);
    }

    @PostMapping("/user/updateAvatar")
    public Result updateAvatar(HttpServletRequest request, @RequestParam("avatar") MultipartFile avatar) {

        String userId = request.getParameter("userId");

        userService.updateAvatar(userId, avatar);

        return new Result("success", "update user avatar");
    }

    @PostMapping("/user/updateNickName")
    public Result updateNickName(HttpServletRequest request) {

        String userId = request.getParameter("userId");
        String nickName = request.getParameter("nickName");

        userService.updateNickName(userId, nickName);

        return new Result("success", "update user nickName");
    }

    @PostMapping("/user/updatePassword")
    public Result updatePassword(HttpServletRequest request) {

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        userService.updatePassword(userId, password);

        return new Result("success", "update user password");
    }

    @PostMapping("/user/updateEmail")
    public Result updateEmail(HttpServletRequest request) {

        String userId = request.getParameter("userId");
        String email = request.getParameter("email");

        userService.updateEmail(userId, email);

        return new Result("success", "update user email");
    }


}
