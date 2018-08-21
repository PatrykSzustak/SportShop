package solshop.user.model;

import org.hibernate.validator.constraints.NotBlank;

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


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
