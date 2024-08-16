package ir.ariana.home_service_mvc.dto.customer;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CustomerCriteriaDto {

    public String firstName;
    public String lastName;
    public String email;
    public int orderCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime minRegisterDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime maxRegisterDate;
}
