package cloud.module.schoolActivity;

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
public class SchoolActivityController extends BaseController {

    @Autowired
    private SchoolActivityRepository schoolActivityRepository;

    @Resource
    private ImageService imageService;

    @Resource
    private SchoolActivityService schoolActivityService;


    @PostMapping("/schoolActivity/all")
    public Result all() {

        Iterable<SchoolActivity> schoolActivitys = schoolActivityRepository.findAllByOrderByDateDesc();

        return new Result("success", "all schoolActivitys", schoolActivitys);

    }

    @PostMapping("/schoolActivity/deleteAll")
    public Result deleteAll() {

        schoolActivityRepository.deleteAll();

        return new Result("success", "delete all schoolActivitys");

    }

    @PostMapping("/schoolActivity/create")
    public Result create(@ModelAttribute SchoolActivity schoolActivity, @RequestParam("image") MultipartFile[] images) {

        if (isEmpty(schoolActivity.getTitle())) {
            return new Result("fail", "title cannot be empty");
        }
        if (isEmpty(schoolActivity.getSubTitle())) {
            return new Result("fail", "subTitle cannot be empty");
        }

        schoolActivityRepository.save(schoolActivity);

        imageService.saveImages(images, schoolActivity.getSchoolActivityId(), "schoolActivity");

        return new Result("success", "create schoolActivity", schoolActivity);

    }

    @PostMapping("/schoolActivity/schoolActivityIdToSchoolActivity")
    public Result schoolActivityIdToSchoolActivity(HttpServletRequest request) {

        String schoolActivityId = request.getParameter("schoolActivityId");

        SchoolActivity schoolActivity = schoolActivityService.schoolActivityIdToSchoolActivity(schoolActivityId);

        return new Result("success", "schoolActivity id to schoolActivity", schoolActivity);

    }

    @PostMapping("/schoolActivity/deleteBySchoolActivityId")
    public Result deleteBySchoolActivityId(HttpServletRequest request) {

        String schoolActivityId = request.getParameter("schoolActivityId");

        schoolActivityService.deleteBySchoolActivityId(schoolActivityId);

        return new Result("success", "delete by schoolActivity id");

    }

}
