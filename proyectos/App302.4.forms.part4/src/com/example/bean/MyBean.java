package com.example.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

// Bean de retención

@ManagedBean
@RequestScoped
public class MyBean {

	private String title;
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
