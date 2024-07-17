package ir.ariana.home_service_mvc.mapper;

import ir.ariana.home_service_mvc.dto.CommentReturn;
import ir.ariana.home_service_mvc.dto.CommentSaveRequest;
import ir.ariana.home_service_mvc.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = OrderMapper.class)
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment commentSaveRequestToModel(CommentSaveRequest request);

    CommentReturn modelCommentToSaveResponse(Comment comment);
}
