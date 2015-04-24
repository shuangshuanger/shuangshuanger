package com.yc.shawantravel.entity;

import java.util.HashMap;
import java.util.Map;

import com.baidu.mapapi.model.LatLng;

public class PointList {
	
	private Map<String,LatLng> pointList = new HashMap<String,LatLng>();
	
	public PointList()
	{
		pointList.put("天博生态大酒店", new LatLng(44.342419, 85.628061));
		pointList.put("沙湾迎宾馆", new LatLng(44.330723,85.623674));
		pointList.put("鑫东方大酒店", new LatLng(44.330723,85.623674));
		pointList.put("德荣酒店", new LatLng(44.333579,85.641306));
		pointList.put("民政宾馆", new LatLng(44.332277,85.618844));
		pointList.put("明盛宾馆", new LatLng(44.336719,85.634739));
		pointList.put("七星商务宾馆", new LatLng(44.333648,85.632019));
		pointList.put("温州宾馆", new LatLng(44.336016,85.633134));
		pointList.put("星光快捷宾馆", new LatLng(44.33405,85.629432));
		pointList.put("和平宾馆", new LatLng(44.33688,85.633303));
		pointList.put("佳苑宾馆", new LatLng(44.328975,85.644612));
		pointList.put("荣旺宾馆", new LatLng(44.336332,85.631747));
		pointList.put("天博生态园", new LatLng(44.36675,85.589718));
		pointList.put("锦绣沙湾大盘美食城", new LatLng(44.332548,85.599814));
		pointList.put("千泉湖酒楼", new LatLng(44.33325,85.599612));
		pointList.put("徐师傅烤鸭", new LatLng(44.336719,85.634739));
		pointList.put("金鲁西肥牛火锅", new LatLng(44.333657,85.599018));
		pointList.put("金融酒家", new LatLng(44.332651,85.636596));
		pointList.put("锦绣大盘食府饭馆", new LatLng(44.332548,85.599814));
		pointList.put("得月楼福顺火锅", new LatLng(44.82498,85.598575));
		pointList.put("金手捞火锅城", new LatLng(44.338653,85.629378));
		pointList.put("森林雨火锅", new LatLng(44.33601,85.630399));
		pointList.put("鑫星宴会厅", new LatLng(44.332799,85.642391));
		pointList.put("沙味王", new LatLng(44.33347,85.601233));
		pointList.put("桥头回民大盘鸡总店", new LatLng(44.335462,85.628826));
		pointList.put("杏花村大盘鸡总店", new LatLng(44.33307,85.606359));
		pointList.put("阿腾布拉克草原牧鸡专卖", new LatLng(44.330846,85.619753));
		pointList.put("稻花香大盘鸡店", new LatLng(44.333508,85.641505));
		pointList.put("风云上海滩特色架子肉", new LatLng(44.330846,85.619753));
		pointList.put("祥福顺特色大盘土鸡店", new LatLng(44.330846,85.619753));
		pointList.put("沙湾金港农家特色土鸡店", new LatLng(44.330846,85.619753));
		pointList.put("飞达影院", new LatLng(44.335036,85.62649));
		pointList.put("银河娱乐会所", new LatLng(44.637545,85.917981));
		pointList.put("好声音欢唱城", new LatLng(44.335146,85.626192));
		pointList.put("夜猫经典音乐酒吧", new LatLng(44.331658,85.631591));
		pointList.put("最劲KTV", new LatLng(44.334837,85.629082));
		pointList.put("歌谷欢唱城", new LatLng(44.332548,85.599814));
		pointList.put("凯萨酒吧", new LatLng(44.331845,85.62165));
		pointList.put("金柜欢唱会所", new LatLng(44.332883,85.600187));
		pointList.put("蓝月亮酒吧", new LatLng(44.334978,85.633295));
		pointList.put("金色年华娱乐会所", new LatLng(44.330858,85.624314));
	}
	
	public void setPointList(Map<String,LatLng> pointList)
	{
		this.pointList = pointList;
	}
	
	public Map<String,LatLng> getPoitnList()
	{
		return this.pointList;
	}

}
