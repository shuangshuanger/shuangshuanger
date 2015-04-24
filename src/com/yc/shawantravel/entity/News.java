package com.yc.shawantravel.entity;

public class News {
	
	private int id;
	private String title;
	private String date;
	private String content;
	
	public void setId( int id )
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setTitle( String title )
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setDate( String date )
	{
		this.date = date;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public void setContent( String content )
	{
		this.content = content;
	}
	
	public String getContent()
	{
		return content;
	}
}
