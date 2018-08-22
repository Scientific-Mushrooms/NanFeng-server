package cloud.module.confess;

import cloud.common.BaseController;
import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class ConfessController extends BaseController {


    @Autowired
    private ConfessRepository confessRepository;

    @Resource
    private ConfessService confessService;


    @PostMapping("/confess/all")
    public Result all() {

        Iterable<Confess> confesses = confessRepository.findAll();

        return new Result("success", "all confesses", confesses);

    }
}
