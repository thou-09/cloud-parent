let loginBtnBoxDoms = document.querySelectorAll("[tag='action-btn-event']>span");
let formDoms = {
    login:document.querySelector(".login"),
    register:document.querySelector(".register")
}

loginBtnBoxDoms.forEach(item=>{
    item.addEventListener("click",function(){
        window.location.hash = "#"+this.dataset.target;

        loginBtnBoxDoms.forEach(item=>void item.classList.remove("active"));
        this.classList.add("active");

        Object.values(formDoms)
              .forEach(item=>void item.classList.remove("active"));
        formDoms[this.dataset.target].classList.add("active");
    })
})

// 根据 url地址的 # hash 值确定当前显示的元素内容
const changeTagFun = function(){
    let hashTarget = window.location.hash;
    hashTarget = hashTarget==""?"login":hashTarget.replace(/^#/,"");
    document.querySelector(`[data-target='${hashTarget}']`).click();
}
// 监控 url地址 # hash 值变化更新方法
window.onhashchange = changeTagFun;

changeTagFun();

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
