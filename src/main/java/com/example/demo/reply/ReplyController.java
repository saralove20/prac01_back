package com.example.demo.reply;

import com.example.demo.common.model.BaseResponse;
import com.example.demo.reply.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@RequestMapping("/reply")
@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

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
