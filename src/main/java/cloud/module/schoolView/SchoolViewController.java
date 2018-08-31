package cloud.module.schoolView;

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

@RestController
public class SchoolViewController extends BaseController {

    @Autowired
    private SchoolViewRepository schoolViewRepository;

    @Resource
    private ImageService imageService;

    @Resource
    private SchoolViewService schoolViewService;


    @PostMapping("/schoolView/all")
    public Result all() {

        Iterable<SchoolView> schoolViews = schoolViewRepository.findAllByOrderByDateDesc();

        return new Result("success", "all schoolViews", schoolViews);

    }

    @PostMapping("/schoolView/deleteAll")
    public Result deleteAll() {

        schoolViewRepository.deleteAll();

        return new Result("success", "delete all schoolViews");

    }

    @PostMapping("/schoolView/create")
    public Result create(@ModelAttribute SchoolView schoolView, @RequestParam("image") MultipartFile[] images) {

        if (isEmpty(schoolView.getContent())) {
            return new Result("fail", "content cannot be empty");
        }

        schoolViewRepository.save(schoolView);

        imageService.saveImages(images, schoolView.getSchoolViewId(), "schoolView");

        return new Result("success", "create schoolView", schoolView);

    }

    @PostMapping("/schoolView/schoolViewIdToSchoolView")
    public Result schoolViewIdToSchoolView(HttpServletRequest request) {

        String schoolViewId = request.getParameter("schoolViewId");

        SchoolView schoolView = schoolViewService.schoolViewIdToSchoolView(schoolViewId);

        return new Result("success", "schoolView id to schoolView", schoolView);

    }

    @PostMapping("/schoolView/deleteBySchoolViewId")
    public Result deleteBySchoolViewId(HttpServletRequest request) {

        String schoolViewId = request.getParameter("schoolViewId");

        schoolViewService.deleteBySchoolViewId(schoolViewId);

        return new Result("success", "delete by schoolView id");

    }


}
