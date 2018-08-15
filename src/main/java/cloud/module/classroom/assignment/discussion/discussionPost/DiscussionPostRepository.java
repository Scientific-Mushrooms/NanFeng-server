package cloud.module.classroom.assignment.discussion.discussionPost;

import org.springframework.data.repository.CrudRepository;

public interface DiscussionPostRepository extends CrudRepository<DiscussionPost, String> {

    Iterable<DiscussionPost> findAllByDiscussionId(String discussionId);

}
