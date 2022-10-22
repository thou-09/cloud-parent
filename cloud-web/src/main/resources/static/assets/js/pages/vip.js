
// --⬆-- 为页面初始化代码 - 如非必要请勿修改
/*
    !@@  @@@@@@@@@3    @@@@      @@@8    @@@  @@@   &@@                     @@@@@@@+  @@     .@@. %@@    @@@@   $@@  
    !@@     $@@       @@$@@@     @@@@@   @@@   @@@ a@@                    @@@+        @@     .@@.  @@@  *@@@@   @@z  
    !@@     $@@      1@@  @@$    @@ z@@  @@@    @@!@@                     @@!         @@@@@@@@@@.  1@@  @@ #@@ ^@@   
    !@@     $@@      @@zvvz@@-   @@   @@n@@@     @@@          @@@@@       @@#         @@     .@@.   @@1i@#  @@ @@;   
    !@@     $@@     @@@6666@@@   @@    @@@@@     @@@                      @@@@        @@     .@@.   ~@@@@   $@#@@    
    !@@     $@@    @@@      @@@  @@     !@@@     @@@                        @@@@@@@+  @@     .@@.    @@@%    @@@.    
*/
// --⬇-- 为页面数据逻辑代码 - 请更具需求进行修改

// 模态窗初始化
coco.init();
// 基础模式
// coco("欢迎使用").onClose(function(opt,ok,done){
//     console.log("窗口配置:",opt);
//     console.log("窗口按钮点击状态:",ok);
//     console.log("关闭窗口执行回调:",done);
//     if(ok){
//         console.log("点击了确定按钮");
//     }else{
//         console.log("点击了取消按钮");
//     }
//     done();
// });

// 消息
// coco.alert("alert").onClose(function(opt,ok,done){
//     console.log("窗口配置:",opt);
//     console.log("窗口按钮点击状态:",ok);
//     console.log("关闭窗口执行回调:",done);
//     if(ok){
//         console.log("点击了确定按钮");
//     }else{
//         console.log("点击了取消按钮");
//     }
//     done();
// });

// 确认
// coco.confirm("confirm").onClose(function (opt, ok, done) {
//     console.log("窗口配置:", opt);
//     console.log("窗口按钮点击状态:", ok);
//     console.log("关闭窗口执行回调:", done);
//     if (ok) {
//         console.log("点击了确定按钮");
//     } else {
//         console.log("点击了取消按钮");
//     }
//     done();
// });

// 内置输入
// coco({
//     title: "验证输入",
//     inputAttrs: {
//         placeholder: "your name",
//     },
// }).onClose((opt, ok, done) => {
//     console.log("窗口配置:", opt);
//     console.log("窗口按钮点击状态:", ok);
//     console.log("关闭窗口执行回调:", done);
//     if (ok) {
//         console.log("输入值:", opt.inputValue);
//         done();
//     } else {
//         done();
//     }
// });

// 完整配置
// coco({
//     // 完全配置
//     maskClose: true,  //点击遮罩层是否关闭
//     header: true,  //是否显示头
//     footer: true, //是否显示脚
//     width: '500px', // 宽度
//     top: '15vh', // 距离顶部的距离
//     borderRadius: '6px', // 圆角
//     inputAttrs: true,  // 开启内置输入文本 ，无需定义标签
//     title: '提示', // 头文字
//     text: '',  // 非标签文本内容
//     html: '',  // 字符串标签内容
//     el: '', // css选择器指定内容 -> 被选定的模板需在外围容器上定义 style="display:none" 完成隐藏，并在hooks 的open中完成 重置
//     escClose: true, // 启用 esc 退出功能
//     okButton: true, // 是否开启确定按钮
//     cancelButton: true, // 是否开启取消按钮
//     okText: '确定', // 确定按钮文本
//     cancelText: '取消', // 取消按钮文本
//     closeButton: true, // 是否显示关闭 符号
//     buttonColor: '#4285ff', // 按钮颜色
//     zIndexOfModal: 1995, // 层级
//     zIndexOfMask: 2008, // 层级
//     zIndexOfActiveModal: 2020, // 层级
//     fullScreen: false, // 全屏模式
//     hooks: {   // 钩子函数
//         open() { },  // 打开
//         opened() { }, // 打开完成
//         close() { },  // 关闭
//         closed() { }, // 关闭结束
//     },
//     destroy: false, // 执行完成后是否销毁该提示框
//     animation: false, // 是否启用动画
//     blur: true, // 毛玻璃效果
// }).onClose((opt, ok, done) => {
//     console.log("窗口配置:", opt);
//     console.log("窗口按钮点击状态:", ok);
//     console.log("关闭窗口执行回调:", done);
//     done()
// });

function openBuyModel(title,price) {
    coco({
        width: '400px', // 宽度
        title: '开通VIP',
        el: "#buyBox",
        cancelButton: false,
        blur: true,
        destroy:true, // 因为每次打开都需更新数据，所以通过销毁重置
        hooks:{
            open(opt){
                console.log("弹出内元素类容:",opt.bodyEl);
                opt.bodyEl.querySelector("#buyBox").style.display = null; // 重置

                let titleDom = opt.bodyEl.querySelector(".title .type");
                let priceDom = opt.bodyEl.querySelector(".price");

                titleDom.innerHTML = title;
                priceDom.innerHTML = "￥"+price;
            }
        }
    });
}