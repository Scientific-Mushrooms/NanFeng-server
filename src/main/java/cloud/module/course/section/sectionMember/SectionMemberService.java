package cloud.module.course.section.sectionMember;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class SectionMemberService {


    @Autowired
    private SectionMemberRepository sectionMemberRepository;



}
