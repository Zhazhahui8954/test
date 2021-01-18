/**
 * 初始化入口文件，和layui有关
 */
layui.config({
    base: '../static/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use('index', function () {
});