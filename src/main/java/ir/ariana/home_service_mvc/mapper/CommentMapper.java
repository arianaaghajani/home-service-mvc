package ir.ariana.home_service_mvc.mapper;

import ir.ariana.home_service_mvc.dto.comment.CommentReturn;
import ir.ariana.home_service_mvc.dto.comment.CommentSaveRequest;
import ir.ariana.home_service_mvc.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment commentSaveRequestToModel(CommentSaveRequest request);

    CommentReturn modelCommentToSaveResponse(Comment comment);

}
