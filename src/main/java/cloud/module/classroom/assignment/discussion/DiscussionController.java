package cloud.module.classroom.assignment.discussion;


import cloud.common.BaseController;
import cloud.common.Result;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DiscussionController extends BaseController {


    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private DiscussionService discussionService;


    @PostMapping("/discussion/all")
    public Result all() {

        Iterable<Discussion> discussions = discussionRepository.findAll();

        return new Result("success", "all discussions", discussions);

    }

    @PostMapping("/discussion/deleteAll")
    public Result deleteAll() {

        discussionRepository.deleteAll();

        return new Result("success", "delete all discussions");

    }
}
