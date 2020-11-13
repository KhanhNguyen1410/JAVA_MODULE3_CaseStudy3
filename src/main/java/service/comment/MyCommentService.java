package service.comment;

import model.Comment;

import java.util.List;

public interface MyCommentService {
    void create(Comment comment);
    List<Comment> findAll(int product_id);

}
