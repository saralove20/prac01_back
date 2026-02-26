package com.example.demo.board;

import com.example.demo.board.model.Board;
import com.example.demo.board.model.Likes;
import com.example.demo.user.UserRepository;
import com.example.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import com.example.demo.board.model.BoardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final LikesRepository likesRepository;
    private final UserRepository userRepository;

    // 게시글 작성
    public BoardDto.RegRes register(BoardDto.RegReq dto) {
        Board entity = boardRepository.save(dto.toEntity());

        return BoardDto.RegRes.from(entity);
    }

    // 게시글 목록 조회
    public List<BoardDto.ListRes> list() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream().map(BoardDto.ListRes::from).toList();
    }

    // 게시글 상세 조회
    public BoardDto.ReadRes read(Long idx) {
        Board board = boardRepository.findById(idx).orElseThrow();
        return BoardDto.ReadRes.from(board);
    }

    // 게시글 수정
    public BoardDto.RegRes update(Long idx, BoardDto.RegReq dto) {
        Board board = boardRepository.findById(idx).orElseThrow();
        board.update(dto);

        boardRepository.save(board);

        return BoardDto.RegRes.from(board);
    }

    // 게시글 삭제
    public void delete(Long idx) {
        boardRepository.deleteById(idx);
    }

    // 게시글 좋아요 기능
    public void addLike(Long idx, Long userIdx) {
        Board board = boardRepository.findById(idx).orElseThrow();
        User user =userRepository.findById(userIdx).orElseThrow();

        Likes likes = Likes.builder()
                .board(board)
                .user(user)
                .build();

        likesRepository.save(likes);
    }
}
