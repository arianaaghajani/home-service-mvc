package ir.ariana.home_service_mvc.dto.subService;

import ir.ariana.home_service_mvc.dto.mainService.MainServiceReturn;

public record SubServiceReturn(Long id,
                               String name,
                               Long basePrice,
                               String description,
                               MainServiceReturn mainService) {

}
