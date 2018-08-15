package cloud.module.classroom.assignment.discussion.discussionPost;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DiscussionPostService {


    @Autowired
    private DiscussionPostRepository discussionPostRepository;


    public Iterable<DiscussionPost> discussionIdToAllDiscussionPosts(String discussionId) {

        Iterable<DiscussionPost> discussionPosts = discussionPostRepository.findAllByDiscussionId(discussionId);

        return discussionPosts;

    }


}
