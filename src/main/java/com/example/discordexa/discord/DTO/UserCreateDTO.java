package com.example.discordexa.discord.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringEscapeUtils;



@Getter
@Setter
public class UserCreateDTO {


    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9-_]+\\.*[a-zA-Z0-9-_]*@([a-zA-Z0-9]+\\.{1})+([a-zA-Z]){2,3}$",message = "Invalid email address")
    private String email;
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 8 , max = 50,message = "Password must be 8-50 characters")
    private String password;
    @NotNull(message = "Lastname is required")
    @NotBlank(message = "Lastname is required")
    @Size(min = 2 , max = 50,message = "Lastname must be between 2 and 50 characters and must contain only letters")
    private String lastname;
    @NotNull(message = "Firstname is required")
    @NotBlank(message = "Firstname is required")
    @Size(min = 2 , max = 50,message = "Firstname must be between 2 and 50 characters and must contain only letters")
    private String firstname;


    public UserCreateDTO(){}

//    public boolean isValidEmail() {
//        if (email == null || email.isEmpty()) {
//            return false;
//        }
//        String regex = "^[a-zA-Z0-9-_]+\\.*[a-zA-Z0-9-_]*@([a-zA-Z0-9]+\\.{1})+([a-zA-Z]){2,3}$";
//        java.util.regex.Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(email.trim());
//        return matcher.matches();
//    }
//
//
//    public boolean isValidPassword() {
//        if (password == null || password.isEmpty()) {
//            return false;
//        }
//        String regex = "^.{8,50}$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(password);
//        return matcher.matches();
//    }
//
//    public boolean isValidLastname() {
//        if (lastname == null || lastname.isEmpty()) {
//            return false;
//        }
//        String regex = "^[a-zA-Z]{2,50}$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(lastname.trim());
//        return matcher.matches();
//    }
//
//    public boolean isValidFirstname() {
//        if (firstname == null || firstname.isEmpty()) {
//            return false;
//        }
//        String regex = "^[a-zA-Z]{2,50}$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(firstname.trim());
//        return matcher.matches();
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = StringEscapeUtils.escapeHtml4(email.trim().toLowerCase());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = StringEscapeUtils.escapeHtml4(password.trim().toLowerCase());
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = StringEscapeUtils.escapeHtml4(lastname.trim().toUpperCase());
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = StringEscapeUtils.escapeHtml4(firstname.trim());
    }
}
