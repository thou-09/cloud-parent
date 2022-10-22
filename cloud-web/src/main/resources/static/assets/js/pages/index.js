/*
funtionName:initLeftMenusFunction 
desc:初始化左侧浮动菜单
     方法未做递归，仅完成三层导航生成
     一级导航只保留前八条
params:
    boxDom:需要写入导航的Dom元素-该方法执行前会将该DOM内部的所有内容清空
    list数据结构如下
        list =  [
                    {
                        id:10001,
                        title:"名称",
                        children:[
                            {
                                id:10001,
                                title:"名称",
                                children:[ …… ]
                            }
                            ……
                        ]
                    }
                    ……
                ]
    path:点击跳转的路径，会附带id作为url get参数
*/
const initLeftMenusFunction = function (boxDom, list = [], path = "list.html") {
    if (!(boxDom instanceof Element)) {
        console.error("方法initLeftMenusFunction接收的参数：'", boxDom, "'不是一个有效的DOM容器");
        return;
    }
    // 下面是 JS 书写的节点生成方法
    let ulDom = document.createElement("ul");
    ulDom.classList.add("nav-list");
    if (list.length == 0) {
        ulDom.classList.add("nav-loading");
        leftMenuBox.innerHTML = "";
        leftMenuBox.appendChild(ulDom);
        return;
    }
    let tempList = list.slice(0, 8);
    tempList.forEach(parent => {
        let liDom = document.createElement("li");
        let spanDom = document.createElement("span");
        spanDom.innerText = parent.title;
        liDom.appendChild(spanDom);
        let divDom = document.createElement("div");
        divDom.classList.add("nav-list-detail");
        if (!parent.children || parent.children.length == 0) {
            divDom.classList.add("nav-loading");
        } else {
            parent.children.forEach(childOne => {
                let dlDom = document.createElement("dl");
                let dtDom = document.createElement("dt");
                let aDom = document.createElement("a");
                aDom.innerText = childOne.title;
                // ------>
                aDom.href = `${path}?id=${childOne.id}`;
                aDom.target="_blank";
                // <------
                dtDom.appendChild(aDom);
                dlDom.appendChild(dtDom);
                childOne.children.forEach(childTwo => {
                    let ddDom = document.createElement("dd");
                    let aDom = document.createElement("a");
                    aDom.innerText = childTwo.title;
                    // ------>
                    aDom.href = `${path}?id=${childTwo.id}`;
                    aDom.target="_blank";
                    // <------
                    ddDom.appendChild(aDom);
                    dlDom.appendChild(ddDom);
                })
                divDom.appendChild(dlDom);
            })
        }
        liDom.appendChild(divDom);
        ulDom.appendChild(liDom);
    });
    leftMenuBox.innerHTML = "";
    leftMenuBox.appendChild(ulDom);
}
/*
funtionName:initServiceContentFunction 
desc:初始化页面正文菜单
     方法未做递归，仅完成三层导航生成
     一级导航的标题背景图 每五个 重复出现一次 建议只显示前五个 (自行操作)
     二级菜单只显示前 四个 (方法内部已完成截取)
     三级菜单最多显示 十个 ，当三级菜单长度大于 10 个时，会自动生成 更多按钮(此时更多按钮id指向于二级菜单id)
                           当三级菜单长度小于或等于 10 个时，会显示所有三级菜单
params:
    boxDom:需要写入导航的Dom元素-该方法执行前会将该DOM内部的所有内容清空
    list数据结构如下
        list =  [
                    {
                        id:10001,
                        title:"名称",
                        children:[
                            {
                                id:10001,
                                title:"名称",
                                children:[ …… ]
                            }
                            ……
                        ]
                    }
                    ……
                ]
    path:点击跳转的路径，会附带id作为url get参数
*/
const initServiceContentFunction = function (boxDom, list = [], path = "list.html") {
    if (!(boxDom instanceof Element)) {
        console.error("方法initServiceContentFunction接收的参数：'", boxDom, "'不是一个有效的DOM容器");
        return;
    }
    // 下面是 JS 书写的节点生成方法
    let fragmentDom = document.createDocumentFragment();
    if (list.length == 0) {
        let divDom = document.createElement("div");
        divDom.classList.add("content-item");
        divDom.classList.add("nav-loading");
        fragmentDom.appendChild(divDom);
        boxDom.innerHTML = "";
        boxDom.appendChild(fragmentDom);
        return;
    }
    list.forEach(parent => {
        let divDom = document.createElement("div");
        divDom.classList.add("content-item");
        let pDom = document.createElement("p");
        pDom.classList.add("left-title");
        pDom.innerHTML = parent.title;
        let bodyDom = document.createElement("div");
        bodyDom.classList.add("right-body");
        if (!parent.children || parent.children.length == 0) {
            bodyDom.classList.add("nav-loading")
        } else {
            let childOneList = parent.children.slice(0, 4)
            childOneList.forEach(childOne => {
                let dlDom = document.createElement("dl");
                let dtDom = document.createElement("dt");
                let aDom = document.createElement("a");
                aDom.href = `${path}?id=${childOne.id}`;
                aDom.target="_blank";
                aDom.innerText = childOne.title;
                dtDom.appendChild(aDom);
                dlDom.appendChild(dtDom);
                let childTwoList = childOne.children
                if (childOne.children.length > 10) {
                    childTwoList = childTwoList.slice(0, 9);
                    childTwoList.push({
                        id: childOne.id,
                        title: "更多",
                        more: true
                    })
                }
                childTwoList.forEach(childTwo => {
                    let ddDom = document.createElement("dd");
                    if (childTwo.more) {
                        ddDom.classList.add("more");
                    }
                    let aDom = document.createElement("a");
                    aDom.href = `${path}?id=${childTwo.id}`;
                    aDom.target="_blank";
                    aDom.innerText = childTwo.title;
                    ddDom.appendChild(aDom);
                    dlDom.appendChild(ddDom);
                })
                bodyDom.appendChild(dlDom);
            })
        }
        divDom.appendChild(pDom);
        divDom.appendChild(bodyDom);
        fragmentDom.appendChild(divDom);
    })
    boxDom.innerHTML = "";
    boxDom.appendChild(fragmentDom);
}

/*
funtionName:initHotListFunction 
desc:初始热门菜单 - 只完成前八个导航生成操作
                   默认最后增加一个‘全部’导航完成无参数跳转
params:
    boxDom:需要写入导航的Dom元素-该方法执行前会将该DOM内部的所有内容清空
    list数据结构如下
        list =  [
                    {
                        id:10001,
                        title:"名称",
                    }
                    ……
                ]
    path:点击跳转的路径，会附带id作为url get参数
*/
const initHotListFunction = function (boxDom,list=[],path="list.html") {
    if (!(boxDom instanceof Element)) {
        console.error("方法initHotListFunction接收的参数：'", boxDom, "'不是一个有效的DOM容器");
        return;
    }
    // 下面是 JS 书写的节点生成方法
    let fragmentDom = document.createDocumentFragment();
    if(list.length==0){
        boxDom.innerHTML = "";
        boxDom.classList.add("nav-loading");
        return;
    }
    boxDom.classList.remove("nav-loading");
    let listTemp = list.slice(0,8);
    listTemp.forEach(item=>{
        let liDom = document.createElement("li");
        let aDom = document.createElement("a");
        aDom.href = `${path}?id=${item.id}`;
        aDom.target="_blank";
        aDom.innerText = item.title;
        liDom.appendChild(aDom);
        fragmentDom.appendChild(liDom);
    })
    let liDom = document.createElement("li");
    liDom.classList.add("more");
    let aDom = document.createElement("a");
    aDom.href = path;
    aDom.target="_blank";
    aDom.innerText = "全部";
    liDom.appendChild(aDom);
    fragmentDom.appendChild(liDom);
    boxDom.innerHTML = "";
    boxDom.appendChild(fragmentDom);
}

