/**
 * Created by shenyoujian on 2018/1/30.
 */
/**
 * created by ZKCX on 2017/11/9
 */

(function () {

    var panel = "<div class='mask'>" +
        "<div class='pop-panel'>" +
        "<div class='panel-head'>{{=it.title}}<span class='panel-close'></span></div>" +
        "<div class='panel-content'>{{=it.content}}</div></div></div>";
    var panelTpl = doT.template(panel);

    $.fn.extend({
        showPanel: function (data) {
            var panel = $(panelTpl(data));
            $(this).append(panel);
            panel.find(".panel-close").on("click", function () {
                hide();
            });

            function hide() {
                // panel.parent().addClass("panel-fadeout");
                // setTimeout(function () {
                panel.remove();
                // }, 800);
            }

            return {
                hide: hide
            };
        }
    });


    /**
     * checkbox
     */
    $(".check-box-item >*").on("click", function () {
        var box = $(this).parent();
        var info = box.parent().find(".info");
        var index = box.parent().children(".check-box-item").index(box);
        $(".check-box-checked", box.parent()).removeClass("check-box-checked");
        box.addClass("check-box-checked");

        box.parent().parent().attr("value", $(this).text());

        if (box.attr("left")) {
            info.hide();
            info.attr("num", index);
            info.css({
                left: box.attr("left") + "px",
                bottom: box.attr("bottom") + "px"
            }).fadeIn(0);
        } else {
            info.attr("num", index);
        }
    });

    /**
     * checkbox muti
     */
    $(".muti-box-item >*").on("click", function () {
        var box = $(this).parent();
        var info = box.parent().find(".info");


        if (box.hasClass("muti-box-checked")) {
            box.removeClass("muti-box-checked")
        } else {
            box.addClass("muti-box-checked")
        }

        var all = box.parent().children(".muti-box-checked");

        var value = "";
        for (var i = 0; i < all.length; i++) {
            value += $("p", all[i]).text() + ",";
        }
        box.parent().parent().attr("value", value);
    });

    $(".form-number-choose > .add").on("click", function () {
        var parent = $(this).parent();
        var input = parent.find("input");
        var max = parent.attr("max") - 0;
        if (input.val() * 1 + 1 <= max) {
            input.val(input.val() * 1 + 1);
            input.trigger("input");
        }
    });

    $(".form-number-choose > .deduct").on("click", function () {
        var parent = $(this).parent();
        var input = parent.find("input");
        var min = parent.attr("min") - 0;
        if (input.val() - 1 >= min) {
            input.val(input.val() - 1);
            input.trigger("input");
        }
    });

    $(".form-number-choose > input").on("input propertychange", function () {
        var value = $(this).val() - 0;
        var max = $(this).parent().attr("max") - 0;
        var min = $(this).parent().attr("min") - 0;
        if (isNaN(value)) {
            $(this).siblings(".error-box").text("Use integers only.");
        } else if (value > max) {
            $(this).siblings(".error-box").text("This value should not exceed " + max + "."); //
        } else if (value < min) {
            $(this).siblings(".error-box").text("This value should be " + min + " or more.");
        } else {
            $(this).siblings(".error-box").text("");
        }

        $(this).parent().parent().css("padding-bottom", $(this).siblings(".error-box").height());
    });

})();