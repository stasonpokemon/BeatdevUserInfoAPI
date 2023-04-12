package com.beatdev.api.entity.mapper;

import com.beatdev.api.config.MapperConfig;
import com.beatdev.api.entity.User;
import com.beatdev.api.entity.dto.request.CreateUserRequestDTO;
import com.beatdev.api.entity.dto.response.CreatedUserResponseDTO;
import com.beatdev.api.entity.dto.response.UpdatedStatusUserResponseDTO;
import com.beatdev.api.entity.dto.response.UserInfoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * This interface presents the basic contract for converting User to UserDTO and vice versa.
 */
@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User createUserRequestDTOToUser(CreateUserRequestDTO createUserRequestDTO);

    CreatedUserResponseDTO userToCreatedUserResponseDTO(User user);

    UserInfoResponseDTO userToUserInfoResponseDTO(User user);

    @Mapping(target = "currentStatus", source = "status")
    UpdatedStatusUserResponseDTO userToUpdatedStatusUserResponseDTO(User user);


}
