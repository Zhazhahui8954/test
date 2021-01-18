var btnId = [];//按钮ID
$(function () {
    //以下方法为了解决easyUI中不支持maxlength的解决方案
    $.post(location.origin + '/btns/renderToobar?pathName=' + location.pathname, function (data) {
        btnId = [];
        var len = data.length;
        createRefreshBtn('icon-refresh', '刷新', 'window.location.reload');
        for (var i = 0; i < len; i++) {
            createRefreshBtn(data[i].iconclass, data[i].buttonname, data[i].buttoncode);
        }
        showButton();
    });
});

function showButton() {
    // var length = btnId.length;
    // for(var i = 0; i < length ; i ++){
    //     $("#"+btnId[i]).linkbutton({
    //         disabled:false
    //     });
    // }
}

/**
 * 创建刷新按钮
 * @returns
 */
function createRefreshBtn(iconClass, text, btnCode) {
    var aEle = document.createElement('a');
    aEle.setAttribute('href', '#');
    aEle.setAttribute('plain', true);
    aEle.setAttribute('class', 'easyui-linkbutton l-btn l-btn-small l-btn-plain');
    aEle.setAttribute('icon', iconClass);
    aEle.setAttribute('text', text);
    aEle.setAttribute('id', btnCode);
    aEle.setAttribute('onclick', btnCode + "()");
    var span1 = document.createElement('span');
    span1.setAttribute('class', 'l-btn-left l-btn-icon-left');
    var span2 = document.createElement('span');
    span2.setAttribute('text', text);
    span2.setAttribute('class', 'l-btn-text');
    span2.textContent = text;
    span1.appendChild(span2);
    var span3 = document.createElement('span');
    span3.setAttribute('class', 'l-btn-icon ' + iconClass);
    span1.appendChild(span3);
    if (text == "刷新") {
        aEle.appendChild(span1);
    } else {
        btnId.push(btnCode);
    }
    var tbar = document.getElementById('toolbar');
    if (tbar != null) {
        tbar.appendChild(aEle);
    }
}
//分页
layui.use(['laypage', 'layer'], function () {
    var laypage = layui.laypage,
        layer = layui.layer;
    laypage.render({
        elem: 'pagenum',
        count: 100,
        layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
        jump: function (obj) {
            console.log(obj)
        }
    });

});
//日期
layui.use(['form', 'layedit', 'laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate;
    laydate.render({
        elem: '#date'
    });
    laydate.render({
        elem: '#date1'
    });
});

//弹出层
//删除按钮
$('.delmsg').click(function () {
    layer.confirm('您是否确定删除？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        layer.msg('删除成功', {
            icon: 1
        });
    }, function () {
        layer.msg('取消成功', {
            time: 2000
        });
    });
});
//编辑-关闭按钮
$('.tcclose').click(function () {
    $('.edit-tc').hide();
});

//编辑table-edit
$('.table-edit').click(function () {
    $('.edit-tc').show();
});

//监听折叠-demo3.html
layui.use(['element', 'layer'], function () {
    var element = layui.element;
    var layer = layui.layer;
    element.on('collapse(test)', function (data) {
        layer.msg('展开状态：' + data.show);
    });
});

//fileimg-demo3.html
layui.use('upload', function () {
    var $ = layui.jquery,
        upload = layui.upload;
    //多图片上传
    upload.render({
        elem: '#moreimg',
        url: '/upload/',
        multiple: true,
        before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#fileimg').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">')
            });
        },
        done: function (res) {
            //上传完毕
        }
    });
});

//选取颜色
//layui.use('colorpicker', function() {
//	var colorpicker = layui.colorpicker;
//	//渲染
//	colorpicker.render({
//		elem: '#test1' //绑定元素
//	});
//});

