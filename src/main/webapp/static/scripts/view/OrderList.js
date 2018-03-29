/**
 * 订单页
 */
(function () {
    var viewTpl = doT.template($("#order_list_view").text());
    var listTpl = doT.template($("#order_list_item").text());
    var pagerTpl = doT.template($("#order_list_pager").text());
    window.paperTypes = [
        "Essay (Any Type)",
        "Admission Essay",
        "Annotated Bibliography",
        "Argumentative Essay",
        "Article Review",
        "Book/Movie Review",
        "Business Plan",
        "Case Study",
        "Coursework",
        "Creative Writing",
        "Critical Thinking",
        "Presentation or Speech",
        "Research Paper",
        "Research Proposal",
        "Term Paper",
        "Thesis",
        "Other",
        "Article (Any Type)",
        "Content (Any Type)",
        "Q&amp;A",
        "Capstone Project",
        "Dissertation",
        "Lab Report",
        "Scholarship Essay",
        "Math Problem",
        "Statistic Project",
        "Research Summary",
        "Assignment",
        "Dissertation chapter",
        "Speech"
    ];
    window.subjectData = ["Art", "Architecture", "Dance", "Design Analysis", "Drama", "Movies", "Music", "Painting", "Theatre", "Biology", "Business", "Chemistry", "Communications and Media", "Advertising", "Communication Strategies", "Journalism", "Public Relations", "Creative Writing", "Economics", "Accounting", "Case Study", "Company Analysis", "E-Commerce", "Finance", "International Affairs/Relations", "Investment", "Logistics", "Trade", "Education", "Application Essay", "Education Theories", "Pedagogy", "Teacher's Career", "Engineering", "English", "Ethics", "History", "African-American Studies", "American History", "Asian Studies", "Canadian Studies", "East European Studies", "Holocaust", "Latin-American Studies", "Native-American Studies", "West European Studies", "Law", "Criminology", "Legal Issues", "Linguistics", "Literature", "American Literature", "Antique Literature", "Asian Literature", "English Literature", "Shakespeare Studies", "Management", "Marketing", "Mathematics", "Medicine and Health", "Alternative Medicine", "Healthcare", "Nursing", "Nutrition", "Pharmacology", "Sport", "Nature", "Agricultural Studies", "Anthropology", "Astronomy", "Environmental Issues", "Geography", "Geology", "Philosophy", "Physics", "Political Science", "Psychology", "Religion and Theology", "Sociology", "Technology", "Aeronautics", "Aviation", "Computer Science", "Internet", "IT Management", "Web design", "Tourism", "Other"]

    window.citationData = ["MLA","APA","Chicago/Turabian","Not Applicable","Other","Harvard","Vancouver"];

    var _model = {};

    init();

    function init() {
        render();
        getList();
    }

    function render() {
        $("body").html(viewTpl(_model));
        addBaseEvent();
    }

    function search_add() {
        utils.ajax({
            url: _ctxPath + "/admin/order/add",
            type: "post",
            data: {
                email: "771643834@qq.com",
                type: "essay",
                topic: "study",
                pageNum: 2,
                deadLine: "",
                serviceType: "",
                quality: "",
                citeNum: 0,
                citation: "",
                instructions: "",
                writers: ""
            },
            success: function (data) {
                console.log(data);
            },
            fail: function (msg) {
                alert(msg);
            }
        });
    }

    // window.search_add = search_add;

    var pages = utils.apply({}, utils.pageParams);

    function search(type, rows) {
        rows = rows - 0;
        switch (type) {
            case "prev":    //上一页
                if (pages.page > 1) {
                    pages.page--;
                    getList();
                }
                break;
            case "last":    //下一页
                pages.page++;
                getList();
                break;
            case "row":
                if (!isNaN(rows)) {
                    pages.rows = rows;
                    getList();
                }
                break;
            case "keywords":
                getList();
                break;
            default:    //
                if (!isNaN(type)) {
                    pages.page = type;
                    getList();
                }
                break;
        }
        $(".pager").html(pagerTpl(pages));
    }

    window.search = search;

    function getList() {
        utils.ajax({
            url: _ctxPath + "/admin/order/list",
            type: "get",
            data: utils.apply({
                keywords: $("#keywords").val(),
                startTime: $("#start_date").val(),
                endTime: $("#end_date").val(),
                page: 1,
                rows: 15
            }, pages),
            success: function (data) {
                pages.total = data.total;
                $(".list_content").html(formList(data.rows));
                $(".pager").html(pagerTpl(pages));
            },
            fail: function (msg) {
                alert(msg);
            }
        });
    }

    function formList(data) {
        var html = "";
        for (var i = 0, item; i < data.length; i++) {
            data[i].num = i + 1;
            html += listTpl(data[i]);
        }
        return html;
    }

    function addBaseEvent() {
        laydate({
            elem: '#start_date', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus', //响应事件。如果没有传入event，则按照默认的click
            choose: function (datas) { // 选择日期完毕的回调
                //alert('您选择的日期为：'+datas);
                //search();
            }
        });
        laydate({
            elem: '#end_date', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus', //响应事件。如果没有传入event，则按照默认的click
            choose: function (datas) { // 选择日期完毕的回调
                //alert('您选择的日期为：'+datas);
                //search();
            }
        });
    }
})();