package ir.ariana.home_service_mvc.dto;

public record SubServiceReturn(Integer id,
                               String name,
                               Long basePrice,
                               String description,
                               MainServiceReturn mainService) {

}
