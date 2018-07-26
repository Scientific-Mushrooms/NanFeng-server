package cloud.module.course.section.sectionMember;

import cloud.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SectionMemberController extends BaseController {


    @Autowired
    private SectionMemberRepository sectionMemberRepository;


}
