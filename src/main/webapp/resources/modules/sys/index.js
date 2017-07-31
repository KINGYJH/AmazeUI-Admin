$(function () {
    $('#tabs').tabs('add', {
        title: "首页",
        content: createFrame(projectPath + "/sys/home_view/index", "home"),
        width: $('#mainPanel').width() - 10,
        height: $('#mainPanel').height() - 26
    });
});

$(function () {
    $('#menu_tree_id').tree({
        url: projectPath + "/sys/menu/user_tree_data",
        method: 'post',
        lines: true,
        onClick: function (node) {
            if (node.attributes.url === "") {
                return;
            }
            if ($('#menu_tree_id').tree('isLeaf', node.target)) {
                if (node.attributes.url.indexOf("#basepath#") !== -1) {
                    addTab(node.text, node.attributes.url
                            .replace("#basepath#", basePath),
                        "iframe" + node.id);
                } else {
                    addTab(node.text, projectPath
                        + node.attributes.url, "iframe"
                        + node.id);
                }
            }
        },
        onLoadSuccess: function (row, data) {
            $('#menu_tree_id').tree("collapseAll");
        }
    })
});

function addTab(subtitle, url, id) {
    var win = $("#" + id).attr("src");
    if (/* !$('#tabs').tabs('exists',subtitle) */win == undefined) {
        $('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url, id),
            closable: true,
            width: $('#mainPanel').width() - 10,
            height: $('#mainPanel').height() - 26
        });
    } else {
        $('#tabs').tabs('select', subtitle);
    }
    tabClose();
}

function createFrame(url, id) {
    var s = '<iframe name="mainFrame" id="' + id
        + '" scrolling="auto" frameborder="0"  src="' + url
        + '" style="width:100%;height:100%;"></iframe>';
    return s;
}

function tabClose() {
    /* 双击关闭TAB选项卡 */
    $('.tabs-inner').dblclick(function () {
        var subtitle = $(this).children("span").text();
        $('#tabs').tabs('close', subtitle);
    });

    //鼠标菜单右键
    // $('.tabs-inner').bind('contextmenu', function (e) {
    //     $('#mm').menu('show', {
    //         left: e.pageX,
    //         top: e.pageY
    //     });
    //
    //     var subtitle = $(this).children("span").text();
    //     $('#mm').data("currtab", subtitle);
    //
    //     return false;
    // });
}