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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wen_zhang_btn);
        final Toolbar toolbar = findViewById(R.id.toolbar_wen_zhang);
        setSupportActionBar(toolbar);
        LinearLayout vc=findViewById(R.id.vc);
        LinearLayout yun1=findViewById(R.id.yun1);
        LinearLayout yun2=findViewById(R.id.yun2);
        LinearLayout rou=findViewById(R.id.rou);
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
                Intent intent=new Intent(WenZhangBtnActivity.this,WenActivity.class);
                intent.putExtra("title",titile);
                intent.putExtra("content",content);
                startActivity(intent);
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
                Intent intent=new Intent(WenZhangBtnActivity.this,WenActivity.class);
                intent.putExtra("title",titile);
                intent.putExtra("content",content);
                startActivity(intent);
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
                Intent intent=new Intent(WenZhangBtnActivity.this,WenActivity.class);
                intent.putExtra("title",titile);
                intent.putExtra("content",content);
                startActivity(intent);
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
                Intent intent=new Intent(WenZhangBtnActivity.this,WenActivity.class);
                intent.putExtra("title",titile);
                intent.putExtra("content",content);
                startActivity(intent);
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
}
