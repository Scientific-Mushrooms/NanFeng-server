package cloud.module.confess;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
