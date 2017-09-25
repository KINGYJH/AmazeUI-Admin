//数据字典
var dict_obj = null;
initDic();

//初始化数据字典
function initDic() {
    $.ajax({
        url: projectPath + '/sys/dictionary/get_all_data',
        type: 'post',
        async: false,
        success: function (data) {
            var obj = JSON.parse(data);
            if (obj.status === "SUCCESS") {
                dict_obj = obj.data;
            }
        }
    });
}

/**
 * 根据id获取字典值
 * @param id
 * @returns {*}
 */
function getDictValue(id) {
    if (dict_obj === null) {
        initDic();
    } else {
        for (var _index in dict_obj) {
            if (dict_obj[_index].id === id) {
                return dict_obj[_index].dataValue;
            }
        }
    }
}

/**
 * 获取显示值
 * @param key
 * @param value
 */
function getDicShowValue(key, value) {
    if (dict_obj === null) {
        initDic();
    } else {
        for (var _index in dict_obj) {
            if (dict_obj[_index].dataKey === key && dict_obj[_index].dataValue === value) {
                return dict_obj[_index].showValue;
            }
        }
    }
}

/**
 * 根据key获取字典
 * @param key
 */
function getDictList(key) {
    var dataList = [];
    for (var _index in dict_obj) {
        if (dict_obj[_index].dataKey === key) {
            dataList.push(dict_obj[_index]);
        }
    }
    return dataList;
}

$(function () {
    $('#tabs').tabs('add', {
        title: "首页",
        content: createFrame(projectPath + "/sys/home_view/index", "home"),
        width: $('#mainPanel').width() - 10,
        height: $('#mainPanel').height() - 26
    });
});

$(function () {

    //菜单
    var icon = new Array();
    $('#menu_tree_id').tree({
        url: projectPath + "/sys/menu/user_tree_data",
        method: 'post',
        lines: true,
        async: false,
        onClick: function (node) {
            if (node.attributes.url === "") {
                return;
            }
            // if ($('#menu_tree_id').tree('isLeaf', node.target)) {
            if (node.attributes.url.indexOf("monitoring") !== -1) {
                addTab(node.text, basePath + node.attributes.url,
                    "iframe" + node.id);
            } else {
                addTab(node.text, projectPath
                    + node.attributes.url, "iframe"
                    + node.id);
            }
            // }
        },
        onLoadSuccess: function (row, data) {
            $('#menu_tree_id').tree("collapseAll");

            //根据url修改图标，使用内联样式覆盖外部链接
            for (var index = 0; index < icon.length; index++) {
                if (icon[index].iconCls !== "" && typeof(icon[index].iconCls) !== "undefined") {
                    $('#' + icon[index].domId + ' .tree-icon').css("background", "url(" + icon[index].iconCls + ") no-repeat center center");
                }
            }
        },
        formatter: function (node) {
            icon.push({"domId": node.domId, "iconCls": node.iconCls});
            return node.text;
        }
    })

    //选择主题
    $('#theme-change').combobox({
        onChange: function (newValue, oldValue) {
            if (newValue) {
                changeThemeFun(newValue);
            }
        },
        onLoadSuccess: function (data) {
            $('#theme-change').combobox('select', $.cookie('easyuiThemeName'));
        }
    })

    if ($.cookie('easyuiThemeName')) {
        changeThemeFun($.cookie('easyuiThemeName'));
    }
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
    $('.tabs-inner').bind('contextmenu', function (e) {
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle = $(this).children("span").text();
        $('#mm').data("currtab", subtitle);

        return false;
    });

    // 关闭当前
    $('#mm-tabclose').click(function () {
        var currtab_title = $('#mm').data("currtab");
        $('#tabs').tabs('close', currtab_title);
    });
    // 全部关闭
    $('#mm-tabcloseall').click(function () {
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            if (t != "首页") {
                $('#tabs').tabs('close', t);
            }
        });
    });
    // 关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function () {
        var currtab_title = $('#mm').data("currtab");
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            if (t != "首页") {
                if (t != currtab_title)
                    $('#tabs').tabs('close', t);
            }
        });
    });
    // 关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function () {
        var nextall = $('.tabs-selected').nextAll();
        if (nextall.length == 0) {
            return false;
        }
        nextall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            if (t != "首页") {
                $('#tabs').tabs('close', t);
            }
        });
        return false;
    });
    // 关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function () {
        var prevall = $('.tabs-selected').prevAll();
        if (prevall.length == 0) {
            return false;
        }
        prevall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            if (t != "首页") {
                $('#tabs').tabs('close', t);
            }
        });
        return false;
    });

    // 退出
    $("#mm-exit").click(function () {
        $('#mm').menu('hide');
    });
}

/* 更换主题 */
function changeThemeFun(themeName) {
    var $easyuiTheme = $('#easyuiTheme');
    var url = $easyuiTheme.attr('href');
    var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
    $easyuiTheme.attr('href', href);

    var $iframe = $('iframe');
    if ($iframe.length > 0) {
        for (var i = 0; i < $iframe.length; i++) {
            var ifr = $iframe[i];
            $(ifr).contents().find('#easyuiTheme').attr('href', href);
        }
    }

    $.cookie('easyuiThemeName', themeName, {
        expires: 7
    });
};