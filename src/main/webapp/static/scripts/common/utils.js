/**
 * Created by shenyoujian on 2018/1/30.
 */
/**
 * @author syj
 *
 */

function extend(o, p, t) {
    for (var i in p) {
        o[i] = p[i];
    }
    for (var i in t) {
        o[i] = t[i];
    }
    return o;
}

function apply(o, p) {
    for (var i in p) {
        o[i] = p[i];
    }
    return o;
}

function applyHave() {
    for (var i in o) {
        if (p[i] != undefined)
            o[i] = p[i];
    }
    return o;
}

function formatTime(date) {
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate()

    var hour = date.getHours()
    var minute = date.getMinutes()
    var second = date.getSeconds()

    return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

function formatNumber(n) {
    n = n.toString()
    return n[1] ? n : '0' + n
}

/**
 * 时间戳转时间格式
 * @param {number} 时间戳13位
 * @return {string}
 * @example
 * formatTimestamp(1489047777000)=>'2017-03-09 16:22:57'
 * */
function formatTimestamp(timestamp, fmt) {
    if (timestamp) {
        var date = new Date(timestamp - 0);
    } else {
        var date = new Date();
    }
    var Y = date.getFullYear(),
        m = date.getMonth() + 1,
        d = date.getDate(),
        H = date.getHours(),
        i = date.getMinutes(),
        s = date.getSeconds(),
        w = date.getDay();
    if (m < 10) {
        m = '0' + m;
    }
    if (d < 10) {
        d = '0' + d;
    }
    if (H < 10) {
        H = '0' + H;
    }
    if (i < 10) {
        i = '0' + i;
    }
    if (s < 10) {
        s = '0' + s;
    }
    switch (w) {
        case 0:
            w = "周日";
            break;
        case 1:
            w = "周一";
            break;
        case 2:
            w = "周二";
            break;
        case 3:
            w = "周三";
            break;
        case 4:
            w = "周四";
            break;
        case 5:
            w = "周五";
            break;
        case 6:
            w = "周六";
            break;
        default:
            w = "啥也不是";
            break;

    }
    var t = fmt || "yyyy-MM-dd hh:mm:ss";
    t = t.replace('yyyy', Y);
    t = t.replace('MM', m);
    t = t.replace('dd', d);
    t = t.replace('hh', H);
    t = t.replace('mm', i);
    t = t.replace('ss', s);
    t = t.replace('ww', w);
    return t;
}


/**
 * 时间格式转时间戳
 */
function transdate(time) {
    var date = new Date();
    date.setFullYear(time.substring(0, 4));
    date.setMonth(time.substring(5, 7) - 1);
    date.setDate(time.substring(8, 10));
    date.setHours(time.substring(11, 13));
    date.setMinutes(time.substring(14, 16));
    date.setSeconds(time.substring(17, 19));
    return Date.parse(date) / 1000 + '000' - 0;
}

/**
 * 对象转url参数
 * @param opt
 * @returns {string}
 */
function parseUrlParams(opt) {
    var params = '';
    for (var i in opt) {
        if (typeof opt[i] != 'object')
            params += i + '=' + opt[i] + '&';
    }
    return params.substr(0, params.length - 1);
}

/**
 * 获取url参数
 * @param name
 * @returns {null}
 * @constructor
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

Date.prototype.format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function updateModel(e) {
    window.pageData[$(e.target).attr("name")] = $(e.target).text() || $(e.target).val();
}

var loadNum = 0;

function ajax(opt) {
    $.ajax({
        url: opt.url || "",
        type: opt.type || "GET",
        data: opt.data || {},
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (status) {
            var result = JSON.parse(status);
            if (result.success) {
                opt.success && opt.success(result.data);
            } else {
                opt.fail && opt.fail(result.msg);
            }
        },
        error: function (res) {
            opt.error && opt.error(res);
        },
        complete: function () {
            loadNum--;
            opt.complete && opt.complete.call();
        }
    });
}

function getFormData(dom) {
    var formItem = $("*[name]", dom || null), values = {};
    for (var i = 0, item; i < formItem.length; i++) {
        item = formItem.eq(i);
        if (item.attr("name")) {
            switch (item[0].tagName.toLocaleLowerCase()) {
                case "select":
                case "input":
                case "textarea":
                case "select":
                    console.log(item.attr("name"), item.val());
                    values[item.attr("name")] = item.val();
                    break;
                case "div":
                    if (item.attr("value") !== undefined) {
                        values[item.attr("name")] = item.attr("value");
                    } else {
                        values[item.attr("name")] = item.text();
                    }
                    break;
                default:
                    values[item.attr("name")] = item.text();
                    break;
            }
        }
    }
    return values;
}

function setDataInForm(data) {
    for (var i in data) {
        $("*[name='" + i + "']").val(data[i]).text(data[i]);
    }
}

var timeZoneData = {
    "157": [{
        "id": 402,
        "name": "Pacific\/Niue",
        "name_with_space": "Pacific \/ Niue",
        "offset": -11
    }],
    "6": [{
        "id": 405,
        "name": "Pacific\/Pago_Pago",
        "name_with_space": "Pacific \/ Pago_Pago",
        "offset": -11
    }],
    "51": [{
        "id": 410,
        "name": "Pacific\/Rarotonga",
        "name_with_space": "Pacific \/ Rarotonga",
        "offset": -10
    }],
    "235": [{
        "id": 54,
        "name": "America\/Adak",
        "name_with_space": "America \/ Adak",
        "offset": -10
    }, {
        "id": 393,
        "name": "Pacific\/Honolulu",
        "name_with_space": "Pacific \/ Honolulu",
        "offset": -10
    }, {
        "id": 55,
        "name": "America\/Anchorage",
        "name_with_space": "America \/ Anchorage",
        "offset": -9
    }, {
        "id": 200,
        "name": "America\/Yakutat",
        "name_with_space": "America \/ Yakutat",
        "offset": -9
    }, {
        "id": 156,
        "name": "America\/Nome",
        "name_with_space": "America \/ Nome",
        "offset": -9
    }, {
        "id": 183,
        "name": "America\/Sitka",
        "name_with_space": "America \/ Sitka",
        "offset": -9
    }, {
        "id": 128,
        "name": "America\/Juneau",
        "name_with_space": "America \/ Juneau",
        "offset": -9
    }, {
        "id": 134,
        "name": "America\/Los_Angeles",
        "name_with_space": "America \/ Los_Angeles",
        "offset": -8
    }, {
        "id": 145,
        "name": "America\/Metlakatla",
        "name_with_space": "America \/ Metlakatla",
        "offset": -8
    }, {
        "id": 98,
        "name": "America\/Denver",
        "name_with_space": "America \/ Denver",
        "offset": -7
    }, {
        "id": 182,
        "name": "America\/Shiprock",
        "name_with_space": "America \/ Shiprock",
        "offset": -7
    }, {
        "id": 82,
        "name": "America\/Boise",
        "name_with_space": "America \/ Boise",
        "offset": -7
    }, {
        "id": 165,
        "name": "America\/Phoenix",
        "name_with_space": "America \/ Phoenix",
        "offset": -7
    }, {
        "id": 121,
        "name": "America\/Indiana\/Tell_City",
        "name_with_space": "America \/ Indiana \/ Tell_City",
        "offset": -6
    }, {
        "id": 118,
        "name": "America\/Indiana\/Knox",
        "name_with_space": "America \/ Indiana \/ Knox",
        "offset": -6
    }, {
        "id": 160,
        "name": "America\/North_Dakota\/New_Salem",
        "name_with_space": "America \/ North_Dakota \/ New_Salem",
        "offset": -6
    }, {
        "id": 143,
        "name": "America\/Menominee",
        "name_with_space": "America \/ Menominee",
        "offset": -6
    }, {
        "id": 89,
        "name": "America\/Chicago",
        "name_with_space": "America \/ Chicago",
        "offset": -6
    }, {
        "id": 159,
        "name": "America\/North_Dakota\/Center",
        "name_with_space": "America \/ North_Dakota \/ Center",
        "offset": -6
    }, {
        "id": 158,
        "name": "America\/North_Dakota\/Beulah",
        "name_with_space": "America \/ North_Dakota \/ Beulah",
        "offset": -6
    }, {
        "id": 130,
        "name": "America\/Kentucky\/Monticello",
        "name_with_space": "America \/ Kentucky \/ Monticello",
        "offset": -5
    }, {
        "id": 124,
        "name": "America\/Indiana\/Winamac",
        "name_with_space": "America \/ Indiana \/ Winamac",
        "offset": -5
    }, {
        "id": 129,
        "name": "America\/Kentucky\/Louisville",
        "name_with_space": "America \/ Kentucky \/ Louisville",
        "offset": -5
    }, {
        "id": 123,
        "name": "America\/Indiana\/Vincennes",
        "name_with_space": "America \/ Indiana \/ Vincennes",
        "offset": -5
    }, {
        "id": 120,
        "name": "America\/Indiana\/Petersburg",
        "name_with_space": "America \/ Indiana \/ Petersburg",
        "offset": -5
    }, {
        "id": 117,
        "name": "America\/Indiana\/Indianapolis",
        "name_with_space": "America \/ Indiana \/ Indianapolis",
        "offset": -5
    }, {
        "id": 154,
        "name": "America\/New_York",
        "name_with_space": "America \/ New_York",
        "offset": -5
    }, {
        "id": 122,
        "name": "America\/Indiana\/Vevay",
        "name_with_space": "America \/ Indiana \/ Vevay",
        "offset": -5
    }, {
        "id": 119,
        "name": "America\/Indiana\/Marengo",
        "name_with_space": "America \/ Indiana \/ Marengo",
        "offset": -5
    }, {
        "id": 99,
        "name": "America\/Detroit",
        "name_with_space": "America \/ Detroit",
        "offset": -5
    }],
    "77": [{
        "id": 412,
        "name": "Pacific\/Tahiti",
        "name_with_space": "Pacific \/ Tahiti",
        "offset": -10
    }, {
        "id": 399,
        "name": "Pacific\/Marquesas",
        "name_with_space": "Pacific \/ Marquesas",
        "offset": -9.5
    }, {
        "id": 390,
        "name": "Pacific\/Gambier",
        "name_with_space": "Pacific \/ Gambier",
        "offset": -9
    }],
    "172": [{
        "id": 407,
        "name": "Pacific\/Pitcairn",
        "name_with_space": "Pacific \/ Pitcairn",
        "offset": -8
    }],
    "140": [{
        "id": 194,
        "name": "America\/Tijuana",
        "name_with_space": "America \/ Tijuana",
        "offset": -8
    }, {
        "id": 176,
        "name": "America\/Santa_Isabel",
        "name_with_space": "America \/ Santa_Isabel",
        "offset": -8
    }, {
        "id": 142,
        "name": "America\/Mazatlan",
        "name_with_space": "America \/ Mazatlan",
        "offset": -7
    }, {
        "id": 90,
        "name": "America\/Chihuahua",
        "name_with_space": "America \/ Chihuahua",
        "offset": -7
    }, {
        "id": 161,
        "name": "America\/Ojinaga",
        "name_with_space": "America \/ Ojinaga",
        "offset": -7
    }, {
        "id": 116,
        "name": "America\/Hermosillo",
        "name_with_space": "America \/ Hermosillo",
        "offset": -7
    }, {
        "id": 149,
        "name": "America\/Monterrey",
        "name_with_space": "America \/ Monterrey",
        "offset": -6
    }, {
        "id": 146,
        "name": "America\/Mexico_City",
        "name_with_space": "America \/ Mexico_City",
        "offset": -6
    }, {
        "id": 141,
        "name": "America\/Matamoros",
        "name_with_space": "America \/ Matamoros",
        "offset": -6
    }, {
        "id": 85,
        "name": "America\/Cancun",
        "name_with_space": "America \/ Cancun",
        "offset": -6
    }, {
        "id": 144,
        "name": "America\/Merida",
        "name_with_space": "America \/ Merida",
        "offset": -6
    }, {
        "id": 75,
        "name": "America\/Bahia_Banderas",
        "name_with_space": "America \/ Bahia_Banderas",
        "offset": -6
    }],
    "39": [{
        "id": 197,
        "name": "America\/Vancouver",
        "name_with_space": "America \/ Vancouver",
        "offset": -8
    }, {
        "id": 96,
        "name": "America\/Dawson",
        "name_with_space": "America \/ Dawson",
        "offset": -8
    }, {
        "id": 198,
        "name": "America\/Whitehorse",
        "name_with_space": "America \/ Whitehorse",
        "offset": -8
    }, {
        "id": 101,
        "name": "America\/Edmonton",
        "name_with_space": "America \/ Edmonton",
        "offset": -7
    }, {
        "id": 92,
        "name": "America\/Creston",
        "name_with_space": "America \/ Creston",
        "offset": -7
    }, {
        "id": 201,
        "name": "America\/Yellowknife",
        "name_with_space": "America \/ Yellowknife",
        "offset": -7
    }, {
        "id": 97,
        "name": "America\/Dawson_Creek",
        "name_with_space": "America \/ Dawson_Creek",
        "offset": -7
    }, {
        "id": 83,
        "name": "America\/Cambridge_Bay",
        "name_with_space": "America \/ Cambridge_Bay",
        "offset": -7
    }, {
        "id": 125,
        "name": "America\/Inuvik",
        "name_with_space": "America \/ Inuvik",
        "offset": -7
    }, {
        "id": 174,
        "name": "America\/Resolute",
        "name_with_space": "America \/ Resolute",
        "offset": -6
    }, {
        "id": 170,
        "name": "America\/Rainy_River",
        "name_with_space": "America \/ Rainy_River",
        "offset": -6
    }, {
        "id": 190,
        "name": "America\/Swift_Current",
        "name_with_space": "America \/ Swift_Current",
        "offset": -6
    }, {
        "id": 173,
        "name": "America\/Regina",
        "name_with_space": "America \/ Regina",
        "offset": -6
    }, {
        "id": 199,
        "name": "America\/Winnipeg",
        "name_with_space": "America \/ Winnipeg",
        "offset": -6
    }, {
        "id": 171,
        "name": "America\/Rankin_Inlet",
        "name_with_space": "America \/ Rankin_Inlet",
        "offset": -6
    }, {
        "id": 193,
        "name": "America\/Thunder_Bay",
        "name_with_space": "America \/ Thunder_Bay",
        "offset": -5
    }, {
        "id": 73,
        "name": "America\/Atikokan",
        "name_with_space": "America \/ Atikokan",
        "offset": -5
    }, {
        "id": 163,
        "name": "America\/Pangnirtung",
        "name_with_space": "America \/ Pangnirtung",
        "offset": -5
    }, {
        "id": 126,
        "name": "America\/Iqaluit",
        "name_with_space": "America \/ Iqaluit",
        "offset": -5
    }, {
        "id": 195,
        "name": "America\/Toronto",
        "name_with_space": "America \/ Toronto",
        "offset": -5
    }, {
        "id": 155,
        "name": "America\/Nipigon",
        "name_with_space": "America \/ Nipigon",
        "offset": -5
    }, {
        "id": 151,
        "name": "America\/Montreal",
        "name_with_space": "America \/ Montreal",
        "offset": -5
    }, {
        "id": 148,
        "name": "America\/Moncton",
        "name_with_space": "America \/ Moncton",
        "offset": -4
    }, {
        "id": 114,
        "name": "America\/Halifax",
        "name_with_space": "America \/ Halifax",
        "offset": -4
    }, {
        "id": 107,
        "name": "America\/Goose_Bay",
        "name_with_space": "America \/ Goose_Bay",
        "offset": -4
    }, {
        "id": 105,
        "name": "America\/Glace_Bay",
        "name_with_space": "America \/ Glace_Bay",
        "offset": -4
    }, {
        "id": 79,
        "name": "America\/Blanc-Sablon",
        "name_with_space": "America \/ Blanc-Sablon",
        "offset": -4
    }, {
        "id": 185,
        "name": "America\/St_Johns",
        "name_with_space": "America \/ St_Johns",
        "offset": -3.5
    }],
    "63": [{
        "id": 389,
        "name": "Pacific\/Galapagos",
        "name_with_space": "Pacific \/ Galapagos",
        "offset": -6
    }, {
        "id": 112,
        "name": "America\/Guayaquil",
        "name_with_space": "America \/ Guayaquil",
        "offset": -5
    }],
    "154": [{
        "id": 137,
        "name": "America\/Managua",
        "name_with_space": "America \/ Managua",
        "offset": -6
    }],
    "65": [{
        "id": 103,
        "name": "America\/El_Salvador",
        "name_with_space": "America \/ El_Salvador",
        "offset": -6
    }],
    "52": [{
        "id": 91,
        "name": "America\/Costa_Rica",
        "name_with_space": "America \/ Costa_Rica",
        "offset": -6
    }],
    "95": [{
        "id": 191,
        "name": "America\/Tegucigalpa",
        "name_with_space": "America \/ Tegucigalpa",
        "offset": -6
    }],
    "23": [{
        "id": 78,
        "name": "America\/Belize",
        "name_with_space": "America \/ Belize",
        "offset": -6
    }],
    "89": [{
        "id": 111,
        "name": "America\/Guatemala",
        "name_with_space": "America \/ Guatemala",
        "offset": -6
    }],
    "54": [{
        "id": 115,
        "name": "America\/Havana",
        "name_with_space": "America \/ Havana",
        "offset": -5
    }],
    "94": [{
        "id": 166,
        "name": "America\/Port-au-Prince",
        "name_with_space": "America \/ Port-au-Prince",
        "offset": -5
    }],
    "229": [{
        "id": 108,
        "name": "America\/Grand_Turk",
        "name_with_space": "America \/ Grand_Turk",
        "offset": -5
    }],
    "108": [{
        "id": 127,
        "name": "America\/Jamaica",
        "name_with_space": "America \/ Jamaica",
        "offset": -5
    }],
    "45": [{
        "id": 383,
        "name": "Pacific\/Easter",
        "name_with_space": "Pacific \/ Easter",
        "offset": -5
    }, {
        "id": 178,
        "name": "America\/Santiago",
        "name_with_space": "America \/ Santiago",
        "offset": -3
    }],
    "49": [{
        "id": 81,
        "name": "America\/Bogota",
        "name_with_space": "America \/ Bogota",
        "offset": -5
    }],
    "170": [{
        "id": 133,
        "name": "America\/Lima",
        "name_with_space": "America \/ Lima",
        "offset": -5
    }],
    "167": [{
        "id": 162,
        "name": "America\/Panama",
        "name_with_space": "America \/ Panama",
        "offset": -5
    }],
    "42": [{
        "id": 88,
        "name": "America\/Cayman",
        "name_with_space": "America \/ Cayman",
        "offset": -5
    }],
    "17": [{
        "id": 153,
        "name": "America\/Nassau",
        "name_with_space": "America \/ Nassau",
        "offset": -5
    }],
    "241": [{
        "id": 86,
        "name": "America\/Caracas",
        "name_with_space": "America \/ Caracas",
        "offset": -4.5
    }],
    "30": [{
        "id": 175,
        "name": "America\/Rio_Branco",
        "name_with_space": "America \/ Rio_Branco",
        "offset": -4
    }, {
        "id": 138,
        "name": "America\/Manaus",
        "name_with_space": "America \/ Manaus",
        "offset": -4
    }, {
        "id": 102,
        "name": "America\/Eirunepe",
        "name_with_space": "America \/ Eirunepe",
        "offset": -4
    }, {
        "id": 80,
        "name": "America\/Boa_Vista",
        "name_with_space": "America \/ Boa_Vista",
        "offset": -4
    }, {
        "id": 168,
        "name": "America\/Porto_Velho",
        "name_with_space": "America \/ Porto_Velho",
        "offset": -4
    }, {
        "id": 104,
        "name": "America\/Fortaleza",
        "name_with_space": "America \/ Fortaleza",
        "offset": -3
    }, {
        "id": 84,
        "name": "America\/Campo_Grande",
        "name_with_space": "America \/ Campo_Grande",
        "offset": -3
    }, {
        "id": 172,
        "name": "America\/Recife",
        "name_with_space": "America \/ Recife",
        "offset": -3
    }, {
        "id": 58,
        "name": "America\/Araguaina",
        "name_with_space": "America \/ Araguaina",
        "offset": -3
    }, {
        "id": 177,
        "name": "America\/Santarem",
        "name_with_space": "America \/ Santarem",
        "offset": -3
    }, {
        "id": 136,
        "name": "America\/Maceio",
        "name_with_space": "America \/ Maceio",
        "offset": -3
    }, {
        "id": 93,
        "name": "America\/Cuiaba",
        "name_with_space": "America \/ Cuiaba",
        "offset": -3
    }, {
        "id": 77,
        "name": "America\/Belem",
        "name_with_space": "America \/ Belem",
        "offset": -3
    }, {
        "id": 157,
        "name": "America\/Noronha",
        "name_with_space": "America \/ Noronha",
        "offset": -2
    }, {
        "id": 74,
        "name": "America\/Bahia",
        "name_with_space": "America \/ Bahia",
        "offset": -2
    }, {
        "id": 180,
        "name": "America\/Sao_Paulo",
        "name_with_space": "America \/ Sao_Paulo",
        "offset": -2
    }],
    "186": [{
        "id": 139,
        "name": "America\/Marigot",
        "name_with_space": "America \/ Marigot",
        "offset": -4
    }],
    "236": [{
        "id": 188,
        "name": "America\/St_Thomas",
        "name_with_space": "America \/ St_Thomas",
        "offset": -4
    }],
    "25": [{
        "id": 292,
        "name": "Atlantic\/Bermuda",
        "name_with_space": "Atlantic \/ Bermuda",
        "offset": -4
    }],
    "10": [{
        "id": 57,
        "name": "America\/Antigua",
        "name_with_space": "America \/ Antigua",
        "offset": -4
    }],
    "41": [{
        "id": 131,
        "name": "America\/Kralendijk",
        "name_with_space": "America \/ Kralendijk",
        "offset": -4
    }],
    "175": [{
        "id": 169,
        "name": "America\/Puerto_Rico",
        "name_with_space": "America \/ Puerto_Rico",
        "offset": -4
    }],
    "85": [{
        "id": 192,
        "name": "America\/Thule",
        "name_with_space": "America \/ Thule",
        "offset": -4
    }, {
        "id": 106,
        "name": "America\/Godthab",
        "name_with_space": "America \/ Godthab",
        "offset": -3
    }, {
        "id": 181,
        "name": "America\/Scoresbysund",
        "name_with_space": "America \/ Scoresbysund",
        "offset": -1
    }, {
        "id": 95,
        "name": "America\/Danmarkshavn",
        "name_with_space": "America \/ Danmarkshavn",
        "offset": 0
    }],
    "61": [{
        "id": 100,
        "name": "America\/Dominica",
        "name_with_space": "America \/ Dominica",
        "offset": -4
    }],
    "136": [{
        "id": 140,
        "name": "America\/Martinique",
        "name_with_space": "America \/ Martinique",
        "offset": -4
    }],
    "188": [{
        "id": 189,
        "name": "America\/St_Vincent",
        "name_with_space": "America \/ St_Vincent",
        "offset": -4
    }],
    "185": [{
        "id": 187,
        "name": "America\/St_Lucia",
        "name_with_space": "America \/ St_Lucia",
        "offset": -4
    }],
    "182": [{
        "id": 184,
        "name": "America\/St_Barthelemy",
        "name_with_space": "America \/ St_Barthelemy",
        "offset": -4
    }],
    "13": [{
        "id": 71,
        "name": "America\/Aruba",
        "name_with_space": "America \/ Aruba",
        "offset": -4
    }],
    "27": [{
        "id": 132,
        "name": "America\/La_Paz",
        "name_with_space": "America \/ La_Paz",
        "offset": -4
    }],
    "93": [{
        "id": 113,
        "name": "America\/Guyana",
        "name_with_space": "America \/ Guyana",
        "offset": -4
    }],
    "9": [{
        "id": 56,
        "name": "America\/Anguilla",
        "name_with_space": "America \/ Anguilla",
        "offset": -4
    }],
    "87": [{
        "id": 110,
        "name": "America\/Guadeloupe",
        "name_with_space": "America \/ Guadeloupe",
        "offset": -4
    }],
    "225": [{
        "id": 167,
        "name": "America\/Port_of_Spain",
        "name_with_space": "America \/ Port_of_Spain",
        "offset": -4
    }],
    "184": [{
        "id": 186,
        "name": "America\/St_Kitts",
        "name_with_space": "America \/ St_Kitts",
        "offset": -4
    }],
    "55": [{
        "id": 94,
        "name": "America\/Curacao",
        "name_with_space": "America \/ Curacao",
        "offset": -4
    }],
    "145": [{
        "id": 152,
        "name": "America\/Montserrat",
        "name_with_space": "America \/ Montserrat",
        "offset": -4
    }],
    "20": [{
        "id": 76,
        "name": "America\/Barbados",
        "name_with_space": "America \/ Barbados",
        "offset": -4
    }],
    "198": [{
        "id": 135,
        "name": "America\/Lower_Princes",
        "name_with_space": "America \/ Lower_Princes",
        "offset": -4
    }],
    "86": [{
        "id": 109,
        "name": "America\/Grenada",
        "name_with_space": "America \/ Grenada",
        "offset": -4
    }],
    "62": [{
        "id": 179,
        "name": "America\/Santo_Domingo",
        "name_with_space": "America \/ Santo_Domingo",
        "offset": -4
    }],
    "31": [{
        "id": 196,
        "name": "America\/Tortola",
        "name_with_space": "America \/ Tortola",
        "offset": -4
    }],
    "76": [{
        "id": 87,
        "name": "America\/Cayenne",
        "name_with_space": "America \/ Cayenne",
        "offset": -3
    }],
    "11": [{
        "id": 70,
        "name": "America\/Argentina\/Ushuaia",
        "name_with_space": "America \/ Argentina \/ Ushuaia",
        "offset": -3
    }, {
        "id": 67,
        "name": "America\/Argentina\/San_Juan",
        "name_with_space": "America \/ Argentina \/ San_Juan",
        "offset": -3
    }, {
        "id": 64,
        "name": "America\/Argentina\/Mendoza",
        "name_with_space": "America \/ Argentina \/ Mendoza",
        "offset": -3
    }, {
        "id": 61,
        "name": "America\/Argentina\/Cordoba",
        "name_with_space": "America \/ Argentina \/ Cordoba",
        "offset": -3
    }, {
        "id": 69,
        "name": "America\/Argentina\/Tucuman",
        "name_with_space": "America \/ Argentina \/ Tucuman",
        "offset": -3
    }, {
        "id": 66,
        "name": "America\/Argentina\/Salta",
        "name_with_space": "America \/ Argentina \/ Salta",
        "offset": -3
    }, {
        "id": 63,
        "name": "America\/Argentina\/La_Rioja",
        "name_with_space": "America \/ Argentina \/ La_Rioja",
        "offset": -3
    }, {
        "id": 60,
        "name": "America\/Argentina\/Catamarca",
        "name_with_space": "America \/ Argentina \/ Catamarca",
        "offset": -3
    }, {
        "id": 68,
        "name": "America\/Argentina\/San_Luis",
        "name_with_space": "America \/ Argentina \/ San_Luis",
        "offset": -3
    }, {
        "id": 65,
        "name": "America\/Argentina\/Rio_Gallegos",
        "name_with_space": "America \/ Argentina \/ Rio_Gallegos",
        "offset": -3
    }, {
        "id": 62,
        "name": "America\/Argentina\/Jujuy",
        "name_with_space": "America \/ Argentina \/ Jujuy",
        "offset": -3
    }, {
        "id": 59,
        "name": "America\/Argentina\/Buenos_Aires",
        "name_with_space": "America \/ Argentina \/ Buenos_Aires",
        "offset": -3
    }],
    "169": [{
        "id": 72,
        "name": "America\/Asuncion",
        "name_with_space": "America \/ Asuncion",
        "offset": -3
    }],
    "210": [{
        "id": 164,
        "name": "America\/Paramaribo",
        "name_with_space": "America \/ Paramaribo",
        "offset": -3
    }],
    "187": [{
        "id": 147,
        "name": "America\/Miquelon",
        "name_with_space": "America \/ Miquelon",
        "offset": -3
    }],
    "70": [{
        "id": 300,
        "name": "Atlantic\/Stanley",
        "name_with_space": "Atlantic \/ Stanley",
        "offset": -3
    }],
    "248": [{
        "id": 298,
        "name": "Atlantic\/South_Georgia",
        "name_with_space": "Atlantic \/ South_Georgia",
        "offset": -2
    }],
    "237": [{
        "id": 150,
        "name": "America\/Montevideo",
        "name_with_space": "America \/ Montevideo",
        "offset": -2
    }],
    "174": [{
        "id": 291,
        "name": "Atlantic\/Azores",
        "name_with_space": "Atlantic \/ Azores",
        "offset": -1
    }, {
        "id": 296,
        "name": "Atlantic\/Madeira",
        "name_with_space": "Atlantic \/ Madeira",
        "offset": 0
    }, {
        "id": 333,
        "name": "Europe\/Lisbon",
        "name_with_space": "Europe \/ Lisbon",
        "offset": 0
    }],
    "40": [{
        "id": 294,
        "name": "Atlantic\/Cape_Verde",
        "name_with_space": "Atlantic \/ Cape_Verde",
        "offset": -1
    }],
    "137": [{
        "id": 47,
        "name": "Africa\/Nouakchott",
        "name_with_space": "Africa \/ Nouakchott",
        "offset": 0
    }],
    "183": [{
        "id": 299,
        "name": "Atlantic\/St_Helena",
        "name_with_space": "Atlantic \/ St_Helena",
        "offset": 0
    }],
    "103": [{
        "id": 324,
        "name": "Europe\/Dublin",
        "name_with_space": "Europe \/ Dublin",
        "offset": 0
    }],
    "244": [{
        "id": 22,
        "name": "Africa\/El_Aaiun",
        "name_with_space": "Africa \/ El_Aaiun",
        "offset": 0
    }],
    "91": [{
        "id": 17,
        "name": "Africa\/Conakry",
        "name_with_space": "Africa \/ Conakry",
        "offset": 0
    }],
    "191": [{
        "id": 50,
        "name": "Africa\/Sao_Tome",
        "name_with_space": "Africa \/ Sao_Tome",
        "offset": 0
    }],
    "82": [{
        "id": 3,
        "name": "Africa\/Accra",
        "name_with_space": "Africa \/ Accra",
        "offset": 0
    }],
    "79": [{
        "id": 9,
        "name": "Africa\/Banjul",
        "name_with_space": "Africa \/ Banjul",
        "offset": 0
    }],
    "146": [{
        "id": 15,
        "name": "Africa\/Casablanca",
        "name_with_space": "Africa \/ Casablanca",
        "offset": 0
    }],
    "71": [{
        "id": 295,
        "name": "Atlantic\/Faroe",
        "name_with_space": "Atlantic \/ Faroe",
        "offset": 0
    }],
    "90": [{
        "id": 326,
        "name": "Europe\/Guernsey",
        "name_with_space": "Europe \/ Guernsey",
        "offset": 0
    }],
    "196": [{
        "id": 23,
        "name": "Africa\/Freetown",
        "name_with_space": "Africa \/ Freetown",
        "offset": 0
    }],
    "193": [{
        "id": 18,
        "name": "Africa\/Dakar",
        "name_with_space": "Africa \/ Dakar",
        "offset": 0
    }],
    "221": [{
        "id": 34,
        "name": "Africa\/Lome",
        "name_with_space": "Africa \/ Lome",
        "offset": 0
    }],
    "110": [{
        "id": 330,
        "name": "Europe\/Jersey",
        "name_with_space": "Europe \/ Jersey",
        "offset": 0
    }],
    "133": [{
        "id": 7,
        "name": "Africa\/Bamako",
        "name_with_space": "Africa \/ Bamako",
        "offset": 0
    }],
    "107": [{
        "id": 2,
        "name": "Africa\/Abidjan",
        "name_with_space": "Africa \/ Abidjan",
        "offset": 0
    }],
    "104": [{
        "id": 328,
        "name": "Europe\/Isle_of_Man",
        "name_with_space": "Europe \/ Isle_of_Man",
        "offset": 0
    }],
    "207": [{
        "id": 293,
        "name": "Atlantic\/Canary",
        "name_with_space": "Atlantic \/ Canary",
        "offset": 0
    }, {
        "id": 16,
        "name": "Africa\/Ceuta",
        "name_with_space": "Africa \/ Ceuta",
        "offset": 1
    }, {
        "id": 337,
        "name": "Europe\/Madrid",
        "name_with_space": "Europe \/ Madrid",
        "offset": 1
    }],
    "234": [{
        "id": 335,
        "name": "Europe\/London",
        "name_with_space": "Europe \/ London",
        "offset": 0
    }],
    "98": [{
        "id": 297,
        "name": "Atlantic\/Reykjavik",
        "name_with_space": "Atlantic \/ Reykjavik",
        "offset": 0
    }],
    "122": [{
        "id": 43,
        "name": "Africa\/Monrovia",
        "name_with_space": "Africa \/ Monrovia",
        "offset": 0
    }],
    "92": [{
        "id": 10,
        "name": "Africa\/Bissau",
        "name_with_space": "Africa \/ Bissau",
        "offset": 0
    }],
    "34": [{
        "id": 48,
        "name": "Africa\/Ouagadougou",
        "name_with_space": "Africa \/ Ouagadougou",
        "offset": 0
    }],
    "155": [{
        "id": 46,
        "name": "Africa\/Niamey",
        "name_with_space": "Africa \/ Niamey",
        "offset": 1
    }],
    "59": [{
        "id": 323,
        "name": "Europe\/Copenhagen",
        "name_with_space": "Europe \/ Copenhagen",
        "offset": 1
    }],
    "83": [{
        "id": 325,
        "name": "Europe\/Gibraltar",
        "name_with_space": "Europe \/ Gibraltar",
        "offset": 1
    }],
    "57": [{
        "id": 346,
        "name": "Europe\/Prague",
        "name_with_space": "Europe \/ Prague",
        "offset": 1
    }],
    "106": [{
        "id": 348,
        "name": "Europe\/Rome",
        "name_with_space": "Europe \/ Rome",
        "offset": 1
    }],
    "214": [{
        "id": 367,
        "name": "Europe\/Zurich",
        "name_with_space": "Europe \/ Zurich",
        "offset": 1
    }],
    "211": [{
        "id": 213,
        "name": "Arctic\/Longyearbyen",
        "name_with_space": "Arctic \/ Longyearbyen",
        "offset": 1
    }],
    "144": [{
        "id": 345,
        "name": "Europe\/Podgorica",
        "name_with_space": "Europe \/ Podgorica",
        "offset": 1
    }],
    "28": [{
        "id": 351,
        "name": "Europe\/Sarajevo",
        "name_with_space": "Europe \/ Sarajevo",
        "offset": 1
    }],
    "124": [{
        "id": 359,
        "name": "Europe\/Vaduz",
        "name_with_space": "Europe \/ Vaduz",
        "offset": 1
    }],
    "97": [{
        "id": 321,
        "name": "Europe\/Budapest",
        "name_with_space": "Europe \/ Budapest",
        "offset": 1
    }],
    "44": [{
        "id": 45,
        "name": "Africa\/Ndjamena",
        "name_with_space": "Africa \/ Ndjamena",
        "offset": 1
    }],
    "22": [{
        "id": 319,
        "name": "Europe\/Brussels",
        "name_with_space": "Europe \/ Brussels",
        "offset": 1
    }],
    "200": [{
        "id": 334,
        "name": "Europe\/Ljubljana",
        "name_with_space": "Europe \/ Ljubljana",
        "offset": 1
    }],
    "66": [{
        "id": 38,
        "name": "Africa\/Malabo",
        "name_with_space": "Africa \/ Malabo",
        "offset": 1
    }],
    "226": [{
        "id": 52,
        "name": "Africa\/Tunis",
        "name_with_space": "Africa \/ Tunis",
        "offset": 1
    }],
    "7": [{
        "id": 314,
        "name": "Europe\/Andorra",
        "name_with_space": "Europe \/ Andorra",
        "offset": 1
    }],
    "194": [{
        "id": 316,
        "name": "Europe\/Belgrade",
        "name_with_space": "Europe \/ Belgrade",
        "offset": 1
    }],
    "4": [{
        "id": 357,
        "name": "Europe\/Tirane",
        "name_with_space": "Europe \/ Tirane",
        "offset": 1
    }],
    "134": [{
        "id": 338,
        "name": "Europe\/Malta",
        "name_with_space": "Europe \/ Malta",
        "offset": 1
    }],
    "249": [{
        "id": 316,
        "name": "Europe\/Belgrade",
        "name_with_space": "Europe \/ Belgrade",
        "offset": 1
    }],
    "53": [{
        "id": 365,
        "name": "Europe\/Zagreb",
        "name_with_space": "Europe \/ Zagreb",
        "offset": 1
    }],
    "213": [{
        "id": 355,
        "name": "Europe\/Stockholm",
        "name_with_space": "Europe \/ Stockholm",
        "offset": 1
    }],
    "75": [{
        "id": 344,
        "name": "Europe\/Paris",
        "name_with_space": "Europe \/ Paris",
        "offset": 1
    }],
    "240": [{
        "id": 360,
        "name": "Europe\/Vatican",
        "name_with_space": "Europe \/ Vatican",
        "offset": 1
    }],
    "126": [{
        "id": 336,
        "name": "Europe\/Luxembourg",
        "name_with_space": "Europe \/ Luxembourg",
        "offset": 1
    }],
    "142": [{
        "id": 341,
        "name": "Europe\/Monaco",
        "name_with_space": "Europe \/ Monaco",
        "offset": 1
    }],
    "24": [{
        "id": 49,
        "name": "Africa\/Porto-Novo",
        "name_with_space": "Africa \/ Porto-Novo",
        "offset": 1
    }],
    "43": [{
        "id": 8,
        "name": "Africa\/Bangui",
        "name_with_space": "Africa \/ Bangui",
        "offset": 1
    }],
    "199": [{
        "id": 318,
        "name": "Europe\/Bratislava",
        "name_with_space": "Europe \/ Bratislava",
        "offset": 1
    }],
    "38": [{
        "id": 21,
        "name": "Africa\/Douala",
        "name_with_space": "Africa \/ Douala",
        "offset": 1
    }],
    "177": [{
        "id": 12,
        "name": "Africa\/Brazzaville",
        "name_with_space": "Africa \/ Brazzaville",
        "offset": 1
    }],
    "15": [{
        "id": 361,
        "name": "Europe\/Vienna",
        "name_with_space": "Europe \/ Vienna",
        "offset": 1
    }],
    "190": [{
        "id": 350,
        "name": "Europe\/San_Marino",
        "name_with_space": "Europe \/ San_Marino",
        "offset": 1
    }],
    "156": [{
        "id": 32,
        "name": "Africa\/Lagos",
        "name_with_space": "Africa \/ Lagos",
        "offset": 1
    }],
    "58": [{
        "id": 31,
        "name": "Africa\/Kinshasa",
        "name_with_space": "Africa \/ Kinshasa",
        "offset": 1
    }, {
        "id": 36,
        "name": "Africa\/Lubumbashi",
        "name_with_space": "Africa \/ Lubumbashi",
        "offset": 2
    }],
    "81": [{
        "id": 317,
        "name": "Europe\/Berlin",
        "name_with_space": "Europe \/ Berlin",
        "offset": 1
    }],
    "151": [{
        "id": 313,
        "name": "Europe\/Amsterdam",
        "name_with_space": "Europe \/ Amsterdam",
        "offset": 1
    }],
    "78": [{
        "id": 33,
        "name": "Africa\/Libreville",
        "name_with_space": "Africa \/ Libreville",
        "offset": 1
    }],
    "128": [{
        "id": 353,
        "name": "Europe\/Skopje",
        "name_with_space": "Europe \/ Skopje",
        "offset": 1
    }],
    "173": [{
        "id": 364,
        "name": "Europe\/Warsaw",
        "name_with_space": "Europe \/ Warsaw",
        "offset": 1
    }],
    "8": [{
        "id": 35,
        "name": "Africa\/Luanda",
        "name_with_space": "Africa \/ Luanda",
        "offset": 1
    }],
    "162": [{
        "id": 343,
        "name": "Europe\/Oslo",
        "name_with_space": "Europe \/ Oslo",
        "offset": 1
    }],
    "5": [{
        "id": 5,
        "name": "Africa\/Algiers",
        "name_with_space": "Africa \/ Algiers",
        "offset": 1
    }],
    "247": [{
        "id": 25,
        "name": "Africa\/Harare",
        "name_with_space": "Africa \/ Harare",
        "offset": 2
    }],
    "130": [{
        "id": 11,
        "name": "Africa\/Blantyre",
        "name_with_space": "Africa \/ Blantyre",
        "offset": 2
    }],
    "147": [{
        "id": 39,
        "name": "Africa\/Maputo",
        "name_with_space": "Africa \/ Maputo",
        "offset": 2
    }],
    "203": [{
        "id": 26,
        "name": "Africa\/Johannesburg",
        "name_with_space": "Africa \/ Johannesburg",
        "offset": 2
    }],
    "121": [{
        "id": 40,
        "name": "Africa\/Maseru",
        "name_with_space": "Africa \/ Maseru",
        "offset": 2
    }],
    "232": [{
        "id": 332,
        "name": "Europe\/Kiev",
        "name_with_space": "Europe \/ Kiev",
        "offset": 2
    }, {
        "id": 366,
        "name": "Europe\/Zaporozhye",
        "name_with_space": "Europe \/ Zaporozhye",
        "offset": 2
    }, {
        "id": 358,
        "name": "Europe\/Uzhgorod",
        "name_with_space": "Europe \/ Uzhgorod",
        "offset": 2
    }],
    "166": [{
        "id": 238,
        "name": "Asia\/Hebron",
        "name_with_space": "Asia \/ Hebron",
        "offset": 2
    }, {
        "id": 236,
        "name": "Asia\/Gaza",
        "name_with_space": "Asia \/ Gaza",
        "offset": 2
    }],
    "36": [{
        "id": 13,
        "name": "Africa\/Bujumbura",
        "name_with_space": "Africa \/ Bujumbura",
        "offset": 2
    }],
    "33": [{
        "id": 354,
        "name": "Europe\/Sofia",
        "name_with_space": "Europe \/ Sofia",
        "offset": 2
    }],
    "111": [{
        "id": 216,
        "name": "Asia\/Amman",
        "name_with_space": "Asia \/ Amman",
        "offset": 2
    }],
    "56": [{
        "id": 261,
        "name": "Asia\/Nicosia",
        "name_with_space": "Asia \/ Nicosia",
        "offset": 2
    }],
    "105": [{
        "id": 245,
        "name": "Asia\/Jerusalem",
        "name_with_space": "Asia \/ Jerusalem",
        "offset": 2
    }],
    "246": [{
        "id": 37,
        "name": "Africa\/Lusaka",
        "name_with_space": "Africa \/ Lusaka",
        "offset": 2
    }],
    "180": [{
        "id": 352,
        "name": "Europe\/Simferopol",
        "name_with_space": "Europe \/ Simferopol",
        "offset": 2
    }, {
        "id": 331,
        "name": "Europe\/Kaliningrad",
        "name_with_space": "Europe \/ Kaliningrad",
        "offset": 3
    }, {
        "id": 363,
        "name": "Europe\/Volgograd",
        "name_with_space": "Europe \/ Volgograd",
        "offset": 4
    }, {
        "id": 342,
        "name": "Europe\/Moscow",
        "name_with_space": "Europe \/ Moscow",
        "offset": 4
    }, {
        "id": 349,
        "name": "Europe\/Samara",
        "name_with_space": "Europe \/ Samara",
        "offset": 4
    }, {
        "id": 289,
        "name": "Asia\/Yekaterinburg",
        "name_with_space": "Asia \/ Yekaterinburg",
        "offset": 6
    }, {
        "id": 264,
        "name": "Asia\/Omsk",
        "name_with_space": "Asia \/ Omsk",
        "offset": 7
    }, {
        "id": 263,
        "name": "Asia\/Novosibirsk",
        "name_with_space": "Asia \/ Novosibirsk",
        "offset": 7
    }, {
        "id": 262,
        "name": "Asia\/Novokuznetsk",
        "name_with_space": "Asia \/ Novokuznetsk",
        "offset": 7
    }, {
        "id": 252,
        "name": "Asia\/Krasnoyarsk",
        "name_with_space": "Asia \/ Krasnoyarsk",
        "offset": 8
    }, {
        "id": 242,
        "name": "Asia\/Irkutsk",
        "name_with_space": "Asia \/ Irkutsk",
        "offset": 9
    }, {
        "id": 288,
        "name": "Asia\/Yakutsk",
        "name_with_space": "Asia \/ Yakutsk",
        "offset": 10
    }, {
        "id": 287,
        "name": "Asia\/Vladivostok",
        "name_with_space": "Asia \/ Vladivostok",
        "offset": 11
    }, {
        "id": 273,
        "name": "Asia\/Sakhalin",
        "name_with_space": "Asia \/ Sakhalin",
        "offset": 11
    }, {
        "id": 257,
        "name": "Asia\/Magadan",
        "name_with_space": "Asia \/ Magadan",
        "offset": 12
    }, {
        "id": 217,
        "name": "Asia\/Anadyr",
        "name_with_space": "Asia \/ Anadyr",
        "offset": 12
    }, {
        "id": 247,
        "name": "Asia\/Kamchatka",
        "name_with_space": "Asia \/ Kamchatka",
        "offset": 12
    }],
    "123": [{
        "id": 51,
        "name": "Africa\/Tripoli",
        "name_with_space": "Africa \/ Tripoli",
        "offset": 2
    }],
    "120": [{
        "id": 225,
        "name": "Asia\/Beirut",
        "name_with_space": "Asia \/ Beirut",
        "offset": 2
    }],
    "68": [{
        "id": 356,
        "name": "Europe\/Tallinn",
        "name_with_space": "Europe \/ Tallinn",
        "offset": 2
    }],
    "3": [{
        "id": 339,
        "name": "Europe\/Mariehamn",
        "name_with_space": "Europe \/ Mariehamn",
        "offset": 2
    }],
    "84": [{
        "id": 315,
        "name": "Europe\/Athens",
        "name_with_space": "Europe \/ Athens",
        "offset": 2
    }],
    "215": [{
        "id": 231,
        "name": "Asia\/Damascus",
        "name_with_space": "Asia \/ Damascus",
        "offset": 2
    }],
    "181": [{
        "id": 30,
        "name": "Africa\/Kigali",
        "name_with_space": "Africa \/ Kigali",
        "offset": 2
    }],
    "148": [{
        "id": 53,
        "name": "Africa\/Windhoek",
        "name_with_space": "Africa \/ Windhoek",
        "offset": 2
    }],
    "212": [{
        "id": 41,
        "name": "Africa\/Mbabane",
        "name_with_space": "Africa \/ Mbabane",
        "offset": 2
    }],
    "74": [{
        "id": 327,
        "name": "Europe\/Helsinki",
        "name_with_space": "Europe \/ Helsinki",
        "offset": 2
    }],
    "29": [{
        "id": 24,
        "name": "Africa\/Gaborone",
        "name_with_space": "Africa \/ Gaborone",
        "offset": 2
    }],
    "125": [{
        "id": 362,
        "name": "Europe\/Vilnius",
        "name_with_space": "Europe \/ Vilnius",
        "offset": 2
    }],
    "141": [{
        "id": 322,
        "name": "Europe\/Chisinau",
        "name_with_space": "Europe \/ Chisinau",
        "offset": 2
    }],
    "119": [{
        "id": 347,
        "name": "Europe\/Riga",
        "name_with_space": "Europe \/ Riga",
        "offset": 2
    }],
    "227": [{
        "id": 329,
        "name": "Europe\/Istanbul",
        "name_with_space": "Europe \/ Istanbul",
        "offset": 2
    }],
    "179": [{
        "id": 320,
        "name": "Europe\/Bucharest",
        "name_with_space": "Europe \/ Bucharest",
        "offset": 2
    }],
    "64": [{
        "id": 14,
        "name": "Africa\/Cairo",
        "name_with_space": "Africa \/ Cairo",
        "offset": 2
    }],
    "69": [{
        "id": 4,
        "name": "Africa\/Addis_Ababa",
        "name_with_space": "Africa \/ Addis_Ababa",
        "offset": 3
    }],
    "113": [{
        "id": 44,
        "name": "Africa\/Nairobi",
        "name_with_space": "Africa \/ Nairobi",
        "offset": 3
    }],
    "139": [{
        "id": 377,
        "name": "Indian\/Mayotte",
        "name_with_space": "Indian \/ Mayotte",
        "offset": 3
    }],
    "102": [{
        "id": 221,
        "name": "Asia\/Baghdad",
        "name_with_space": "Asia \/ Baghdad",
        "offset": 3
    }],
    "50": [{
        "id": 372,
        "name": "Indian\/Comoro",
        "name_with_space": "Indian \/ Comoro",
        "offset": 3
    }],
    "129": [{
        "id": 368,
        "name": "Indian\/Antananarivo",
        "name_with_space": "Indian \/ Antananarivo",
        "offset": 3
    }],
    "206": [{
        "id": 27,
        "name": "Africa\/Juba",
        "name_with_space": "Africa \/ Juba",
        "offset": 3
    }],
    "202": [{
        "id": 42,
        "name": "Africa\/Mogadishu",
        "name_with_space": "Africa \/ Mogadishu",
        "offset": 3
    }],
    "231": [{
        "id": 28,
        "name": "Africa\/Kampala",
        "name_with_space": "Africa \/ Kampala",
        "offset": 3
    }],
    "21": [{
        "id": 340,
        "name": "Europe\/Minsk",
        "name_with_space": "Europe \/ Minsk",
        "offset": 3
    }],
    "18": [{
        "id": 222,
        "name": "Asia\/Bahrain",
        "name_with_space": "Asia \/ Bahrain",
        "offset": 3
    }],
    "60": [{
        "id": 20,
        "name": "Africa\/Djibouti",
        "name_with_space": "Africa \/ Djibouti",
        "offset": 3
    }],
    "218": [{
        "id": 19,
        "name": "Africa\/Dar_es_Salaam",
        "name_with_space": "Africa \/ Dar_es_Salaam",
        "offset": 3
    }],
    "245": [{
        "id": 214,
        "name": "Asia\/Aden",
        "name_with_space": "Asia \/ Aden",
        "offset": 3
    }],
    "209": [{
        "id": 29,
        "name": "Africa\/Khartoum",
        "name_with_space": "Africa \/ Khartoum",
        "offset": 3
    }],
    "116": [{
        "id": 255,
        "name": "Asia\/Kuwait",
        "name_with_space": "Asia \/ Kuwait",
        "offset": 3
    }],
    "67": [{
        "id": 6,
        "name": "Africa\/Asmara",
        "name_with_space": "Africa \/ Asmara",
        "offset": 3
    }],
    "176": [{
        "id": 269,
        "name": "Asia\/Qatar",
        "name_with_space": "Asia \/ Qatar",
        "offset": 3
    }],
    "192": [{
        "id": 272,
        "name": "Asia\/Riyadh",
        "name_with_space": "Asia \/ Riyadh",
        "offset": 3
    }],
    "101": [{
        "id": 281,
        "name": "Asia\/Tehran",
        "name_with_space": "Asia \/ Tehran",
        "offset": 3.5
    }],
    "80": [{
        "id": 280,
        "name": "Asia\/Tbilisi",
        "name_with_space": "Asia \/ Tbilisi",
        "offset": 4
    }],
    "233": [{
        "id": 234,
        "name": "Asia\/Dubai",
        "name_with_space": "Asia \/ Dubai",
        "offset": 4
    }],
    "178": [{
        "id": 378,
        "name": "Indian\/Reunion",
        "name_with_space": "Indian \/ Reunion",
        "offset": 4
    }],
    "16": [{
        "id": 223,
        "name": "Asia\/Baku",
        "name_with_space": "Asia \/ Baku",
        "offset": 4
    }],
    "163": [{
        "id": 260,
        "name": "Asia\/Muscat",
        "name_with_space": "Asia \/ Muscat",
        "offset": 4
    }],
    "138": [{
        "id": 376,
        "name": "Indian\/Mauritius",
        "name_with_space": "Indian \/ Mauritius",
        "offset": 4
    }],
    "12": [{
        "id": 290,
        "name": "Asia\/Yerevan",
        "name_with_space": "Asia \/ Yerevan",
        "offset": 4
    }],
    "195": [{
        "id": 374,
        "name": "Indian\/Mahe",
        "name_with_space": "Indian \/ Mahe",
        "offset": 4
    }],
    "2": [{
        "id": 246,
        "name": "Asia\/Kabul",
        "name_with_space": "Asia \/ Kabul",
        "offset": 4.5
    }],
    "217": [{
        "id": 235,
        "name": "Asia\/Dushanbe",
        "name_with_space": "Asia \/ Dushanbe",
        "offset": 5
    }],
    "132": [{
        "id": 375,
        "name": "Indian\/Maldives",
        "name_with_space": "Indian \/ Maldives",
        "offset": 5
    }],
    "238": [{
        "id": 279,
        "name": "Asia\/Tashkent",
        "name_with_space": "Asia \/ Tashkent",
        "offset": 5
    }, {
        "id": 274,
        "name": "Asia\/Samarkand",
        "name_with_space": "Asia \/ Samarkand",
        "offset": 5
    }],
    "164": [{
        "id": 248,
        "name": "Asia\/Karachi",
        "name_with_space": "Asia \/ Karachi",
        "offset": 5
    }],
    "112": [{
        "id": 219,
        "name": "Asia\/Aqtobe",
        "name_with_space": "Asia \/ Aqtobe",
        "offset": 5
    }, {
        "id": 218,
        "name": "Asia\/Aqtau",
        "name_with_space": "Asia \/ Aqtau",
        "offset": 5
    }, {
        "id": 265,
        "name": "Asia\/Oral",
        "name_with_space": "Asia \/ Oral",
        "offset": 5
    }, {
        "id": 215,
        "name": "Asia\/Almaty",
        "name_with_space": "Asia \/ Almaty",
        "offset": 6
    }, {
        "id": 270,
        "name": "Asia\/Qyzylorda",
        "name_with_space": "Asia \/ Qyzylorda",
        "offset": 6
    }],
    "228": [{
        "id": 220,
        "name": "Asia\/Ashgabat",
        "name_with_space": "Asia \/ Ashgabat",
        "offset": 5
    }],
    "208": [{
        "id": 230,
        "name": "Asia\/Colombo",
        "name_with_space": "Asia \/ Colombo",
        "offset": 5.5
    }],
    "99": [{
        "id": 251,
        "name": "Asia\/Kolkata",
        "name_with_space": "Asia \/ Kolkata",
        "offset": 5.5
    }],
    "150": [{
        "id": 250,
        "name": "Asia\/Kathmandu",
        "name_with_space": "Asia \/ Kathmandu",
        "offset": 5.75
    }],
    "19": [{
        "id": 232,
        "name": "Asia\/Dhaka",
        "name_with_space": "Asia \/ Dhaka",
        "offset": 6
    }],
    "117": [{
        "id": 226,
        "name": "Asia\/Bishkek",
        "name_with_space": "Asia \/ Bishkek",
        "offset": 6
    }],
    "26": [{
        "id": 282,
        "name": "Asia\/Thimphu",
        "name_with_space": "Asia \/ Thimphu",
        "offset": 6
    }],
    "48": [{
        "id": 371,
        "name": "Indian\/Cocos",
        "name_with_space": "Indian \/ Cocos",
        "offset": 6.5
    }],
    "35": [{
        "id": 271,
        "name": "Asia\/Rangoon",
        "name_with_space": "Asia \/ Rangoon",
        "offset": 6.5
    }],
    "100": [{
        "id": 267,
        "name": "Asia\/Pontianak",
        "name_with_space": "Asia \/ Pontianak",
        "offset": 7
    }, {
        "id": 243,
        "name": "Asia\/Jakarta",
        "name_with_space": "Asia \/ Jakarta",
        "offset": 7
    }, {
        "id": 258,
        "name": "Asia\/Makassar",
        "name_with_space": "Asia \/ Makassar",
        "offset": 8
    }, {
        "id": 244,
        "name": "Asia\/Jayapura",
        "name_with_space": "Asia \/ Jayapura",
        "offset": 9
    }],
    "118": [{
        "id": 286,
        "name": "Asia\/Vientiane",
        "name_with_space": "Asia \/ Vientiane",
        "offset": 7
    }],
    "219": [{
        "id": 224,
        "name": "Asia\/Bangkok",
        "name_with_space": "Asia \/ Bangkok",
        "offset": 7
    }],
    "47": [{
        "id": 370,
        "name": "Indian\/Christmas",
        "name_with_space": "Indian \/ Christmas",
        "offset": 7
    }],
    "242": [{
        "id": 239,
        "name": "Asia\/Ho_Chi_Minh",
        "name_with_space": "Asia \/ Ho_Chi_Minh",
        "offset": 7
    }],
    "143": [{
        "id": 241,
        "name": "Asia\/Hovd",
        "name_with_space": "Asia \/ Hovd",
        "offset": 7
    }, {
        "id": 228,
        "name": "Asia\/Choibalsan",
        "name_with_space": "Asia \/ Choibalsan",
        "offset": 8
    }, {
        "id": 284,
        "name": "Asia\/Ulaanbaatar",
        "name_with_space": "Asia \/ Ulaanbaatar",
        "offset": 8
    }],
    "37": [{
        "id": 266,
        "name": "Asia\/Phnom_Penh",
        "name_with_space": "Asia \/ Phnom_Penh",
        "offset": 7
    }],
    "127": [{
        "id": 256,
        "name": "Asia\/Macau",
        "name_with_space": "Asia \/ Macau",
        "offset": 8
    }],
    "46": [{
        "id": 229,
        "name": "Asia\/Chongqing",
        "name_with_space": "Asia \/ Chongqing",
        "offset": 8
    }, {
        "id": 285,
        "name": "Asia\/Urumqi",
        "name_with_space": "Asia \/ Urumqi",
        "offset": 8
    }, {
        "id": 237,
        "name": "Asia\/Harbin",
        "name_with_space": "Asia \/ Harbin",
        "offset": 8
    }, {
        "id": 249,
        "name": "Asia\/Kashgar",
        "name_with_space": "Asia \/ Kashgar",
        "offset": 8
    }, {
        "id": 276,
        "name": "Asia\/Shanghai",
        "name_with_space": "Asia \/ Shanghai",
        "offset": 8
    }],
    "197": [{
        "id": 277,
        "name": "Asia\/Singapore",
        "name_with_space": "Asia \/ Singapore",
        "offset": 8
    }],
    "14": [{
        "id": 311,
        "name": "Australia\/Perth",
        "name_with_space": "Australia \/ Perth",
        "offset": 8
    }, {
        "id": 306,
        "name": "Australia\/Eucla",
        "name_with_space": "Australia \/ Eucla",
        "offset": 8.75
    }, {
        "id": 305,
        "name": "Australia\/Darwin",
        "name_with_space": "Australia \/ Darwin",
        "offset": 9.5
    }, {
        "id": 308,
        "name": "Australia\/Lindeman",
        "name_with_space": "Australia \/ Lindeman",
        "offset": 10
    }, {
        "id": 302,
        "name": "Australia\/Brisbane",
        "name_with_space": "Australia \/ Brisbane",
        "offset": 10
    }, {
        "id": 303,
        "name": "Australia\/Broken_Hill",
        "name_with_space": "Australia \/ Broken_Hill",
        "offset": 10.5
    }, {
        "id": 301,
        "name": "Australia\/Adelaide",
        "name_with_space": "Australia \/ Adelaide",
        "offset": 10.5
    }, {
        "id": 309,
        "name": "Australia\/Lord_Howe",
        "name_with_space": "Australia \/ Lord_Howe",
        "offset": 11
    }, {
        "id": 205,
        "name": "Antarctica\/Macquarie",
        "name_with_space": "Antarctica \/ Macquarie",
        "offset": 11
    }, {
        "id": 310,
        "name": "Australia\/Melbourne",
        "name_with_space": "Australia \/ Melbourne",
        "offset": 11
    }, {
        "id": 307,
        "name": "Australia\/Hobart",
        "name_with_space": "Australia \/ Hobart",
        "offset": 11
    }, {
        "id": 304,
        "name": "Australia\/Currie",
        "name_with_space": "Australia \/ Currie",
        "offset": 11
    }, {
        "id": 312,
        "name": "Australia\/Sydney",
        "name_with_space": "Australia \/ Sydney",
        "offset": 11
    }],
    "216": [{
        "id": 278,
        "name": "Asia\/Taipei",
        "name_with_space": "Asia \/ Taipei",
        "offset": 8
    }],
    "131": [{
        "id": 254,
        "name": "Asia\/Kuching",
        "name_with_space": "Asia \/ Kuching",
        "offset": 8
    }, {
        "id": 253,
        "name": "Asia\/Kuala_Lumpur",
        "name_with_space": "Asia \/ Kuala_Lumpur",
        "offset": 8
    }],
    "171": [{
        "id": 259,
        "name": "Asia\/Manila",
        "name_with_space": "Asia \/ Manila",
        "offset": 8
    }],
    "96": [{
        "id": 240,
        "name": "Asia\/Hong_Kong",
        "name_with_space": "Asia \/ Hong_Kong",
        "offset": 8
    }],
    "32": [{
        "id": 227,
        "name": "Asia\/Brunei",
        "name_with_space": "Asia \/ Brunei",
        "offset": 8
    }],
    "220": [{
        "id": 233,
        "name": "Asia\/Dili",
        "name_with_space": "Asia \/ Dili",
        "offset": 9
    }],
    "109": [{
        "id": 283,
        "name": "Asia\/Tokyo",
        "name_with_space": "Asia \/ Tokyo",
        "offset": 9
    }],
    "159": [{
        "id": 268,
        "name": "Asia\/Pyongyang",
        "name_with_space": "Asia \/ Pyongyang",
        "offset": 9
    }],
    "204": [{
        "id": 275,
        "name": "Asia\/Seoul",
        "name_with_space": "Asia \/ Seoul",
        "offset": 9
    }],
    "165": [{
        "id": 406,
        "name": "Pacific\/Palau",
        "name_with_space": "Pacific \/ Palau",
        "offset": 9
    }],
    "72": [{
        "id": 382,
        "name": "Pacific\/Chuuk",
        "name_with_space": "Pacific \/ Chuuk",
        "offset": 10
    }, {
        "id": 408,
        "name": "Pacific\/Pohnpei",
        "name_with_space": "Pacific \/ Pohnpei",
        "offset": 11
    }, {
        "id": 396,
        "name": "Pacific\/Kosrae",
        "name_with_space": "Pacific \/ Kosrae",
        "offset": 11
    }],
    "88": [{
        "id": 392,
        "name": "Pacific\/Guam",
        "name_with_space": "Pacific \/ Guam",
        "offset": 10
    }],
    "161": [{
        "id": 411,
        "name": "Pacific\/Saipan",
        "name_with_space": "Pacific \/ Saipan",
        "offset": 10
    }],
    "168": [{
        "id": 409,
        "name": "Pacific\/Port_Moresby",
        "name_with_space": "Pacific \/ Port_Moresby",
        "offset": 10
    }],
    "152": [{
        "id": 404,
        "name": "Pacific\/Noumea",
        "name_with_space": "Pacific \/ Noumea",
        "offset": 11
    }],
    "239": [{
        "id": 384,
        "name": "Pacific\/Efate",
        "name_with_space": "Pacific \/ Efate",
        "offset": 11
    }],
    "201": [{
        "id": 391,
        "name": "Pacific\/Guadalcanal",
        "name_with_space": "Pacific \/ Guadalcanal",
        "offset": 11
    }],
    "158": [{
        "id": 403,
        "name": "Pacific\/Norfolk",
        "name_with_space": "Pacific \/ Norfolk",
        "offset": 11.5
    }],
    "135": [{
        "id": 397,
        "name": "Pacific\/Kwajalein",
        "name_with_space": "Pacific \/ Kwajalein",
        "offset": 12
    }, {
        "id": 398,
        "name": "Pacific\/Majuro",
        "name_with_space": "Pacific \/ Majuro",
        "offset": 12
    }],
    "73": [{
        "id": 387,
        "name": "Pacific\/Fiji",
        "name_with_space": "Pacific \/ Fiji",
        "offset": 12
    }],
    "114": [{
        "id": 413,
        "name": "Pacific\/Tarawa",
        "name_with_space": "Pacific \/ Tarawa",
        "offset": 12
    }, {
        "id": 385,
        "name": "Pacific\/Enderbury",
        "name_with_space": "Pacific \/ Enderbury",
        "offset": 13
    }, {
        "id": 395,
        "name": "Pacific\/Kiritimati",
        "name_with_space": "Pacific \/ Kiritimati",
        "offset": 14
    }],
    "149": [{
        "id": 401,
        "name": "Pacific\/Nauru",
        "name_with_space": "Pacific \/ Nauru",
        "offset": 12
    }],
    "243": [{
        "id": 416,
        "name": "Pacific\/Wallis",
        "name_with_space": "Pacific \/ Wallis",
        "offset": 12
    }],
    "230": [{
        "id": 388,
        "name": "Pacific\/Funafuti",
        "name_with_space": "Pacific \/ Funafuti",
        "offset": 12
    }],
    "189": [{
        "id": 379,
        "name": "Pacific\/Apia",
        "name_with_space": "Pacific \/ Apia",
        "offset": 13
    }],
    "153": [{
        "id": 380,
        "name": "Pacific\/Auckland",
        "name_with_space": "Pacific \/ Auckland",
        "offset": 13
    }, {
        "id": 381,
        "name": "Pacific\/Chatham",
        "name_with_space": "Pacific \/ Chatham",
        "offset": 13.75
    }],
    "223": [{
        "id": 414,
        "name": "Pacific\/Tongatapu",
        "name_with_space": "Pacific \/ Tongatapu",
        "offset": 13
    }],
    "222": [{
        "id": 386,
        "name": "Pacific\/Fakaofo",
        "name_with_space": "Pacific \/ Fakaofo",
        "offset": 14
    }]
};

var TimeZone = {
    TimeZoneData: timeZoneData,
    getGMT: function () {
        var now = new Date();
        var offset = now.getTimezoneOffset() * 60000;
        var time = now.getTime();
        return utils.formatTimestamp(time + offset);
    },
    getHour: function () {
        var hour = -Math.round(new Date().getTimezoneOffset() / 60);
        return hour;
        // return Math.floor(hour / 12) ? -hour % 12 : hour % 12;
    },
    getContryFlag: function () {

    }
};

window.utils = {
    extend: extend,
    apply: apply,
    formatTime: formatTime,
    formatTimestamp: formatTimestamp,
    parseUrlParams: parseUrlParams,
    transdate: transdate,
    updateModel: updateModel,
    setDataInForm: setDataInForm,
    getFormData: getFormData,
    getUrlParam: getUrlParam,
    TimeZone: TimeZone,
    ajax: ajax,
    pageParams: {
        page: 1,
        rows: 15,
        total: 0
    }
}