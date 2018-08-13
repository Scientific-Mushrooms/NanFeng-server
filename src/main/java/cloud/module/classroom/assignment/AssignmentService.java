package cloud.module.classroom.assignment;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AssignmentService {


    @Autowired
    private AssignmentRepository assignmentRepository;


    public Assignment assignmentIdToAssignment(String assignmentId) {

        Assignment assignment = assignmentRepository.findByAssignmentId(assignmentId);

        return assignment;

    }

    public Iterable<Assignment> classroomIdToAllAssignments(String classroomId) {

        Iterable<Assignment> assignments = assignmentRepository.findAllByClassroomId(classroomId);

        return assignments;

    }
}
