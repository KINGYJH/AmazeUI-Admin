/**
 * 去掉html标签
 * @param tab
 * @returns {XML|string|void}
 */
function removeHtmlTab(tab) {
    return tab.replace(/<[^<>]+?>/g, '');//删除所有HTML标签
}

/**
 *
 * 普通字符转换成转意符
 * @param sHtml
 * @returns {XML|string|void}
 */
function html2Escape(sHtml) {
    return sHtml.replace(/[<>&"]/g, function (c) {
        return {'<': '&lt;', '>': '&gt;', '&': '&amp;', '"': '&quot;'}[c];
    });
}

/**
 * 转意符换成普通字符
 * @param str
 * @returns {XML|string|void}
 */
function escape2Html(str) {
    var arrEntities = {'lt': '<', 'gt': '>', 'nbsp': ' ', 'amp': '&', 'quot': '"'};
    return str.replace(/&(lt|gt|nbsp|amp|quot);/ig, function (all, t) {
        return arrEntities[t];
    });
}

/**
 * 转成空格
 * @param str
 * @returns {XML|string|void}
 */
function nbsp2Space(str) {
    var arrEntities = {'nbsp': ' '};
    return str.replace(/&(nbsp);/ig, function (all, t) {
        return arrEntities[t]
    })
}

/**
 * 回车转为br标签
 * @param str
 * @returns {XML|string|void}
 */
function return2Br(str) {
    return str.replace(/\r?\n/g, "<br />");
}

/**
 * 去除开头结尾换行,并将连续3次以上换行转换成2次换行
 * @param str
 * @returns {XML|string|*}
 */
function trimBr(str) {
    str = str.replace(/((\s|&nbsp;)*\r?\n){3,}/g, "\r\n\r\n");//限制最多2次换行
    str = str.replace(/^((\s|&nbsp;)*\r?\n)+/g, '');//清除开头换行
    str = str.replace(/((\s|&nbsp;)*\r?\n)+$/g, '');//清除结尾换行
    return str;
}

/**
 * 将多个连续空格合并成一个空格
 * @param str
 * @returns {XML|string|void|*}
 */
function mergeSpace(str) {
    str = str.replace(/(\s|&nbsp;)+/g, ' ');
    return str;
}