package com.example.demo.reply;

import com.example.demo.board.BoardRepository;
import com.example.demo.board.model.Board;
import com.example.demo.board.model.BoardDto;
import com.example.demo.reply.model.Reply;
import com.example.demo.reply.model.ReplyDto;
import com.example.demo.user.UserRepository;
import com.example.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    // 댓글 작성
    public ReplyDto.ReplyRegRes register(Long userIdx, Long boardIdx, ReplyDto.ReplyRegReq dto) {
        User user = userRepository.findById(userIdx).orElseThrow();
        Board board = boardRepository.findById(boardIdx).orElseThrow();
        Reply entity = replyRepository.save(dto.toEntity(user, board));

        return ReplyDto.ReplyRegRes.from(entity);
    }

    public ReplyDto.ReplyRes read(Long idx) {
        Reply reply = replyRepository.findById(idx).orElseThrow();
        return ReplyDto.ReplyRes.from(reply);
    }

    public List<ReplyDto.ReplyRes> list() {
        List<Reply> replyList = replyRepository.findAll();
        return replyList.stream().map(ReplyDto.ReplyRes::from).toList();
    }
}
