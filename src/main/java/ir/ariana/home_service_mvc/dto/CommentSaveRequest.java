package ir.ariana.home_service_mvc.dto;

public record CommentSaveRequest(OrderReturn order,
                                 Integer score,
                                 String textComment) {
}
