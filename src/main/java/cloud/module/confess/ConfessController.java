package cloud.module.confess;

import cloud.common.BaseController;
import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


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

    @PostMapping("/confess/deleteAll")
    public Result deleteAll() {

        confessRepository.deleteAll();

        return new Result("success", "delete all confesses");

    }

    @PostMapping("/confess/create")
    public Result create(@ModelAttribute Confess confess) {

        if (isEmpty(confess.getUserId())) {
            return new Result("fail", "user id cannot be empty");
        }

        confessRepository.save(confess);

        return new Result("success", "create confess", confess);

    }

    @PostMapping("/confess/confessIdToConfess")
    public Result confessIdToConfess(HttpServletRequest request) {

        String confessId = request.getParameter("confessId");

        Confess confess = confessService.confessIdToConfess(confessId);

        return new Result("success", "confess id to confess", confess);

    }

    @PostMapping("/confess/deleteByConfessId")
    public Result deleteByConfessId(HttpServletRequest request) {

        String confessId = request.getParameter("confessId");

        confessService.deleteByConfessId(confessId);

        return new Result("success", "delete by confess id");

    }

}