// 轮播图方法
const initNavLoopContent = function (boxDom, list = []) {
    new Swiper({
        el: boxDom,
        autoplay: true,
        loop: true,
        pagination: {
            el: '.swiper-pagination',
        }
    })
}

// 初始化轮播图
const navBodyTopLoop = document.querySelector("#navBodyTopLoop");
initNavLoopContent(navBodyTopLoop);

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

// 模拟数据 start
// 本地生活服务
let localLife = [
    {
        "id": "100001",
        "title": "家政服务",
        "children": [
            {
                "id": "200001",
                "title": "保姆/月嫂",
                "children": [
                    {
                        "id": "300001",
                        "title": "育儿嫂"
                    },
                    {
                        "id": "300002",
                        "title": "保姆"
                    },
                    {
                        "id": "300003",
                        "title": "钟点工"
                    },
                    {
                        "id": "300005",
                        "title": "月嫂"
                    },
                    {
                        "id": "300006",
                        "title": "护工陪护"
                    },
                    {
                        "id": "300007",
                        "title": "住家阿姨"
                    },
                    {
                        "id": "300008",
                        "title": "育婴师"
                    },
                    {
                        "id": "300009",
                        "title": "涉外家政"
                    },
                    {
                        "id": "3000010",
                        "title": "养老院"
                    },
                    {
                        "id": "3000011",
                        "title": "管家"
                    },
                    {
                        "id": "3000012",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "物品回收",
                "children": [
                    {
                        "id": "300001",
                        "title": "奢侈品回收"
                    },
                    {
                        "id": "300002",
                        "title": "设备机械回收"
                    },
                    {
                        "id": "300003",
                        "title": "电脑回收"
                    },
                    {
                        "id": "300004",
                        "title": "黄金回收"
                    },
                    {
                        "id": "300005",
                        "title": "废旧物品回收"
                    },
                    {
                        "id": "300006",
                        "title": "礼品回收"
                    },
                    {
                        "id": "300007",
                        "title": "库存积压"
                    },
                    {
                        "id": "300008",
                        "title": "家电回收"
                    },
                    {
                        "id": "300009",
                        "title": "卡券回收"
                    },
                    {
                        "id": "3000010",
                        "title": "家具回收"
                    },
                    {
                        "id": "3000011",
                        "title": "手机回收"
                    },
                    {
                        "id": "3000012",
                        "title": "数码产品回收"
                    },
                    {
                        "id": "3000013",
                        "title": "办公耗材回收"
                    },
                    {
                        "id": "3000014",
                        "title": "电池回收"
                    },
                    {
                        "id": "3000015",
                        "title": "塑料回收"
                    },
                    {
                        "id": "3000016",
                        "title": "纺织皮革"
                    },
                    {
                        "id": "3000017",
                        "title": "玻璃回收"
                    },
                    {
                        "id": "3000018",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "洗衣护理",
                "children": [
                    {
                        "id": "300001",
                        "title": "改衣"
                    },
                    {
                        "id": "300002",
                        "title": "洗衣干洗"
                    },
                    {
                        "id": "300003",
                        "title": "皮衣护理"
                    },
                    {
                        "id": "300004",
                        "title": "皮鞋护理"
                    },
                    {
                        "id": "300005",
                        "title": "皮包护理"
                    },
                    {
                        "id": "300006",
                        "title": "修鞋"
                    },
                    {
                        "id": "300007",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200004",
                "title": "生活配送",
                "children": [
                    {
                        "id": "300001",
                        "title": "跑腿服务"
                    },
                    {
                        "id": "300002",
                        "title": "桶装水"
                    },
                    {
                        "id": "300003",
                        "title": "食材配送"
                    },
                    {
                        "id": "300004",
                        "title": "代排队"
                    },
                    {
                        "id": "300005",
                        "title": "液化气煤气配送"
                    },
                    {
                        "id": "300006",
                        "title": "外卖"
                    },
                    {
                        "id": "300007",
                        "title": "宴会外卖"
                    },
                    {
                        "id": "300008",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200005",
                "title": "保洁清洗",
                "children": [
                    {
                        "id": "300001",
                        "title": "日常保洁"
                    },
                    {
                        "id": "300002",
                        "title": "开荒保洁"
                    },
                    {
                        "id": "300003",
                        "title": "玻璃清洗"
                    },
                    {
                        "id": "300004",
                        "title": "物业保洁"
                    },
                    {
                        "id": "300005",
                        "title": "外墙清洗"
                    },
                    {
                        "id": "300006",
                        "title": "高空清洗"
                    },
                    {
                        "id": "300007",
                        "title": "沙发清洗"
                    },
                    {
                        "id": "300008",
                        "title": "家庭保洁"
                    },
                    {
                        "id": "300009",
                        "title": "油烟机清洗"
                    },
                    {
                        "id": "3000010",
                        "title": "空调清洗"
                    },
                    {
                        "id": "3000011",
                        "title": "空气净化"
                    },
                    {
                        "id": "3000012",
                        "title": "瓷砖美缝"
                    },
                    {
                        "id": "3000013",
                        "title": "地毯清洗"
                    },
                    {
                        "id": "3000014",
                        "title": "石材翻新养护"
                    },
                    {
                        "id": "3000015",
                        "title": "地板打蜡"
                    },
                    {
                        "id": "3000016",
                        "title": "灯具清洗"
                    },
                    {
                        "id": "3000017",
                        "title": "除虫除蚁"
                    },
                    {
                        "id": "3000018",
                        "title": "壁纸清洗"
                    },
                    {
                        "id": "3000019",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200006",
                "title": "搬家",
                "children": [
                    {
                        "id": "300001",
                        "title": "居民搬家"
                    },
                    {
                        "id": "300002",
                        "title": "公司搬家"
                    },
                    {
                        "id": "300003",
                        "title": "设备搬迁"
                    },
                    {
                        "id": "300004",
                        "title": "长途搬家"
                    },
                    {
                        "id": "300005",
                        "title": "家具拆装"
                    },
                    {
                        "id": "300006",
                        "title": "搬家搬场"
                    },
                    {
                        "id": "300007",
                        "title": "空调拆装"
                    },
                    {
                        "id": "300008",
                        "title": "钢琴搬运"
                    },
                    {
                        "id": "300009",
                        "title": "小型搬场"
                    },
                    {
                        "id": "3000010",
                        "title": "国际搬家"
                    },
                    {
                        "id": "3000011",
                        "title": "起重吊装"
                    },
                    {
                        "id": "3000012",
                        "title": "其他"
                    }
                ]
            }
        ]
    },
    {
        "id": "100002",
        "title": "维修服务",
        "children": [
            {
                "id": "200001",
                "title": "开锁修锁",
                "children": []
            },
            {
                "id": "200002",
                "title": "房屋维修",
                "children": [
                    {
                        "id": "300001",
                        "title": "防水补漏"
                    },
                    {
                        "id": "300002",
                        "title": "电路维修"
                    },
                    {
                        "id": "300003",
                        "title": "水管/水龙头维修"
                    },
                    {
                        "id": "300004",
                        "title": "粉刷防腐"
                    },
                    {
                        "id": "300005",
                        "title": "马桶维修"
                    },
                    {
                        "id": "300006",
                        "title": "淋浴房维修"
                    },
                    {
                        "id": "300007",
                        "title": "打孔"
                    },
                    {
                        "id": "300008",
                        "title": "门窗维修"
                    },
                    {
                        "id": "300009",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "家电维修",
                "children": [
                    {
                        "id": "300001",
                        "title": "厨房家电"
                    },
                    {
                        "id": "300002",
                        "title": "热水器维修"
                    },
                    {
                        "id": "300003",
                        "title": "空调维修"
                    },
                    {
                        "id": "300004",
                        "title": "洗衣机维修"
                    },
                    {
                        "id": "300005",
                        "title": "冰箱维修"
                    },
                    {
                        "id": "300006",
                        "title": "空调移机"
                    },
                    {
                        "id": "300007",
                        "title": "电视机维修"
                    },
                    {
                        "id": "300008",
                        "title": "小家电"
                    },
                    {
                        "id": "300009",
                        "title": "空调清洗"
                    },
                    {
                        "id": "3000010",
                        "title": "饮水机维修"
                    },
                    {
                        "id": "3000011",
                        "title": "影音家电维修"
                    },
                    {
                        "id": "3000012",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200004",
                "title": "手机维修",
                "children": [
                    {
                        "id": "300001",
                        "title": "刷机/升级"
                    },
                    {
                        "id": "300002",
                        "title": "配件"
                    },
                    {
                        "id": "300003",
                        "title": "屏幕"
                    },
                    {
                        "id": "300004",
                        "title": "主板"
                    },
                    {
                        "id": "300005",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200005",
                "title": "管道维修",
                "children": [
                    {
                        "id": "300001",
                        "title": "管道疏通"
                    },
                    {
                        "id": "300002",
                        "title": "下水道疏通"
                    },
                    {
                        "id": "300003",
                        "title": "化粪池疏通"
                    },
                    {
                        "id": "300004",
                        "title": "管道清淤"
                    },
                    {
                        "id": "300005",
                        "title": "马桶疏通"
                    },
                    {
                        "id": "300006",
                        "title": "隔油池清理"
                    },
                    {
                        "id": "300007",
                        "title": "打捞"
                    },
                    {
                        "id": "300008",
                        "title": "煤气/天然气管道"
                    },
                    {
                        "id": "300009",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200006",
                "title": "钟表维修",
                "children": []
            },
            {
                "id": "200007",
                "title": "家具维修",
                "children": [
                    {
                        "id": "300001",
                        "title": "办公家具维修"
                    },
                    {
                        "id": "300002",
                        "title": "沙发维修"
                    },
                    {
                        "id": "300003",
                        "title": "地板维修"
                    },
                    {
                        "id": "300004",
                        "title": "桌椅柜维修"
                    },
                    {
                        "id": "300005",
                        "title": "钟表维修"
                    },
                    {
                        "id": "300006",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200008",
                "title": "电脑维修",
                "children": [
                    {
                        "id": "300001",
                        "title": "台式机维修"
                    },
                    {
                        "id": "300002",
                        "title": "笔记本维修"
                    },
                    {
                        "id": "300003",
                        "title": "电脑组装"
                    },
                    {
                        "id": "300004",
                        "title": "数据恢复"
                    },
                    {
                        "id": "300005",
                        "title": "ipad维修"
                    },
                    {
                        "id": "300006",
                        "title": "平板电脑维修"
                    },
                    {
                        "id": "300007",
                        "title": "其他"
                    }
                ]
            }
        ]
    },
    {
        "id": "100003",
        "title": "装修装饰",
        "children": [
            {
                "id": "200001",
                "title": "家庭装修",
                "children": [
                    {
                        "id": "300001",
                        "title": "二手房装修"
                    },
                    {
                        "id": "300002",
                        "title": "新房装修"
                    },
                    {
                        "id": "300003",
                        "title": "局部翻新"
                    },
                    {
                        "id": "300004",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "软装装饰",
                "children": [
                    {
                        "id": "300001",
                        "title": "装饰画"
                    },
                    {
                        "id": "300002",
                        "title": "窗帘"
                    },
                    {
                        "id": "300003",
                        "title": "地毯"
                    },
                    {
                        "id": "300004",
                        "title": "墙纸壁纸"
                    },
                    {
                        "id": "300005",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "建材",
                "children": [
                    {
                        "id": "300001",
                        "title": "水泥"
                    },
                    {
                        "id": "300002",
                        "title": "钢材"
                    },
                    {
                        "id": "300003",
                        "title": "窗"
                    },
                    {
                        "id": "300004",
                        "title": "地板"
                    },
                    {
                        "id": "300005",
                        "title": "瓷砖"
                    },
                    {
                        "id": "300006",
                        "title": "油漆涂料"
                    },
                    {
                        "id": "300007",
                        "title": "吊顶"
                    },
                    {
                        "id": "300008",
                        "title": "门"
                    },
                    {
                        "id": "300009",
                        "title": "五金"
                    },
                    {
                        "id": "3000010",
                        "title": "卫浴洁具"
                    },
                    {
                        "id": "3000011",
                        "title": "灯饰照明"
                    },
                    {
                        "id": "3000012",
                        "title": "玻璃"
                    },
                    {
                        "id": "3000013",
                        "title": "智能家居"
                    },
                    {
                        "id": "3000014",
                        "title": "淋浴房"
                    },
                    {
                        "id": "3000015",
                        "title": "晾衣架/杆"
                    },
                    {
                        "id": "3000016",
                        "title": "橱柜"
                    },
                    {
                        "id": "3000017",
                        "title": "厨卫电器"
                    },
                    {
                        "id": "3000018",
                        "title": "马桶"
                    },
                    {
                        "id": "3000019",
                        "title": "楼梯"
                    },
                    {
                        "id": "3000020",
                        "title": "水龙头"
                    },
                    {
                        "id": "3000021",
                        "title": "暖气地暖"
                    },
                    {
                        "id": "3000022",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200004",
                "title": "工装装修",
                "children": [
                    {
                        "id": "300001",
                        "title": "店铺装修"
                    },
                    {
                        "id": "300002",
                        "title": "商场装修"
                    },
                    {
                        "id": "300003",
                        "title": "办公室装修"
                    },
                    {
                        "id": "300004",
                        "title": "展厅装修"
                    },
                    {
                        "id": "300005",
                        "title": "厂房装修"
                    },
                    {
                        "id": "300006",
                        "title": "酒店装修"
                    },
                    {
                        "id": "300007",
                        "title": "写字楼装修"
                    },
                    {
                        "id": "300008",
                        "title": "学校装修"
                    },
                    {
                        "id": "300009",
                        "title": "服装店装修"
                    },
                    {
                        "id": "3000010",
                        "title": "餐厅装修"
                    },
                    {
                        "id": "3000011",
                        "title": "美容院装修"
                    },
                    {
                        "id": "3000012",
                        "title": "医院装修"
                    },
                    {
                        "id": "3000013",
                        "title": "售楼中心装修"
                    },
                    {
                        "id": "3000014",
                        "title": "咖啡厅装修"
                    },
                    {
                        "id": "3000015",
                        "title": "烟酒店装修"
                    },
                    {
                        "id": "3000016",
                        "title": "幼儿园装修"
                    },
                    {
                        "id": "3000017",
                        "title": "KTV装修"
                    },
                    {
                        "id": "3000018",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200005",
                "title": "拆旧",
                "children": []
            }
        ]
    },
    {
        "id": "100004",
        "title": "汽车服务",
        "children": [
            {
                "id": "200001",
                "title": "租车",
                "children": [
                    {
                        "id": "300001",
                        "title": "大巴"
                    },
                    {
                        "id": "300002",
                        "title": "商务车"
                    },
                    {
                        "id": "300003",
                        "title": "货车"
                    },
                    {
                        "id": "300004",
                        "title": "面包车"
                    },
                    {
                        "id": "300005",
                        "title": "豪华轿车"
                    },
                    {
                        "id": "300006",
                        "title": "带司机租车"
                    },
                    {
                        "id": "300007",
                        "title": "普通轿车"
                    },
                    {
                        "id": "300008",
                        "title": "婚车"
                    },
                    {
                        "id": "300009",
                        "title": "SUV/越野车"
                    },
                    {
                        "id": "3000010",
                        "title": "跑车"
                    },
                    {
                        "id": "3000011",
                        "title": "中巴"
                    },
                    {
                        "id": "3000012",
                        "title": "出租车"
                    },
                    {
                        "id": "3000013",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "道路救援",
                "children": [
                    {
                        "id": "300001",
                        "title": "道路拖车"
                    },
                    {
                        "id": "300002",
                        "title": "紧急送油"
                    },
                    {
                        "id": "300003",
                        "title": "汽车搭电"
                    },
                    {
                        "id": "300004",
                        "title": "流动补胎"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "陪驾陪练",
                "children": []
            },
            {
                "id": "200004",
                "title": "代驾",
                "children": [
                    {
                        "id": "300001",
                        "title": "长途代驾"
                    },
                    {
                        "id": "300002",
                        "title": "酒后代驾"
                    },
                    {
                        "id": "300003",
                        "title": "商务代驾"
                    },
                    {
                        "id": "300004",
                        "title": "司机外派"
                    },
                    {
                        "id": "300005",
                        "title": "旅游代驾"
                    },
                    {
                        "id": "300006",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200005",
                "title": "驾校",
                "children": [
                    {
                        "id": "300001",
                        "title": "B2大型货车"
                    },
                    {
                        "id": "300002",
                        "title": "A2牵引车"
                    },
                    {
                        "id": "300003",
                        "title": "A大型客车"
                    },
                    {
                        "id": "300004",
                        "title": "A3城市公交车"
                    },
                    {
                        "id": "300005",
                        "title": "B1中型客车"
                    },
                    {
                        "id": "300006",
                        "title": "C1手动档"
                    },
                    {
                        "id": "300007",
                        "title": "C2自动挡"
                    },
                    {
                        "id": "300008",
                        "title": "C3载货汽车"
                    },
                    {
                        "id": "300009",
                        "title": "C4三轮汽车"
                    },
                    {
                        "id": "3000010",
                        "title": "C5残疾人专用"
                    },
                    {
                        "id": "3000011",
                        "title": "E二轮摩托车"
                    },
                    {
                        "id": "3000012",
                        "title": "D三轮摩托车"
                    },
                    {
                        "id": "3000013",
                        "title": "F轻便摩托车"
                    },
                    {
                        "id": "3000014",
                        "title": "其他"
                    }
                ]
            }
        ]
    },
    {
        "id": "100005",
        "title": "婚庆摄影",
        "children": [
            {
                "id": "200001",
                "title": "婚庆",
                "children": [
                    {
                        "id": "300001",
                        "title": "新娘化妆"
                    },
                    {
                        "id": "300002",
                        "title": "婚庆公司"
                    },
                    {
                        "id": "300003",
                        "title": "婚礼策划"
                    },
                    {
                        "id": "300004",
                        "title": "婚车租赁"
                    },
                    {
                        "id": "300005",
                        "title": "婚纱礼服"
                    },
                    {
                        "id": "300006",
                        "title": "司仪"
                    },
                    {
                        "id": "300007",
                        "title": "婚礼跟拍"
                    },
                    {
                        "id": "300008",
                        "title": "婚礼用品"
                    },
                    {
                        "id": "300009",
                        "title": "喜糖"
                    },
                    {
                        "id": "3000010",
                        "title": "花车装饰"
                    },
                    {
                        "id": "3000011",
                        "title": "婚戒首饰"
                    },
                    {
                        "id": "3000012",
                        "title": "婚宴酒店"
                    },
                    {
                        "id": "3000013",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "摄影服务",
                "children": [
                    {
                        "id": "300001",
                        "title": "商业摄影"
                    },
                    {
                        "id": "300002",
                        "title": "摄像录影"
                    },
                    {
                        "id": "300003",
                        "title": "艺术照/写真"
                    },
                    {
                        "id": "300004",
                        "title": "婚纱摄影"
                    },
                    {
                        "id": "300005",
                        "title": "儿童摄影"
                    },
                    {
                        "id": "300006",
                        "title": "相片冲印"
                    },
                    {
                        "id": "300007",
                        "title": "相框相册制作"
                    },
                    {
                        "id": "300008",
                        "title": "其他"
                    }
                ]
            }
        ]
    },
    {
        "id": "100006",
        "title": "旅游服务",
        "children": [
            {
                "id": "200001",
                "title": "签证服务",
                "children": [
                    {
                        "id": "300001",
                        "title": "留学签证"
                    },
                    {
                        "id": "300002",
                        "title": "工作签证"
                    },
                    {
                        "id": "300003",
                        "title": "商务签证"
                    },
                    {
                        "id": "300004",
                        "title": "旅游签证"
                    },
                    {
                        "id": "300005",
                        "title": "移民签证"
                    },
                    {
                        "id": "300006",
                        "title": "探亲访友签证"
                    },
                    {
                        "id": "300007",
                        "title": "护照"
                    },
                    {
                        "id": "300008",
                        "title": "港澳通行证"
                    },
                    {
                        "id": "300009",
                        "title": "台湾通行证"
                    },
                    {
                        "id": "3000010",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "酒店预订",
                "children": [
                    {
                        "id": "300001",
                        "title": "经济型酒店预订"
                    },
                    {
                        "id": "300002",
                        "title": "青年旅舍预订"
                    },
                    {
                        "id": "300003",
                        "title": "度假村预订"
                    },
                    {
                        "id": "300004",
                        "title": "宾馆预订"
                    },
                    {
                        "id": "300005",
                        "title": "酒店用品"
                    },
                    {
                        "id": "300006",
                        "title": "星级酒店预订"
                    },
                    {
                        "id": "300007",
                        "title": "特价住宿"
                    },
                    {
                        "id": "300008",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "旅游度假",
                "children": [
                    {
                        "id": "300001",
                        "title": "周边游"
                    },
                    {
                        "id": "300002",
                        "title": "度假村"
                    },
                    {
                        "id": "300003",
                        "title": "农家乐"
                    },
                    {
                        "id": "300004",
                        "title": "烧烤"
                    },
                    {
                        "id": "300005",
                        "title": "旅行社"
                    },
                    {
                        "id": "300006",
                        "title": "国内游"
                    },
                    {
                        "id": "300007",
                        "title": "温泉"
                    },
                    {
                        "id": "300008",
                        "title": "出国游"
                    },
                    {
                        "id": "300009",
                        "title": "导游"
                    },
                    {
                        "id": "3000010",
                        "title": "港澳台游"
                    },
                    {
                        "id": "3000011",
                        "title": "采摘"
                    },
                    {
                        "id": "3000012",
                        "title": "漂流"
                    },
                    {
                        "id": "3000013",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200004",
                "title": "机票服务",
                "children": [
                    {
                        "id": "300001",
                        "title": "国内机票"
                    },
                    {
                        "id": "300002",
                        "title": "特价机票"
                    },
                    {
                        "id": "300003",
                        "title": "国际机票"
                    },
                    {
                        "id": "300004",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200005",
                "title": "移民服务",
                "children": []
            }
        ]
    },
    {
        "id": "100007",
        "title": "休闲服务",
        "children": [
            {
                "id": "200001",
                "title": "休闲娱乐",
                "children": [
                    {
                        "id": "300001",
                        "title": "KTV"
                    },
                    {
                        "id": "300002",
                        "title": "夜总会"
                    },
                    {
                        "id": "300003",
                        "title": "会所"
                    },
                    {
                        "id": "300004",
                        "title": "酒吧"
                    },
                    {
                        "id": "300005",
                        "title": "演唱会"
                    },
                    {
                        "id": "300006",
                        "title": "演出票务"
                    },
                    {
                        "id": "300007",
                        "title": "别墅聚会"
                    },
                    {
                        "id": "300008",
                        "title": "棋牌室"
                    },
                    {
                        "id": "300009",
                        "title": "密室"
                    },
                    {
                        "id": "3000010",
                        "title": "电影票"
                    },
                    {
                        "id": "3000011",
                        "title": "桌游吧"
                    },
                    {
                        "id": "3000012",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "运动健身",
                "children": [
                    {
                        "id": "300001",
                        "title": "健身中心"
                    },
                    {
                        "id": "300002",
                        "title": "足球场"
                    },
                    {
                        "id": "300003",
                        "title": "篮球场"
                    },
                    {
                        "id": "300004",
                        "title": "乒乓球馆"
                    },
                    {
                        "id": "300005",
                        "title": "瑜伽馆"
                    },
                    {
                        "id": "300006",
                        "title": "游泳馆"
                    },
                    {
                        "id": "300007",
                        "title": "羽毛球馆"
                    },
                    {
                        "id": "300008",
                        "title": "网球场"
                    },
                    {
                        "id": "300009",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "按摩足浴",
                "children": [
                    {
                        "id": "300001",
                        "title": "按摩"
                    },
                    {
                        "id": "300002",
                        "title": "足疗"
                    },
                    {
                        "id": "300003",
                        "title": "火疗拔罐"
                    },
                    {
                        "id": "300004",
                        "title": "刮痧"
                    },
                    {
                        "id": "300005",
                        "title": "纤体瘦身"
                    },
                    {
                        "id": "300006",
                        "title": "美容美体"
                    },
                    {
                        "id": "300007",
                        "title": "洗浴"
                    },
                    {
                        "id": "300008",
                        "title": "针灸推拿"
                    }
                ]
            }
        ]
    },
    {
        "id": "100008",
        "title": "其他生活服务",
        "children": [
            {
                "id": "200001",
                "title": "鲜花盆景",
                "children": [
                    {
                        "id": "300001",
                        "title": "绿植/盆栽"
                    },
                    {
                        "id": "300002",
                        "title": "园艺用品"
                    },
                    {
                        "id": "300003",
                        "title": "鲜花"
                    },
                    {
                        "id": "300004",
                        "title": "仿真花"
                    },
                    {
                        "id": "300005",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "本地名站",
                "children": [
                    {
                        "id": "300001",
                        "title": "购物"
                    },
                    {
                        "id": "300002",
                        "title": "新闻"
                    },
                    {
                        "id": "300003",
                        "title": "论坛"
                    },
                    {
                        "id": "300004",
                        "title": "交通地图"
                    },
                    {
                        "id": "300005",
                        "title": "游戏"
                    },
                    {
                        "id": "300006",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "白事服务",
                "children": []
            },
            {
                "id": "200004",
                "title": "商品出售",
                "children": [
                    {
                        "id": "300001",
                        "title": "办公家具"
                    },
                    {
                        "id": "300002",
                        "title": "家居日用"
                    },
                    {
                        "id": "300003",
                        "title": "办公用品"
                    },
                    {
                        "id": "300004",
                        "title": "数码产品"
                    },
                    {
                        "id": "300005",
                        "title": "家用电器"
                    },
                    {
                        "id": "300006",
                        "title": "服饰箱包"
                    },
                    {
                        "id": "300007",
                        "title": "笔记本"
                    },
                    {
                        "id": "300008",
                        "title": "乐器/运动"
                    },
                    {
                        "id": "300009",
                        "title": "家用家具"
                    },
                    {
                        "id": "3000010",
                        "title": "手机"
                    },
                    {
                        "id": "3000011",
                        "title": "图书音像"
                    },
                    {
                        "id": "3000012",
                        "title": "母婴用品"
                    },
                    {
                        "id": "3000013",
                        "title": "台式电脑"
                    },
                    {
                        "id": "3000014",
                        "title": "手机配件"
                    },
                    {
                        "id": "3000015",
                        "title": "门票卡券"
                    },
                    {
                        "id": "3000016",
                        "title": "照相机"
                    },
                    {
                        "id": "3000017",
                        "title": "平板电脑"
                    }
                ]
            },
            {
                "id": "200005",
                "title": "工艺鉴定",
                "children": []
            },
            {
                "id": "200006",
                "title": "其他服务",
                "children": []
            }
        ]
    }
]
// 本地商务服务
let localBusiness = [
    {
        "id": "100001",
        "title": "投资理财",
        "children": [
            {
                "id": "200001",
                "title": "投资担保",
                "children": [
                    {
                        "id": "300001",
                        "title": "房产抵押贷款"
                    },
                    {
                        "id": "300002",
                        "title": "汽车抵押贷款"
                    },
                    {
                        "id": "300003",
                        "title": "质押贷款"
                    },
                    {
                        "id": "300004",
                        "title": "应急贷款"
                    },
                    {
                        "id": "300005",
                        "title": "买车贷款"
                    },
                    {
                        "id": "300006",
                        "title": "无抵押贷款"
                    },
                    {
                        "id": "300007",
                        "title": "企业/个体户贷款"
                    },
                    {
                        "id": "300008",
                        "title": "买房贷款"
                    },
                    {
                        "id": "300009",
                        "title": "证券抵押贷款"
                    },
                    {
                        "id": "3000010",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "投资理财",
                "children": [
                    {
                        "id": "300001",
                        "title": "期货投资"
                    },
                    {
                        "id": "300002",
                        "title": "股票投资"
                    },
                    {
                        "id": "300003",
                        "title": "理财产品"
                    },
                    {
                        "id": "300004",
                        "title": "证券投资"
                    },
                    {
                        "id": "300005",
                        "title": "黄金投资"
                    },
                    {
                        "id": "300006",
                        "title": "基金投资"
                    },
                    {
                        "id": "300007",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "财务会计",
                "children": [
                    {
                        "id": "300001",
                        "title": "代理记账"
                    },
                    {
                        "id": "300002",
                        "title": "会计"
                    },
                    {
                        "id": "300003",
                        "title": "审计"
                    },
                    {
                        "id": "300004",
                        "title": "纳税申报"
                    },
                    {
                        "id": "300005",
                        "title": "资产评估"
                    },
                    {
                        "id": "300006",
                        "title": "验资"
                    },
                    {
                        "id": "300007",
                        "title": "税务咨询"
                    },
                    {
                        "id": "300008",
                        "title": "工程造价"
                    },
                    {
                        "id": "300009",
                        "title": "其他"
                    }
                ]
            }
        ]
    },
    {
        "id": "100002",
        "title": "工业设备 设备租赁",
        "children": [
            {
                "id": "200001",
                "title": "工业设备",
                "children": [
                    {
                        "id": "300001",
                        "title": "工程机械"
                    },
                    {
                        "id": "300002",
                        "title": "发电设备"
                    },
                    {
                        "id": "300003",
                        "title": "化工设备"
                    },
                    {
                        "id": "300004",
                        "title": "环保设备"
                    },
                    {
                        "id": "300005",
                        "title": "冶金设备"
                    },
                    {
                        "id": "300006",
                        "title": "机床"
                    },
                    {
                        "id": "300007",
                        "title": "橡塑机械"
                    },
                    {
                        "id": "300008",
                        "title": "食品机械"
                    },
                    {
                        "id": "300009",
                        "title": "印刷设备"
                    },
                    {
                        "id": "3000010",
                        "title": "造纸设备"
                    },
                    {
                        "id": "3000011",
                        "title": "矿业机械"
                    },
                    {
                        "id": "3000012",
                        "title": "锅炉"
                    },
                    {
                        "id": "3000013",
                        "title": "包装机械"
                    },
                    {
                        "id": "3000014",
                        "title": "农业设备"
                    },
                    {
                        "id": "3000015",
                        "title": "木工设备"
                    },
                    {
                        "id": "3000016",
                        "title": "电机"
                    },
                    {
                        "id": "3000017",
                        "title": "仪器仪表"
                    },
                    {
                        "id": "3000018",
                        "title": "交通机械"
                    },
                    {
                        "id": "3000019",
                        "title": "纺织设备"
                    },
                    {
                        "id": "3000020",
                        "title": "流体设备"
                    },
                    {
                        "id": "3000021",
                        "title": "制药设备"
                    },
                    {
                        "id": "3000022",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "设备租赁",
                "children": [
                    {
                        "id": "300001",
                        "title": "机械设备"
                    },
                    {
                        "id": "300002",
                        "title": "物品租赁"
                    },
                    {
                        "id": "300003",
                        "title": "叉车"
                    },
                    {
                        "id": "300004",
                        "title": "庆典会展用品"
                    },
                    {
                        "id": "300005",
                        "title": "家具"
                    },
                    {
                        "id": "300006",
                        "title": "办公用品"
                    },
                    {
                        "id": "300007",
                        "title": "灯光音响"
                    },
                    {
                        "id": "300008",
                        "title": "服装"
                    },
                    {
                        "id": "300009",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "设备维修",
                "children": [
                    {
                        "id": "300001",
                        "title": "打印机维修"
                    },
                    {
                        "id": "300002",
                        "title": "一体机维修"
                    },
                    {
                        "id": "300003",
                        "title": "复印机维修"
                    },
                    {
                        "id": "300004",
                        "title": "传真机维修"
                    },
                    {
                        "id": "300005",
                        "title": "扫描仪维修"
                    },
                    {
                        "id": "300006",
                        "title": "投影仪维修"
                    },
                    {
                        "id": "300007",
                        "title": "绘图仪维修"
                    },
                    {
                        "id": "300008",
                        "title": "考勤机维修"
                    },
                    {
                        "id": "300009",
                        "title": "碎纸机维修"
                    },
                    {
                        "id": "3000010",
                        "title": "印刷机维修"
                    },
                    {
                        "id": "3000011",
                        "title": "其他"
                    }
                ]
            }
        ]
    },
    {
        "id": "100003",
        "title": "公司注册 网站建设",
        "children": [
            {
                "id": "200001",
                "title": "公司注册",
                "children": [
                    {
                        "id": "300001",
                        "title": "公司注册"
                    },
                    {
                        "id": "300002",
                        "title": "商标注册"
                    },
                    {
                        "id": "300003",
                        "title": "工商年检"
                    },
                    {
                        "id": "300004",
                        "title": "公司注销"
                    },
                    {
                        "id": "300005",
                        "title": "资质认证"
                    },
                    {
                        "id": "300006",
                        "title": "公司转让"
                    },
                    {
                        "id": "300007",
                        "title": "一般纳税人申请"
                    },
                    {
                        "id": "300008",
                        "title": "验资开户"
                    },
                    {
                        "id": "300009",
                        "title": "专利注册"
                    },
                    {
                        "id": "3000010",
                        "title": "外资公司注册"
                    },
                    {
                        "id": "3000011",
                        "title": "海外公司注册"
                    },
                    {
                        "id": "3000012",
                        "title": "专项审批"
                    },
                    {
                        "id": "3000013",
                        "title": "香港公司注册"
                    },
                    {
                        "id": "3000014",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "网站建设",
                "children": [
                    {
                        "id": "300001",
                        "title": "网站推广"
                    },
                    {
                        "id": "300002",
                        "title": "app开发"
                    },
                    {
                        "id": "300003",
                        "title": "网站建设"
                    },
                    {
                        "id": "300004",
                        "title": "软件开发"
                    },
                    {
                        "id": "300005",
                        "title": "网站外包"
                    },
                    {
                        "id": "300006",
                        "title": "服务器"
                    },
                    {
                        "id": "300007",
                        "title": "企业邮箱"
                    },
                    {
                        "id": "300008",
                        "title": "网站维护"
                    },
                    {
                        "id": "300009",
                        "title": "域名注册"
                    },
                    {
                        "id": "3000010",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "网络布线",
                "children": [
                    {
                        "id": "300001",
                        "title": "网络布线"
                    },
                    {
                        "id": "300002",
                        "title": "安防监控"
                    },
                    {
                        "id": "300003",
                        "title": "弱电工程"
                    },
                    {
                        "id": "300004",
                        "title": "系统集成"
                    },
                    {
                        "id": "300005",
                        "title": "网络维护"
                    },
                    {
                        "id": "300006",
                        "title": "IT外包"
                    },
                    {
                        "id": "300007",
                        "title": "光纤宽带"
                    },
                    {
                        "id": "300008",
                        "title": "机房建设"
                    },
                    {
                        "id": "300009",
                        "title": "其他"
                    }
                ]
            }
        ]
    },
    {
        "id": "100004",
        "title": "货运物流 物品批发",
        "children": [
            {
                "id": "200001",
                "title": "货运物流",
                "children": [
                    {
                        "id": "300001",
                        "title": "托运服务"
                    },
                    {
                        "id": "300002",
                        "title": "国内货运"
                    },
                    {
                        "id": "300003",
                        "title": "货运运输"
                    },
                    {
                        "id": "300004",
                        "title": "国际货运"
                    },
                    {
                        "id": "300005",
                        "title": "仓储"
                    },
                    {
                        "id": "300006",
                        "title": "货运代理"
                    },
                    {
                        "id": "300007",
                        "title": "进出口报关"
                    },
                    {
                        "id": "300008",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "物品批发",
                "children": [
                    {
                        "id": "300001",
                        "title": "化妆品"
                    },
                    {
                        "id": "300002",
                        "title": "办公用品"
                    },
                    {
                        "id": "300003",
                        "title": "食品"
                    },
                    {
                        "id": "300004",
                        "title": "服装"
                    },
                    {
                        "id": "300005",
                        "title": "电子元器件"
                    },
                    {
                        "id": "300006",
                        "title": "礼品"
                    },
                    {
                        "id": "300007",
                        "title": "手机数码"
                    },
                    {
                        "id": "300008",
                        "title": "母婴用品"
                    },
                    {
                        "id": "300009",
                        "title": "灯具照明"
                    },
                    {
                        "id": "3000010",
                        "title": "运动用品"
                    },
                    {
                        "id": "3000011",
                        "title": "饰品"
                    },
                    {
                        "id": "3000012",
                        "title": "箱包"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "代运营/托管",
                "children": [
                    {
                        "id": "300001",
                        "title": "网店"
                    },
                    {
                        "id": "300002",
                        "title": "网站"
                    },
                    {
                        "id": "300003",
                        "title": "其它"
                    },
                    {
                        "id": "300004",
                        "title": "微信公众号"
                    }
                ]
            },
            {
                "id": "200004",
                "title": "快递服务",
                "children": [
                    {
                        "id": "300001",
                        "title": "国际快递"
                    },
                    {
                        "id": "300002",
                        "title": "国内快递"
                    },
                    {
                        "id": "300003",
                        "title": "同城快递"
                    },
                    {
                        "id": "300004",
                        "title": "其他"
                    }
                ]
            }
        ]
    },
    {
        "id": "100005",
        "title": "农林牧副渔",
        "children": [
            {
                "id": "200001",
                "title": "农林牧副渔",
                "children": [
                    {
                        "id": "300001",
                        "title": "畜禽养殖"
                    },
                    {
                        "id": "300002",
                        "title": "农机具/设备"
                    },
                    {
                        "id": "300003",
                        "title": "农产品加工/代理"
                    },
                    {
                        "id": "300004",
                        "title": "农作物"
                    },
                    {
                        "id": "300005",
                        "title": "园林花卉"
                    },
                    {
                        "id": "300006",
                        "title": "水产"
                    },
                    {
                        "id": "300007",
                        "title": "动植物种苗"
                    },
                    {
                        "id": "300008",
                        "title": "饲料"
                    },
                    {
                        "id": "300009",
                        "title": "其他"
                    }
                ]
            }
        ]
    },
    {
        "id": "100006",
        "title": "印刷包装 广告媒体",
        "children": [
            {
                "id": "200001",
                "title": "印刷包装",
                "children": [
                    {
                        "id": "300001",
                        "title": "纸类印刷"
                    },
                    {
                        "id": "300002",
                        "title": "印刷"
                    },
                    {
                        "id": "300003",
                        "title": "不干胶印刷"
                    },
                    {
                        "id": "300004",
                        "title": "书刊印刷"
                    },
                    {
                        "id": "300005",
                        "title": "制卡"
                    },
                    {
                        "id": "300006",
                        "title": "名片印刷"
                    },
                    {
                        "id": "300007",
                        "title": "防伪印刷"
                    },
                    {
                        "id": "300008",
                        "title": "易拉宝制作"
                    },
                    {
                        "id": "300009",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "广告媒体",
                "children": [
                    {
                        "id": "300001",
                        "title": "广告位招租"
                    },
                    {
                        "id": "300002",
                        "title": "DM广告"
                    },
                    {
                        "id": "300003",
                        "title": "导视牌广告"
                    },
                    {
                        "id": "300004",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "礼品定制",
                "children": [
                    {
                        "id": "300001",
                        "title": "礼品定制"
                    },
                    {
                        "id": "300002",
                        "title": "工艺品"
                    },
                    {
                        "id": "300003",
                        "title": "商务礼品"
                    },
                    {
                        "id": "300004",
                        "title": "杯子茶具"
                    },
                    {
                        "id": "300005",
                        "title": "奖杯证书"
                    },
                    {
                        "id": "300006",
                        "title": "文具本册"
                    },
                    {
                        "id": "300007",
                        "title": "服装"
                    },
                    {
                        "id": "300008",
                        "title": "小家电"
                    },
                    {
                        "id": "300009",
                        "title": "家居家纺"
                    },
                    {
                        "id": "3000010",
                        "title": "箱包皮具"
                    },
                    {
                        "id": "3000011",
                        "title": "数码电子"
                    },
                    {
                        "id": "3000012",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200004",
                "title": "喷绘招牌",
                "children": [
                    {
                        "id": "300001",
                        "title": "LED显示屏"
                    },
                    {
                        "id": "300002",
                        "title": "户外广告"
                    },
                    {
                        "id": "300003",
                        "title": "喷绘制作"
                    },
                    {
                        "id": "300004",
                        "title": "标牌制作"
                    },
                    {
                        "id": "300005",
                        "title": "灯箱制作"
                    },
                    {
                        "id": "300006",
                        "title": "背景/形象墙"
                    },
                    {
                        "id": "300007",
                        "title": "条幅/锦旗/奖牌"
                    },
                    {
                        "id": "300008",
                        "title": "亮化工程"
                    },
                    {
                        "id": "300009",
                        "title": "展架制作"
                    },
                    {
                        "id": "3000010",
                        "title": "其他"
                    }
                ]
            }
        ]
    },
    {
        "id": "100007",
        "title": "庆典演出 设计策划",
        "children": [
            {
                "id": "200001",
                "title": "庆典演出",
                "children": [
                    {
                        "id": "300001",
                        "title": "场地布置"
                    },
                    {
                        "id": "300002",
                        "title": "庆典公司"
                    },
                    {
                        "id": "300003",
                        "title": "灯光音响"
                    },
                    {
                        "id": "300004",
                        "title": "化妆"
                    },
                    {
                        "id": "300005",
                        "title": "礼仪模特"
                    },
                    {
                        "id": "300006",
                        "title": "乐队演出"
                    },
                    {
                        "id": "300007",
                        "title": "主持服务"
                    },
                    {
                        "id": "300008",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "设计策划",
                "children": [
                    {
                        "id": "300001",
                        "title": "广告策划"
                    },
                    {
                        "id": "300002",
                        "title": "活动策划"
                    },
                    {
                        "id": "300003",
                        "title": "工业设计"
                    },
                    {
                        "id": "300004",
                        "title": "景观设计"
                    },
                    {
                        "id": "300005",
                        "title": "平面设计"
                    },
                    {
                        "id": "300006",
                        "title": "网页设计"
                    },
                    {
                        "id": "300007",
                        "title": "室内设计"
                    },
                    {
                        "id": "300008",
                        "title": "VI/Logo设计"
                    },
                    {
                        "id": "300009",
                        "title": "视频制作"
                    },
                    {
                        "id": "3000010",
                        "title": "服装设计"
                    },
                    {
                        "id": "3000011",
                        "title": "影视制作"
                    },
                    {
                        "id": "3000012",
                        "title": "动画制作"
                    },
                    {
                        "id": "3000013",
                        "title": "名片设计"
                    },
                    {
                        "id": "3000014",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "展览展会",
                "children": [
                    {
                        "id": "300001",
                        "title": "展会策划"
                    },
                    {
                        "id": "300002",
                        "title": "展会布置"
                    },
                    {
                        "id": "300003",
                        "title": "展厅设计"
                    },
                    {
                        "id": "300004",
                        "title": "展柜制作"
                    },
                    {
                        "id": "300005",
                        "title": "展板制作"
                    },
                    {
                        "id": "300006",
                        "title": "气球拱门"
                    },
                    {
                        "id": "300007",
                        "title": "礼炮烟花"
                    },
                    {
                        "id": "300008",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200004",
                "title": "餐饮美食",
                "children": []
            }
        ]
    },
    {
        "id": "100008",
        "title": "律师服务 招商加盟",
        "children": [
            {
                "id": "200001",
                "title": "律师服务",
                "children": [
                    {
                        "id": "300001",
                        "title": "婚姻家庭"
                    },
                    {
                        "id": "300002",
                        "title": "刑事辩护"
                    },
                    {
                        "id": "300003",
                        "title": "房产纠纷"
                    },
                    {
                        "id": "300004",
                        "title": "法律援助"
                    },
                    {
                        "id": "300005",
                        "title": "合同纠纷"
                    },
                    {
                        "id": "300006",
                        "title": "债务纠纷"
                    },
                    {
                        "id": "300007",
                        "title": "交通事故"
                    },
                    {
                        "id": "300008",
                        "title": "知识产权"
                    },
                    {
                        "id": "300009",
                        "title": "劳动纠纷"
                    },
                    {
                        "id": "3000010",
                        "title": "建筑工程"
                    },
                    {
                        "id": "3000011",
                        "title": "医疗事故"
                    },
                    {
                        "id": "3000012",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200002",
                "title": "招商加盟",
                "children": [
                    {
                        "id": "300001",
                        "title": "微商加盟"
                    },
                    {
                        "id": "300002",
                        "title": "产品代理"
                    },
                    {
                        "id": "300003",
                        "title": "餐饮加盟"
                    },
                    {
                        "id": "300004",
                        "title": "美容保健"
                    },
                    {
                        "id": "300005",
                        "title": "网店加盟"
                    },
                    {
                        "id": "300006",
                        "title": "零售业"
                    },
                    {
                        "id": "300007",
                        "title": "教育培训"
                    },
                    {
                        "id": "300008",
                        "title": "建材加盟"
                    },
                    {
                        "id": "300009",
                        "title": "干洗加盟"
                    },
                    {
                        "id": "3000010",
                        "title": "母婴用品"
                    },
                    {
                        "id": "3000011",
                        "title": "快递物流"
                    },
                    {
                        "id": "3000012",
                        "title": "服装加盟"
                    },
                    {
                        "id": "3000013",
                        "title": "旅游/票务"
                    },
                    {
                        "id": "3000014",
                        "title": "家具加盟"
                    },
                    {
                        "id": "3000015",
                        "title": "汽车服务"
                    },
                    {
                        "id": "3000016",
                        "title": "礼品饰品"
                    },
                    {
                        "id": "3000017",
                        "title": "农业养殖"
                    },
                    {
                        "id": "3000018",
                        "title": "箱包加盟"
                    },
                    {
                        "id": "3000019",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200003",
                "title": "翻译服务",
                "children": [
                    {
                        "id": "300001",
                        "title": "笔译"
                    },
                    {
                        "id": "300002",
                        "title": "同声传译"
                    },
                    {
                        "id": "300003",
                        "title": "口译"
                    },
                    {
                        "id": "300004",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200004",
                "title": "保险服务",
                "children": [
                    {
                        "id": "300001",
                        "title": "健康保险"
                    },
                    {
                        "id": "300002",
                        "title": "意外保险"
                    },
                    {
                        "id": "300003",
                        "title": "财产保险"
                    },
                    {
                        "id": "300004",
                        "title": "汽车保险"
                    },
                    {
                        "id": "300005",
                        "title": "投资型保险"
                    },
                    {
                        "id": "300006",
                        "title": "旅游保险"
                    },
                    {
                        "id": "300007",
                        "title": "人寿保险"
                    },
                    {
                        "id": "300008",
                        "title": "少儿保险"
                    },
                    {
                        "id": "300009",
                        "title": "其他"
                    }
                ]
            },
            {
                "id": "200005",
                "title": "咨询服务",
                "children": [
                    {
                        "id": "300001",
                        "title": "代缴社保/公积金"
                    },
                    {
                        "id": "300002",
                        "title": "市场调查"
                    },
                    {
                        "id": "300003",
                        "title": "户口咨询"
                    },
                    {
                        "id": "300004",
                        "title": "心理咨询"
                    }
                ]
            }
        ]
    }
]
// 热门列表
let hotList = [
    { id: "400001", title: "工装" },
    { id: "400002", title: "物品回收" },
    { id: "400003", title: "租车" },
    { id: "400004", title: "开锁修锁" },
    { id: "400005", title: "保洁清洗" },
    { id: "400006", title: "管道维修" },
    { id: "400007", title: "房屋维修" },
    { id: "400008", title: "驾校" }
]
// 模拟数据 end

// 初始化热门列表
let hotListBox = document.querySelector(".hot-list");
initHotListFunction(hotListBox,hotList,"list.html")

// 初始化菜单&初始化下部正文菜单
let leftMenuBox = document.querySelector("#leftMenu");
let serviceContent = document.querySelector("#serviceContent");

// 根据 url地址的 # hash 值确定当前显示的元素内容
// 对应index.html 页面中的 本地生活服务 和 本地商务服务 a 标签
const changeTagFun = function () {
    let hashTarget = window.location.hash;
    // 默认为 lift 本地生活服务
    hashTarget = hashTarget == "" ? "life" : hashTarget.replace(/^#/, "");
    document.querySelector(`[data-target='${hashTarget}']`).click();
    document.querySelectorAll(`[data-target`).forEach(item => {
        item.classList.remove("active")
    })
    document.querySelector(`[data-target='${hashTarget}']`).classList.add("active");

    // 页面内容制空
    initLeftMenusFunction(leftMenuBox, [],"list.html");
    initServiceContentFunction(serviceContent, [],"list.html");

    if (hashTarget == "life") {
        // 测试-start
        // initLeftMenusFunction(leftMenuBox,[]); // 测试数组为空
        // initLeftMenusFunction(leftMenuBox,[
        //     {id:1,title:"asdad"}
        // ]); // 测试二级菜单数据为空
        // 测试-end
        // 本地生活服务
        initLeftMenusFunction(leftMenuBox, localLife,"list.html");

        // 测试-start
        // initServiceContentFunction(serviceContent,[]);
        // initServiceContentFunction(serviceContent,[
        //     {id:1,title:"asdad"}
        // ]); // 测试二级菜单数据为空
        // 测试-end
        // 页面正文菜单
        // 模拟数据使用和左侧菜单数据相同-实际开发请自行选择设定的数据
        initServiceContentFunction(serviceContent, localLife.slice(0, 5),"list.html");
    }
    if (hashTarget == "business") {
        // 本地商务服务
        initLeftMenusFunction(leftMenuBox, localBusiness,"list.html");
        initServiceContentFunction(serviceContent, localBusiness.slice(0, 5),"list.html");

    }
}
// 监控 url地址 # hash 值变化更新方法
window.onhashchange = changeTagFun;
changeTagFun();