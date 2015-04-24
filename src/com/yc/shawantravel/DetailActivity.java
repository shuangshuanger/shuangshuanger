package com.yc.shawantravel;

import java.util.List;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import com.yc.shawantravel.entity.ActivityManager;
import com.yc.shawantravel.entity.Collection;
import com.yc.shawantravel.entity.DBManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends KJActivity {

	@BindView(id = R.id.detail_img)
	private ImageView img;
	@BindView(id = R.id.detail_text)
	private TextView text;
	@BindView(id = R.id.detail_map, click = true)
	private Button mapBtn;
	@BindView(id = R.id.detail_phone, click = true)
	private Button phoneBtn;
	
	@BindView(id = R.id.whichpage)
	private TextView pageText;
	@BindView(id = R.id.back, click = true)
	private ImageButton backBtn;
	@BindView(id = R.id.btn_collection, click = true)
	private ImageButton collectionBtn;
	@BindView(id = R.id.btn_search, click = true)
	private ImageButton searchBtn;
	
	private String phoneNum = null;
	private String address = null;
	private String name;
	private String kind;
	
	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		this.setContentView(R.layout.detail);
	}

	@Override
    public void initData() {
		super.initData();
        ActivityManager.getInstace().addActivity(aty);
    }
	
	@Override
	public void initWidget() {
		super.initWidget();
		
		mapBtn.setVisibility(View.VISIBLE);
		mapBtn.setVisibility(View.VISIBLE);
		
		Bundle bundle = this.getIntent().getExtras();
		name = bundle.getString("name");
		kind = bundle.getString("kind");
		
		pageText.setText(name);
		if ( kind.equals("scenic") )
		{
			mapBtn.setVisibility(View.GONE);
			phoneBtn.setVisibility(View.GONE);
			if ( name.equals("东大塘风景区") )
			{
				img.setImageResource(R.drawable.scenic1);
				text.setText("东大塘景区位于天山深处，依连哈比尔尕山北簏，景区距石河子市、沙湾县城各60余公里，东与“蒙古庙”旅游区隔山相望，系国家A级旅游景区。    东大塘景区分为东、南、西三区，南部高山峻岭绵延，山中有河，清流淙淙。东部三面环山，有百米瀑布从崖头飞流直下，直泻沟底深潭。西面是主景区东大塘，在丘陵之间分布着山坳与盆地，柳树、桦树、杨树、松树与野花草场混交，形成了特殊的丘陵草原。沿松林中的崎岖小道可达山顶，顶部一片起伏绵延的碧绿草地，云杉苍郁，间有松柏散布。 景区内佛教文化气息浓厚。一尊高23米的镀金大佛矗立在山崖之上，是目前北疆修建在悬崖之上最高、最为宏伟的大佛之一，每逢吉日，香火不断。独特的天山风光，壮观的石山大佛，纯朴的哈萨克民俗，古老的游牧生活，浓郁的草原文化气息和源远流长的佛教文化，是您休闲避暑、怡情养性、登山及科学考察的好去处。");
			}
        
			else if ( name.equals("鹿角湾景区") )
			{
				img.setImageResource(R.drawable.scenic2);
				text.setText("鹿角湾位于沙湾县城西南60公里的天山北坡，是天山马鹿生息繁衍的地方，马鹿每年要蜕落一次角壳，因此在山涧、河边遗留下大量蜕落的鹿角壳，鹿角俯拾皆是，故名“鹿角湾”，系国家AAA级旅游风景区。景区包括雪峰冰川、高山裸岩、高山松林和高山草原四个完整的山地垂直自然景观带，可谓“四季风光一眼揽”，是一处集雪山景观和森林草原为一体的多层次旅游胜地。景区内冰峰雪岭与蓝天白云相映生辉，参天松万株相倚，辽阔无垠的山前草原，绿茵绒绒，野花怒放，香气袭人。加之涌动的羊群，奔驰的骏马，踱步的牛驼，点点的毡房，奶茶的飘香，共同构成了一幅静溢温馨、粗犷豪放、如诗如画的草原风物画卷。");
			}
        
			else if ( name.equals("温泉景区") )
			{
				img.setImageResource(R.drawable.scenic3);
				text.setText("温泉景区位于沙湾县城南60公里的高山区、居于金沟河谷之中，四面丛峦叠嶂，云杉蓊郁成帐，夏季无酷暑，冬季少严寒，具有典型的天山自然景观特色，是一处集旅游、疗养、避暑、娱乐、观景为一体的多功能综合游览区，系国家AAA级旅游风景区。景区内温泉泉眼众多，温热发烫，泉水由周围的山崖石壁中溢出，无色透明，泉口处急腾冒泡，具有浓重的硫磺味，富碱性，含有碳酸根、硫化氢、钙、镁、锶、钠、砷、铬、铅、铁等30多种对人体有益的矿物质，为“重磷酸盐钠型”高热泉，可治疗风湿病、皮肤病、神经系统疾病、妇科病、消化系统疾病等。洗浴后，肌肤爽滑，通体舒畅，面色红润，具有独特的美容效果，被誉为“神水天医”，是全国八大名泉之一。");
			}
        
			else if ( name.equals("森林公园") )
			{
				img.setImageResource(R.drawable.scenic4);
				text.setText("三道河子森林公园位于沙湾县城西2公里处，312国道旁，占地面积800亩，公园周围被3800多亩水果基地环绕，环境优美，风光旖旎，是沙湾县各旅游景点中最具特色的生态型城市园林景区。系国家AAA级旅游景区、自治区级森林公园、自治区AAAA级农家乐和全疆百家乡村旅游示范点。景区内树林参天，绿荫簇拥，水渠、溪流穿梭其中，公园中覆盖有300多亩果林，有近2公里长的葡萄长廊，有桃树林、李树林、苹果林、梨树林、红枣树、枸杞、无花果树等。公园西部，动物保护区内有各种野生的珍禽异兽，还有供孩子游乐的电动火车、碰碰车、赛车，标准化的成人和儿童游泳池。同时，在景区内专设了“民俗村”，内设别具风情和民俗的蒙古包，供游人留宿、就餐、小憩，在这里，游人不仅可以品尝到正宗的沙湾大盘鸡等大盘系列菜肴，哈萨克人的奶茶、手抓肉，更能欣赏到地道的哈萨克族舞蹈和冬不拉弹唱。");
			}
        
			else if ( name.equals("千泉湖景区") )
			{
				img.setImageResource(R.drawable.scenic5);
				text.setText("千泉湖景区南距县城约26公里。因湖区泉眼众多，故名千泉湖，属于典型的湿地草甸沼泽型自然风光区。千泉湖畔苇荡中，雨后的蘑菇如白云落地，故而又被称之为“蘑菇湖”。这里除蘑菇这个奇景之外，还是芦苇的迷宫，泉水的世界，水族动物的乐园，鸟类的王国。 一望无际的芦苇荡中，有大小泉眼三千多个，各类水生植物郁郁青青，天鹅翩翩起舞，鸬鹚静静觅食，鱼儿畅游水中。千泉湖北岸，有千亩以上保护完好的平原原始次生林区，桦树、灌柳、沙枣、红柳等林木生机勃勃。狐狸、野兔、狍鹿、黄鸟、苍鹰在这里物竞天泽繁衍生息。湖区内有星罗棋布的鱼塘，蟹、虾尤为著名。景区系国家级水利风景区，蟹苗、虾苗繁育基地，婚纱摄影基地，来这里旅游，不仅可以领略湿地草原的自然风光，还可以驾船在水中畅游、垂钓，品尝新鲜的螃蟹、鱼虾水产大餐，充分享受田园休闲时光。");
			}
        
			else if ( name.equals("瀚海柳浪景区") )
			{
				img.setImageResource(R.drawable.scenic6);
				text.setText("瀚海柳浪景区南距沙湾县城66公里。北接准噶尔盆地中的古尔班通古特大沙漠，南临“呼克”高速公路，玛纳斯河斜贯其中。景区由瀚海沙漠与原始次生林两部分组成，沿玛纳斯河两岸展开。北面茫茫瀚海，沙丘连绵起伏，河湾边，胡杨茂密，梭梭成帐，红柳顶红着绿点缀其中。在景区河道与沙漠的吻接带上，有近四万亩的红柳原始次生林区。每年4至6月，柳花怒放，整个景区是一片粉红色的海洋。来此旅游，野营、探幽，别有一番风情。");
			}
		}
		
		else if ( kind.equals("hotel") )
		{
			phoneNum = bundle.getString("phoneNum");
			address = bundle.getString("address");
			if ( name.equals("天博生态大酒店") )
			{
				img.setImageResource(R.drawable.hotel1);
				text.setText("酒店座落于沙湾县大泉乡三道沟村旁，是集人才培训、餐饮住宿、会议接待、康乐健身、洗浴疗养、旅游度假为一体的综合性酒店。同时可接待260人的团队入住，是您休闲、健身的首选之地。");
			}
			
			else if ( name.equals("沙湾迎宾馆") )
			{
				img.setImageResource(R.drawable.hotel2);
				text.setText("沙湾迎宾馆是一家集餐饮、住宿、培训、娱乐为一体的综合性服务场所，可提供豪华套间、豪华标准间、普通标准间。");
			}
			
			else if ( name.equals("鑫东方大酒店") )
			{
				img.setImageResource(R.drawable.hotel3);
				text.setText("鑫东方大酒店位于沙湾县中心城区地段，位置优越，交通便利，客房干净整洁，温馨舒适，欢迎您的光临！");
			}
			
			else if ( name.equals("德荣酒店"))
			{
				img.setImageResource(R.drawable.hotel4);
				text.setText("德荣酒店是集餐饮，客房、桑拿、KTV、棋牌室、商务中心、精品部等项目为一体的三星级涉外酒店，客房部设有标准间、行政套房和豪华套房，室内设施齐全；350人同时用餐的宴会厅，4000平方米餐饮广场，可同时容纳1000多人同事消费，是观光者投宿、下榻之首选。");
			}
			  
			else if ( name.equals("民政宾馆"))
			{
				img.setImageResource(R.drawable.hotel5);
				text.setText("民政宾馆位于沙湾县火车站路23号，是一家集餐饮住宿为一体的综合性酒店。地理位置优越，交通便利。酒店设施齐全，装修豪华.");
			}
			
			else if ( name.equals("明盛宾馆"))
			{
				img.setImageResource(R.drawable.hotel6);
				text.setText("沙湾县明盛宾馆是一家住宿休闲性酒店，房间配有电脑、棋牌桌等设施，紧邻沙湾县客运站，地理位置优越，交通便利。酒店设施齐全，装修豪华，拥有多种类型"
						+ "的客房共30套，保健中心、棋牌等服务和娱乐设施，配套项目齐全。酒店以“宾客至上，服务第一”为经营宗旨，采用了科学的经营机制和管理方法，不断追求卓越，"
						+ "得到了社会的认可，无论商务、宴会、休闲、娱乐，都是您的理想之选。");
			}
			
			else if ( name.equals("七星商务宾馆"))
			{
				img.setImageResource(R.drawable.hotel7);
				text.setText("沙湾县七星商务宾馆包含多种设施，国内长途电话、空调、免费提供一次性洗漱用品、电热水壶、拖鞋、电视、多种规格电源插座、雨伞、针线包、220V电压插座、"
						+ "室内卫生间、全部房间免费电脑、室内洗浴间、暖气、淋浴等，欢迎您的光临！");
			}
			
			else if ( name.equals("温州宾馆"))
			{
				img.setImageResource(R.drawable.hotel8);
				text.setText("沙湾县温州宾馆秉承“顾客至上，锐意进取”的经营理念，坚持“客户第一”的原则为广大客户提供优质的服务。欢迎惠顾！");
			}
			
			else if ( name.equals("星光快捷宾馆"))
			{
				img.setImageResource(R.drawable.hotel9);
				text.setText("星光快捷宾馆位于沙湾县世界大道182号，是一家住宿休闲性宾馆，宾馆采用了科学的经营机制和管理方法，不断追求卓越，得到了社会的认可，无论商务、宴会、休闲、娱乐，都是您的理想之选。");
			}
			
			else if ( name.equals("和平宾馆"))
			{
				img.setImageResource(R.drawable.hotel10);
				text.setText("沙湾县和平宾馆是一家住宿休闲性酒店，采用了科学的经营机制和管理方法，不断追求卓越，得到了社会的认可，无论商务、宴会、休闲、娱乐，都是您的理想之选。");
			}
			
			else if ( name.equals("佳苑宾馆"))
			{
				img.setImageResource(R.drawable.hotel11);
				text.setText("沙湾县佳苑宾馆是一家住宿休闲性酒店，采用了科学的经营机制和管理方法，不断追求卓越，得到了社会的认可，无论商务、宴会、休闲、娱乐，都是您的理想之选。");
			}
			
			else if ( name.equals("荣旺宾馆"))
			{
				img.setImageResource(R.drawable.hotel12);
				text.setText("沙湾县荣旺宾馆是一家住宿休闲性酒店，采用了科学的经营机制和管理方法，不断追求卓越，得到了社会的认可，无论商务、宴会、休闲、娱乐，都是您的理想之选。");
			}
		}
		
		else if ( kind.equals("food") )
		{
			phoneNum = bundle.getString("phoneNum");
			address = bundle.getString("address");
			if ( name.equals("沙湾迎宾馆") )
			{
				img.setImageResource(R.drawable.food1);
				text.setText("欢迎光临！");
			}
			

			else if ( name.equals("天博生态园") )
			{
				img.setImageResource(R.drawable.food2);
				text.setText("欢迎光临！");
			}
			
			
			else if ( name.equals("锦绣沙湾大盘美食城") )
			{
				img.setImageResource(R.drawable.food3);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("千泉湖酒楼") )
			{
				img.setImageResource(R.drawable.food4);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("金鲁西肥牛火锅") )
			{
				img.setImageResource(R.drawable.food5);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("徐师傅烤鸭") )
			{
				img.setImageResource(R.drawable.food6);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("金融酒家") )
			{
				img.setImageResource(R.drawable.food7);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("锦绣大盘食府饭馆") )
			{
				img.setImageResource(R.drawable.food8);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("得月楼福顺火锅") )
			{
				img.setImageResource(R.drawable.food9);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("金手捞火锅城") )
			{
				img.setImageResource(R.drawable.food10);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("森林雨火锅") )
			{
				img.setImageResource(R.drawable.food11);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("鑫星宴会厅") )
			{
				img.setImageResource(R.drawable.food12);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("沙味王") )
			{
				img.setImageResource(R.drawable.food13);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("桥头回民大盘鸡总店") )
			{
				img.setImageResource(R.drawable.food14);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("杏花村大盘鸡总店") )
			{
				img.setImageResource(R.drawable.food15);
				text.setText("欢迎光临！");
			}
			else if ( name.equals("阿腾布拉克草原牧鸡专卖") )
			{
				img.setImageResource(R.drawable.food16);
				text.setText("欢迎光临！");
			}
			else if ( name.equals("稻花香大盘鸡店") )
			{
				img.setImageResource(R.drawable.food17);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("风云上海滩特色架子肉") )
			{
				img.setImageResource(R.drawable.food18);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("祥福顺特色大盘土鸡店") )
			{
				img.setImageResource(R.drawable.food19);
				text.setText("欢迎光临！");
			}

			else if ( name.equals("沙湾金港农家特色土鸡店") )
			{
				img.setImageResource(R.drawable.food20);
				text.setText("欢迎光临！");
			}
		
		}
		
		else if ( kind.equals("shopping") )
		{
			mapBtn.setVisibility(View.GONE);
			phoneBtn.setVisibility(View.GONE);
			if ( name.equals("人民路商业街") )
			{
				img.setImageResource(R.drawable.shopping);
				text.setText("人民路商业街位于三道河子镇人民路，主要经营服装、特色小吃，北与西山步行街相连，南头是万客隆超市，中经人民路地下街。");
			}
			
			else if ( name.equals("鑫沙湾步行街") )
			{
				img.setImageResource(R.drawable.shopping);
				text.setText("鑫沙湾步行街位于沙湾县世纪大道与教育路之间，全长近500米，经营商户近50家，经营范围：针纺织品、服装鞋帽、文体用品、化妆品、玩具等。");
			}
			
			else if ( name.equals("人民路地下街") )
			{
				img.setImageResource(R.drawable.shopping);
				text.setText("人民路地下街购物中心位于万客隆超市前人民路地下一层，与2013年建成投入使用，地下街全长近500米，经营商户近50家，经营范围：针纺织品、服装鞋帽、文体用品、化妆品、玩具等。");
			}
			
			else if ( name.equals("星光街") )
			{
				img.setImageResource(R.drawable.shopping);
				text.setText("星光街位于三道河子镇世纪路中段，连接教育路，以服装、日用品、小吃为主，有商铺150家。");
			}
			
			else if ( name.equals("爱家超市") )
			{
				img.setImageResource(R.drawable.shopping);
				text.setText("爱家超市位于沙湾县大十字黄金地段，经营范围：烟酒、食品、化妆品、日用百货、针纺织品、服装鞋帽、文体用品、玩具、工艺美术品、家用电器等2000余种。");
			}			
			
			else if ( name.equals("万客隆") )
			{
				img.setImageResource(R.drawable.shopping);
				text.setText("万客隆超市位于沙湾县人民路明盛源商贸城二楼，经营范围：烟酒、食品、化妆品、日用百货、针纺织品、服装鞋帽、文体用品、玩具、工艺美术品、钟表、照相器材、珠宝等首饰。");
			}
		}
		
		else if ( kind.equals("game") )
		{
			phoneNum = bundle.getString("phoneNum");
			address = bundle.getString("address");
			if ( name.equals("飞达影院") )
			{
				img.setImageResource(R.drawable.game1);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("银河娱乐会所") )
			{
				img.setImageResource(R.drawable.game2);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("好声音欢唱城") )
			{
				img.setImageResource(R.drawable.game3);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("夜猫经典音乐酒吧") )
			{
				img.setImageResource(R.drawable.game4);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("最劲KTV") )
			{
				img.setImageResource(R.drawable.game5);
				text.setText("欢迎光临！");
			}

			else if ( name.equals("歌谷欢唱城") )
			{
				img.setImageResource(R.drawable.game6);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("凯萨酒吧") )
			{
				img.setImageResource(R.drawable.game7);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("金柜欢唱会所") )
			{
				img.setImageResource(R.drawable.game8);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("蓝月亮酒吧") )
			{
				img.setImageResource(R.drawable.game9);
				text.setText("欢迎光临！");
			}
			
			else if ( name.equals("金色年华娱乐会所") )
			{
				img.setImageResource(R.drawable.game10);
				text.setText("欢迎光临！");
			}
		}
		
		else if ( kind.equals("info") )
		{
			mapBtn.setVisibility(View.GONE);
			phoneBtn.setVisibility(View.GONE);
			if ( name.equals("鼓励投资和招商引资") )
			{
				img.setImageResource(R.drawable.info1);
				text.setText("                            总  则\n"
						+ "   第一条 为营造良好的投资环境，鼓励投资者参与沙湾县开发建设，根据《国务院关于进一步促进新疆经济社会发展的若干意见》等国家和自治区有关法律、法规和政策，结合沙湾县实际，制定本优惠办法。\n"
						+ "   第二条 围绕把沙湾县建成丝绸之路经济带上的休闲之都，重点推进新型城镇化建设、旅游开发、大型商业综合体、农业现代化等重点项目，采取“一事一议”、“一企一策”、“特事特办”等个性化办法，给予最大优惠。\n"
						+ "	     第三条 投资者在沙湾县投资国家和自治区重点扶持、鼓励发展且符合国家相关规定，将从政策上给予优惠。\n"
						+ "   鼓励发展绿色食品加工业、轻工纺织业、精细化工业、出口产品（独山子下游产品）加工业、新型建材及装备制造业、新型能源业、旅游业为主的支柱产业；鼓励发展大型专业市场、现代物流、现代金融、旅游文化产业、星级酒店、商业综合体等第三产业。鼓励引进世界500强、国内500强、境内外上市公司投资项目；鼓励国内外大中型企业在沙湾县设立企业总部或新疆销售公司。\n"
						+ "               第一章  税收优惠\n"
						+ "   第四条 税收优惠执行国家税收优惠政策及新疆维吾尔自治区人民政府出台的各项税收优惠政策和配套办法。\n"
						+ "   第五条 投资者从事国家和自治区重点扶持、鼓励发展的产业和项目，按照国家和自治区税收优惠政策规定，享受免征、减征企业所得税政策。\n"
						+ "               第二章  土地优惠\n"
						+ "   第六条 供地方式：出让、划拨、租赁等方式依法提供。\n"
						+ "   各类投资者兴办项目（包括新建、改建和扩建的项目）必须符合国土资源部有关规定，方可依法取得国有建设用地土地使用权。国有建设用地土地使用权的取得，须经县人民政府批准后，采用公开招标、拍卖、挂牌出让（或租赁）方式进行供地。\n"
						+ "   工业用地供地原则：由投资强度、容积率、建筑系数、行政办公及生活服务设施用地所占比重、绿地率五项指标来确定供地面积。投资强度每亩≥50万元。工业用地出让最低价标准：111元/平方米。土地使用权出让须按沙湾县工业项目用地基准价评估后，经招标、拍卖、挂牌等出让手续后的价格执行。\n"
						+ "   土地优惠：对使用国有未利用地中，未列入耕地后备资源的盐碱地、沼泽地、沙地、裸地）的工业项目用地，按国土资源部有关规定执行优惠：即使用土地利用总体规划确定的城镇建设用地范围内的国有未利用地，且土地前期开发由土地使用者自行完成的工业项目用地，可按不低于所在地土地等别相对应《标准》的50％执行。\n"
						+ "   鼓励有实力的大企业、大集团和企业联合会（商会）等社会团体投资创办产业园或园中园，但其创办的产业园或园中园建设必须要符合我县土地利用总体规划。\n"
						+ "   第九条 以出让方式取得国有土地使用权的，结合国家、自治区相关政策，依据项目固定资产投资额度以及税收贡献大小，采取“一事一议”，享受基础设施补助的优惠政策。投资项目分期实施、则分期供地。\n"
						+ "               第三章  财政扶持办法\n"
						+ "   第十条 沙湾县人民政府负责各类招商引资项目的“五通一平”（至厂区道路、供水、排水、供电、通讯、场地平整）基础设施配套完善；企业投资基础设施建设的，可享受基础设施补助政策。\n"
						+ "   第十一条 对于在我县落户的企业，以出让方式取得国有土地使用权的，均采取“一事一议、一企一策”政策，依据项目固定资产投资额度或税收贡献大小，给予基础设施补助，补助的数额，由财政局、招商局初审，报县财经领导小组审定确认后，兑现政策；进驻园区内的企业，由园区管委会负责落实兑现政策，园区以外的，由住建局负责落实兑现政策。\n"
						+ "   第十二条 根据财政部、国土资源部、中国人民银行关于印发《国有土地使用权出让收支管理办法》的通知（财综[2006]68号）第十三条土地出让收入使用范围相关规定，土地使用权出让支出—基础设施补助必须取得合法票据（发票）按财政部门核定的预算执行。\n"
						+ "   第十三条 财政补贴政策。对我县2014年起新办的国家鼓励类产业企业，根据其新增税收，自开始生产经营之日起两年，由财政部门配合发改委、商信委积极申报项目资金给予投资企业补助（贴息），补助（贴息）的金额，按新增税收地方分享部分的数额确定，但最高不得超过当年企业在金融机构借款的利息费用。\n"
						+ "               第四章  规费优惠办法\n"
						+ "   第十四条 投资者在沙湾县建设固定资产投资5000万元以上的第二产业和鼓励的第三产业，办理相关手续时，除代国家、自治区、地区收取的行政事业性收费、劳保统筹费按标准收取以外，其他行政事业性收费按行政事业性收费地方留成部分全免；投资3000万～5000万元的其他行政事业性收费按地方留成部分30%收取；投资1000万～3000万元的其他行政事业性收费按地方留成部分50%收取。\n"
						+ "   第十五条 世界500强企业、国内500强企业、境内外上市公司投资的项目及固定资产投资10亿元以上的项目，在国家、自治区规定范围内，给予更多优惠条件。\n"
						+ "               第五章  其他优惠办法\n"
						+ "   第十六条 投资者当年新招用持《就业失业登记证》的大中专毕业生，并与其签订1年以上期限劳动合同并缴纳社会保险费的，按实际招用人数，享受有关税收优惠政策和贷款扶持 政策。\n"
						+ "   第十七条 县重点产业领域的并按国家规定提取和使用职工教育培训经费的各类企业，新录用符合补贴条件的劳动者，并与其签订1年以上期限劳动合同，企业对其进行技能培训或委托定点培训机构实施技能培训的，可向人力社会保障部门申请一次性培训补贴。\n"
						+ "   第十八条 投资企业在完成投资运营后，可按我县户籍管理的有关规定，持企业引进人员的有关证明材料，向所在地的公安机关为企业法人代表（个人独资企业的投资人）、高层经营管理人员及其配偶、子女申请办理城镇居民常住户口。\n"
						+ "   第十九条 县外投资企业职工子女就学，到沙湾县教育部门办理就学手续，按当地居民子女标准对待。\n"
						+ "               第六章  投资保障\n"
						+ "   第二十条 简化审批手续，提高办事效率。实行服务承诺制和首问责任制，明确服务内容、标准和时限。\n"
						+ "   为方便投资项目尽快落地，并为保证项目建设提供好的服务，由相关县领导牵头，投资企业协助，施行“一个重大项目，一套服务班子，一跟到底”的服务方式，对涉及有关部门的服务性审批事项帮助跑办，限期办结。\n"
						+ "   第二十一条 明确收费标准，减轻企业负担，严禁以任何名义向投资企业乱收费、乱摊派、乱罚款。收费单位向企业收取行政事业性收费和政府性基金，必须出示收费许可证，开具统一收费票据，并按照本优惠办法的规定收取，否则企业拒绝缴纳。\n"
						+ "   第二十二条 营造优质高效投资环境，规范检查程序，保证经营秩序。所有行政部门必须严格执行《沙湾县经济发展环境监督管理规定》，确保每月1～25日为安静生产日，除安监、消防、公安等部门外确需进行执法检查，须报经县经济发展软环境监督管理办公室（经信委）同意并出具证明，否则企业拒绝接受检查；严禁借执法检查干扰企业正常的生产、经营秩序。\n"
						+ "   第二十三条 完善监督机制，保障投资者合法权益。县纪检监察部门、政府价格主管部门要定期对涉企收费情况进行检查，严厉查处针对投资企业的违法违纪问题。因非法干预给企业造成损失的，要依法赔偿；情节严重的，要追究法律责任。\n"
						+ "                                  附      则\n"
						+ "   第二十四条 县招商局及各相关职能部门依据职能全方位为投资企业提供服务，县纪委、绩效办、招商局负责受理投资企业投诉，协调处理本优惠政策执行过程中的问题。\n"
						+ "   第二十五条 本优惠办法由县招商局商园区管委会负责解释。原有的规定与本办法相抵触的以本办法为准。\n"
						+ "   第二十六条 本优惠办法自颁布之日起开始实施。此前沙湾县有关招商引资优惠办法与本办法不同之处均以此办法为准。\n"
						+ "                       中共沙湾县委办公室\n"
						+ "                                                                      2014年3月29日印发");
			}
			
			else if ( name.equals("真味沙湾") )
			{
				img.setImageResource(R.drawable.info2);
				text.setText("							 真味沙湾\n"
						+ "																			王庆绪 （安徽）\n"
						+ "   去年暑假，我有幸随市摄影家协会去新疆拍摄写生。本来我们的目的地是伊犁。但车子行至沙湾的时候爆胎了，而备胎也因前天漏气还没有修好，我们只得在这个地处新疆西北的小县城停留下来，等待司机补胎后再行。\n"
						+ "   也算是上天作美，就是这次车子爆胎的小事故，就是这次停留，让我们没有错过祖国大好河山一处美丽的风景和一道过舌不忘的美味——大盘鸡。所以我们要对生活中的小事情心存感激，正是它们汇聚了我们生活中精彩。\n"
						+ "   本来，我们打算修好车后马上行进，不知当时是谁说了一句，车子累了都要抛锚，不如我们顺其自然，索性就在这住一夜。\n"
						+ "   连续地乘车，我们每个人也着实有些累，虽然我们的目的地不是这里，但还是一呼百应，大家立即接受了这位老兄的提议。\n"
						+ "   爆胎的地方离县城不远，我们便决定步行向县城进发。路上，我用手机百度了一下。沙湾，位于新疆西北部，准噶尔盆地南缘，天山北麓。地处北疆要冲，古丝绸之路有四处驿站在沙湾境内。欧亚第二大陆桥北疆铁路和国防、乌伊、呼克公路自东向西横穿全县南、中、北，为乌鲁木齐通往伊犁、塔城、阿勒泰的交通要道，是新疆通往欧洲的必经之地。真可谓是“八方通衢，交通咽喉。”\n"
						+ "   沙湾的美食是大盘鸡。看一看大盘鸡的做法和图片，非常像我们家乡农家乐里的土菜。土菜虽土，但却是最养人、最适合胃口、最百吃不厌的一种菜。不像高星级宾馆里的菜，虽做工讲究，色泽艳丽，但放下筷子后，便忘了滋味，还有一种吃不饱的感觉。就如妻子，虽外表不怎么妖艳，但却能为你洗衣做饭，一应家务，体贴入微。而屏幕上的明星，虽外表光彩照人，姿态妖媚，却似不食人间烟火的仙人，仿佛离生活很远，中看不中用。\n"
						+ "   我们选择一家门面不甚起眼的小菜馆，就像艺术界所说的高手在民间一样，我一直认为要尝到真正的地方特色土菜，是不用去装潢考究的大饭店或大宾馆的，因为那里的厨师往往会十八班武艺，一道普通的菜，愣是能整出其本身没有的味道来，从而失去其原本的味道。\n"
						+ "   在一家叫“阿城大盘鸡”的小饭馆，我们拣了一张靠近窗子的桌子坐下，这样方便那些怀揣相机的朋友偷空拍照。毕竟是一个从未来过，或许以后也不会再来的地方，一切都是新鲜的。\n"
						+ "   老板是一位哈萨克人，估计三十四五岁，个头不高，身材却很壮实，脖子上搭一白毛巾。他热情地招呼我们，把我们领到座位上，然后问我们要什么菜。我们一路走来 ，每到吃饭时都是诗人江叶点菜、算账，因为他在做这两种事时能顺便和漂亮的老板娘或者服务员调侃几句，所以我们就把这活包给了他。而江叶也乐此不疲，他说，这样最容易聚集灵感。\n"
						+ "   这次，可能因为接待人是一个大老爷们，没有能引起江叶的注意，他显得有点懒散。我们正欲提醒他，那位哈萨克老兄迅速打开了菜谱本子，往我们眼前一放，自己也不看，滔滔不绝地说起了自己的特色菜——大盘鸡。\n"
						+ "   刚才的搜索中，我了解了大盘鸡的一些情况，故对他的介绍也没有多么在意，可当他说到他店里的鸡是野放的蚱蜢鸡时，我一下来了兴趣。因为十多年前，我在一个姨表家里吃过这种鸡，肉质细腻，劲道有力，肉中有一种清香，仿佛来自遥远的天边，一路慢慢悠悠，通过你的舌尖传递到五脏六腑，使你的味蕾瞬间有爆炸般的感觉。曾经向姨表打听，他说家里养了十多只鸡，由于田多人忙，顾不上，就任由鸡满山满野地跑。它们在山坡上捉蚱蜢，在土地上逮蜘蛛，加上常年奔跑锻炼，所以肉质才十分鲜美。\n"
						+ "   自从那次吃过一次这种纯野生的鸡后，我就对市场上的鸡少了口味。可是这种野生的鸡现在很少找了。\n"
						+ "   老板又介绍说，沙湾是一个以农业为主、农牧结合的农业大县，境内水土、光热等自然资源丰富，是塔城地区乃至北疆片区棉花、粮油、蔬菜、林果等生产供应基地。正因为以农业为特色，这里出产的农产品，像粮棉以及禽牧产品才饱含原生态的滋味，保你食用后念念不忘。\n"
						+ "   老板说完走了，墙上的电视开始播放节目，介绍的还是大盘鸡。\n"
						+ "   看着节目，我们逐渐了解到，新疆大盘鸡起源于90年代初期，原创地就在沙湾县的一家清真饭馆。当时正值改革开放初始，许多四川人来到新疆干体力活。干体力活的人饭量都很大，偶尔宰杀一只鸡，也舍不得纯烧肉来吃，总会在里面添加一些便宜的蔬菜，这样更容易饱肚。由于添加的辅料多，只能用大盘来装，所以叫大盘鸡。吃着吃着，人们惊奇地发现，这种食法不仅没有冲淡鸡肉的味道，而且使鸡肉更有风味了。以致吃剩的汤汁也舍不得丢，下上特制的面皮，又是一道美味。这种简约而美味的吃法很快流行开来，风靡新疆。\n"
						+ "   一会儿，大盘鸡上来了，红的辣椒，煲得红亮的鸡块和土豆块，看着就让人食欲大增，不由拿起筷子，吃下第一口，我就在心中认定，这就是十多年前我在姨表家尝过的那种味道，虽然十多年过去了，但我对它刻骨铭心的记忆丝毫没有减退。\n"
						+ "   就在我们对大盘鸡赞不绝口的时候，外面响起了突突突的机器声，我们通过窗子向外一望，是一辆送鸡的小型三轮，鸡装在一个铁笼子里。老板迎出来，两个服务员帮着卸货。突然笼里的鸡嘎啦一声惊叫，有一只竟翅膀一扑棱飞了出来，像鹰一样，一下飞到了不远处的树杈上。\n"
						+ "   我对同伴们说，你们看到没有，那鸡的飞翔能力有多强，居然能一下飞到树上，绝对是纯野外放养的。我们家乡那些笼养的，围网养的，喂饲料快速育肥的鸡是不会有这么强的飞行能力的。\n"
						+ "   大家一致认为我说的有理。等我说完低头一看，盘里的鸡肉已经寥寥无几了。\n"
						+ "   这一餐，我们每个人都吃得很满足。饭后我们安排好住宿后，又沿着路边转了转，发现有许多大盘鸡餐馆。大盘鸡竟然成了这里的名片。\n"
						+ "   晚上，我打开电脑搜索，发现沙湾本土作者方如果曾写有《大盘鸡正传》，10万多字，而我却不知道。文章里除了讲吃，还谈了一些吃的文化，并借助沙湾大盘鸡的传说进行更深层次的人文、文化溯源，引领读者去关注这个地方本身所蕴涵的乡土文化意味，从而获得了丰富的审美体验。");
			}
			
			else if ( name.equals("沙湾爱情") )
			{
				img.setImageResource(R.drawable.info3);
				text.setText("   人们说，距离是爱情的杀手，这话不假，我的爱情差点就因距离而夭折，然而我的真诚与执着还是让我收获到了真爱。我之所以能够成功，还要感谢一个地方的一道菜，那就是沙湾大盘鸡。\n"
						+ "   那年我去新疆旅行，途径沙湾，我去探望外公外婆。我的母亲是沙湾县人，外公外婆一直居住在沙湾。\n"
						+ "   在外婆家待了两天，外婆每天都会炒一盘大盘鸡端上菜桌，外婆说大盘鸡在沙湾县可是特色，不能亏待我的外孙。\n"
						+ "   探望完外公外婆，我打算搭火车离开，我跟我女朋友就是那时候在沙湾火车站认识的。我的女朋友有个好听的名字，叫倪岚莺。她家在沙湾县，人在西安念书。我跟她认识纯属偶然，旅客排队进站的时候，她拎着大包小包，行李比她大出一倍。我跟她并排着走，可是拥挤的人群把她和行李冲散了，有些旅客还从她的行李箱上踩过，她想捡回她的行李，可是她连弯腰下去的空隙都没有。看到她这样我就上前帮她，她很感激我，这是我们故事的开头。\n"
						+ "   后来我发现她跟我在同一节车厢，而且我们离得还比较近。她也发现了我，主动跟我对面的人换了座位，于是我们坐在了一起。在火车上我们聊得很高兴，我们聊到旅行，聊到娱乐八卦和学校里的趣事，最后很默契地聊到了沙湾的大盘鸡。她叫我有时间去西安看她，到时带我去吃新疆大盘鸡。\n"
						+ "   我本来买的是去兰州的火车票，兰州有我以前的一个同学，他一直希望我能去看他。但在火车上碰见了倪岚莺，我就对她情有独钟了。因此我没有选择在兰州下车，而是补了一张到西安的火车票。在西安，倪岚莺带我去了很多地方，我们去了博物馆、登了华山、上了钟楼……那时候也是我们感情升温的阶段。所以当我快要离开西安的时候，我跟她表白了，她也接受了我的表白，那时我幸福得眩晕。\n"
						+ "   我在成都念书，她在西安，刚开始我们是电话打得多，后来慢慢就在网上聊开了，这样的日子持续到了大三，我们最期待的就是寒假和暑假，放寒假或暑假时，我经常去沙湾看她。在沙湾，我们把每一家的大盘鸡都尝了一遍。后来随着炽热感情的升温和距离矛盾的冲突，她感觉到了疲惫，分手是她提出来的，分手的理由就是异地恋太苦，不能耽误了彼此。可我舍不得，所以我一直都在坚持。\n"
						+ "   我的大伯在成都开了一间饭店，大伯知道我在恋爱，倪岚莺想要放手时，大伯给我讲了一个故事，这故事让我从低落的情绪里走了出来。\n"
						+ "   即将毕业的时候，我买了北上的车票，目的地就是沙湾。倪岚莺知道我要来，她去火车站接我。我知道她想到的是了断，但我不是。我有备而来，我相信能给倪岚莺一个未来。从火车站出来，倪岚莺沉默着，我像往常一样始终微笑着，像什么事都没有发生过。\n"
						+ "   在老地方，我们点了几个菜，其中自然少不了大盘鸡。倪岚莺眼睛有点红，我看得出来她也舍不得离开我。我给她夹鸡块，她笑了笑，像是想起我们之前在一起吃大盘鸡的情景。可是吃到一半，她说，我们还是分手吧，这样对我们彼此都好，我觉得累了。我没有回答她，只是跟她讲了一个故事。\n"
						+ "   从前在沙湾县有一对恋人，男人是外地来做买卖的，女人是普通人家的女儿。人们刚开始还看好他们的恋情，女人的父母对他们的恋爱也是充满希冀，可是后来男人在生意场上一夜间成了乞丐，他连一盘大盘鸡都买不起了。于是男人决定跟女人分手，可是女人没有放弃，她依然默默地为男人付出，男人骂她，赶她她都不走。男人那时候穷途末路，为了东山再起，他一个人扛着麻袋四处捡垃圾为生，女人知道男人自尊心很强，从来没有当面说要帮男人。有次女人知道男人很久没有吃到肉了，她亲手为男人做了一道大盘鸡，托付收破烂的老板说，他来了，你就请他喝酒，我给你钱。那老板刚开始很不以为然，但看在女人一片苦心的份上就答应了。\n"
						+ "   男人后来攒下了本钱，开起了一家杂货店，最后把女人风光地娶回了家。结婚当晚，男人说，那盘大盘鸡是他吃过的世间最美味的佳肴，让他吃得泪流满脸。女人说，你怎么知道那盘大盘鸡是我做的？男人说，那收破烂的从没给过我好脸色，还经常在小钱上跟我斤斤计较，他怎么会平白无故请我喝酒？女人听到男人的话感到很欣慰，她紧紧地依偎着男人，男人则是满脸幸福。\n"
						+ "   倪岚莺听我讲完这个故事，哭得一塌糊涂。我给她递过纸巾，安慰着她。我说，岚莺，我们好不容易能够走在一起，距离并不是问题，只要我们彼此扶持，我相信我们的爱情也能够经受住时间的考验。我想过了，毕业之后你选择在哪里，我就在哪里。我相信我能给你一个未来，请你相信我，我们不分手好吗？\n"
						+ "   倪岚莺一边哭一边不停地点头。后来倪岚莺问我，你是从哪里听来的故事？\n"
						+ "   我说，我大伯告诉我的，故事中的人物也是我最亲的人，他们现在很恩爱，男人是我父亲，女人是我母亲……");
			}
		}
		
		else
		{
			img.setImageResource(R.drawable.info1);
		}
        
        backBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DetailActivity.this.finish();
			}
        });
        
        searchBtn.setOnClickListener(new OnClickListener()
        {
			@SuppressLint("InlinedApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				View view = LayoutInflater.from(aty).inflate(R.layout.dialog, null);
				final EditText editText = (EditText) view.findViewById(R.id.search_content);
				
				Builder dialog = new AlertDialog.Builder(aty,AlertDialog.THEME_HOLO_LIGHT);
				dialog.setTitle("搜索");
				dialog.setIcon(R.drawable.btn_search);
				((ViewManager) editText.getParent()).removeView(editText);
				dialog.setView(editText);
				dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Bundle bundle = new Bundle();
						String s = editText.getText().toString();
						bundle.putString("content", s);
						showActivity(aty,SearchResultActivity.class,bundle);
						
						DetailActivity.this.finish();
						dialog.dismiss();
					}
				});
				
				dialog.setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}					
				});
				dialog.show();
			}
        });
        
        collectionBtn.setOnClickListener(new OnClickListener()
        {
			@SuppressLint({ "ShowToast", "InlinedApi" })
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				boolean isIn = false;
				
				DBManager dbManager = new DBManager();
				List<Collection> collections = dbManager.queryCollection();
				
				for ( int i = 0; i < collections.size(); i++ )
				{
					if ( collections.get(i).getName().equals(name) )
					{
						isIn = true;
						dbManager.deleteCollection(name);
						
						Builder dialog = new AlertDialog.Builder(aty,AlertDialog.THEME_HOLO_LIGHT);
						dialog.setTitle("消息");
						dialog.setMessage("就这样不要我了……");
						dialog.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.dismiss();
							}
						});
						dialog.show();
						break;
					}
				}
				
				if ( !isIn )
				{
					if ( (phoneNum != null) && (address != null) )
					{
						dbManager.addCollection(name, kind, phoneNum, address);
					}
					
					else
					{
						dbManager.addCollection(name,kind);
					}
					Builder dialog = new AlertDialog.Builder(aty,AlertDialog.THEME_HOLO_LIGHT);
					dialog.setTitle("消息");
					dialog.setMessage("我进收藏夹啦，回见!");
					dialog.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					dialog.show();
				}
				
				
			}
        });
	}
	
	@Override
	public void widgetClick( View v )
	{
		super.widgetClick(v);
		switch(v.getId()){
		case R.id.detail_map:
			mapBtn.setBackgroundResource(R.drawable.btn_map_press);
			
			Bundle bundle = new Bundle();
			bundle.putString("name", name);
			showActivity(aty,LocationActivity.class,bundle);
			break;
		case R.id.detail_phone:
			phoneBtn.setBackgroundResource(R.drawable.btn_phone_press);

			Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + phoneNum));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			
			break;
		}
	}
}
