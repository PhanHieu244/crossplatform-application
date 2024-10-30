package vn.edu.hust.project.crossplatform.port;

import vn.edu.hust.project.crossplatform.dto.ClassDetailDto;

public interface IClassDetailPort {
    Integer getStudentCount(Integer classId);
    void addStudent(Integer classId, Integer studentId);
    boolean isStudentBelongToClass(Integer classId, Integer studentId);
    Integer getClassDetailId(Integer classId, Integer studentId);
    ClassDetailDto getClassDetail(Integer classDetailId);
}
