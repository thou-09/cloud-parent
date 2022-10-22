const changeTagFun = function (hashTarget) {
    console.log(hashTarget);
    hashTarget = hashTarget == "" ? "user-account" : hashTarget.replace(/^#/, "");
    window.location.hash = hashTarget;
    document.querySelectorAll(`[data-target`).forEach(item => {
        item.classList.remove("active")
    })
    document.querySelector(`[data-target='${hashTarget}']`).classList.add("active");
    $("#managerBody").load(`/pc/template/${hashTarget}.htm`)
}
let changeBtns = document.querySelectorAll("[data-target]");
changeBtns.forEach(item=>{
    item.addEventListener("click",changeTagFun.bind(item,item.dataset.target))
})
// 监控 url地址 # hash 值变化更新方法
window.onhashchange = changeTagFun;
changeTagFun(window.location.hash);
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