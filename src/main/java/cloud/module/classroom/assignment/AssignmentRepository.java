package cloud.module.classroom.assignment;

import org.springframework.data.repository.CrudRepository;

public interface AssignmentRepository extends CrudRepository<Assignment, String> {

    Assignment findByAssignmentId(String assignmentId);

    Iterable<Assignment> findAllByClassroomId(String classroomId);

}
