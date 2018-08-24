package solshop.user.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserDTO {

    @NotBlank
    private String mail;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;

    public UserDTO() {
    }

    public UserDTO( String mail, String password,String confirmPassword) {
        this.mail = mail;
        this.password = password;
        this.confirmPassword =confirmPassword;
    }



}
