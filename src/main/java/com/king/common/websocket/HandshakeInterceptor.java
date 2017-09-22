package com.king.common.websocket;

import com.king.modules.sys.user.util.UserUtil;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 握手（handshake）接口
 * Created by YJH
 * on 2017/9/22 15:01
 * 注释
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        System.out.println("Before Handshake:" + UserUtil.getUser().getAcctName());
        attributes.put("userId", UserUtil.getUser().getId());
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        System.out.println("After Handshake:" + UserUtil.getUser().getAcctName());
        super.afterHandshake(request, response, wsHandler, ex);
    }

}
