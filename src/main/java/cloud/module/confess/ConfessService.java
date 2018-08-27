package cloud.module.confess;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

@Transactional
@Service
public class ConfessService {


    @Autowired
    private ConfessRepository confessRepository;


    public Confess confessIdToConfess(String confessId) {

        Confess confess = confessRepository.findByConfessId(confessId);

        return confess;

    }

    public void deleteByConfessId(String confessId) {

        confessRepository.deleteByConfessId(confessId);

    }

    public Iterable<Confess> searchByType(String type) {

        String newType = "%" + type + "%";

        Iterable<Confess> confesses = confessRepository.findByTypeLike(newType);

        return confesses;
    }

    public Iterable<Confess> searchByContent(String content) {

        String newContent = "%" + content + "%";

        Iterable<Confess> confesses = confessRepository.findByContentLike(newContent);

        return confesses;
    }

    public void incLove(String confessId) {

        Confess confess = confessRepository.findByConfessId(confessId);

        confess.setLove(confess.getLove() + 1);
    }
}
