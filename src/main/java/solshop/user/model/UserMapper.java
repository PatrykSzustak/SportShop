package solshop.user.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import solshop.user.service.UserService;

import java.util.Collection;
import java.util.Set;


@Mapper(componentModel = "spring", uses = UserService.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {


    @Mappings({
            @Mapping(source = "email", target = "mail"),
            @Mapping(target = "confirmPassword", ignore = true),
            @Mapping(target = "password", ignore = true)
    })
    UserDTO toUserDTO(UserEntity userEntity);

    Set<UserDTO> toUserDTO(Collection<UserEntity> userEntities);
}
