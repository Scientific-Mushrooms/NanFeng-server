package cloud.module.confess.confessComment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ConfessCommentRepository extends CrudRepository<ConfessComment, String> {
    ConfessComment findByConfessCommentId(String ratingId);

    @Transactional
    void deleteByUserId(String userId);

    Iterable<ConfessComment> findAllByConfessId(String confessId);

    @Transactional
    void deleteAllByConfessId(String confessId);
}
