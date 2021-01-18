layui.use('element', function () {
    var element = layui.element;
    var $ = layui.jquery;
    $.ajax({
        type: 'post',
        url: '/menu/getAllMenus',
        data: {},
        dataType: 'json',
        crossDomain: true,
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            var trMenu = utils.toTreeData(data.rows, "menu_id", "parent_code");
            //console.log(trMenu);
            var liStr = "";
            //生成主菜单
            for (var i = 0; i < trMenu.length; i++) {
                //console.log(trMenu[i]);
                //是否存在子菜单
                if (trMenu[i].children != null && trMenu[i].children.length > 0) {
                    liStr += "<li class=\"layui-nav-item\"><a class=\"\" href=\"javascript:;\">" + trMenu[i].menu_name + "<i class=\"layui-icon layui-icon-template\"></i></a>\n" +
                        "<dl class=\"layui-nav-child\">\n";
                    //遍历子菜单
                    for (var k = 0; k < trMenu[i].children.length; k++) {
                        liStr += getChildMenu(trMenu[i].children[k], 0);
                    }
                    liStr += "</dl></li>";
                } else {
                    liStr += "<li class=\"layui-nav-item\"><a class=\"\" href=\"" + trMenu[i].url + "\">" + trMenu[i].menu_name + "</a></li>";
                }
            }

            $("#navBarId").html("<ul class=\"layui-nav layui-nav-tree\"  lay-shrink=\"all\" lay-filter=\"layadmin-system-side-menu\">\n" + liStr + "</ul>");
            element.init();

        },
        error: function () {
            console.log('获取失败！');
        }
    });
});

//寻找三级菜单
function getChildMenu(subMenu, num) {
    console.log("num: " + num);
    num++;
    var subStr = "";
    if (subMenu.children != null && subMenu.children.length > 0) {
        subStr += "<dd><a class=\"\" href=\"javascript:;\" style=\"margin-Left:\" + num * menuCell + \"px\">" + subMenu.menu_name + "</a>" +
            "<dl class=\"layui-nav-child\">\n";
        for (var j = 0; j < subMenu.children.length; j++) {
            subStr += getChildMenu(subMenu.children[j], num);
        }
        subStr += "</dl></dd>";
    } else {
        subStr += "<dd><a  style=\"margin-Left:\" + num * menuCell + \"px\" style=\"margin-Left:\" + num * menuCell + \"px\" lay-href=\"" + subMenu.url + "\">" + subMenu.menu_name + "</a></dd>";
    }
    return subStr;
}