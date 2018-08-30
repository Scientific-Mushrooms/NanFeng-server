package cloud.module.course;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Course")
@Data
public class Course {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String courseId;

    // 课程编号
    private String code;

    //课程名称
    private String name;

    // 课程介绍
    private String introduction;

    // 性质
    private String type;

    // 校区
    private String campus;

    // 开课院系
    private String faculty;

    // 学分
    private String credit;

    // 学时
    private String period;

    // 头像
    private String avatarId;

    // 建议教材
    private String resource;

    // 参考资料
    private String reference;

    // 教学目标
    private String target;

    // 教学内容
    private String content;

    // 其他要求
    private String other;

    private Integer enjoyNum = 0;

    private Integer usefulNum = 0;

    private Integer easyNum = 0;

}
