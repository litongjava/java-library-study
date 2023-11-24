package com.litongjava.jfinal.voidetotext.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRecognized<M extends BaseRecognized<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}
	
	public void setPcmFile(java.lang.String pcmFile) {
		set("pcm_file", pcmFile);
	}
	
	public java.lang.String getPcmFile() {
		return getStr("pcm_file");
	}
	
	public void setText(java.lang.String text) {
		set("text", text);
	}
	
	public java.lang.String getText() {
		return getStr("text");
	}
	
}

