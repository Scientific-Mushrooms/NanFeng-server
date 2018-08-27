package cloud.module.confess.confessComment;

import cloud.module.confess.confessComment.ConfessComment;
import cloud.module.confess.confessComment.ConfessCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ConfessCommentService {

    @Autowired
    private ConfessCommentRepository confessCommentRepository;


    public ConfessComment confessCommentIdToConfessComment(String confessCommentId) {

        ConfessComment confessComment = confessCommentRepository.findByConfessCommentId(confessCommentId);

        return confessComment;
    }

    public Iterable<ConfessComment> confessIdToConfessComments(String confessId) {

        Iterable<ConfessComment> confessComments = confessCommentRepository.findAllByConfessId(confessId);

        return confessComments;

    }

    public void deleteAllByConfessId(String confessId) {

        confessCommentRepository.deleteAllByConfessId(confessId);

    }
}
