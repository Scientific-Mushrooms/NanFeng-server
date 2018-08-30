package cloud.module.confess;

import cloud.common.BaseController;
import cloud.common.Result;
import cloud.common.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ConfessController extends BaseController {


    @Autowired
    private ConfessRepository confessRepository;

    @Resource
    private ConfessService confessService;

    @Resource
    private ImageService imageService;


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
    public Result create(@ModelAttribute Confess confess, @RequestParam("image") MultipartFile[] images) {

        if (isEmpty(confess.getUserId())) {
            return new Result("fail", "user id cannot be empty");
        }

        confessRepository.save(confess);

        imageService.saveImages(images, confess.getConfessId(), "confess");

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

    @PostMapping("/confess/searchByType")
    public Result searchByType(HttpServletRequest request) {

        String type = request.getParameter("type");

        Iterable<Confess> confesses = confessService.searchByType(type);

        return new Result("success", "search by type", confesses);

    }

    @PostMapping("/confess/autoCompleteByType")
    public Result autoCompleteByType(HttpServletRequest request) {

        String type = request.getParameter("type");

        Iterable<Confess> confesses = confessService.searchByType(type);

        List<String> types = new ArrayList();

        for (Confess confess : confesses) {
            types.add(confess.getType());
        }

        return new Result("success", "auto complete", types);

    }

    @PostMapping("/confess/searchByContent")
    public Result searchByContent(HttpServletRequest request) {

        String content = request.getParameter("content");

        Iterable<Confess> confesses = confessService.searchByContent(content);

        return new Result("success", "search by content", confesses);

    }

    @PostMapping("/confess/autoCompleteByContent")
    public Result autoCompleteByContent(HttpServletRequest request) {

        String content = request.getParameter("content");

        Iterable<Confess> confesses = confessService.searchByContent(content);

        List<String> contents = new ArrayList();

        for (Confess confess : confesses) {
            contents.add(confess.getContent());
        }

        return new Result("success", "auto complete", contents);

    }

    @PostMapping("/confess/Love")
    public Result love(HttpServletRequest request) {

        String confessId = request.getParameter("confessId");

        if (confessService.confessIdToConfess(confessId) == null){
            return new Result("fail", "confess does not exist");
        }

        confessService.confessIdToConfess(confessId).setLove(confessService.confessIdToConfess(confessId).getLove() + 1);

        return new Result("success", "love number increased");

    }

    @PostMapping("/confess/undoLove")
    public Result undoLove(HttpServletRequest request) {

        String confessId = request.getParameter("confessId");

        if (confessService.confessIdToConfess(confessId).getLove() > 0){
            confessService.confessIdToConfess(confessId).setLove(confessService.confessIdToConfess(confessId).getLove() - 1);
        }

        return new Result("success", "love cancelled");

    }
}
