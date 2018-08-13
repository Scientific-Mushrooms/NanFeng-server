package cloud.module.classroom.assignment.discussion.discussionPost;


import cloud.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DiscussionPostController extends BaseController {


    @Autowired
    private DiscussionPostRepository discussionPostRepository;

    @Resource
    private DiscussionPostService discussionPostService;
}
