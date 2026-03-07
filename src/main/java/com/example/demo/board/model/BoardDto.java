package com.example.demo.board.model;

import com.example.demo.reply.model.ReplyDto;
import com.example.demo.user.model.AuthUserDetails;
import com.example.demo.user.model.User;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

public class BoardDto {
    // 게시글 작성 요청
    @Getter
    public static class RegReq {
        @Schema(description = "제목, 제목은 50글자까지만 입력 가능합니다.", required = true, example = "제목01")
        private String title;
        private String contents;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(this.title)
                    .contents(this.contents)
                    .user(user)
                    .build();
        }
    }

    // 게시글 작성 응답
    @Builder
    @Getter
    public static class RegRes {
        private Long idx;
        private String title;
        private String contents;
        private String writer;

        public static RegRes from(Board entity) {
            return RegRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .writer(entity.getUser().getName())
                    .build();
        }
    }

    // 게시글 목록 조회 페이지 응답
    @Builder
    @Getter
    public static class PageRes {
        private List<ListRes> boardList;    // 기존 ListRes 활용
        private int totalPage;              // 전체 페이지 수
        private long totalCount;            // 전체 게시글 수
        private int currentPage;            // 현재 페이지 번호
        private int currentSize;            // 페이지당 사이즈 (페이지당 게시글 수)

        public static PageRes from(Page<Board> result) {
            return PageRes.builder()
                    .boardList(result.get().map(ListRes::from).toList())
                    .totalPage(result.getTotalPages())
                    .totalCount(result.getTotalElements())
                    .currentPage(result.getPageable().getPageNumber())
                    .currentSize(result.getPageable().getPageSize())
                    .build();
        }
    }

    // 게시글 목록 조회 응답
    @Builder
    @Getter
    public static class ListRes {
        private Long idx;
        private String title;
        private String writer;
        private Integer replyCount;
        private Integer likesCount;

        public static ListRes from(Board entity) {
            return ListRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .writer(entity.getUser().getName())
                    .replyCount(entity.getReplyList().size())
                    .likesCount(entity.getLikesList().size())
                    .build();
        }
    }


    // 게시글 상세 조회 응답
    @JsonPropertyOrder({
            "idx",
            "title",
            "contents",
            "writer",
            "likesCount",
            "replies"
    })
    @Builder
    @Getter
    public static class ReadRes {
        private Long idx;
        private String title;
        private String contents;
        private String writer;
        private Integer likesCount;
        private List<ReplyDto.ReplyListRes> replies;

        public static ReadRes from(Board entity) {
            return ReadRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .writer(entity.getUser().getName())
                    .likesCount(entity.getLikesList().size())
                    .replies(entity.getReplyList().stream().map(ReplyDto.ReplyListRes::from).toList())
                    .build();
        }
    }
}
