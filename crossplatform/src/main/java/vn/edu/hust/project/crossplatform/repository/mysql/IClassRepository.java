package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.ClassModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClassRepository extends IBaseRepository<ClassModel> {
    List<ClassModel> findAllByLecturerId(Integer lecturerId);
    Optional<ClassModel> findClassByCode(String code);
}
