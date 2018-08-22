package cloud.module.confess;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ConfessRepository extends CrudRepository<Confess, String> {

    Confess findByConfessId(String confessId);

    @Transactional
    void deleteByConfessId(String confessId);
}
