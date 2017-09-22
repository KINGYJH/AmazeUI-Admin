var ws = null;
var url = 'ws://' + window.location.host + '/websocket';

connect();

//连接
function connect() {
    ws = new WebSocket(url);

    ws.onopen = function () {
        $.messager.show({
            width: 250,
            height: 100,
            title: '系统提示',
            msg: '已连接到消息服务器.',
            timeout: 2000,
            showType: 'slide'
        });
    };
    ws.onmessage = function (event) {
        $.messager.show({
            width: 250,
            height: 100,
            title: '系统提示',
            timeout: 0,
            msg: event.data,
            showType: 'slide'
        });
    };
    ws.onclose = function (event) {
        console.log('关闭信息: connection closed.');
    };
}

