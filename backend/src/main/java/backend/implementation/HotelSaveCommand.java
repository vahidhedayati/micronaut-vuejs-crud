package backend.implementation;

import javax.validation.constraints.NotBlank;

public class HotelSaveCommand {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;



    public HotelSaveCommand() {}

    public HotelSaveCommand(String code,String name) {
        this.name = name;
        this.code=code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String name) {
        this.code = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

