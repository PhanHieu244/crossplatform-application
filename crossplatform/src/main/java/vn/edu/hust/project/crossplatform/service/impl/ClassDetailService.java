package vn.edu.hust.project.crossplatform.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.dto.request.AddStudentRequest;
import vn.edu.hust.project.crossplatform.port.IClassDetailPort;
import vn.edu.hust.project.crossplatform.port.IClassPort;
import vn.edu.hust.project.crossplatform.port.IStudentPort;
import vn.edu.hust.project.crossplatform.service.IClassDetailService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassDetailService implements IClassDetailService {
    private final IClassDetailPort classDetailPort;
    private final IClassPort classPort;
    private final IStudentPort studentPort;

    @Override
    public void addStudent(AddStudentRequest addStudentRequest) {
        var classId = classPort.findClassByCode(addStudentRequest.getClassId()).getId();
        var student = studentPort.findStudentById(addStudentRequest.getStudentId());
        classDetailPort.addStudent(classId, addStudentRequest.getStudentId());
    }
}
