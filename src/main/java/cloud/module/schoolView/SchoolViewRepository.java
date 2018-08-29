package cloud.module.schoolView;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface SchoolViewRepository extends CrudRepository<SchoolView, String> {

    SchoolView findBySchoolViewId(String schoolViewId);

    @Transactional
    void deleteBySchoolViewId(String schoolViewId);

}
