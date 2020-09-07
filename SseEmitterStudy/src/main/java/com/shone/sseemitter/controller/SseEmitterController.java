package com.shone.sseemitter.controller;

import com.shone.sseemitter.service.SseEmitterServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * DemoClass
 *
 * @author Xiao GuoJian
 * @date 2020/9/7 下午4:28
 */

@RestController
@RequestMapping("/sse")
public class SseEmitterController {

    /**
     * 1、先访问接口简历连接
     * 用于创建连接
     */
    @GetMapping("/connect/{userId}")
    public SseEmitter connect(@PathVariable String userId) {
        return SseEmitterServer.connect(userId);
    }

    /**
     * 2、推送消息给所有人
     * 推送消息给所有人
     * @param message 消息
     * @return 返回
     */
    @GetMapping("/push/{message}")
    public ResponseEntity<String> push(@PathVariable(name = "message") String message) {
        SseEmitterServer.batchSendMessage(message);
        return ResponseEntity.ok("SseEmitter 推送消息给所有人");
    }

    /**
     * 1、推送消息给刚刚建立连接的用户
     * 推送消息给指定用户
     * @param userId 用户ID
     * @param message 消息
     * @return 返回
     */
    @GetMapping("/push/{userId}/{message}")
    public ResponseEntity<String> push(@PathVariable(name = "userId") String userId,@PathVariable(name = "message") String message) {
        SseEmitterServer.sendMessage(userId, message);
        return ResponseEntity.ok("SseEmitter 推送消息给指定用户");
    }

}
