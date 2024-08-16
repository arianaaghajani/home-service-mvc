package ir.ariana.home_service_mvc.dto.comment;

import ir.ariana.home_service_mvc.dto.order.OrderReturn;

public record CommentReturn(Long id,
                            OrderReturn order,
                            Integer score,
                            String textComment) {
}
