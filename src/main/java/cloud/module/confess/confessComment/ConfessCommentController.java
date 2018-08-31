package cloud.module.confess.confessComment;

import cloud.common.BaseController;
import cloud.common.Result;
import cloud.common.User.User;
import cloud.common.User.UserService;
import cloud.common.image.ImageService;
import cloud.module.confess.confessComment.ConfessComment;
import cloud.module.confess.confessComment.ConfessCommentRepository;
import cloud.module.confess.confessComment.ConfessCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ConfessCommentController extends BaseController {

    @Autowired
    private ConfessCommentRepository confessCommentRepository;

    @Resource
    private ConfessCommentService confessCommentService;

    @Resource
    private UserService userService;

    @Resource
    private ImageService imageService;

    @PostMapping("/confessComment/all")
    public Result all(HttpServletRequest request) {

        Iterable<ConfessComment> confessComments = confessCommentRepository.findAll();

        return new Result("success", "all confessComment", confessComments);

    }

    @PostMapping("/confessComment/deleteAll")
    public Result deleteAll(HttpServletRequest request) {

        confessCommentRepository.deleteAll();

        return new Result("success", "delete all confess comments");

    }

    @PostMapping("/confessComment/deleteAllByUserId")
    public Result deleteAllByUserId(HttpServletRequest request) {

        String userId = request.getParameter("userId");

        confessCommentRepository.deleteByUserId(userId);

        return new Result("success", "delete all confess comments by user id");

    }

    @PostMapping("/confessComment/deleteAllByConfessId")
    public Result deleteAllByConfessId(HttpServletRequest request) {

        String confessId = request.getParameter("confessId");

        confessCommentRepository.deleteAllByConfessId(confessId);

        return new Result("success", "delete all confess comments by confess id");

    }

    @PostMapping("/confessComment/confessCommentIdToConfessComment")
    public Result confessCommentIdToConfessComment(HttpServletRequest request) {

        String confessCommentId = request.getParameter("confessCommentId");

        ConfessComment confessComment = confessCommentService.confessCommentIdToConfessComment(confessCommentId);

        return new Result("success", "ratingID to ConfessComment", confessComment);

    }

    @PostMapping("/confessComment/confessIdToConfessComments")
    public Result confessIdToConfessComment(HttpServletRequest request) {

        String confessId = request.getParameter("confessId");

        Iterable<ConfessComment> confessComments = confessCommentRepository.findAllByConfessId(confessId);

        List<User> users = new ArrayList();

        for (ConfessComment comment : confessComments) {

            User user = userService.userIdToUser(comment.getUserId());

            users.add(user);

        }

        return new Result("success", "ratingID to ConfessComment", confessComments, users);

    }

    @PostMapping("/confessComment/create")
    public Result create(@ModelAttribute ConfessComment confessComment, @RequestParam("image") MultipartFile[] images) {

        // verify parameters
        if (confessComment.getConfessId() == null || confessComment.getUserId() == null) {
            return new Result("fail", "confess id or user id can not be empty");
        }

        confessComment.setDate(new Date());

        confessCommentRepository.save(confessComment);

        imageService.saveImages(images, confessComment.getConfessCommentId(), "confessComment");

        return new Result("success", "create confessComment", confessComment);

    }


}
