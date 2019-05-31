package gateway.adaptors.models.implementation;

import javax.validation.constraints.NotBlank;

public class HotelSaveCommand {


    @NotBlank
    private String code;

    @NotBlank
    private String name;


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

}
