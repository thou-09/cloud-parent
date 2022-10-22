// 模态窗初始化
coco.init();
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

// --⬇-- 发布按钮事件绑定
let publishingServiceBtn = document.querySelector("#publishingService");
if(publishingServiceBtn){
    publishingServiceBtn.addEventListener("click",function(){
        let flag = true;  // 模拟登录变量
        if(flag){
            window.location.href = "publishing-service.html"
        }else{
            coco.confirm({
                header: false, 
                html:"<p style='color:#f46;text-align: center;font-size:20px'>您尚未登录,请先登录!</p>"
            }).onClose(function (opt, ok, done) {
                if (ok) {
                    console.log("点击了确定按钮");
                    window.location.href = "action.html"
                }
                done();
            });
        }
    })
}