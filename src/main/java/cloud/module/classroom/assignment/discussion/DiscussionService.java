package cloud.module.classroom.assignment.discussion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DiscussionService {


    @Autowired
    private DiscussionRepository discussionRepository;


}
