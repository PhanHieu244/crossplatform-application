package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.constant.ClassStatus;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.dto.request.EditClassRequest;
import vn.edu.hust.project.crossplatform.exception.NoDataException;
import vn.edu.hust.project.crossplatform.exception.UnauthorizedException;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.port.IClassPort;
import vn.edu.hust.project.crossplatform.repository.mysql.IClassDetailRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.IClassRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.mapper.ClassModelMapper;
import vn.edu.hust.project.crossplatform.repository.mysql.model.ClassDetailModel;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassAdapter implements IClassPort {
    private final IClassRepository classRepository;
    private final IClassDetailRepository classDetailRepository;

    @Override
    public ClassDto createClass(ClassDto classDto) {
        try{
            if(classDto.getStartDate().isAfter(classDto.getEndDate())){
                throw new ApplicationException(
                        ResponseCode.PARAMETER_VALUE_IS_INVALID,
                        "Start date cannot be after end date"
                );
            }
            classDto.setStatus(ClassStatus.ACTIVE);
            return ClassModelMapper.INSTANCE.toEntity(
                    classRepository.save(ClassModelMapper.INSTANCE.toModel(classDto))
            );
        }
        catch(Exception e){
            log.error("Error creating class", e);
            throw new ApplicationException("create class error");
        }
    }

    @Override
    public ClassDto findClassById(Integer classId) {
        var classModel = classRepository.findById(classId).orElseThrow(
                () -> new NoDataException("Class not found")
        );
        return ClassModelMapper.INSTANCE.toEntity(classModel);
    }

    @Override
    public ClassDto findClassByCode(String classCode) {
        var classModel = classRepository.findClassByCode(classCode).orElseThrow(
                () -> new NoDataException("Class not found")
        );
        return ClassModelMapper.INSTANCE.toEntity(classModel);
    }

    @Override
    public ClassDto editClass(EditClassRequest request) {
        var existingClass = classRepository.findClassByCode(request.getCode()).orElseThrow(
                () -> new NoDataException("class is not exist")
        );
        if(request.getClassName() != null){
            existingClass.setClassName(request.getClassName());
        }
        if(request.getStartDate() != null){
            existingClass.setStartDate(request.getStartDate());
        }
        if(request.getEndDate() != null){
            existingClass.setEndDate(request.getEndDate());
        }
        if (request.getStatus() != null){
            existingClass.setStatus(request.getStatus().getValue());
        }
        return ClassModelMapper.INSTANCE.toEntity(
                classRepository.save(existingClass)
        );
    }

    @Override
    public List<ClassDto> getStudentClasses(Integer studentId) {
        var classIds = classDetailRepository.findAllByStudentId(studentId)
                .stream()
                .map(ClassDetailModel::getClassId).toList();
        return classRepository.findAllById(classIds)
                .stream()
                .map(ClassModelMapper.INSTANCE::toEntity)
                .toList();
    }

    @Override
    public List<ClassDto> getLecturerClasses(Integer lecturerId) {
        return classRepository.findAllByLecturerId(lecturerId)
                .stream()
                .map(ClassModelMapper.INSTANCE::toEntity)
                .toList();
    }

    @Override
    public void deleteClass(Integer classId) {
        var existingClass = classRepository.findById(classId).orElseThrow(
                () -> new NoDataException("class is not exist")
        );
        classRepository.delete(existingClass);
    }

    @Override
    public void deleteClass(Integer classId, Integer lecturerId) {
        var existingClass = classRepository.findById(classId).orElseThrow(
                () -> new NoDataException("class is not exist")
        );
        if(!existingClass.getLecturerId().equals(lecturerId)){
            throw new UnauthorizedException("cannot delete class with different lecturer");
        }
        classRepository.delete(existingClass);
    }
}
