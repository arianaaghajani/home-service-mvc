package ir.ariana.home_service_mvc.dto.comment;

public record CommentSaveRequest(Long orderId,
                                 Integer score,
                                 String textComment,
                                 Long offerId) {
}
