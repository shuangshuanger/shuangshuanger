package com.yc.shawantravel.entity;

import java.util.List;

import org.kymjs.kjframe.KJDB;

public class DBManager {
	private Scenic scenic;
	private Food food;
	private Game game;
	private Hotel hotel;
	private Shopping shopping;
	private Collection collection;
	private Information info;
	private static KJDB kjdb;
	
	public DBManager(  )
	{
	}
	
	public void initData()
	{
		kjdb = KJDB.create();
	}
	
	public void addDatas()
	{
		//存储风景区
		scenic = new Scenic();
		scenic.setName("东大塘风景区");
		kjdb.save(scenic);

		scenic.setName("鹿角湾景区");
		kjdb.save(scenic);

		scenic.setName("温泉景区");
		kjdb.save(scenic);

		scenic.setName("森林公园");
		kjdb.save(scenic);

		scenic.setName("千泉湖景区");
		kjdb.save(scenic);		
				
		scenic.setName("瀚海柳浪景区");
		kjdb.save(scenic);
				
		//存储酒店
		//1
		hotel = new Hotel();
		hotel.setName("天博生态大酒店");
		hotel.setAddress("沙湾县大泉乡三道沟村");
		hotel.setPhoneNum("0993-6099588");
		kjdb.save(hotel);
		//2	
		hotel.setName("沙湾迎宾馆");
		hotel.setAddress("沙湾县塔城西路25号");
		hotel.setPhoneNum("0993-6010620");
		kjdb.save(hotel);
		//3	
		hotel.setName("鑫东方大酒店");
		hotel.setAddress("沙湾县世界大道12号");
		hotel.setPhoneNum("0993-6011185");
		kjdb.save(hotel);
		//4
		hotel.setName("德荣酒店");
		hotel.setAddress("沙湾县乌鲁木齐东路166号");
		hotel.setPhoneNum("0993-6058888");
		kjdb.save(hotel);
		//5
		hotel.setName("民政宾馆");
		hotel.setAddress("沙湾县火车站路23号");
		hotel.setPhoneNum("0993-6020788");
		kjdb.save(hotel);
		//6
		hotel.setName("明盛宾馆");
		hotel.setAddress("沙湾县老沙湾县28号");
		hotel.setPhoneNum("0993-6027777");
		kjdb.save(hotel);
		//7
		hotel.setName("七星商务宾馆");
		hotel.setAddress("沙湾县三道河子镇教育路701号");
		hotel.setPhoneNum("0993-6056688");
		kjdb.save(hotel);
		//8
		hotel.setName("温州宾馆");
		hotel.setAddress("沙湾县乌鲁木齐东路6号");
		hotel.setPhoneNum("0993-6024088");
		kjdb.save(hotel);
		//9
		hotel.setName("星光快捷宾馆");
		hotel.setAddress("沙湾县世界大道182号");
		hotel.setPhoneNum("0993-6011760");
		kjdb.save(hotel);
		//10
		hotel.setName("和平宾馆");
		hotel.setAddress("沙湾县团结路1号");
		hotel.setPhoneNum("0993-6021999");
		kjdb.save(hotel);
		//11
		hotel.setName("佳苑宾馆");
		hotel.setAddress("沙湾县凯旋路");
		hotel.setPhoneNum("0993-6099266");
		kjdb.save(hotel);
		//12
		hotel.setName("荣旺宾馆");
		hotel.setAddress("沙湾县乌鲁木齐东路8号");
		hotel.setPhoneNum("15276916111");
		kjdb.save(hotel);
				
		//存储美食
		//1
		food = new Food();
		food.setName("沙湾迎宾馆");
		food.setAddress("沙湾县塔城西路");
		food.setPhoneNum("13999339608");
		kjdb.save(food);
		//2
		food.setName("天博生态园");
		food.setAddress("沙湾县大泉六队");
		food.setPhoneNum("18799286888");
		kjdb.save(food);
		//3
		food.setName("锦绣沙湾大盘美食城");
		food.setAddress("沙湾县大盘美食城");
		food.setPhoneNum("0993-6028333");
		kjdb.save(food);
		//4		
		food.setName("千泉湖酒楼");
		food.setAddress("沙湾县乌鲁木齐西路");
		food.setPhoneNum("13899529222");
		kjdb.save(food);
		//5
		food.setName("金鲁西肥牛火锅");
		food.setAddress("沙湾县大盘美食城");
		food.setPhoneNum("0993-6023555");
		kjdb.save(food);
		//6
		food.setName("徐师傅烤鸭");
		food.setAddress("沙湾县青年路明盛宾馆综合楼");
		food.setPhoneNum("15026220085");
		kjdb.save(food);
		//7
		food.setName("金融酒家");
		food.setAddress("沙湾县金沟河路");
		food.setPhoneNum("13677545323");
		kjdb.save(food);
		//8
		food.setName("锦绣大盘食府饭馆");
		food.setAddress("沙湾县锦绣大盘食府");
		food.setPhoneNum("0993-6028333");
		kjdb.save(food);
		//9
		food.setName("得月楼福顺火锅");
		food.setAddress("沙湾县人民路");
		food.setPhoneNum("15609935860");
		kjdb.save(food);
		
		//10
		food.setName("金手捞火锅城");
		food.setAddress("沙湾县沙城路");
		food.setPhoneNum("15309939999");
		kjdb.save(food);
		
		//11
		food.setName("森林雨火锅");
		food.setAddress("沙湾县乌鲁木齐西路");
		food.setPhoneNum("18609935551");
		kjdb.save(food);
		
		//12
		food.setName("鑫星宴会厅");
		food.setAddress("沙湾县乌鲁木齐西路");
		food.setPhoneNum("15899290320");
		kjdb.save(food);
		//13
		food.setName("沙味王");
		food.setAddress("沙湾县乌鲁木齐西路");
		food.setPhoneNum("0993-6099989");
		kjdb.save(food);
		//14
		food.setName("桥头回民大盘鸡总店");
		food.setAddress("沙湾县");
		food.setPhoneNum("0993-6010889");
		kjdb.save(food);
		//15
		food.setName("杏花村大盘鸡总店");
		food.setAddress("沙湾县");
		food.setPhoneNum("0993-6106094");
		kjdb.save(food);
		//16
		food.setName("阿腾布拉克草原牧鸡专卖");
		food.setAddress("沙湾县");
		food.setPhoneNum("0993-6010968");
		kjdb.save(food);
		//17
		food.setName("稻花香大盘鸡店");
		food.setAddress("沙湾县");
		food.setPhoneNum("13579773008");
		kjdb.save(food);
		//18
		food.setName("风云上海滩特色架子肉");
		food.setAddress("沙湾县");
		food.setPhoneNum("0993-6106789");
		kjdb.save(food);
		//19
		food.setName("祥福顺特色大盘土鸡店");
		food.setAddress("沙湾县");
		food.setPhoneNum("15909935386");
		kjdb.save(food);
		//20
		food.setName("沙湾金港农家特色土鸡店");
		food.setAddress("沙湾县");
		food.setPhoneNum("13119939855");
		kjdb.save(food);
		
		//存储购物商城
		//1
		shopping = new Shopping();
		shopping.setName("人民路商业街");
		kjdb.save(shopping);

		//2
		shopping.setName("鑫沙湾步行街");
		kjdb.save(shopping);
		
		//3
		shopping.setName("星光街");
		kjdb.save(shopping);
			
		//4
		shopping.setName("爱家超市");
		kjdb.save(shopping);
			
		//5
		shopping.setName("人民路地下街");
		kjdb.save(shopping);
		
		//6
		shopping.setName("万客隆");
		kjdb.save(shopping);
				
		//存储娱乐中心
		//1
		game = new Game();
		game.setName("飞达影院");
		game.setAddress("沙湾县乌鲁木齐西路荣盛大厦");
		game.setPhoneNum("13899500010");
		kjdb.save(game);
		//2	
		game.setName("银河娱乐会所");
		game.setAddress("沙湾县柳毛湾镇");
		game.setPhoneNum("13070099111");
		kjdb.save(game);
		//3	
		game.setName("好声音欢唱城");
		game.setAddress("沙湾县乌鲁木齐西路荣盛大厦负一层");
		game.setPhoneNum("18799737789");
		kjdb.save(game);
		//4	
		game.setName("夜猫经典音乐酒吧");
		game.setAddress("沙湾县人民路2号");
		game.setPhoneNum("13319930768");
		kjdb.save(game);
		//5	
		game.setName("最劲KTV");
		game.setAddress("沙湾县世纪大道97号");
		game.setPhoneNum("0993-6013444");
		kjdb.save(game);
		//6
		game.setName("歌谷欢唱城");
		game.setAddress("沙湾县大盘美食城");
		game.setPhoneNum("0993-6105588");
		kjdb.save(game);
		//7
		game.setName("凯萨酒吧");
		game.setAddress("沙湾县塔城西路63号");
		game.setPhoneNum("13364984333");
		kjdb.save(game);
		//8
		game.setName("金柜欢唱会所");
		game.setAddress("沙湾县大盘美食城12栋");
		game.setPhoneNum("0993-6028222");
		kjdb.save(game);
		//9
		game.setName("蓝月亮酒吧");
		game.setAddress("沙湾县教育路175-1号");
		game.setPhoneNum("13779201222");
		kjdb.save(game);
		//10
		game.setName("金色年华娱乐会所");
		game.setAddress("沙湾县金钩河路");
		game.setPhoneNum("13519939366");
		kjdb.save(game);
		
		//存储地方文化
		info = new Information();
		info.setTitle("沙湾县鼓励投资和招商引资优惠政策（试行）");
		kjdb.save(info);
		
		info.setTitle("真味沙湾");
		kjdb.save(info);
		
		info.setTitle("沙湾爱情");
		kjdb.save(info);
	}
	
