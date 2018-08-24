package solshop.user.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

    @Generated(
            value = "org.mapstruct.ap.MappingProcessor",
            date = "2018-06-16T22:54:25+0200",
            comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
    )
    @Component
    public class UserMapperImpl implements UserMapper {

        @Override
        public UserDTO toUserDTO(UserEntity userEntity) {
            if ( userEntity == null ) {
                return null;
            }

            UserDTO userDTO = new UserDTO();

            userDTO.setMail( userEntity.getEmail());

            return userDTO;
        }

        @Override
        public Set<UserDTO> toUserDTO(Collection<UserEntity> userEntities) {
            if ( userEntities == null ) {
                return null;
            }

            Set<UserDTO> set = new HashSet<UserDTO>( Math.max( (int) ( userEntities.size() / .75f ) + 1, 16 ) );
            for ( UserEntity userEntity : userEntities ) {
                set.add( toUserDTO( userEntity ) );
            }

            return set;
        }
    }

