package cloud.module.classroom.classroomMember;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ClassroomMemberRepository extends CrudRepository<ClassroomMember, String> {

    ClassroomMember findByClassroomMemberId(String classroomMemberId);

    @Transactional
    void deleteByClassroomMemberId(String classroomMemberId);

    Iterable<ClassroomMember> findAllByClassroomId(String classroomId);
}
