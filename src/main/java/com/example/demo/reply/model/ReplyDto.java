package com.example.demo.reply.model;

import lombok.Builder;
import lombok.Getter;

public class ReplyDto {
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
