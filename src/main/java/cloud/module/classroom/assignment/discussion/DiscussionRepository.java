package cloud.module.classroom.assignment.discussion;

import org.springframework.data.repository.CrudRepository;

public interface DiscussionRepository extends CrudRepository<Discussion, String> {

    Discussion findByAssignmentId(String assignmentId);

}
