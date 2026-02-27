package com.example.demo.reply.model;

import com.example.demo.board.model.Board;
import com.example.demo.board.model.BoardDto;
import com.example.demo.user.model.User;
import lombok.Builder;
import lombok.Getter;

public class ReplyDto {
    // 게시글 작성 요청
    @Getter
    public static class ReplyRegReq {
        private String contents;

        public Reply toEntity(User user, Board board) {
            return Reply.builder()
                    .contents(this.contents)
                    .user(user)
                    .board(board)
                    .build();
        }
    }

    // 게시글 작성 응답
    @Builder
    @Getter
    public static class ReplyRegRes {
        private Long idx;
        private String contents;
        private String writer;
//        private Long boardIdx;
        private String boardTitle;

        public static ReplyDto.ReplyRegRes from(Reply entity) {
            return ReplyRegRes.builder()
                    .idx(entity.getIdx())
                    .contents(entity.getContents())
                    .writer(entity.getUser().getName())
//                    .boardIdx(entity.getBoard().getIdx())
                    .boardTitle(entity.getBoard().getTitle())
                    .build();
        }
    }

    // 댓글 목록 조회 응답
    @Builder
    @Getter
    public static class ReplyListRes {
        private Long idx;
        private String contents;
        private String writer;

        public static ReplyListRes from(Reply entity) {
            return ReplyListRes.builder()
                    .idx(entity.getIdx())
                    .contents(entity.getContents())
                    .writer(entity.getUser().getName())
                    .build();
        }
    }

    // 댓글 단건 조회 응답
    @Builder
    @Getter
    public static class ReplyRes {
        private Long idx;
        private String contents;
        private String boardTitle;
        private String writer;

        public static ReplyRes from(Reply entity) {
            return ReplyRes.builder()
                    .idx(entity.getIdx())
                    .contents(entity.getContents())
                    .boardTitle(entity.getBoard().getTitle())
                    .writer(entity.getUser().getName())
                    .build();
        }
    }
}
