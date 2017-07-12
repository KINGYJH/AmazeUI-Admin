/**
 * Created by king on 2017/7/12.
 * validatebox 扩展
 */
$.extend($.fn.validatebox.defaults.rules, {
    //不能包含中文
    NOCHS: {
        validator: function (value) {
            return !/^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '不能包含中文字符'
    },
    //验证汉字
    CHS: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '请输入中文字符.'
    },
    //移动手机号码验证
    Mobile: {
        validator: function (value) {
            var reg = /^1[3|4|5|8|9]\d{9}$/;
            return reg.test(value);
        },
        message: '请输入正确的手机号码.'
    },
    //国内邮编验证
    ZipCode: {
        validator: function (value) {
            var reg = /^[0-9]\d{5}$/;
            return reg.test(value);
        },
        message: '请输入正确的邮编.'
    },
    //数字
    Number: {
        validator: function (value) {
            var reg = /^[0-9]*$/;
            return reg.test(value);
        },
        message: '请输入数字.'
    }
});
