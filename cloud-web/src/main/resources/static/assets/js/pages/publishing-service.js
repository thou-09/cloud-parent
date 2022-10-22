/*
funtionName:initServiceSelectFunction 
desc:服务级联选择器初始化方法 - 通过数据自动完成级联选择器切换和列表更新操作
                              任意选择器未选中 value 值默认为 -1
params:
    boxDom:需要级联选中器的父级Dom元素-该方法会自动加载内部所有select元素，并根据顺序自动构建级联关系
    parents:级联选择器第一个 select 的初始默认值
    callback:级联选择器第一个 select 的change事件回调函数
            该函数默认提供两个参数：
                    第一个参数：级联选择器第一个 select DOM 对象，可通过value属性获取选中分类ID
                    第二个参数：级联选择器后续 select 列表重置方法，通过传入如下结构数据构建列表值
                                [
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
*/
const initServiceSelectFunction = function(boxDom,parents=[],callback=function(){}){
    if (!(boxDom instanceof Element)) {
        console.error("方法initServiceSelectFunction接收的参数：'", boxDom, "'不是一个有效的DOM容器");
        return;
    }
    let selectOne = boxDom.querySelector("select:nth-child(1)");
    let selectOneOption = new Option("请选择服务类型",-1);
    selectOne.innerHTML = "";
    selectOne.appendChild( selectOneOption );

    let selectTwo = boxDom.querySelector("select:nth-child(2)");
    let selectTwoOption = new Option("请选择服务范围",-1)
    selectTwoOption.list = [];
    selectTwo.innerHTML = "";
    selectTwo.appendChild( selectTwoOption );

    let selectThree = boxDom.querySelector("select:nth-child(3)");
    let selectThreeOption = new Option("请选择二级服务范围",-1)
    selectThreeOption.list = [];
    selectThree.innerHTML = "";
    selectThree.appendChild( selectThreeOption );

    let selectFour = boxDom.querySelector(".select-three");
    let selectFourTitle = document.createElement("p");
    selectFourTitle.innerHTML = "请选择三级服务范围";
    selectFour.innerHTML = "";
    selectFour.appendChild( selectFourTitle );

    let selectFragmenOne = document.createDocumentFragment();
    parents.forEach(item=>{
        selectFragmenOne.appendChild( new Option(item.title,item.id) );
    })
    selectOne.appendChild(selectFragmenOne);
    selectOne.addEventListener("change",callback.bind(selectOne,selectOne,function(list){
        selectTwo.innerHTML = "";
        selectTwo.appendChild( selectTwoOption );
        selectThree.innerHTML = "";
        selectThree.appendChild( selectThreeOption );
        selectFour.innerHTML = "";
        selectFour.appendChild( selectFourTitle );

        let selectFragmenTwo = document.createDocumentFragment();
        list.forEach(item=>{
            let tempOption = new Option(item.title,item.id)
            tempOption.list = item.children;
            selectFragmenTwo.appendChild( tempOption );
        })
        selectTwo.appendChild( selectFragmenTwo );
    }))

    selectTwo.addEventListener("change",function(){
        let options = this.options;
        let si = this.options.selectedIndex;
        let list = options[si].list;
        selectThree.innerHTML = "";
        selectThree.appendChild( selectThreeOption );
        selectFour.innerHTML = "";
        selectFour.appendChild( selectFourTitle );

        let selectFragmenThree = document.createDocumentFragment();
        list.forEach(item=>{
            let tempOption = new Option(item.title,item.id)
            tempOption.list = item.children;
            selectFragmenThree.appendChild( tempOption );
        })
        selectThree.appendChild( selectFragmenThree );
    })

    selectThree.addEventListener("change",function(){
        let options = this.options;
        let si = this.options.selectedIndex;
        let list = options[si].list;
        selectFour.innerHTML = "";
        selectFour.appendChild( selectFourTitle );

        let selectFragmenFour = document.createDocumentFragment();
        list.forEach(item=>{
            // <label><input type="checkbox" name="" value="全南京">全南京</label>
            let labelDom = document.createElement("label");
            let checkBoxDom = document.createElement("input");
            checkBoxDom.type = "checkbox";
            checkBoxDom.name = "serviceList";
            checkBoxDom.value = item.id+","+item.title;
            let textNode = document.createTextNode(item.title);
            labelDom.appendChild(checkBoxDom);
            labelDom.appendChild(textNode);
            selectFragmenFour.appendChild( labelDom );
        })
        selectFour.appendChild( selectFragmenFour );
    })
}

