$(function() {
	$('#tb1 tbody').on('click', '.outlineBtn', function() {
		if(window.confirm('是否强制令该用户下线?')){
			var username = $(this).parents('tr').data('username');
			var key = $('#key').val().trim();
			$.ajax({
				url : host + '/admin/forceOutlineUser.action',
				type : 'post',
				dataType : 'json',
				data : {
					key : key,
					username : username
				},
				success : function(result) {
					if (result.status == 0) {
						alert('该用户已强制下线');
					} else if (result.status == 1) {
						$('#tb1 tbody').empty();
						alert('密钥不正确，没有操作权限');
					}
				},
				error : function(e) {
					alert('系统错误');
				}
			});
		}
	});
	/**
	 * 加载在线用户列表
	 */
	$('#btn1')
			.click(
					function() {
						var key = $('#key').val().trim();
						if (key == '') {
							alert('请输入密钥');
							return;
						} else {
							$('#tb1 tbody').empty();
							$
									.ajax({
										url : host
												+ '/admin/manageOnlineUsers.action',
										type : 'post',
										dataType : 'json',
										data : {
											key : key
										},
										success : function(result) {
											if (result.status == 0) {
												var datas = result.data;
												for (var i = 0; i < datas.length; i++) {
													var data = datas[i];
													var str = '<tr>'
															+ '<td>'
															+ (i + 1)
															+ '</td>'
															+ '<td>'
															+ data
															+ '</td>'
															+ '<td><a class="outlineBtn" href="#">下线</a></td>'
															+ '</tr>';
													var $str = $(str).data(
															'username', data);
													$('#tb1 tbody')
															.append($str);
												}
											} else if (result.status == 1) {
												$('#tb1 tbody').empty();
												alert('密钥不正确，没有操作权限');
											}
										},
										error : function(e) {
											alert('系统错误');
										}
									});
						}
					});

});