package com.example.demo.board;

import com.example.demo.common.model.BaseResponse;
import lombok.RequiredArgsConstructor;
import com.example.demo.board.model.BoardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@RequestMapping("/board")
@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 등록
    @PostMapping("/reg")
    public ResponseEntity register(@RequestBody BoardDto.RegReq dto) {
        BoardDto.RegRes result = boardService.register(dto);
        return ResponseEntity.ok(BaseResponse.success(dto));
    }


    // 게시글 목록 조회
    @GetMapping("/list")
    public ResponseEntity list() {
        List<BoardDto.ListRes> dto = boardService.list();
        return ResponseEntity.ok(BaseResponse.success(dto));
    }

    // 게시글 상세 조회
    @GetMapping("/read/{idx}")
    public ResponseEntity read(@PathVariable Long idx) {
        BoardDto.ReadRes dto = boardService.read(idx);
        return ResponseEntity.ok(BaseResponse.success(dto));
    }

    // 게시글 수정
    @PutMapping("/update/{idx}")
    public ResponseEntity update(@PathVariable Long idx, @RequestBody BoardDto.RegReq dto) {
        BoardDto.RegRes returnDto = boardService.update(idx, dto);
        return ResponseEntity.ok(returnDto);
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{idx}")
    public ResponseEntity update(@PathVariable Long idx) {
        boardService.delete(idx);
        return ResponseEntity.ok(BaseResponse.success("성공"));
    }

    // 게시글 좋아요 기능
    @PostMapping("/{idx}/likes")
    public ResponseEntity addLike(@PathVariable Long idx, @RequestBody Long userIdx) {
        boardService.addLike(idx, userIdx);
        return ResponseEntity.ok(BaseResponse.success("성공"));    }
}

