package cloud.common.User;


import cloud.common.BaseController;
import cloud.common.FollowRepository;
import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FollowRepository followRepository;


    @PostMapping("/user/all")
    public Iterable<User> all(HttpServletRequest request) {
        return userRepository.findAll();
    }

    @PostMapping("/user/delAll")
    public Result deleteAll(HttpServletRequest request) {
        userRepository.deleteAll();
        return new Result("success");
    }

    @PostMapping("/user/create")
    public Result create(HttpServletRequest request) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userRepository.existsByEmail(email)) {
            return new Result("fail", "duplicate email");
        }

        User user = userService.create(email, password);

        return new Result("success", "nothing", user);
    }

    @PostMapping("/user/deleteByUserId")
    public Result deleteById(HttpServletRequest request) {

        String id = request.getParameter("userId");

        if (!userRepository.existsById(id)) {
            return new Result("fail", "user not exist");
        }
        userRepository.deleteById(id);

        return new Result("success");
    }


    @PostMapping("/user/updateAvatar")
    public Result updateAvatar(HttpServletRequest request, @RequestParam("avatar") MultipartFile avatar) {

        String userId = request.getParameter("userId");
        Result result = saveImage(avatar);

        if (!userRepository.existsById(userId)) {
            return new Result("fail", "user not exist");
        }

        if (result.getStatus().equals("fail")) {
            return result;
        }

        userRepository.updateAvatarById(result.getDescription(), userId);

        return new Result("success", "update the avatar");
    }


}
