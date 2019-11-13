/**
 * Copyright (c) 2011-2017, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jfinal.config;

import java.io.File;
import java.util.Properties;
import com.jfinal.core.Const;
import com.jfinal.kit.Prop;
import com.jfinal.template.Engine;

/**
 * JFinalConfig.
 * <p>
 * Config order: configConstant(), configPlugin(), configRoute(), configEngine(), configInterceptor(), configHandler()
 */
public abstract class JFinalConfig {
	
	/**
	 * Config constant
	 */
	/**
	 * 此方法用来配置JFinal常量值，如开发模式常量devMode的配置，如下代码配置了JFinal运行在开发模式：
	 * 在开发模式下，JFinal会对每次请求输出报告，如输出本次请求的URL、Controller、Method以及请求所携带的参数。
	 */
	public abstract void configConstant(Constants me);
	
	/**
	 * Config route
	 */
	/**
	 * 此方法用来配置访问路由，如下代码配置了将 "/hello" 映射到HelloController这个控制器，通过以下的配置，http://localhost/hello  将访问 HelloController.index() 方法，而http://localhost/hello/methodName  将访问到 HelloController.methodName() 方法。
	 * @param me
	 */
	public abstract void configRoute(Routes me);
	
	/**
	 * Config engine
	 */
	/**
	 * 此方法用来配置Template Engine
	 * @param me
	 */
	public abstract void configEngine(Engine me);
	
	/**
	 * Config plugin
	 */
	/**
	 * 此方法用来配置JFinal的Plugin，如下代码配置了Druid数据库连接池插件与ActiveRecord数据库访问插件。通过以下的配置，可以在应用中使用ActiveRecord非常方便地操作数据库
	 * @param me
	 */
	public abstract void configPlugin(Plugins me);
	
	/**
	 * Config interceptor applied to all actions.
	 */
	/**
	 * 此方法用来配置JFinal的全局拦截器，全局拦截器将拦截所有 action 请求，除非使用@Clear在Controller中清除
	 *  JFinal 的 Interceptor 非常类似于 Struts2，但使用起来更方便，Interceptor 配置粒度分为 Global、Inject、Class、Method四个层次，其中以上代码配置粒度为全局。Inject、Class与Method级的Interceptor配置将在后续章节中详细介绍
	 * @param me
	 */
	public abstract void configInterceptor(Interceptors me);
	
	/**
	 * Config handler
	 */
	/**
	 * 此方法用来配置JFinal的Handler，如下代码配置了名为ResourceHandler的处理器，Handler可以接管所有web请求，并对应用拥有完全的控制权，可以很方便地实现更高层的功能性扩展
	 * @param me
	 */
	public abstract void configHandler(Handlers me);
	
	/**
	 * Call back after JFinal start
	 */
	public void afterJFinalStart(){}
	
	/**
	 * Call back before JFinal stop
	 */
	public void beforeJFinalStop(){}
	
	protected Prop prop = null;
	
	/**
	 * Load property file.
	 * @see #loadPropertyFile(String, String)
	 */
	public Properties loadPropertyFile(String fileName) {
		return loadPropertyFile(fileName, Const.DEFAULT_ENCODING);
	}
	
	/**
	 * Load property file.
	 * Example:<br>
	 * loadPropertyFile("db_username_pass.txt", "UTF-8");
	 * 
	 * @param fileName the file in CLASSPATH or the sub directory of the CLASSPATH
	 * @param encoding the encoding
	 */
	public Properties loadPropertyFile(String fileName, String encoding) {
		prop = new Prop(fileName, encoding);
		return prop.getProperties();
	}
	
	/**
	 * Load property file.
	 * @see #loadPropertyFile(File, String)
	 */
	public Properties loadPropertyFile(File file) {
		return loadPropertyFile(file, Const.DEFAULT_ENCODING);
	}
	
	/**
	 * Load property file
	 * Example:<br>
	 * loadPropertyFile(new File("/var/config/my_config.txt"), "UTF-8");
	 * 
	 * @param file the properties File object
	 * @param encoding the encoding
	 */
	public Properties loadPropertyFile(File file, String encoding) {
		prop = new Prop(file, encoding);
		return prop.getProperties();
	}
	
	public void unloadPropertyFile() {
		this.prop = null;
	}
	
	private Prop getProp() {
		if (prop == null) {
			throw new IllegalStateException("Load propties file by invoking loadPropertyFile(String fileName) method first.");
		}
		return prop;
	}
	
	public String getProperty(String key) {
		return getProp().get(key);
	}
	
	public String getProperty(String key, String defaultValue) {
		return getProp().get(key, defaultValue);
	}
	
	public Integer getPropertyToInt(String key) {
		return getProp().getInt(key);
	}
	
	public Integer getPropertyToInt(String key, Integer defaultValue) {
		return getProp().getInt(key, defaultValue);
	}
	
	public Long getPropertyToLong(String key) {
		return getProp().getLong(key);
	}
	
	public Long getPropertyToLong(String key, Long defaultValue) {
		return getProp().getLong(key, defaultValue);
	}
	
	public Boolean getPropertyToBoolean(String key) {
		return getProp().getBoolean(key);
	}
	
	public Boolean getPropertyToBoolean(String key, Boolean defaultValue) {
		return getProp().getBoolean(key, defaultValue);
	}
}







