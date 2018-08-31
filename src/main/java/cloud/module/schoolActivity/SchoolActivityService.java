package cloud.module.schoolActivity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SchoolActivityService {

    @Autowired
    private SchoolActivityRepository schoolActivityRepository;


    public SchoolActivity schoolActivityIdToSchoolActivity(String schoolActivityId) {

        SchoolActivity schoolActivity = schoolActivityRepository.findBySchoolActivityId(schoolActivityId);

        return schoolActivity;

    }

    public void deleteBySchoolActivityId(String schoolActivityId) {

        schoolActivityRepository.deleteBySchoolActivityId(schoolActivityId);

    }
}
