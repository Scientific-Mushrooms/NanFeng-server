package cloud.module.classroom;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;


}