/*
funtionName:initChooseFileFunction 
desc:列表文件上传功能初始化，该功能为单次文件上传及时处理方法
params:
    fileBtnDom:文件上传按钮的DOM元素对象
    callback:文件选择后的回调执行函数
             该回调函数默认提供三个参数
                    第一个参数为 File 文件对象，为选择的文件信息，用于ajax文件上传
                    第二个参数为 ajax 上传成功后的回调方法，通过执行该方法可以保留页面上传图片的显示结果
                            该函数参数执行时，可以继续提供一个function参数，用于绑定页面中的删除按钮功能
                    第三个参数为 ajax 上传失败后的回调方法，通过执行该方法可以移除页面上传图片的显示结果

*/
const initChooseFileFunction = function(fileBtnDom,callback=function(){}){
    if (!(fileBtnDom instanceof Element)) {
        console.error("方法initChooseFileFunction接收的参数：'", fileBtnDom, "'不是一个有效的DOM");
        return;
    }
    let inputFileDom = fileBtnDom.querySelector("input[type=file]")
    inputFileDom.addEventListener("change",function(){
        let file = this.files[0]
        let spanDom = document.createElement("span");
        spanDom.classList.add("img-choose-list");

        let imgChoose = document.createElement("img");
        imgChoose.classList.add("img-choose");
        imgChoose.src = window.URL.createObjectURL(file);

        let imgLoading = document.createElement("img");
        imgLoading.classList.add("img-loading");
        imgLoading.src = "./assets/img/loading2.gif";

        let imgClose = document.createElement("img");
        imgClose.classList.add("img-close");
        imgClose.src = "./assets/img/close.png";

        spanDom.appendChild(imgChoose);
        spanDom.appendChild(imgLoading);
        spanDom.appendChild(imgClose);
        
        fileBtnDom.parentElement.insertBefore(spanDom,fileBtnDom);

        callback(file,function(closeFunction){
            inputFileDom.value = "";
            spanDom.removeChild(imgLoading);
            imgClose.addEventListener("click",function(){
                fileBtnDom.parentElement.removeChild(spanDom);
                closeFunction();
            })
        },function(){
            inputFileDom.value = "";
            fileBtnDom.parentElement.removeChild(spanDom);
        })
    })
}

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

let serviceType = [
    {
        "id": "100001",
        "title": "本地生活服务",
    },
    {
        "id": "100002",
        "title": "本地商务服务",
    }
]
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

let serviceSelectParent = document.querySelector("#serviceSelect");
initServiceSelectFunction(serviceSelectParent,serviceType,function(selectDom,resetService){
    console.log(selectDom.value);
    let serviceTypeId = selectDom.value;
    if(serviceTypeId==-1){
        resetService([]); // 级联选择器制空
    }else if(serviceTypeId==100001){
        // 根据分类ID发送ajax请求加载后续级联数据

        // 根据请求结果重置后续级联数据
        resetService(localLife);
    }else if(serviceTypeId==100002){
        // 根据分类ID发送ajax请求加载后续级联数据

        // 根据请求结果重置后续级联数据
        resetService(localBusiness);
    }
});

let chooseFileInput = document.querySelector("#chooseFileInput");

// 自行定义文件路径存储集合 - 用于后续服务信息添加
let filesPath = [];

initChooseFileFunction(chooseFileInput,function(file,done,fail){
    console.log(file); // 需要上传的文件信息

    // 模拟ajax文件上传处理
    setTimeout(()=>{
        let num = Math.random(); // 通过随机值模拟上传成功还是上传失败
        if(num>0.4){
            // 上传成功
            let path = Math.random(); // 模拟请求返回的上传成功后的文件路径数据
            let i = filesPath.push(path)-1  // push方法会返回数据存储后数组的长度，-1 即为当前元素的下标
            // 执行success方法固定页面展示效果
            done(function(){
                // 该方法用于绑定删除按钮事件
                // 如果上传成功的图片被点击了删除按钮，通过该回调可以在 filesPath 数组中删除对应路径
                filesPath.splice(i,1);
            });
        }else{
            // 上传失败;
            fail(); // 执行fail方法移除页面展示效果
        }
    },2000)
})