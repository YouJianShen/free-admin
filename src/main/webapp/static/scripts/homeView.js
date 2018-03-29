/**
 * 主页
 */
(function() {
	var homeView, menu, panel, message = [[], []];
	var noData = "<div class='no_data'><img src='" + _ctxPath
			+ "static/images/nodata.png'><div></div>";
	homeView = $("#main");
	var loadMask = new suit.LoadMask({
				target : homeView
			});
	suit.matchWindow($(".match_window"));
	page = new suit.SlidePage({
				target : $("#main")
			});
	suit.apply(suit.bindEvent, {
		acceptJoin : function(id) {
			var ids = id.split("-");
			dealJoin(ids[0], ids[1]);
		},
		refuseJoin : function(id) {
			dealMessage(id, "DEL");
		},
		joinin : function() {
			suit.msgBox({
						text : "<input name='dormName' placeholder='请输入要加入的宿舍名称' />",
						buttons : [{
									title : "确认",
									selected : true,
									onClick : "joinCommit"
								}, {
									title : "取消",
									onClick : "cancel"
								}]
					});
		},
		cancel : function() {
			suit.hideMsgBox();
		},
		joinCommit : function() {
			var name = $("input[name=dormName]").val();
			if (name) {
				loadMask.show();
				suit.http.ajax({
							url : _ctxPath + "/message/create",
							type : "post",
							data : {
								msgType : "JOIN_IN",
								message : name,
								createUserId : userInfo.id,
								sender : userInfo.userName
							},
							success : function(status) {
								var res = JSON.parse(status);
								if (res.success) {
									suit.alertMsg({
												msg : "请求发送成功，等待宿舍管理员审核。"
											});
									suit.hideMsgBox();
								} else {
									suit.alertMsg(res);
								}
								loadMask.hide();
							},
							failure : function() {
								var res = JSON.parse(status);
								suit.alertMsg(res);
								loadMask.hide();
							}
						});
			} else {
				suit.alertMsg({
							msg : "宿舍名称不能为空"
						});
			}
		},
		createDorm : function() {
			suit.msgBox({
						text : "<input name='dormName' placeholder='请输入要创建的宿舍名称' />",
						buttons : [{
									title : "确认",
									selected : true,
									onClick : "dormSubmit"
								}, {
									title : "取消",
									onClick : "cancel"
								}]
					});
		},
		dormSubmit : function() {
			var name = $("input[name=dormName]").val()
			if (name) {
				loadMask.show();
				suit.hideMsgBox();
				suit.http.ajax({
							url : _ctxPath + "/dorm/create",
							type : "post",
							data : {
								name : name,
								createUserId : userInfo.id
							},
							success : function(status) {
								var res = JSON.parse(status);
								if (res.success) {
									suit.alertMsg({
												msg : "创建成功"
											});
									location.href = "";
								} else {
									suit.alertMsg(res);
								}
								loadMask.hide();
							},
							failure : function() {
								var res = JSON.parse(status);
								suit.alertMsg(res);
								loadMask.hide();
							}
						});
			} else {
				suit.alertMsg({
							msg : "宿舍名称不能为空"
						});
			}
		},
		createItem : function() {
			var data = {};
			if (suit.getSubmitValue(data, "need_submit")) {
				data.belongsId = userInfo.belongsId;
				data.creator = userInfo.userName;
				data.confirm = userInfo.userType == "DORM_ADMIN";
				data.accept = false;
				data.createUserId = userInfo.id;
				data.inFlag = data.useIn == "**88aadd";
				loadMask.show();
				suit.http.ajax({
							url : _ctxPath + "/inout/create",
							type : "post",
							data : data,
							success : function(status) {
								var res = JSON.parse(status);
								if (res.success) {
									suit.alertMsg({
												msg : "创建成功"
											});
									getDormList();
								} else {
									suit.alertMsg(res);
								}
								suit.hideMsgBox();
								loadMask.hide();
							},
							failure : function() {
								var res = JSON.parse(status);
								suit.alertMsg(res);
								suit.hideMsgBox();
								loadMask.hide();
							}
						});
			}
		},
		refreshMessage : function() {
			loadMessage("PERSON", userInfo.id);
			loadMessage("DORM", userInfo.belongsId);
		},
		cancelDorm : function() {
			suit.msgBox({
						text : "确认退出当前宿舍？",
						buttons : [{
									title : "确认",
									selected : true,
									onClick : "cancelDormSub"
								}, {
									title : "取消",
									onClick : "cancel"
								}]
					});
		},
		cancelDormSub : function() {
			loadMask.show();
			suit.http.ajax({
						url : _ctxPath + "/system/user/cancel",
						type : "post",
						data : {
							partnerId : userInfo.id
						},
						success : function(status) {
							var res = JSON.parse(status);
							if (res.success) {
								suit.alertMsg({
											msg : "退出成功"
										});
								suit.hideMsgBox();
							} else {
								suit.alertMsg(res);
							}
							loadMask.hide();
						},
						failure : function() {
							var res = JSON.parse(status);
							suit.alertMsg(res);
							loadMask.hide();
						}
					});
		},
		showUpdatePassword : function() {
			suit.msgBox({
				title : "修改密码",
				text : "<div class='update_form'>"
						+ "<div><label>旧密码:</label><input data-name='旧密码' class='need_submit_3' name='old' type=password /></div>"
						+ "<div><label>新密码:</label><input data-name='新密码' class='need_submit_3' name='password' type=password /></div></div>",
				buttons : [{
							title : "确认",
							selected : true,
							onClick : "updatePassword"
						}, {
							title : "取消",
							onClick : "cancel"
						}]
			});
		},
		updatePassword : function() {
			var data = {};
			var check = suit.getSubmitValue(data, "need_submit_3");
			if (check) {
				data.account = userInfo.userAccount;
				loadMask.show();
				suit.http.ajax({
							url : _ctxPath + "/system/user/update/pass",
							type : "post",
							data : data,
							success : function(status) {
								var res = JSON.parse(status);
								if (res.success) {
									suit.hideMsgBox();
									suit.alertMsg({
												msg : "修改成功"
											});
								} else {
									suit.alertMsg(res);
								}
								loadMask.hide();
							},
							failure : function() {
								var res = JSON.parse(status);
								suit.alertMsg(res);
								loadMask.hide();
							}
						});
			}
		},
		logout : function() {
			location.href = "logout";
		}
	});
	suit.attachEvent();
	init();
	addEvent();
	FastClick.attach(document.body);
	function init() {
		var menu = getMenu();
		panelHeight = homeView.height() - menu.dom.height();
		panel = $("<div id='main_panel' style='height:" + panelHeight
				+ "px'></div>");
		homeView.append(panel);
		panel.html(getPersonInfo());
		loadMessage("PERSON", userInfo.id);
		loadMessage("DORM", userInfo.belongsId);
	}
	function getMenu() {
		return new suit.BottomMenu({
			target : $("#main"),
			menu : [{
				title : "个人<div class='message_tag hide'><div class='msg_num_person hide'>0</div></div>",
				selected : true,
				img : _ctxPath + "/static/images/main_person.png",
				imgSelected : _ctxPath
						+ "/static/images/main_person_selected.png",
				click : function() {
					panel.html(getPersonInfo());
				}
			}, {
				title : "宿舍<div class='message_tag hide'><div class='msg_num_dorm hide'>0</div></div>",
				img : _ctxPath + "/static/images/main_dorm.png",
				imgSelected : _ctxPath
						+ "/static/images/main_dorm_selected.png",
				click : function() {
					if (!userInfo.belongsId) {
						panel.html(getDormJoin());
						suit.attachEvent();
					} else {
						getDormList();
					}
				}
			}, {
				title : "管理",
				img : _ctxPath + "/static/images/main_config.png",
				imgSelected : _ctxPath
						+ "/static/images/main_config_selected.png",
				click : function() {
					panel.html(getConfigView());
					suit.attachEvent();
				}
			}]
		});
	}
	// 未加入宿舍
	function getDormJoin() {
		return "<div class='dorm_info'>"
				+ "<div class='error_message row'>你还未加入宿舍，请选择:</div>"
				+ "<div class='button' event-click='joinin'>加入宿舍</div>"
				+ "<div class='button' event-click='createDorm'>创建宿舍</div></div>";
	}
	function getDormList() {
		loadMask.show();
		suit.http.ajax({
			url : _ctxPath + "/dorm/getInfo",
			type : "get",
			data : {
				dormId : userInfo.belongsId,
				page : 1,
				rows : 1000
			},
			success : function(status) {
				var res = JSON.parse(status);
				if (res.success) {
					panel.html(getDorm(res.data));
					var menu = new suit.ButtonMenu({
						target : $("#main_panel .dorm_info_data"),
						menu : [{
									title : "支出",
									click : function(choosed) {
										suit.msgBox({
													title : "填写",
													text : getForm(true),
													buttons : [{
																title : "确认",
																selected : true,
																onClick : "createItem"
															}, {
																title : "取消",
																onClick : "cancel"
															}]
												});
									}
								}, {
									title : "交室费",
									click : function(choosed) {
										suit.msgBox({
													title : "填写",
													text : getForm(false),
													buttons : [{
																title : "确认",
																selected : true,
																onClick : "createItem"
															}, {
																title : "取消",
																onClick : "cancel"
															}]
												});
									}
								}, {
									title : "明细",
									click : function(choosed) {
										page.show("收支明细", getDetail(),
												function() {
													new suit.ListView({
														renderTo : "listview",
														url : _ctxPath
																+ "/inout/dorm/list",
														store : {
															dormId : userInfo.belongsId
														},
														itemRender : function(
																data) {
															var css;
															if (data.useIn == "**88aadd") {
																css = "income";
															} else {
																css = "outcome";
															}
															var str = data.useIn == "**88aadd"
																	? "缴纳室费"
																			+ (data.remark
																					? "("
																							+ data.remark
																							+ ")"
																					: "")
																	: data.useIn;
															return "<div class='list_item'>"
																	+ "<div class='vertical_center'>"
																	+ "<div style='width:50%'>"
																	+ new Date(data.createTime)
																			.format("yyyy-MM-dd hh:mm:ss")
																	+ "</div><div class='text_right' style='width:50%'>"
																	+ str
																	+ "</div></div>"
																	+ "<div class='vertical_center'>"
																	+ "<div style='width:50%'>"
																	+ data.creator
																	+ "</div><div class='text_right "
																	+ css
																	+ "' style='width:50%'>"
																	+ data.money
																			.toFixed(2)
																	+ "</div></div>"
																	+ "</div>";
														}
													});
												});
									}
								}]
					});
				} else {
					suit.alertMsg(res);
				}
				loadMask.hide();
			},
			failure : function() {
				var res = JSON.parse(status);
				suit.alertMsg(res);
				loadMask.hide();
			}
		});
	}
	// 收支明细页
	function getDetail() {
		return "<div id='listview'></div>";
	}

	function loadInOutByDorm() {
		loadMask.show();
		suit.http.ajax({
					url : _ctxPath + "/inout/dorm/list",
					type : "get",
					data : {
						partnerId : id,
						dormId : userInfo.belongsId
					},
					success : function(status) {
						var res = JSON.parse(status);
						if (res.success) {

						} else {
							suit.alertMsg(res);
						}
						loadMask.hide();
					},
					failure : function() {
						var res = JSON.parse(status);
						suit.alertMsg(res);
						loadMask.hide();
					}
				});
	}

	// 宿舍信息页
	function getDorm(data) {
		var html = "<div class='dorm_info_data'>"
				+ "<div class='info_item'>宿舍:<span class='dorm_name'>"
				+ data.dormInfo.name
				+ "</span></div>"
				+ "<div class='info_item'>成员:<span class='dorm_name'>"
				+ getPartners(JSON.parse(data.partners))
				+ "</span></div>"
				+ "<div class='info_item'>剩余室费:<span class='remain'>"
				+ data.remain
				+ "</span></div>"
				+ "<div class='info_item'>总计支出:<span class='outcome'>"
				+ data.expense
				+ "</span></div><div class='info_item'>本月支出:<span class='this'>"
				+ data.thenPay
				+ "</span></div><div class='info_item'>上月支出:<span class='pre'>"
				+ data.prePay + "</span></div></div>";
		return html;
		function getPartners(data) {
			var html = "";
			for (var i = 0; i < data.length; i++) {
				html += "<div class='partner'>" + data[i].userName + "</div>  ";
			}
			return html;
		}
	}

	function getPersonInfo() {
		return "<div class='person_info'>"
				+ "<div class='info_item'><div>姓名:</div><span class='name'>"
				+ userInfo.userName + "</span></div></div>";
	}

	function getConfigView() {
		var config = "<div class='config_info'>"
				+ "<div class='info_item'><div class='button' event-click='refreshMessage'>刷新消息</div>";
		if (userInfo.belongsId)
			config += "<div class='button' event-click='cancelDorm'>退出当前宿舍</div>";
		config += "<div class='button' event-click='showUpdatePassword'>修改密码</div>";
		config += "<div class='button' event-click='logout'>退出登录</div>";

		return config;
	}

	// 获取记录表单
	function getForm(tag) {
		if (tag) {
			return "<div class='inout_form'>"
					+ "<div><label>金额:</label><input data-name='金额' class='need_submit' name='money' type=number /></div>"
					+ "<div><label>用途:</label><input data-name='用途' class='need_submit' name='useIn' /></div></div>";
		} else {
			return "<div class='inout_form'>"
					+ "<div><label>金额:</label>"
					+ "<input class='need_submit' name='money' type=number />"
					+ "<input data-name='用途' class='need_submit hide' name='useIn' value='**88aadd' /></div>";
		}
	}

	function loadMessage(type, id, callback) {
		loadMask.show();
		suit.http.ajax({
					url : _ctxPath + "/message/list",
					type : "get",
					data : {
						BelongsType : type,
						belongsId : id
					},
					success : function(status) {
						var res = JSON.parse(status);
						if (res.success) {
							if (type == "PERSON") {
								if (isActiveMsg(res.data)) {
									$(".msg_num_person").parent()
											.removeClass("hide");
								} else {
									$(".msg_num_person").parent()
											.addClass("hide");
								}
								message[0] = res.data;
								callback && callback.call();
							} else if (type == "DORM") {
								if (isActiveMsg(res.data)) {
									$(".msg_num_dorm").parent()
											.removeClass("hide");
								} else {
									$(".msg_num_dorm").parent()
											.addClass("hide");
								}
								message[1] = res.data;
								callback && callback.call();
							}
						} else {
							suit.alertMsg(res);
						}
						loadMask.hide();
						function isActiveMsg(data) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].status == "NORMAL")
									return true;
							}
							return false;
						}
					},
					failure : function() {
						var res = JSON.parse(status);
						suit.alertMsg(res);
						loadMask.hide();
					}
				});
	}

	function getMessage() {

	};

	function addEvent() {
		$(".message_tag").on("click", function(e) {
					var type = $(this).children().attr("class");
					if (type == "msg_num_dorm") {
						showMessageView("个人");
					} else {
						showMessageView("宿舍");
					}
					e.stopPropagation();
				});
	}
	function showMessageView(type) {
		var data;
		if (type == "个人") {
			data = message[0];
		} else {
			data = message[1];
		}
		var dom = "<div class='list_message'>";
		for (var i = 0; i < data.length; i++) {
			dom += getListItem(data[i]);
		}
		dom += "</div>";
		suit.msgBox({
					title : type + "消息",
					text : dom,
					buttons : [{
								title : "关闭",
								onClick : "cancel"
							}]
				});
		suit.attachEvent();
		function getListItem(item) {
			var msg = item.message;
			var hideDeal = userInfo.userType == "PARTNER" ? "hide" : "";
			if (item.msgType == "JOIN_IN") {
				msg = "请求加入";
				if (item.status == "FROZEN") {
					msg = "已加入";
					hideDeal = "hide";
				} else if (item.status == "DEL") {
					msg += "，已拒绝";
					hideDeal = "hide";
				}
			}
			return "<div class='message_item' click-data='" + item.createUserId
					+ " event-click='acceptJoin''><div style='width:80%'>"
					+ new Date(item.createTime).format("yyyy-MM-dd hh:mm:ss")
					+ "</div><div class='sender'>" + item.sender
					+ "<span style='color:#888'>" + msg
					+ "</span></div><div class='options " + hideDeal + "'>"
					+ "<div class='button acceptJoin' click-data='"
					+ item.createUserId + "-" + item.id
					+ "' event-click='acceptJoin'>同意</div>"
					+ "<div class='button refuseJoin' click-data='" + item.id
					+ "' event-click='refuseJoin'>拒绝</div></div></div>";
		}
	}
	function dealJoin(id, msgId) {
		loadMask.show();
		suit.http.ajax({
					url : _ctxPath + "/system/user/join",
					type : "post",
					data : {
						partnerId : id,
						dormId : userInfo.belongsId
					},
					success : function(status) {
						var res = JSON.parse(status);
						if (res.success) {
							dealMessage(msgId, "FROZEN");
						} else {
							suit.alertMsg(res);
						}
						loadMask.hide();
					},
					failure : function() {
						var res = JSON.parse(status);
						suit.alertMsg(res);
						loadMask.hide();
					}
				});
	}
	function dealMessage(id, status) {
		loadMask.show();
		suit.http.ajax({
					url : _ctxPath + "/message/deal",
					type : "post",
					data : {
						msgId : id,
						flag : status
					},
					success : function(status) {
						var res = JSON.parse(status);
						if (res.success) {
							suit.alertMsg({
										msg : "处理成功"
									});
							loadMessage("DORM", userInfo.belongsId, function() {
										showMessageView("宿舍");
									});
						} else {
							suit.alertMsg(res);
						}
						loadMask.hide();
					},
					failure : function() {
						var res = JSON.parse(status);
						suit.alertMsg(res);
						loadMask.hide();
					}
				});
	}
})();