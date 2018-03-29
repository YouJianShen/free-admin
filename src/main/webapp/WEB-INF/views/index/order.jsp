<%@page import="com.suit.util.JsonUtil" %>
<%@page import="com.suit.core.util.SystemUtil" %>
<%@page import="com.suit.shiro.ShiroUser" %>
<%@page import="com.suit.util.JsonUtil" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
    String time = String.valueOf(new Date().getTime());
    String userInfo = JsonUtil.getJSONSerializer(new String[]{""},
            new String[]{"salt", "password", "", "", ""},
            "yyyy-MM-dd HH:mm:ss").serialize(
            request.getAttribute("user"));
    String region;
    String country;
    try {
        region = SystemUtil.getIpRegion(request);
        country = region.split("-")[0];
    } catch (Exception e) {
        region = e.getMessage();
        country = e.getMessage();
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>dinoedu</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/html/dinoedu_web/css/country.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/html/dinoedu_web/css/country.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/html/dinoedu_web/css/order.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>/static/html/dinoedu_web/lib/jquery-ui/jquery-ui.min.css"/>
    <script type="text/javascript" src="<%=basePath%>/static/html/dinoedu_web/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/scripts/common/doT.min.js"></script>
    <script type="text/javascript">
        var _ctxPath = "<%=basePath%>";
        var userInfo = JSON.parse('<%=userInfo%>');

        $(document).ready(function () {
            $(document).bind("keypress", function (event) {
                if (event.keyCode == 8) {
                    if (event.target.tagName.toLowerCase() == "body") {
                        return false;
                    }
                }
            });
        });

    </script>
</head>
<body>
<div id='main'>
    <nav style="transform: translate(0,0);opacity: 1;position: static">
        <div class="width_control" style="padding:0 20px;">
            <img style="width: 100px" class="logo" src="<%=basePath%>/static/html/dinoedu_web/images/image004.png"
                 onclick="location.href='<%=basePath%>/'"/>
        </div>
    </nav>

    <section class="place_order">
        <div class="width_control">
            <div class="form-panel form_panel_1 active">
                <input style="display: none" name="email"/>
                <input style="display: none" name="region" value="<%=region%>"/>
                <div class="form-item">
                    <label>Type of paper</label>
                    <select name="type" class="white-input">
                        <option value="2">Admission Essay</option>
                        <option value="3">Annotated Bibliography</option>
                        <option value="4">Argumentative Essay</option>
                        <option value="18">Article (Any Type)</option>
                        <option value="5">Article Review</option>
                        <option value="28">Assignment</option>
                        <option value="6">Book/Movie Review</option>
                        <option value="7">Business Plan</option>
                        <option value="21">Capstone Project</option>
                        <option value="8">Case Study</option>
                        <option value="19">Content (Any Type)</option>
                        <option value="9">Coursework</option>
                        <option value="10">Creative Writing</option>
                        <option value="11">Critical Thinking</option>
                        <option value="22">Dissertation</option>
                        <option value="29">Dissertation chapter</option>
                        <option value="1" selected="selected">Essay (Any Type)</option>
                        <option value="23">Lab Report</option>
                        <option value="25">Math Problem</option>
                        <option value="12">Presentation or Speech</option>
                        <option value="20">Q&amp;A</option>
                        <option value="13">Research Paper</option>
                        <option value="14">Research Proposal</option>
                        <option value="27">Research Summary</option>
                        <option value="24">Scholarship Essay</option>
                        <option value="30">Speech</option>
                        <option value="26">Statistic Project</option>
                        <option value="15">Term Paper</option>
                        <option value="16">Thesis</option>
                        <option value="17">Other</option>
                    </select>
                    <input class="white-input" style="display: none;margin-top:10px" name="type_2"
                           placeholder="please specify"/>
                </div>

                <div class="form-item">
                    <label>Topic</label>
                    <input class="white-input" name="topic"/>
                </div>
                <div class="form-item">
                    <label>Select your subject</label>
                    <select class="white-input" name="subject">
                        <option value="20" selected="selected">Accounting</option>
                        <option value="14">Advertising</option>
                        <option value="81">Aeronautics</option>
                        <option value="38">African-American Studies</option>
                        <option value="68">Agricultural Studies</option>
                        <option value="61">Alternative Medicine</option>
                        <option value="39">American History</option>
                        <option value="52">American Literature</option>
                        <option value="69">Anthropology</option>
                        <option value="53">Antique Literature</option>
                        <option value="30">Application Essay</option>
                        <option value="2">Architecture</option>
                        <option value="1">Art</option>
                        <option value="54">Asian Literature</option>
                        <option value="40">Asian Studies</option>
                        <option value="70">Astronomy</option>
                        <option value="82">Aviation</option>
                        <option value="10">Biology</option>
                        <option value="11">Business</option>
                        <option value="41">Canadian Studies</option>
                        <option value="21">Case Study</option>
                        <option value="12">Chemistry</option>
                        <option value="15">Communication Strategies</option>
                        <option value="13">Communications and Media</option>
                        <option value="22">Company Analysis</option>
                        <option value="83">Computer Science</option>
                        <option value="18">Creative Writing</option>
                        <option value="48">Criminology</option>
                        <option value="3">Dance</option>
                        <option value="4">Design Analysis</option>
                        <option value="5">Drama</option>
                        <option value="23">E-Commerce</option>
                        <option value="42">East European Studies</option>
                        <option value="19">Economics</option>
                        <option value="29">Education</option>
                        <option value="31">Education Theories</option>
                        <option value="34">Engineering</option>
                        <option value="35">English</option>
                        <option value="55">English Literature</option>
                        <option value="71">Environmental Issues</option>
                        <option value="36">Ethics</option>
                        <option value="24">Finance</option>
                        <option value="72">Geography</option>
                        <option value="73">Geology</option>
                        <option value="62">Healthcare</option>
                        <option value="37">History</option>
                        <option value="43">Holocaust</option>
                        <option value="25">International Affairs/Relations</option>
                        <option value="84">Internet</option>
                        <option value="26">Investment</option>
                        <option value="85">IT Management</option>
                        <option value="16">Journalism</option>
                        <option value="44">Latin-American Studies</option>
                        <option value="47">Law</option>
                        <option value="49">Legal Issues</option>
                        <option value="50">Linguistics</option>
                        <option value="51">Literature</option>
                        <option value="27">Logistics</option>
                        <option value="57">Management</option>
                        <option value="58">Marketing</option>
                        <option value="59">Mathematics</option>
                        <option value="60">Medicine and Health</option>
                        <option value="6">Movies</option>
                        <option value="7">Music</option>
                        <option value="45">Native-American Studies</option>
                        <option value="67">Nature</option>
                        <option value="63">Nursing</option>
                        <option value="64">Nutrition</option>
                        <option value="8">Painting</option>
                        <option value="32">Pedagogy</option>
                        <option value="65">Pharmacology</option>
                        <option value="74">Philosophy</option>
                        <option value="75">Physics</option>
                        <option value="76">Political Science</option>
                        <option value="77">Psychology</option>
                        <option value="17">Public Relations</option>
                        <option value="78">Religion and Theology</option>
                        <option value="56">Shakespeare Studies</option>
                        <option value="79">Sociology</option>
                        <option value="66">Sport</option>
                        <option value="33">Teacher's Career</option>
                        <option value="80">Technology</option>
                        <option value="9">Theatre</option>
                        <option value="87">Tourism</option>
                        <option value="28">Trade</option>
                        <option value="86">Web design</option>
                        <option value="46">West European Studies</option>
                        <option value="88">Other</option>
                    </select>
                    <input class="white-input" style="display: none;margin-top:10px" name="subject_2"
                           placeholder="please specify"/>
                </div>
                <div class="form-item">
                    <label>Number of pages</label>
                    <div class="vertical-center">
                        <div class="white-input form-number-choose vertical-center" max=150 min=1>
                            <span class="deduct"></span>
                            <input style="background: transparent" value=1 name="pageNum"/>
                            <span class="add"></span>
                            <div class="error-box"></div>
                        </div>
                        <label style="margin-bottom: 0;margin-left: 8px;">(<span>275</span> words)</label>
                    </div>
                </div>
                <div class="form-item">
                    <label>Deadline</label>
                    <div class="form-group">
                        <input class="white-input" name="deadlineDate" style="width: 170px"/>
                        <select class="white-input" style="margin-left:10px;width: 114px;background-position-x:72px;"
                                name="deadlineTime">
                            <option>12am</option>
                            <option>1am</option>
                            <option>2am</option>
                            <option>3am</option>
                            <option>4am</option>
                            <option>5am</option>
                            <option>6am</option>
                            <option>7am</option>
                            <option>8am</option>
                            <option>9am</option>
                            <option>10am</option>
                            <option>11am</option>
                            <option>12pm</option>
                            <option>1pm</option>
                            <option>2pm</option>
                            <option>3pm</option>
                            <option>4pm</option>
                            <option>5pm</option>
                            <option>6pm</option>
                            <option>7pm</option>
                            <option>8pm</option>
                            <option>9pm</option>
                            <option>10pm</option>
                            <option>11pm</option>
                        </select>
                    </div>
                </div>
                <div class="text-right step_1_proceed" style="height:50px">
                    <div style="box-shadow: none;margin-bottom: 0" class="orange_button button-arrow">GO TO STEP 2/3
                    </div>
                </div>
            </div>
            <div class="form-panel form_panel_2">
                <div name="serviceType" class="form-item" value="Writing from scratch">
                    <label>Type of service</label>
                    <div class="check-box">
                        <div class="check-box-item check-box-checked">
                            <span></span>
                            <p>Writing from scratch</p>
                        </div>
                        <div class="check-box-item">
                            <span></span>
                            <p>Rewriting</p>
                        </div>
                        <div class="check-box-item">
                            <span></span>
                            <p>Editing</p>
                        </div>
                    </div>
                </div>

                <div name="quality" value="Standard" class="form-item" style="margin-top:20px;">
                    <label>Writer quality</label>
                    <div class="check-box">
                        <div class="check-box-item check-box-checked">
                            <span></span>
                            <p>Standard</p>
                        </div>
                        <div class="check-box-item">
                            <span></span>
                            <p>Premium</p>
                        </div>
                        <div class="check-box-item">
                            <span></span>
                            <p>Platinum</p>
                        </div>
                        <div class="info" num="0" style="opacity: 1">
                            <ul class="info-1">
                                <li>2年research经验硕士导师</li>
                                <li>适用于A level/IB etc</li>
                            </ul>
                            <ul class="info-2">
                                <li>3年research经验博士导师</li>
                                <li>100%保证最少成绩达到<br/>2:1(UK)<br/>Distinction(AUS)<br/>80%(CA,UK)<br/>B+(US,SG)<br/>75%(NZ)
                                </li>
                                <li>适用于本科</li>
                            </ul>
                            <ul class="info-3">
                                <li>8年research经验博士后导师</li>
                                <li>100%保证最少成绩达到<br/>1:1(UK)<br/>High Distinction(AUS)<br/>90%(CA,HK)<br/>A (US,
                                    SG)<br/>80%(NZ)
                                </li>
                                <li>适用于硕士博士、高难度以及顶尖名校的assignment</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div name="languageLevel" value="IELTS 5-6" class="form-item" style="margin-top:20px;">
                    <label>Desired language level</label>
                    <div class="check-box">
                        <div class="check-box-item check-box-checked">
                            <span></span>
                            <p>IELTS 5-6</p>
                        </div>
                        <div class="check-box-item">
                            <span></span>
                            <p>IELTS 7-8</p>
                        </div>
                        <div class="check-box-item">
                            <span></span>
                            <p>Native</p>
                        </div>
                    </div>
                </div>

                <div class="form-item" style="margin-top:32px">
                    <label>Number of cited resources</label>
                    <div class="vertical-center">
                        <div class="white-input form-number-choose vertical-center" max=80 min=0>
                            <span class="deduct"></span>
                            <input style="background: transparent" value=1 name="resources"/>
                            <span class="add"></span>
                            <div class="error-box"></div>
                        </div>
                    </div>
                </div>

                <div class="form-item">
                    <label>Format of citation</label>
                    <select name="citation" class="white-input">
                        <option value="1" selected="selected">MLA</option>
                        <option value="2">APA</option>
                        <option value="3">Chicago/Turabian</option>
                        <option value="6">Harvard</option>
                        <option value="7">Vancouver</option>
                        <option value="4">Not Applicable</option>
                        <option value="5">Other</option>
                    </select>
                    <input class="white-input" style="display: none;margin-top:10px" name="citation_2"
                           placeholder="please specify"/>
                </div>

                <div class="form-item">
                    <label>contact information（wechat）</label>
                    <input class="white-input" name="contact"
                           placeholder=""/>
                </div>

                <div class="text-right step_2_proceed" style="height:50px">
                    <div style="box-shadow: none;margin-bottom: 0" class="orange_button button-arrow">GO TO STEP 3/3
                    </div>
                </div>
            </div>
            <div class="form-panel form_panel_3">
                <div class="form-item">
                    <label>Paper instructions</label>
                    <textarea class="white-input" name="instructions" placeholder="List your instructions."></textarea>
                </div>

                <div name="instructions_2" value="Professional Turitin Report(Free)" class="form-item"
                     style="margin-top:20px;">
                    <div class="check-box">
                        <div left=290 bottom=-54 class="muti-box-item muti-box-checked">
                            <span></span>
                            <p>Professional Turitin Report(Free)</p>
                        </div>
                        <div left=186 bottom=-88 class="muti-box-item">
                            <span></span>
                            <p>Unlimited Editing</p>
                        </div>
                        <div left=186 bottom=-117 class="muti-box-item">
                            <span></span>
                            <p>Progression Check</p>
                        </div>
                        <%--<div class="info info-fixed" num="0" style="left: 290px;bottom:-54px">--%>
                        <%--<ul class="info-1">--%>
                        <%--<li>与多家数据库签订长期合作协议，每一份稿件都会经过多个数据库的检测并且提供专业版Turnitin plagiarism--%>
                        <%--report。稿件一概不会被录入不入库，避免发生提交给学校时出现100%相似度的情况。--%>
                        <%--</li>--%>
                        <%--</ul>--%>
                        <%--<ul class="info-2">--%>
                        <%--<li>提供交稿后一周内无限次修改服务，我们的导师可根据老师的回馈在24小时内提供修改以及润色</li>--%>
                        <%--</ul>--%>
                        <%--<ul class="info-3">--%>
                        <%--<li>3</li>--%>
                        <%--</ul>--%>
                        <%--</div>--%>
                    </div>

                    <div class="info-show white-input">与多家数据库签订长期合作协议，每一份稿件都会经过多个数据库的检测并且提供专业版Turnitinplagiarism
                        report。稿件一概不会被录入不入库，避免发生提交给学校时出现100%相似度的情况。
                    </div>
                </div>

                <%--<div style="padding-top: 120px"></div>--%>

                <div class="text-center submit" style="height:50px;">
                    <div id="submit" style="box-shadow: none;margin-bottom: 0;" class="orange_button button-arrow">
                        PROCEED TO BIDDING
                    </div>
                </div>
            </div>
        </div>
    </section>

    <footer class="font-color-white">
        <div class="width_control">
            <div class="footer_info">
                <div class="footer_left">
                    <h4 class="">THE SERVICE WILL BE USEFUL FOR:</h4>
                    <p>接待来访时间发了聚少离多接待来访时间发了聚少离多接待来访时间发了聚少离多接待来访时间发了聚少离多接待来访时间发了聚少离多接待来访时间发了聚少离多</p>
                </div>
                <div class="footer_right">
                    <h4 class="">WE ACCEPT:</h4>
                    <div class="link_field">

                    </div>
                </div>
            </div>

        </div>
        <div class="copyright">
            <div class="width_control" style="padding: 0 30px;">
                <p>
                    2018 © DinoEdu.com. All rights reserved<br/>
                    Privacy Policy / Terms of Use
                </p>
            </div>
        </div>
    </footer>
</div>
<script type="text/x-dot-template" id="time_zone_view">
    <div class='time-zone-choose' style='width:100%;margin:20px 0;'><span class='white-input'
                                                                          style='width:100%'>{{=it.region}}</span>
        <ul class='region-list'>
            <li class="select2-results__option select2-results__option--highlighted"
                id="select2-user_timezone_country_country-result-cy2q-2" role="treeitem" aria-selected="false"><span
                    class="flag_16x11 flag_2"></span> Afghanistan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-znmb-3" role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_3"></span> Aland Islands (Finland)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-m6rr-4" role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_4"></span> Albania
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-cmv4-5" role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_5"></span> Algeria
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-05x3-6" role="treeitem"
                aria-selected="true"><span class="flag_16x11 flag_6"></span> American Samoa (USA)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-49tf-7" role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_7"></span> Andorra
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-8v6j-8" role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_8"></span> Angola
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-k0dn-9" role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_9"></span> Anguilla (UK)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-n39w-10"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_10"></span> Antigua and Barbuda
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-h5g3-11"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_11"></span> Argentina
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-3vd8-12"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_12"></span> Armenia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-goev-13"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_13"></span> Aruba
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-o00u-14"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_14"></span> Australia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-i0r3-15"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_15"></span> Austria
            </li>deadlineTime
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ozna-16"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_16"></span> Azerbaijan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-677x-17"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_17"></span> Bahamas
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-pe3x-18"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_18"></span> Bahrain
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-kngs-19"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_19"></span> Bangladesh
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-3bii-20"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_20"></span> Barbados
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-osei-21"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_21"></span> Belarus
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-iqsh-22"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_22"></span> Belgium
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-26ij-23"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_23"></span> Belize
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ppuw-24"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_24"></span> Benin
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-9zhh-25"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_25"></span> Bermuda (UK)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-2tjt-26"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_26"></span> Bhutan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-05yi-27"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_27"></span> Bolivia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-hj34-28"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_28"></span> Bosnia and Herzegovina
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-6e1u-29"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_29"></span> Botswana
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-gdgl-30"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_30"></span> Brazil
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-7i3p-31"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_31"></span> British Virgin Islands (UK)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-5m5x-32"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_32"></span> Brunei
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-c4wb-33"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_33"></span> Bulgaria
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-m60i-34"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_34"></span> Burkina Faso
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ld1d-35"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_35"></span> Myanmar
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-tzj6-36"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_36"></span> Burundi
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-rmqm-37"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_37"></span> Cambodia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-8i3p-38"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_38"></span> Cameroon
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-htwv-39"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_39"></span> Canada
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-k08b-40"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_40"></span> Cape Verde
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-37r0-41"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_41"></span> Bonaire (Netherlands)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-khew-42"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_42"></span> Cayman Islands (UK)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-wl4k-43"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_43"></span> Central African Republic
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-iool-44"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_44"></span> Chad
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-mq8l-45"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_45"></span> Chile
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-fvsv-46"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_46"></span> China
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-o1h7-47"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_47"></span> Christmas Island (Australia)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-au4j-48"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_48"></span> Cocos (Keeling) Islands (Australia)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-yff6-49"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_49"></span> Colombia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-06dg-50"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_50"></span> Comoros
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-xojy-51"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_51"></span> Cook Islands
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-f7ry-52"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_52"></span> Costa Rica
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-p5sw-53"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_53"></span> Croatia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-rp7g-54"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_54"></span> Cuba
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ivk4-55"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_55"></span> Curacao
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-uw1p-56"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_56"></span> Cyprus
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-9l7c-57"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_57"></span> Czech Republic
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-vkjx-58"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_58"></span> Democratic Republic of the Congo
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-qz5d-59"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_59"></span> Denmark
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-xwgt-60"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_60"></span> Djibouti
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-n6o3-61"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_61"></span> Dominica
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-lm3w-62"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_62"></span> Dominican Republic
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-sn4e-63"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_63"></span> Ecuador
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-bn5g-64"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_64"></span> Egypt
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-g8tc-65"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_65"></span> El Salvador
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-eh1p-66"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_66"></span> Equatorial Guinea
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-aep1-67"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_67"></span> Eritrea
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-7nz3-68"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_68"></span> Estonia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-9eh9-69"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_69"></span> Ethiopia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-i10y-70"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_70"></span> Falkland Islands (UK)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-a4f2-71"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_71"></span> Faroe Islands
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-uqn6-72"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_72"></span> Micronesia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-t0fg-73"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_73"></span> Fiji
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-l319-74"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_74"></span> Finland
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-9jqd-75"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_75"></span> France
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-jysr-76"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_76"></span> French Guiana (France)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-pnwu-77"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_77"></span> French Polynesia (France)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-7s0d-78"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_78"></span> Gabon
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-31ub-79"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_79"></span> Gambia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-fb5v-80"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_80"></span> Georgia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-htf5-81"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_81"></span> Germany
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-68xk-82"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_82"></span> Ghana
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-dans-83"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_83"></span> Gibraltar (UK)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ips6-84"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_84"></span> Greece
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-q5sa-85"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_85"></span> Greenland
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-249i-86"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_86"></span> Grenada
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-hjms-87"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_87"></span> Guadeloupe (France)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-6ogu-88"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_88"></span> Guam (USA)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-egl4-89"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_89"></span> Guatemala
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-nij9-90"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_90"></span> Guernsey (UK)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-3fix-91"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_91"></span> Guinea
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-d2lf-92"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_92"></span> Guinea-Bissau
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-4u7z-93"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_93"></span> Guyana
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-3xn2-94"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_94"></span> Haiti
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-11a8-95"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_95"></span> Honduras
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-uo1i-96"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_96"></span> Hong Kong (China)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-uro6-97"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_97"></span> Hungary
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-8tcr-98"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_98"></span> Iceland
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-kzt5-99"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_99"></span> India
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-03ds-100"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_100"></span> Indonesia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-k6j8-101"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_101"></span> Iran
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-hu7g-102"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_102"></span> Iraq
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-kcs2-103"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_103"></span> Ireland
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-2vig-104"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_104"></span> Isle of Man (UK)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-6kcm-105"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_105"></span> Israel
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-dmzl-106"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_106"></span> Italy
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-m8e1-107"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_107"></span> Ivory Coast
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-do6w-108"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_108"></span> Jamaica
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-dq7k-109"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_109"></span> Japan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-hqlr-110"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_110"></span> Jersey (UK)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-b8c7-111"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_111"></span> Jordan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-9u5t-112"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_112"></span> Kazakhstan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-15yj-113"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_113"></span> Kenya
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-52h8-114"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_114"></span> Kiribati
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-a1ca-116"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_116"></span> Kuwait
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-kaev-117"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_117"></span> Kyrgyzstan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-n75q-118"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_118"></span> Laos
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-kbxe-119"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_119"></span> Latvia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ecmk-120"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_120"></span> Lebanon
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-5te5-121"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_121"></span> Lesotho
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-vy7q-122"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_122"></span> Liberia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-5pj1-123"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_123"></span> Libya
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-bzyk-124"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_124"></span> Liechtenstein
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-mdyl-125"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_125"></span> Lithuania
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-bsv2-126"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_126"></span> Luxembourg
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-nohv-127"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_127"></span> Macao (China)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ie2y-128"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_128"></span> Macedonia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-g263-129"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_129"></span> Madagascar
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-wu7i-130"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_130"></span> Malawi
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-6rlx-131"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_131"></span> Malaysia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-kvyl-132"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_132"></span> Maldives
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-kvkj-133"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_133"></span> Mali
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-36xe-134"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_134"></span> Malta
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-viic-135"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_135"></span> Marshall Islands
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-zolj-136"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_136"></span> Martinique (France)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-40nw-137"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_137"></span> Mauritania
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-5zdc-138"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_138"></span> Mauritius
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-vxwq-139"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_139"></span> Mayotte (France)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-hr5k-140"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_140"></span> Mexico
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-snxf-141"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_141"></span> Moldova
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-164r-142"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_142"></span> Monaco
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-s9rt-143"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_143"></span> Mongolia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-9u0p-144"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_144"></span> Montenegro
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-erb3-145"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_145"></span> Montserrat (UK)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-px50-146"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_146"></span> Morocco
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-8x2x-147"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_147"></span> Mozambique
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-lfux-148"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_148"></span> Namibia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-a31r-149"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_149"></span> Nauru
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ddzw-150"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_150"></span> Nepal
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-sfth-151"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_151"></span> Netherlands
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-o0u8-152"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_152"></span> New Caledonia (France)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-h65u-153"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_153"></span> New Zealand
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-tdve-154"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_154"></span> Nicaragua
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-7rqy-155"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_155"></span> Niger
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-98fx-156"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_156"></span> Nigeria
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ifpy-157"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_157"></span> Niue
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-lz8x-158"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_158"></span> Norfolk Island (Australia)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-8seu-159"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_159"></span> North Korea
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-q3aw-161"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_161"></span> Northern Mariana Islands (USA)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-1nej-162"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_162"></span> Norway
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ukou-163"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_163"></span> Oman
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-p0li-164"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_164"></span> Pakistan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-wtjs-165"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_165"></span> Palau
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ce2u-166"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_166"></span> Palestine
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-cz42-167"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_167"></span> Panama
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-0ltp-168"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_168"></span> Papua New Guinea
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-f2xq-169"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_169"></span> Paraguay
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-flqy-170"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_170"></span> Peru
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-mhz7-171"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_171"></span> Philippines
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-wbay-172"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_172"></span> Pitcairn Islands (UK)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-eat1-173"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_173"></span> Poland
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-pvjt-174"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_174"></span> Portugal
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-q6pf-175"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_175"></span> Puerto Rico (USA)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-0mg0-176"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_176"></span> Qatar
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-e508-177"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_177"></span> Republic of the Congo
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-4c2y-178"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_178"></span> Reunion (France)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-013t-179"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_179"></span> Romania
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-qrqg-180"
                title="Russian Federation" role="treeitem" aria-selected="false"><span
                    class="flag_16x11 flag_180"></span>
                Russia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-s0d4-181"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_181"></span> Rwanda
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-5466-182"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_182"></span> Saint Barthelemy (France)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-36w4-183"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_183"></span> Saint Helena
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-3dfp-184"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_184"></span> Saint Kitts and Nevis
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-wiy2-185"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_185"></span> Saint Lucia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-allr-186"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_186"></span> Saint Martin (France)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-w5hn-187"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_187"></span> Saint Pierre and Miquelon (France)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-fbwj-188"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_188"></span> Saint Vincent and the Grenadines
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-4xat-189"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_189"></span> Samoa
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-p9kz-190"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_190"></span> San Marino
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-21ge-191"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_191"></span> Sao Tome and Principe
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-zd7s-192"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_192"></span> Saudi Arabia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-lvu4-193"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_193"></span> Senegal
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-m8q1-194"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_194"></span> Serbia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ri2n-195"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_195"></span> Seychelles
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ofhu-196"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_196"></span> Sierra Leone
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-pom6-197"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_197"></span> Singapore
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-8r44-198"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_198"></span> Sint Maarten
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-u457-199"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_199"></span> Slovakia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ja7k-200"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_200"></span> Slovenia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-s1w8-201"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_201"></span> Solomon Islands
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ay1m-202"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_202"></span> Somalia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-gqpr-203"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_203"></span> South Africa
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ckmk-204"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_204"></span> South Korea
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-v11f-206"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_206"></span> South Sudan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-vo8a-207"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_207"></span> Spain
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-6zh6-208"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_208"></span> Sri Lanka
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-uip0-209"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_209"></span> Sudan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-frws-210"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_210"></span> Suriname
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-wa22-211"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_211"></span> Svalbard and Jan Mayen (Norway)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-7vd3-212"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_212"></span> Swaziland
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-vyt4-213"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_213"></span> Sweden
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-sgq8-214"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_214"></span> Switzerland
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ipuw-215"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_215"></span> Syria
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-sws0-216"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_216"></span> Taiwan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-tt78-217"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_217"></span> Tajikistan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-5196-218"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_218"></span> Tanzania
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-3ae6-219"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_219"></span> Thailand
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-h1lg-220"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_220"></span> East Timor
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-msne-221"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_221"></span> Togo
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-pmbi-222"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_222"></span> Tokelau (NZ)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ewrx-223"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_223"></span> Tonga
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-77tz-225"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_225"></span> Trinidad and Tobago
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-3c84-226"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_226"></span> Tunisia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-r9b0-227"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_227"></span> Turkey
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-zqyp-228"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_228"></span> Turkmenistan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-3jft-229"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_229"></span> Turks and Caicos Islands (UK)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-uahn-230"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_230"></span> Tuvalu
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-j04m-231"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_231"></span> Uganda
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-m6d8-232"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_232"></span> Ukraine
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-smpx-233"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_233"></span> United Arab Emirates
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ato4-234"
                title="Britain, UK, Great Britain" role="treeitem" aria-selected="false"><span
                    class="flag_16x11 flag_234"></span> United Kingdom
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-12je-235" title="USA"
                role="treeitem" aria-selected="false"><span class="flag_16x11 flag_235"></span> United States
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-wxuj-236"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_236"></span> United States Virgin Islands (USA)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-wzhh-237"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_237"></span> Uruguay
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-k7nv-238"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_238"></span> Uzbekistan
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-5hs9-239"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_239"></span> Vanuatu
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-q5tu-240"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_240"></span> Vatican
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-dvea-241"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_241"></span> Venezuela
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-ar76-242"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_242"></span> Vietnam
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-k1f4-243"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_243"></span> Wallis and Futuna (France)
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-hvkj-244"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_244"></span> Western Sahara
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-kzz1-245"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_245"></span> Yemen
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-2jtu-246"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_246"></span> Zambia
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-linm-247"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_247"></span> Zimbabwe
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-3uui-248"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_248"></span> South Georgia and the South Sandwich
                Islands
            </li>
            <li class="select2-results__option" id="select2-user_timezone_country_country-result-imyc-249"
                role="treeitem"
                aria-selected="false"><span class="flag_16x11 flag_249"></span> Kosovo
            </li>
        </ul>
    </div>
    <div class='orange_button button-arrow text-center region_submit'
         style='display:block;box-shadow: none;color:#fff;margin-top:20px;'>submit
    </div>
</script>
<script type="text/javascript" src="<%=basePath%>/static/scripts/common/utils.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/scripts/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/html/dinoedu_web/lib/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript">
    (function () {
        console.log("<%=region%>");
        var pageData = {
            region: "<%=country%>",
            flag: ""
        };
        var regionTpl = doT.template(document.getElementById("time_zone_view").innerHTML);
        var scrollTop = 0;
        $(window).on("scroll", function (e) {
            scrollTop = $(window).scrollTop();
            if (scrollTop > 50) {
                $("nav").addClass("show");
            } else {
                $("nav").removeClass("show");
            }
        });

        var type = utils.getUrlParam("type");
        var email = utils.getUrlParam("email");
        if (type) {
            $("select[name='type']").val(type);
            $("input[name='email']").val(email);
        }

        $("input[name='pageNum']").on("input propertychange", function () {
            var value = $(this).val();
            $(this).parent().siblings("label").children("span").text(275 * value);
        });

        $("input[name='deadlineDate']").val(utils.formatTimestamp(new Date().getTime(), "MM/dd/yyyy"));
        $("input[name='deadlineDate']").datepicker();


        $("input[name='deadlineDate']").on("click", function () {
            $("#ui-datepicker-div").append("<div class='time_zone'><p>Current Time:</p><span class='" + pageData.flag + "'></span><p>" + utils.formatTimestamp(new Date().getTime(), "hh:mm") + "(GMT+" + utils.TimeZone.getHour() + ")</p><div id='choose_timezone'>edit</div></div>");
            $("#choose_timezone").on("click", function () {
                var panel = $("body").showPanel({
                    title: "Select your Timezone",
                    content: regionTpl(pageData)
                });

                $(".time-zone-choose span").on("click", function () {
                    $(".region-list").show();
                });
                $(".region-list > li").on("click", function () {
                    var name = $(this).text();
                    $(".time-zone-choose > span").text(name);
                    $(".time-zone-choose > span").attr("flag", $(this).children("span")[0].className);
                    $(".region-list").hide();
                });

                $(".region_submit").on("click", function () {
                    var value = $(".time-zone-choose span").text();
                    $("input[name='region']").val(value);
                    pageData.region = value;
                    pageData.flag = $(".time-zone-choose span").attr("flag");
                    window.pageData = pageData;
                    panel.hide();
                });
            });
        });

        $(".step_1_proceed").on("click", function () {
            $(".form_panel_2").css("display", "inline-block");
            $(this).css("visibility", "hidden");
            setTimeout(function () {
                $(".form_panel_2").addClass("show");
                setTimeout(function () {
                    $(".form_panel_1").removeClass("active");
                    $(".form_panel_2").addClass("active");
                }, 1010);
            }, 100);
        });

        $(".step_2_proceed").on("click", function () {
            $(".form_panel_3").css("display", "inline-block");
            $(this).css("visibility", "hidden");
            setTimeout(function () {
                $(".form_panel_3").addClass("show");
                setTimeout(function () {
                    $(".form_panel_2").removeClass("active");
                    $(".form_panel_3").addClass("active");
                }, 500);
            }, 100);
        });

        $("select[name=subject]").change(function () {
            if ($(this).val() == 88) {
                $(this).next().show();
            } else {
                $(this).next().hide();
            }
        });

        $("select[name=type]").change(function () {

            if ($(this).val() == 17) {
                $(this).next().show();
            } else {
                $(this).next().hide();
            }
        });

        $("select[name=citation]").change(function () {
            console.log($(this).val());
            if ($(this).val() == 5) {
                $(this).next().show();
            } else {
                $(this).next().hide();
            }
        });

        var instructions_2 = [
            "与多家数据库签订长期合作协议，每一份稿件都会经过多个数据库的检测并且提供专业版Turnitinplagiarism report。稿件一概不会被录入不入库，避免发生提交给学校时出现100%相似度的情况。",
            "提供交稿后一周内无限次修改服务，导师可根据tutor的回馈在48小时内提供修改以及润色服务，次数不限",
            "See as we type! 随时随地了解稿件进度，并且可和导师实时沟通。让你心中有数，稳拿高分。"
        ];
        $("div[name='instructions_2'] .muti-box-item > * ").on("click", function () {
            var box = $(this).parent();
            var all = box.parent().children(".muti-box-item");

            var value = "";
            for (var i = 0; i < all.length; i++) {
                if ($(all[i]).hasClass("muti-box-checked")) {
                    value += instructions_2[i] + "<br/>";
                }
            }
            if (value) {
                $(".info-show").show();
                $(".info-show").html(value);
            } else {
                $(".info-show").hide();
            }

        });

        $("#submit").on("click", function () {
            submit();
        });

        function getTimeZoneData(keywords) {
            var data = utils.TimeZone.TimeZoneData, html = "";

            for (var i in data) {
                if (keywords) {
                    if (RegExp(keywords).test(data[i][0].name)) {
                        html += "<li data-num='" + i + "'>" + data[i][0].name.split("\/")[0] + "</li>";
                    }
                } else {
                    html += "<li data-num='" + i + "'>" + data[i][0].name.split("\/")[0] + "</li>";
                }
            }
            return html;
        }

        function search_add(data) {
            var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            if (data.email && data.email.match(reg)) {
                loading();
                data.deadLine = data.deadlineDate + " " + data.deadlineTime;
                if (data.subject == "88") {
                    data.subject = data.subject_2;
                }
                if (data.type == "17") {
                    data.type = data.type_2;
                }
                if (data.citation == "5") {
                    data.citation = data.citation_2;
                }
                utils.ajax({
                    url: _ctxPath + "/admin/order/add",
                    type: "post",
                    data: utils.apply({
                        email: "-------",
                        type: "-------",
                        topic: "-------",
                        pageNum: 2,
                        deadLine: "-------",
                        serviceType: "-------",
                        quality: "-------",
                        citeNum: 0,
                        citation: "-------",
                        instructions: "-------",
                        writers: "-------"
                    }, data),
                    success: function (data) {
                        alert("操作成功");
                    },
                    fail: function (msg) {
                        alert(msg);
                    },
                    complete: function () {
                        hideLoading();
                    }
                });
            } else {

                var panel = $("body").showPanel({
                    title: "Log in / Sign up",
                    content: "<p class='bold'>Please enter your email to proceed</p>" +
                    "<div class='form-item' style='margin:30px 0;position: relative'>" +
                    "<input class='white-input' name='email_submit' style='margin-top:10px;'>" +
                    "<div class=\"error-box\"></div></div>" +
                    "<div class='submit_email text-center'><div class='blue_button' id='submit_email' style='display: block'>Continue</div></div>"
                });

                $("#submit_email").on("click", function () {
                    var email = $("input[name='email_submit']").val();
                    var error = $(this).parent().prev().children(".error-box");
                    console.log(email);
                    if (email.match(reg)) {
                        $("input[name='email_submit']").removeClass("white-input-error");
                        error.text("");
                        data.email = email;
                        submit(data);
                    } else {
                        $("input[name='email_submit']").addClass("white-input-error");
                        error.text("请输入正确格式email");
                    }
                });
            }
        }

        function loading() {
            $("#submit").parent().html("<img src='<%=basePath%>/static/images/loading.png' class='loading' />");
            $(".submit_email").html("<img src='<%=basePath%>/static/images/loading.png' class='loading' />");
        }

        function hideLoading() {
            $(".submit_email").html("<div class='submit_email text-center'><div class='blue_button' id='submit_email' style='display: block'>Continue</div></div>");
            $(".submit").html("<div id=\"submit\" style=\"box-shadow: none;margin-bottom: 0;\" class=\"button-arrow orange_button\">Proceed to Bidding </div>");
        }

        function submit(data) {
            var data = data || utils.getFormData($(".place_order"));
            if (validate(data)) {
                search_add(data);
            }
        }

        function validate(data) {
            var isValid = true;
            $(".white-input-error").removeClass("white-input-error");

            console.log(data.type);
            if (data.type == "17" && data.type_2 == "") {
                $("input[name='type_2']").addClass("white-input-error");
                isValid = false;
            }
            if (data.subject == "88" && data.subject_2 == "") {
                $("input[name='subject_2']").addClass("white-input-error");
                isValid = false;
            }
            if (!data.topic) {
                $("input[name='topic']").addClass("white-input-error");
                isValid = false;
            }
            if (data.citation == "5" && data.citation_2 == "") {
                $("input[name='citation_2']").addClass("white-input-error");
                isValid = false;
            }
            if (!data.contact) {
                $("input[name='contact']").addClass("white-input-error");
                isValid = false;
            }
            return isValid;
        }

    })();
</script>
</body>
</html>