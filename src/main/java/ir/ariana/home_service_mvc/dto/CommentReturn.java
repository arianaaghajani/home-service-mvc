package ir.ariana.home_service_mvc.dto;

public record CommentReturn(Long id,
                            OrderReturn order,
                            Integer score,
                            String textComment) {
}