	public List<Scenic> queryScenics()
	{
		return  kjdb.findAll(Scenic.class);
	}
	
	public List<Hotel> queryHotels()
	{
		return kjdb.findAll(Hotel.class);
	}
	
	public List<Food> queryFood()
	{
		return kjdb.findAll(Food.class);
	}
	
	public List<Shopping> queryShopping()
	{
		return kjdb.findAll(Shopping.class);
	}
	
	public List<Game> queryGame()
	{
		return kjdb.findAll(Game.class);
	}
	
	public List<Information> queryInformation()
	{
		return kjdb.findAll(Information.class);
	}
	
	public List<Collection> queryCollection()
	{
		return kjdb.findAll(Collection.class);
	}
	
	public void addCollection( String name, String kind )
	{
		collection = new Collection();
		collection.setName(name);
		collection.setKind(kind);
		kjdb.save(collection);
	}
	
	public void addCollection( String name, String kind, String phoneNum, String address )
	{
		collection = new Collection();
		collection.setName(name);
		collection.setKind(kind);
		collection.setPhoneNum(phoneNum);
		collection.setAddress(address);
		kjdb.save(collection);
	}
	
	public void deleteCollection( String name )
	{
		kjdb.deleteByWhere(Collection.class, "name="+"'"+name+"'");
	}
}