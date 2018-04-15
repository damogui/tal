/**
 * 进行Layer的自适应
 */
//设置弹窗的值进行兼容
var layerIndex;//索引
var layerInitWidth;//宽度
var layerInitHeight;//高度
//传递参数
//layerIndex  layer但是索引, layerInitWidth 宽度, layerInitHeight  高度
function resizeLayer(layerIndex, layerInitWidth, layerInitHeight) {
    var docWidth = $(document).width();
    var docHeight = $(document).height();
    var minWidth = layerInitWidth > docWidth ? docWidth : layerInitWidth;
    var minHeight = layerInitHeight > docHeight ? docHeight : layerInitHeight;
    layer.style(layerIndex, {
        top: 0,
        width: minWidth,
        height: minHeight
    });

}
//监听窗口的变化进行更改
$(window).resize(function() {
    resizeLayer(layerIndex,layerInitWidth,layerInitHeight);
});