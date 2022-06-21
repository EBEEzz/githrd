package com.githrd.www.vo;

import java.util.*;
import java.util.Date;

import org.springframework.web.multipart.*;

import java.sql.*;
import java.text.*;

public class BoardVO {
	private int mno, bno, upno, rno, ano, click, cnt, step;
	private String id, title, body, sdate, avatar;
	private Date wdate;
	private List<FileVO> list;
	private MultipartFile[] file;
	/*
	 	JSP 프로젝트에서는 cos.jar라는 라이브러리를 사용해서 파일업로드 기능을 구현했었지만
	 	이번 스프링 프로젝트에서는
	 	commons-fileupload.jar
	 	라는 라이브러리를 사용해서 업로드 기능을 구현한다.
	 	
	 	위의 file 변수는
	 	업로드된 파일을 기억하는 변수이다.
	 	이때 만들어져야할 타입은 MultipartFile이라는 클래스 타입으로 변수가 만들어져야한다.
	 	
	 	jsp에서는 cos.jar를 이용해서 업로드를 하기 때문에
	 	input 태그의 name 속성값을 모두 다르게 해줘야만 했었다.
	 	<= 배열처리가 안되기 때문에
	 	commons-fileupload.jar는 배열처리가 되기 때문에 같은 키값으로 파일을 업로드 할 수 있다.
	 */
	
	public MultipartFile[] getFile() {
		return file;
	}
	public void setFile(MultipartFile[] file) {
		this.file = file;
	}
	public List<FileVO> getList() {
		return list;
	}
	public void setList(List<FileVO> list) {
		this.list = list;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getUpno() {
		return upno;
	}
	public void setUpno(int upno) {
		this.upno = upno;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		sdate = form.format(wdate);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
		setSdate();
	}
	@Override
	public String toString() {
		return "BoardVO [mno=" + mno + ", bno=" + bno + ", upno=" + upno + ", rno=" + rno + ", ano=" + ano + ", click="
				+ click + ", cnt=" + cnt + ", step=" + step + ", id=" + id + ", title=" + title + ", body=" + body
				+ ", sdate=" + sdate + ", avatar=" + avatar + ", wdate=" + wdate + "]";
	}
	
}