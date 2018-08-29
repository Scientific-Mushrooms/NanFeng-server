package cloud.module.schoolView;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SchoolViewService {

    @Autowired
    private SchoolViewRepository schoolViewRepository;


    public SchoolView schoolViewIdToSchoolView(String schoolViewId) {

        SchoolView schoolView = schoolViewRepository.findBySchoolViewId(schoolViewId);

        return schoolView;

    }

    public void deleteBySchoolViewId(String schoolViewId) {

        schoolViewRepository.deleteBySchoolViewId(schoolViewId);

    }
}
