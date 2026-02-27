package com.example.demo.reply;

import com.example.demo.board.model.BoardDto;
import com.example.demo.common.model.BaseResponse;
import com.example.demo.reply.model.Reply;
import com.example.demo.reply.model.ReplyDto;
import com.example.demo.user.model.AuthUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@RequestMapping("/reply")
@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/register/{boardIdx}")
    public ResponseEntity register(@AuthenticationPrincipal AuthUserDetails user, @PathVariable Long boardIdx, @RequestBody ReplyDto.ReplyRegReq dto) {
        ReplyDto.ReplyRegRes result = replyService.register(user.getIdx(), boardIdx, dto);
        return ResponseEntity.ok(BaseResponse.success(result));
    }


    @GetMapping("/{idx}")
    public ResponseEntity read(@PathVariable Long idx) {
        ReplyDto.ReplyRes dto = replyService.read(idx);
        return ResponseEntity.ok(BaseResponse.success(dto));
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        List<ReplyDto.ReplyRes> dto = replyService.list();
        return ResponseEntity.ok(BaseResponse.success(dto));
    }
}
