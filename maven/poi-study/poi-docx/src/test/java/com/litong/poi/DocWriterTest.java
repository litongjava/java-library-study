package com.litong.poi;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author litong
 * @date 2018年5月30日_下午9:22:31 
 * @version 1.0 
 */
public class DocWriterTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void searchAndReplaceTest() {
		//String src = "D:\\项目云信公司\\资源平台\\P2-云信资源平台\\受控库\\文档\\01 项目管理\\08 项目监控\\03 项目周报\\周会通知\\云信资源平台-周会通知_20171012.doc";
		String src="C:\\Users\\Administrator\\Desktop\\异常.docx";
		DocWriter.searchAndReplace(src, null, null);
	}
}
