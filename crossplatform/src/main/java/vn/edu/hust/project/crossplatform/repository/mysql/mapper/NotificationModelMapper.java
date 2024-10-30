package vn.edu.hust.project.crossplatform.repository.mysql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.hust.project.crossplatform.dto.NotificationDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.NotificationModel;

@Mapper
public interface NotificationModelMapper {
    NotificationModelMapper INSTANCE = Mappers.getMapper(NotificationModelMapper.class);

    NotificationModel toModel(NotificationDto notificationDto);
    NotificationDto toEntity(NotificationModel notificationModel);
}
