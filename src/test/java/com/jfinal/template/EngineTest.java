package com.jfinal.template;

import com.jfinal.kit.Kv;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class EngineTest {

	public static void main(String[] args) {
		Kv para = Kv.by("key", "value");
		String result = Engine.use().getTemplateByString("#(key)").renderToString(para);
		System.out.println(result);
	}

	@Test
	public void addFirstTest() {
		assertEquals("1", "1");
	}
}
