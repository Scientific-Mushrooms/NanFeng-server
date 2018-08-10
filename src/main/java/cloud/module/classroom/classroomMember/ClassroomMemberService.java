package cloud.module.classroom.classroomMember;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ClassroomMemberService {


    @Autowired
    private ClassroomMemberRepository classroomMemberRepository;


    public ClassroomMember classroomMemberIdToClassroomMember(String classroomMemberId) {

        ClassroomMember classroomMember = classroomMemberRepository.findByClassroomMemberId(classroomMemberId);

        return classroomMember;

    }

    public Iterable<ClassroomMember> classroomIdToAllClassroomMembers(String classroomId) {

        Iterable<ClassroomMember> classroomMembers = classroomMemberRepository.findAllByClassroomId(classroomId);

        return classroomMembers;

    }

    public void deleteByClassroomMemberId(String classroomMemberId) {

        classroomMemberRepository.deleteByClassroomMemberId(classroomMemberId);

    }
}
