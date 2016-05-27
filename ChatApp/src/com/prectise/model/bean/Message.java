package com.prectise.model.bean;

import java.sql.Timestamp;
import java.util.Date;

import com.prectise.ICommons.ICommons;
import com.prectise.utils.Utils;

public class Message {
	
	private String text;
	private String userName;
	private Date msgDateAndTime;
	
	public Message(){};
	
	public Message(String text, String userName, Date msgDateAndTime) {
		this.text = text;
		this.userName = userName;
		this.msgDateAndTime = msgDateAndTime;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getMsgDateAndTime() {
		return msgDateAndTime;
	}
	public void setMsgDateAndTime(Date msgDateAndTime) {
		this.msgDateAndTime = msgDateAndTime;
	}
	
	public void setMsgDateAndTime(Timestamp timestamp) {
		if(timestamp != null){
			this.msgDateAndTime = new Date(timestamp.getTime());
		}
	}
	
	@Override
	public String toString() {
		return "Message [text=" + text + ", userName=" + userName + ", msgDateAndTime=" + Utils.getDateFormat(ICommons.DATE_AND_TIME_PATTERN,msgDateAndTime) + "]";
	}
}
