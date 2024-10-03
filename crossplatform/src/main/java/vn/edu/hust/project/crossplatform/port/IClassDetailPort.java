package vn.edu.hust.project.crossplatform.port;

public interface IClassDetailPort {
    Integer getStudentCount(Integer classId);
    void addStudent(Integer classId, Integer studentId);
}
