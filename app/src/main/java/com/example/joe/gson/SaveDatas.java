package com.example.joe.gson;

import com.example.joe.db.FoodName;

public class SaveDatas {
    public SaveDatas() {
    }
    public void initFoodName(){
        FoodName rice=new FoodName(1,"米饭",116,100);
         rice.save();
        FoodName mantou=new FoodName(2,"馒头",221,100);
        mantou.save();
        FoodName baizhou=new FoodName(3,"白粥",32,100);
        baizhou.save();
        FoodName mianbao=new FoodName(4,"面包",312,100);
        mianbao.save();
        FoodName miantiao=new FoodName(5,"面条",284,100);
        miantiao.save();
        FoodName shuijiao=new FoodName(6,"水饺",213,100);
       shuijiao.save();
        FoodName baozi=new FoodName(7,"包子",227,100);
        baozi.save();
        FoodName mifen=new FoodName(8,"米粉",346,100);
       mifen.save();
        FoodName jianbing=new FoodName(9,"煎饼",336,100);
        jianbing.save();
        FoodName hongshu=new FoodName(10,"红薯",99,100);
        hongshu.save();
    }
    //初始化蔬菜数据
    public void initFoodName2(){
        FoodName qingcai=new FoodName(11,"青菜",15,100);
        qingcai.save();
        FoodName guacai=new FoodName(12,"瓜菜",13,100);
        guacai.save();
        FoodName dabaicai=new FoodName(13,"大白菜",17,100);
        dabaicai.save();
        FoodName qiezi=new FoodName(14,"茄子",21,100);
        qiezi.save();
        FoodName mogu=new FoodName(15,"蘑菇",20,100);
        mogu.save();
        FoodName huanggua=new FoodName(16,"黄瓜",15,100);
        huanggua.save();
        FoodName bailoubo=new FoodName(17,"白萝卜",21,100);
        bailoubo.save();
        FoodName doujiao=new FoodName(18,"豆角",30,100);
        doujiao.save();
        FoodName zhusun=new FoodName(19,"竹笋",19,100);
        zhusun.save();
        FoodName qingjiao=new FoodName(20,"青椒",23,100);
        qingjiao.save();
    }
    //初始化肉蛋
    public void initFoodName3(){
        FoodName jidan=new FoodName(21,"鸡蛋",144,100);
        jidan.save();
        FoodName jirou=new FoodName(22,"鸡肉",167,100);
        jirou.save();
        FoodName zhurou=new FoodName(23,"猪肉",143,100);
        zhurou.save();
        FoodName yurou=new FoodName(24,"鱼肉",113,100);
        yurou.save();
        FoodName yarou=new FoodName(25,"鸭肉",240,100);
        yarou.save();
        FoodName niurou=new FoodName(26,"牛肉",106,100);
        niurou.save();
        FoodName huotuichang=new FoodName(27,"火腿肠",212,100);
        huotuichang.save();
        FoodName yangrou=new FoodName(28,"羊肉",118,100);
        yangrou.save();
        FoodName xia=new FoodName(29,"虾",79,100);
        xia.save();
        FoodName yadan=new FoodName(30,"鸭蛋",180,100);
        yadan.save();
    }
    //初始化饮料数据
    public void initFoodName4(){
        FoodName tansuan=new FoodName(31,"碳酸饮料",43,100);
        tansuan.save();
        FoodName kafei=new FoodName(32,"咖啡",45,100);
        kafei.save();
        FoodName niunai=new FoodName(33,"牛奶",54,100);
        niunai.save();
        FoodName chengzhi=new FoodName(34,"橙汁",46,100);
        chengzhi.save();
        FoodName naicha=new FoodName(35,"珍珠奶茶",35,100);
        naicha.save();
        FoodName suannai=new FoodName(36,"酸奶",72,100);
        suannai.save();
        FoodName liangcha=new FoodName(37,"凉茶",39,100);
        liangcha.save();
        FoodName pijiu=new FoodName(38,"啤酒",32,100);
        pijiu.save();
        FoodName xingren=new FoodName(39,"杏仁椰汁饮料",39,100);
        xingren.save();
        FoodName putaojiu=new FoodName(40,"葡萄酒",72,100);
        putaojiu.save();
    }
    //初始化快餐数据
    public void initFoodName5(){
        FoodName hanbao=new FoodName(41,"汉堡包",256,100);
        hanbao.save();
        FoodName shutiao=new FoodName(42,"薯条",298,100);
        shutiao.save();
        FoodName zhajitui=new FoodName(43,"炸鸡腿",255,100);
        zhajitui.save();
        FoodName fangbianmian=new FoodName(44,"方便面",278,100);
        fangbianmian.save();
        FoodName pisa=new FoodName(45,"披萨",230,100);
        pisa.save();
    }
}
