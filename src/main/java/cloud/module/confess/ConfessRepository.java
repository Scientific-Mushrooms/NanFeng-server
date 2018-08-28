package cloud.module.confess;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ConfessRepository extends CrudRepository<Confess, String> {

    Confess findByConfessId(String confessId);

    @Transactional
    void deleteByConfessId(String confessId);

    Iterable<Confess> findByTypeLikeOrderByDateDesc(String type);

    Iterable<Confess> findByContentLikeOrderByDateDesc(String content);

    Iterable<Confess> findAllByOrderByDateDesc();
}
