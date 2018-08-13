package cloud.module.classroom.assignment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AssignmentService {


    @Autowired
    private AssignmentRepository assignmentRepository;



}
