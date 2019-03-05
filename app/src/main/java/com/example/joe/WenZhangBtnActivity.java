package com.example.joe;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class WenZhangBtnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wen_zhang_btn);
        final Toolbar toolbar = findViewById(R.id.toolbar_wen_zhang);
        setSupportActionBar(toolbar);
        LinearLayout vc=findViewById(R.id.vc);
        LinearLayout yun1=findViewById(R.id.yun1);
        LinearLayout yun2=findViewById(R.id.yun2);
        LinearLayout rou=findViewById(R.id.rou);
        LinearLayout Butie=findViewById(R.id.Butie);
        LinearLayout sport=findViewById(R.id.sport);
        LinearLayout food_exchange=findViewById(R.id.Food_exchange);
        LinearLayout bmi=findViewById(R.id.BMI);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            WenZhangBtnActivity.this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titile="科学补充维生素C";
                String content="维生素C在胎宝宝脑发育期起到提高脑功能敏锐的作用，孕期准妈妈充足地摄取维生素C，可以提高胎宝宝的智力。还有，维生素C对于胎宝宝的皮肤、骨骼、牙齿以及造血器官的生长发育有促进作用。另外，维生素C能够增强机体的免疫力，促进钙和铁的吸收，可以提高抗病能力并有效预防缺钙和缺铁。" +
                        "怎样科学补充维生素C\n\n" +
                        "  1．可通过食用富含维生素C的蔬果来补充，如西红柿、青椒、黄瓜、菜花、大枣、草莓、柑橘、猕猴桃等。\n" +
                        "  2．蔬菜不要浸泡或煮得过久。\n" +
                        "  3．蔬菜尽量先洗再切，这样可以减少维生素C溶于水中的量。\n" +
                        "  4．蔬菜被撕碎、挤压都会造成维生素C的流失，因此应尽量吃新鲜蔬菜。\n" +
                        "  5．炒菜时，为了绿色蔬菜更青翠好看，有时会加点小苏打，维生素C就这样流失了。\n" +
                        "  维生素C过量也有危害\n\n" +
                        "  准妈妈适量补充维生素C，每日大约130毫克，可预防胎宝宝先天性畸形，但是如果摄入过量，超过1000毫克，则会影响胚胎发育，长期过量服用还会使宝宝在出生后发生坏血症。此外，超过正常剂量很多倍服用维生素C，可能刺激准妈妈胃黏膜致出血并形成尿路结石。\n" +
                        "";
                sendWenActivity(titile,content,0);
            }
        });
        yun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titile="孕早期怎么预防流产";
                String content="准妈妈在孕期需要注意的事情很多，例如要精神愉快、情绪稳定、陲眠充足、饮食合理、搞" +
                        "好卫生和劳动保护、预防疾病等，这样才能保证身体健康和胎宝宝的正常发育。" +
                        "生活有规律" +
                        "起居应以平和为上，如早晨多吸收新鲜空气，适当地活动，每日保证睡眠8小时，条件允许可以午睡一会。既不要过于贪睡，也不要太过劳累。养成每日定时大便的习惯，保证大便通畅，但避免用泻药。\n" +
                        "注意个人卫生" +
                        "多换衣，勤洗澡，但不宜选择盆浴。因为脏水和细菌会进入阴道引发感染。特别要注意阴部清洁，防止病菌感染；衣着应宽大，腰带不宜束紧；平时应穿平底鞋。\n" +
                        "合理饮食\n\n" +
                        "  薏米、山楂、螃蟹、甲鱼不宜多吃或尽量不吃。选择富含各种维生素及矿物质的食品，如各种蔬菜、水果、豆类、蛋类、肉类等\n\n" +
                        "影响腹部的动作要注意，" +
                        "避免使腹部紧张或受压迫的动作，如弯腰、搬动重物、伸手到高处去取东西及频繁的上楼下楼等活动。\n\n" +
                        "保持心情舒畅\n\n" +
                        "   自然流产是因为准妈妈大脑皮层下中枢兴奋亢进所致，实验证明神经系统的功能状态对流产起着决定性的作用，因此妊娠期精神要舒畅，避免各种刺激。\n" +
                        "＊孕事叮咛\n\n" +
                        "  如果在孕早期发生自然流产，准妈妈不要太过于伤心，因为自然流产是一种淘汰缺陷胎\n\n" +
                        "  宝宝的机制，不是完全有害的，流产后，一定要注意坐个小月子，养好身体。\n\n" +
                        "  小心易导致流产食物\n" +
                        "  妊娠期间，准妈妈应注意营养的摄入，但同时也该注意到有些饮食会对自己或者胎宝宝产生不良影响。准妈妈要熟悉对保胎、安胎不" +
                        "利的食物。\n\n" +
                        "  易导致流产的食物表：\n  薏米:对子宫平滑肌有兴奋作用，可促使子宫收缩，因而有诱发流产的可能。\n\n" +
                        "  马齿苋:性寒凉而滑利，对于子宫有明显的兴奋作用，能使子宫收缩次数增多、强度増大，易造成流产。\n\n" +
                        "  桂圆:性温味甘，极易助火，动胎动血。准妈妈食用后可能会出现燥热现象，甚至引起腹痛、“见红”等流产症状，甚至引起流产或早产。\n\n" +
                        "  杏、杏仁:味酸性热，有滑胎作用。\n\n" +
                        "  山楂:对子宫有收缩作用，准妈妈若大量食用山楂食品，会刺激子官收缩，甚至导致流产。\n\n" +
                        "  芦荟:芦荟含有一定的毒素，准妈妈若饮用芦荟汁，会导致子宫出血，甚至造成流产。\n\n" +
                        "  螃蟹:性寒凉，可用于活血祛瘀，也因而对准妈妈不利，尤其是蟹爪，易引发流产。\n\n" +
                        "  甲鱼:性寒，有滋阴益肾的功效，但同时还有着较强的活血散瘀作用，准妈妈若食用容易造成流产\n\n";
                sendWenActivity(titile,content,0);
            }
        });
        yun2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titile="孕3月保健护理";
                String content="  该去医院进行第一次正式产检了产前检査在很大程度上可以为准妈妈和胎宝宝的健康提供保证。定期进行产前检查，与医生保持密切的联系，是每个准妈妈都应该积极去做的。\n\n" +
                        "  第一次产检什么时候去\n\n" +
                        "  一般来说，系统的产前检查从怀孕11～13周开始。但各地医院的规定可能略有差异，特别是医疗资源颇为紧张的大城市，如北京、上海等。第一次产前检查的时候最好提前到7～9周，准妈妈最好提前询问自己选择的医院的具体规定。\n\n" +
                        "  第一次产检有哪些项目\n\n" +
                        "  第一次产检时，医生会测量准妈妈的身高、体重、血压、宫高、腹围，进行全身体格检查，并核对孕周。此外，准妈妈还需进行一系列实验室检查，包括血常规、血型、乙丙肝抗体、艾滋病抗体、梅毒抗体、肝功能、尿检、心电图检查。\n\n" +
                        "＊孕事叮咛\n\n" +
                        "  准妈妈最好在上午空腹去医院，因为需要进行各种血常规检查。还应带一瓶水。做过全身体检者可以带上体检报告，有些检查项目就不必重复检查了。\n\n" +
                        "11周进行NT排畸檢査\n\n" +
                        "  NT检查实际上是超声检查胎儿须项透明带。有相关的研究发现，在准妈妈怀孕11～14周，若孩子的颈项透明带增厚，就说明胎儿可能有问题。颈项透明带增厚也与胎儿染色体核型、胎儿先天性心脏病以及其他结构畸形有关，颈项透明带越厚，胎儿异常的概率越大。" +
                        "因此，NT检查的目的是在妊娠较早阶段筛查染色体疾病和发现多种原因造成的胎儿异常。" +
                        "医生可通过B超进行NT检查胎宝宝的颈项透明带的厚度。若测量值小于3毫米则属于正常，若是超过3毫米就需要进一步检査，如进行羊水穿刺等" +
                        "NT检查最好在怀孕11-14周做，比做中孕期血清唐氏综合征筛查的时间更早。而过了14周，过多的液体可能被宝宝正在发育的淋巴系统吸收，检查会不准确。\n\n" +
                        "＊孕事叮咛\n\n" +
                        "  NT检查，然后再配合抽血化验，唐氏综合征的检出率能达到85％以上。\n\n" +
                        "  建档都需要做什么准备\n\n" +
                        "  大部分医院要求满孕12周才给建档,但有的医院孕6周就开始预建档，有的则要求在孕16周时才可以。如果此前都没有做过产检在建档时需要先做B超，若胎儿在官腔中，有胎心，就可以建档了。建档需要携带的材料也不同，有些医院只要夫妻双方的身份证就可以了，有的则要求必须带准生证或围产卡、母子健康档案等。决定了在哪家医院建档后，最好先向医院咨询清楚，做好心理准备。" +
                        "如果之前没做过检查，医生会让你先做B超，看看胎儿是否正常。胎儿正常，医生会填一张表，内容包括姓名、年龄、家庭住址、结婚年龄、月经情况、既往怀孕情况、既往病史、有无外伤史、药物过敏史、家族遗传病、怀孕前后有无用过药物、有无接受过放射线等。" +
                        "填完表之后，医生会安排你做一系列检查，大概有身高、体重、血压、宫高、腹围、胎方位、胎心、尿常规、血常规、心电图等。";
                sendWenActivity(titile,content,0);
            }
        });
        rou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titile="孕期吃什么肉好、怎样吃肉更健康";
                String content="妊娠反应结束后，很多准妈妈又能接受荤食了，这时候，饮食里要适当增加肉。肉类含有丰富的优质蛋白质，我们平时经常吃的肉类包括猪肉、牛肉、羊肉、鸡肉和鱼肉，这些肉类的蛋白质含量在16%~26%，而且这些肉类中所含的氨基酸最容易被人体吸收利用，同时肉类也是我们每天所需的铁、铜、锌、镁等营养元素的最好的来源之一。\n\n" +
                        "准妈妈最适合吃哪些肉？\n\n" +
                        "鱼肉\n\n" +
                        "  鱼类尤其是海鱼含有多不饱和脂肪酸以及丰富的DNA，能预防流产、早产和胎宝宝发育迟缓。尤其是鳗鱼，建议准妈妈每周最好能够吃2~3次。\n\n"+
                        "牛肉\n\n" +
                        "  牛肉中不仅含有丰富蛋白质、铁和铜，而且B族维生素含量也很高，脂肪含量相对较低，因此也是准妈妈饭桌上不错的选择。\n\n" +
                        "鸡肉\n\n" +
                        "  蛋白质含量高，容易消化和吸收。\n\n"+
                        "准妈妈如何吃肉更健康？\n\n" +
                        "  1、掌握食用量。对于健康的准妈妈来说，每天肉类的摄取量在200克左右为最佳，而每个星期所摄入的肉类中最好能包括300克鱼肉。如果每天摄入肉类过多，日积月累就会导致高脂血症、动脉粥样硬化，甚至会使心血管系统或其他脏器发生病变。\n" +
                        "  2、和豆类、豆制品一起食用。可以降低血液中的胆固醇，增加多不饱和脂肪酸的含量，减少动脉硬化等疾病的发病率。\n" +
                        "  3、补充足够的膳食纤维。膳食纤维能够减少食用肉类后，脂肪、胆固醇在肠道内的吸收，有降血脂。降低胆固醇的作用。还能有效地预防便秘，是肉食的最佳配餐。\n\n";
                sendWenActivity(titile,content,0);
            }
        });
        Butie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="妊娠中后期如何正确补铁";
                String content="  进入本月之后，随着胎宝宝的不断生长发育的需要，以及准妈妈自身血容量的不断增加，对矿物质铁的需求量日渐增加。为了避免出现缺铁性贫血，准妈妈应注意及时补充铁质。\n\n" +
                        "1.多吃富铁食物\n\n" +
                        "  适当多吃瘦肉、家禽、动物肝及血（鸭血猪血）、蛋类等富铁食物。豆制品含铁量也较多肠道的吸收率也较高，要注意摄取。主食多吃面食，面食较大米含铁多，肠道吸收也比大米好。\n\n" +
                        "2.多吃有助于铁吸收的食物\n\n" +
                        "  水果和蔬菜不仅能够补铁，所含的维生素C还可以促进铁在肠道的吸收。因此，在吃富铁食物的同时，准妈妈最好一同多吃一些水果和蔬菜，也有很好的补铁作用。\n\n" +
                        "3.正确选择补铁剂\n\n" +
                        "  如果准妈妈贫血比较严重，就需要在专业医生的指导下服用补铁剂了。准妈妈最好选择硫酸亚铁、碳酸亚铁、富马酸亚铁、葡萄糖酸亚铁这些铁剂属二价铁，容易被人体吸收。铁剂对胃肠道有刺激作用，常引起恶心、呕吐、腹痛等，应在饭后服用为宜。多糖铁复合物可以降低不良反应，可试着换药。反应严重者可停服数天后再由小量开始，直至所需剂量。若仍不能耐受，可改用注射剂。\n";
                     sendWenActivity(title,content,0);
            }
        });
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="IOM指南体重管理表";
                String content="·IOM指南体重管理表";
                String content2="·每日摄入热量计算法\n" +
                        "全日能量供给量（kcal）＝能量（kcal）/kg×理想体重（kg）。理想体重（kg）＝身高（cm）－105，每公斤体重摄入能量与BMI和体力劳动强度有关，见下表。\n";
                sendWenActivity(title,content,content2,1);
            }
        });
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="运动推荐项目";
                String content="  孕期运动可分为全身耐力锻炼以及局部阻力锻炼。耐力锻炼是指较长时间的保持中、低强度的运动方式，包括散步、快走、慢跑、爬楼梯、游泳、孕妇广播操、瑜伽等；局部阻力锻炼以上肢运动为主。\n\n" +
                        "1）散步、快走、慢跑、爬楼梯\n\n" +
                        "  这些运动形式不需要学习，对锻炼的条件要求不高，也无需借助器械，均是适宜孕期进行的运动形式。散步是一种运动量小的活动方式，比较适合孕妇进行，只要身体允许，孕妇最好坚持户外散步。\n" +
                        "  运动时间定在餐后0.5小时，每晚运动1小时（步行6km）\n\n" +
                        "2）水中运动\n\n" +
                        "  水中运动在国外开展较多，国内较少。主要包括游泳、水中体操、水中行走、滑水等。水中运动的好处在于，能够减轻支持妊娠子宫的腰肌和背肌的负担，减少胎儿对直肠的压迫；水的传热性比空气大，在水中活动比陆地上活动消耗能量多；水中体位的变化，关节、初带、肌肉得到充分的伸展、锻炼，有利于顺产。\n" +
                        "3）孕妇保健操\n\n" +
                        "孕期体操和瑜伽是依据孕期身体变化而编排的一种运动疗法，在妊娠不同时期有不同的动作，能够锻炼到孕妇全身不同的肌群，是一种非常适合孕期进行的运动方式。有目的地锻炼孕妇在分娩时产生肌力\n" +
                        "的肌群，消耗体内多余热量，防止脂肪蓄积，提高了自然分挽率。\n\n" +
                        "4）上肢运动\n\n" +
                        "  上肢运动不会产生宫缩，是比较安全且容易被接受的运动方式。\n" +
                        "  建议孕妇坐在坚固的椅子上，手持哑铃或饮料瓶内装水或沙到2榜，先交替上举，左右各举10次，然后双手同时上举10次，重复，持续20分钟\n\n" +
                        "注意：\n\n" +
                        "  对于每次运动的时间，郎景和教授认为孕妇无论进行怎样的运动，每运动15分钟应休息一次，经过5-10分钟时间降低体温，再继续运动。\n" +
                        "  运动强度则以不产生宫缩和不适为宜。\n";
                sendWenActivity(title,content,0);
            }
        });
        food_exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="食物交换表";
                String content="  中国营养学会在《中国居民膳食营养素参考摄入量》中，一个健康的成年女性每天需要摄取1800～1900卡路里的热量,根据孕前体重跟运动量作调整。推荐孕中期后能量在非孕基础上增加200kcal/日，孕早期能量需要量同非孕期。碳水化合物、蛋白质、脂肪提供能量分别占总能量的50%-60%、15%-20%、20%-30%。孕早期、中期、晚期蛋白质摄取在非孕期基础上分别增加5g/d、15g/d、20g/d。\n\n" +
                        "食品交换法\n\n";
                sendWenActivity(title,content,2);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
               finish();
                break;
            default:
                break;
        }
        return true;
    }
    public void sendWenActivity(String titile,String content,int isHaveImage){
        Intent intent=new Intent(WenZhangBtnActivity.this,WenActivity.class);
        intent.putExtra("title",titile);
        intent.putExtra("content",content);
        intent.putExtra("isHaveImage",isHaveImage);
        startActivity(intent);
    }
    public void sendWenActivity(String titile,String content,String content2,int isHaveImage){
        Intent intent=new Intent(WenZhangBtnActivity.this,WenActivity.class);
        intent.putExtra("title",titile);
        intent.putExtra("content",content);
        intent.putExtra("content2",content2);
        intent.putExtra("isHaveImage",isHaveImage);
        startActivity(intent);
    }
}
