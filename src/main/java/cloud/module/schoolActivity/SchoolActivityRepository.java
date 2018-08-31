package cloud.module.schoolActivity;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface SchoolActivityRepository extends CrudRepository<SchoolActivity, String> {

    SchoolActivity findBySchoolActivityId(String schoolActivityId);

    @Transactional
    void deleteBySchoolActivityId(String schoolActivityId);

    Iterable<SchoolActivity> findAllByOrderByDateDesc();
}
