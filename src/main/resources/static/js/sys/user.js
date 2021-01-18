$(function () {

    var password = $.trim($('#password').val());
    // var userID =
    //获取用户表格数据
    layui.use(['table', 'form', 'jquery', 'layer'], function () {
        var $ = layui.$
            , layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        table.render({
            elem: '#userTable'
            , url: 'getAllUser'
            , cols: [
                [
                    {type: 'numbers', align: 'center'}
                    , {field: 'userName', title: '用户名称', sort: true, align: 'center'}
                    , {field: 'loginName', title: '登录名称', sort: true, align: 'center'}
                    , {field: 'userEmail', title: '用户Email', sort: true, align: 'center'}
                    , {field: 'phoneNumber', title: '联系方式', sort: true, align: 'center'}
                    , {field: 'enabledState', title: '启用状态', sort: true, align: 'center'}
                    , {field: 'loginCount', title: '登录次数', sort: true, align: 'center'}
                    , {field: 'description', title: '描述', align: 'center'}
                    , {field: 'creator', title: '创建者', sort: true, align: 'center'}
                    , {field: 'createTime', title: '创建时间', sort: true, align: 'center'}
                    , {fixed: 'right', title: '操作', toolbar: '#userBtn', width: 100}
                    // ,{field:'', title:'用户编码',sort: true, totalRow: true, align: 'center'}
                    // ,{field:'', title:'最后一次登录时间', sort: true}
                    // ,{field:'', title:'操作', }
                ]
            ]
            , page: true
            , id: 'userReload'
            , response: {
                statusCode: 200,//重新规定成功的状态码为 200，table 组件默认为 0
            }
            , parseData: function (data) { //将原始数据解析成 table 组件所规定的数据
                console.log(data);
                return {
                    "code": data.status, //解析接口状态
                    "msg": data.message, //解析提示文本
                    "count": data.total, //解析数据长度
                    "data": data.rows //解析数据列表
                };
            }
        });
        //表格重载
        var $ = layui.$, active = {
            reload: function () {
                var userReload = $('#userReload');
                //执行重载
                table.reload('userReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        key: {
                            id: userReload.val()
                        }
                    }
                });
            }
        };

        //标注选中样式
        table.on('row(userTable)', function (obj) {
            var objdata = obj.data;
            var trows = JSON.stringify(objdata);
            //console.log(rows);
            // layer.alert(JSON.stringify(data), {
            //     title: '当前行数据：'
            // });
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');

            if (trows != null) {
                //编辑
                $('.usereditor').click(function () {
                    $('.edit-tc').show();
                });
                //获取数据 url==？？？

                //编辑 用戶 HOU
                form.on('submit(UserEditor)', function (trows) {
                    $.ajax({
                        type: 'post',
                        url: 'updateUser',
                        data: {
                            loginName: loginName,
                            userName: userName,
                            userEmail: userEmail,
                            phoneNumber: phoneNumber,
                            description: description,
                        },
                        async: false,
                        crossDomain: true,
                        xhrFields: {
                            withCredentials: true
                        },
                        success: function (trows) {

                            loginName = trows.loginName;
                            userName = trows.userName;
                            userEmail = trows.rows.userEmail;
                            phoneNumber = trows.rows.phoneNumber;
                            description = trows.rows.description;

                            var type = $(this).data('type');
                            active[type] ? active[type].call(this) : '';
                        },
                        error: function (trows) {
                            layer.msg('编辑失败', {
                                time: 500,
                            });
                        }
                    });

                });

                //删除用戶
                $('.userdelete').click(function () {
                    $.ajax({
                        type: 'post',
                        url: 'deleteUser',
                        data: {
                            'userID': trows.userID
                        },
                        async: false,
                        crossDomain: true,
                        xhrFields: {
                            withCredentials: true
                        },
                        success: function (data) {
                            layer.confirm('您是否确定删除此用戶？', {
                                btn: ['确定', '取消'] //按钮
                            }, function () {
                                layer.msg('删除成功', {
                                    icon: 1
                                });
                                var type = $(this).data('type');
                                active[type] ? active[type].call(this) : '';
                            }, function () {
                                layer.msg('取消成功', {
                                    time: 2000
                                });
                            });
                        }
                    });
                });

                //启用
                $('.toEnable').click(function () {
                    if (trows != null && trows.enabledState == 0) {
                        $.ajax({
                            type: "post",
                            url: "toEnableToUser",
                            data: {
                                "userID": trows.userID
                            },
                            async: false,
                            crossDomain: true,
                            xhrFields: {
                                withCredentials: true
                            },
                            success: function (data) {
                                if (data) {
                                    var type = $(this).data('type');
                                    active[type] ? active[type].call(this) : '';
                                } else {
                                    layer.msg('启用失败！');
                                }
                            }
                        });
                    } else if (trows.enabledState == 1) {
                        layer.msg("请选择禁用状态的数据");
                    }

                });

                //禁用
                $('.disable').click(function () {
                    if (trows != null && trows.enabledState == 1) {
                        $.ajax({
                            type: "post",
                            url: "disableToUser",
                            data: {
                                "userID": trows.userID
                            },
                            async: false,
                            crossDomain: true,
                            xhrFields: {
                                withCredentials: true
                            },
                            success: function (data) {
                                if (data) {
                                    var type = $(this).data('type');
                                    active[type] ? active[type].call(this) : '';
                                } else {
                                    layer.msg("禁用失败！");
                                }
                            }
                        });
                    } else if (trows.enabledState == 0) {
                        layer.msg("请选择启用状态的数据");
                    }
                });

                //重置密码
                $('.userResetPwd').click(function () {
                    $('.pwd-tc').show();
                });

                //重置密码
                form.on('submit(resetPwd)', function (data) {
                    $.ajax({
                        type: "post",
                        url: "resetPassword",
                        data: {
                            "userID": trows.userID,
                            "password": "sinochem"
                        },
                        async: false,
                        crossDomain: true,
                        xhrFields: {
                            withCredentials: true
                        },
                        success: function (data) {
                            if (data == "成功") {
                                layer.msg("密码重置成功！");
                            } else {
                                layer.msg("密码重置失败！");
                            }
                        }
                    });
                });

            } else {
                layer.msg('请先选择数据');
            }


        });


        //新增用户
        form.on('submit(UserAdd)', function (data) {
            //添加-编辑 用戶
            var loginName = $.trim($('#loginName').val());
            var userName = $.trim($('#userName').val());
            var userEmail = $.trim($('#userEmail').val());
            var phoneNumber = $.trim($('#phoneNumber').val());
            var description = $.trim($('#description').val());
            $.ajax({
                type: 'post',
                url: 'addUser',
                data: {
                    "loginName": loginName,
                    "userName": userName,
                    "userEmail": userEmail,
                    "phoneNumber": phoneNumber,
                    "description": description,
                },
                async: false,
                crossDomain: true,
                xhrFields: {
                    withCredentials: true
                },
                success: function (data) {
                    var type = $(this).data('type');
                    active[4\] ? active[type].call(this) : '';

                },
                error: function (data) {
                    layer.msg('新增失败', {
                        time: 500,
                    });
                }
            });

        });

    });

    //新增
    $('.useradd').click(function () {
        $('.add-tc').show();
    });
    //新增-关闭按钮
    $('.tcclose').click(function () {
        $('.add-tc').hide();
    });
    //编辑-关闭按钮
    $('.tcclose').click(function () {
        $('.edit-tc').hide();
    });
    //重置密码-关闭按钮
    $('.tcclose').click(function () {
        $('.pwd-tc').hide();
    });

});