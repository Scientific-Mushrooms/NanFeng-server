package cloud.module.classroom.assignment.discussion;


import cloud.module.classroom.assignment.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DiscussionService {


    @Autowired
    private DiscussionRepository discussionRepository;


    public Discussion create(String assignmnetId, String topic) {

        Discussion discussion = new Discussion();

        discussion.setAssignmentId(assignmnetId);
        discussion.setTopic(topic);

        discussionRepository.save(discussion);

        return discussion;

    }

}
