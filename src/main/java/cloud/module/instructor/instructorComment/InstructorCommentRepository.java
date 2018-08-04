package cloud.module.instructor.instructorComment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface InstructorCommentRepository extends CrudRepository<InstructorComment, String> {

    @Transactional
    void deleteByInstructorCommentId(String instructorCommentId);

    InstructorComment findByInstructorCommentId(String instructorCommentId);

    @Transactional
    void deleteAllByInstructorId(String instructorId);
}
