package ir.ariana.home_service_mvc.dto.subService;

import ir.ariana.home_service_mvc.model.MainService;

public record SubServiceSaveRequest(String name,
                                    Long basePrice,
                                    String description,
                                    MainService mainService) {
}
