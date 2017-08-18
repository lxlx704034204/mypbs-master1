package com.pbs.ams.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pbs.ams.common.constant.GeneratorEnum;
import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.pbs.ams.common.util.StringUtil.lineToHump;

/**
 * Mybatis代码生成类
 * Created by ams on 2017/6/10.
 * @author TiAmo
 * 如果要生成的mapper.xml存在 内容会被追加,如果需要重写切记备份后手动删除原文件.
 * 如果要生成的service/serviceImpl存在,不会被覆盖.
 * 如果要生成的model存在,会被重写.如需原文件请注意备份.
 */
public class MybatisGeneratorUtil {

	private static String generatorConfig_vm = "/template/generatorConfig.vm";// generatorConfig模板路径
	private static String service_vm = "/template/Service.vm";				// Service模板路径
	private static String serviceMock_vm = "/template/ServiceMock.vm";		// ServiceMock模板路径
	private static String serviceImpl_vm = "/template/ServiceImpl.vm";		// ServiceImpl模板路径



	public static void generator(String jdbc_driver,
								  String jdbc_url,
								  String jdbc_username,
								  String jdbc_password,		//JDBC_PASSWORD

								  String module,			//null   zheng-upms
								  String database,			//数据库name
								  String table_prefix,		//"upms_" 先研究权限模块
								  String package_name,		//PACKAGE_NAME   "com.pbs.ams.web"
								  Map<String, String> last_insert_id_tables) throws Exception {
		System.out.println("-------test-0.1----: "+ MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath() );
		//	/F:/999/mypbs-master/target/classes/template/generatorConfig.vm

		generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath().replaceFirst("/", "");
		service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath().replaceFirst("/", "");
		serviceMock_vm = MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath().replaceFirst("/", "");
		serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath().replaceFirst("/", "");

		System.out.println("-------test-2----: "+ MybatisGeneratorUtil.class.getResource("/").getPath());
		//	/F:/999/mypbs-master/target/classes/
//		String basePath 		   = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "").replace(targetProject, "").replaceFirst("/", "");
		String basePath 		   = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "").replaceFirst("/", "");
		System.out.println("-------test-3----: "+ basePath); 		//  F:/999/mypbs-master   D:/CRM/000/zhengDiy2/zheng-master/ 	//当前program的路径


		// 通过当前的module中的Generator.java中的main函数调用MybatisGeneratorUtil中的generator(...)方法 在当前module下自动生成当前module的 generatorConfig.xml 文件 !
		String generatorConfig_xml = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "") + "/src/main/resources/generatorConfig.xml";
		System.out.println("-------test-4----: "+ generatorConfig_xml);
		// /D:/CRM/000/zhengDiy2/zheng-master/zheng-upms/zheng-upms-dao/src/main/resources/generatorConfig.xml
		// 										   /F:/999/mypbs-master/src/main/resources/generatorConfig.xml
		String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "' AND table_name LIKE '" + table_prefix + "_%';";

		System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
		List<Map<String, Object>> tables = new ArrayList<>();
		try {
			VelocityContext context = new VelocityContext();
			Map<String, Object> table;

			// 查询定制前缀项目的所有表
			JdbcUtil jdbcUtil = new JdbcUtil(jdbc_driver, jdbc_url, jdbc_username, AESUtil.AESDecode(jdbc_password));
			List<Map> result = jdbcUtil.selectByParams(sql, null);
			for (Map map : result) {
				System.out.println(map.get("TABLE_NAME"));
				table = new HashMap<>();
				table.put("table_name", map.get("TABLE_NAME"));
				table.put("model_name", lineToHump(ObjectUtils.toString(map.get("TABLE_NAME"))));
				tables.add(table);
			}
			jdbcUtil.release();

			System.out.println("-------test-8.0----: "+ generatorConfig_xml);
			File xmlFile = new File(generatorConfig_xml); // 也可以写"src/main/resources/generatorConfig.xml"
			if (xmlFile.exists()){
				FileUtil.deleteDir(xmlFile);
				System.out.println("========== 删除原有generatorConfig.xml文件 ==========");
			}

			//用这块 就无法通过UpmsCompanyMapperTest测试
//			String model_package_string = basePath + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/modle";
//			System.out.println("-------test-8.1----: "+ model_package_string); 	   // F:/999/mypbs-master/src/main/java/com/pbs/ams/web/modle
//			String mappers_package_string = basePath + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/mappers";
//			String mapperXML_package_string = basePath + "/src/main/resources" + "/mappers";
//			System.out.println("-------test-8.2----: "+ mapperXML_package_string); // F:/999/mypbs-master/src/main/resources/mappers
//			File model_package = new File(model_package_string); File mappers_package = new File(mappers_package_string);
//			if (model_package.exists())   FileUtil.deleteDir(model_package);   System.out.println("========== 删除原有model目录 ==========");
//			if (mappers_package.exists()) FileUtil.deleteDir(mappers_package); System.out.println("========== 删除原有mappersInterface目录 ==========");
//			File mapperXML_package = new File(mappers_package_string);
//			if (mapperXML_package.exists()) FileUtil.deleteDir(mapperXML_package); System.out.println("========== 删除原有mappersXML目录 ==========");



			context.put("listTables", tables);//lstTables
			//										副包
			context.put("model_targetPackage", 				package_name +".model");		//model生成
			context.put("mapperInterface_targetPackage",	package_name +".mappers");		//Mapper接口生成
			context.put("modelAndMapperInterface_moduleSon",basePath);//targetProject	    (model和Mapper接口 生成的module位置) D:/CRM/000/zhengDiy2/zheng-master/zheng-upms/zheng-upms-dao			-- 代码生成模块，无需开发
			context.put("mapperXML_moduleSon",				basePath);//targetProject_sqlMap(MapperXML         生成的module位置) D:/CRM/000/zhengDiy2/zheng-master/zheng-upms/zheng-upms-rpc-service  -- rpc服务provider
			context.put("mapperXML_targetPackage",						  ".mappers");		//MapperXML生成 (resources旗下)

			context.put("jdbc_password", 			AESUtil.AESDecode(jdbc_password));
			context.put("last_insert_id_tables", last_insert_id_tables);


//			context.put("listTables", tables);//lstTables
//			context.put("model_target_package", 	package_name +".model");						//model生成
//			context.put("mapper_target_package",	package_name +".mappers");						//Mapper接口生成
//			context.put("targetProject_java", 		basePath+"/"+"src/main/java");					//副包名："src/main/java"	   (model、Mapper接口)
//			context.put("targetProject_resource", 	basePath+"/"+"src/main/resources");				//资源名："src/main/resources" (MapperXML )
//			context.put("xml_target_package",		"mappers");										//MapperXML生成
//
//			context.put("jdbc_password", 			AESUtil.AESDecode(jdbc_password));


			System.out.println("-------test-9.1----: "+ generatorConfig_vm);
			System.out.println("-------test-9.2----: "+ generatorConfig_xml);
			VelocityUtil.generate(generatorConfig_vm,  generatorConfig_xml,  context);

//			context.put("listTables", tables);//lstTables
//			context.put("model_target_package", GeneratorEnum.PACKAGE_NAME.getValue()+".model");	//model生成
//			context.put("xml_target_package","mappers");											//MapperXML生成
//			context.put("mapper_target_package",GeneratorEnum.PACKAGE_NAME.getValue()+".mappers");	//Mapper接口生成
//			context.put("targetProject_java", GeneratorEnum.TARGET_PROJECT_JAVA.getValue());		//副包名："src/main/java"
//			context.put("targetProject_resource", GeneratorEnum.TARGET_PROJECT_RESOUCE.getValue());	//资源名："src/main/resources"
//			context.put("jdbc_password", GeneratorEnum.JDBC_PASSWORD.getByAESDecode());
//			VelocityUtil.generate(GeneratorEnum.GENERATORCONFIG_VM.getValue(), GeneratorEnum.XML_PATH.getValue(), context);


		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("========== 结束生成generatorConfig.xml文件 ==========");

		System.out.println("========== 开始运行MybatisGenerator ==========");
		List<String> warnings = new ArrayList<>();
//		File configFile = new File(GeneratorEnum.XML_PATH.getValue());
		File configFile = new File("src/main/resources/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		for (String warning : warnings) {
			System.out.println(warning);
		}
		System.out.println("========== 结束运行MybatisGenerator ==========");

		System.out.println("========== 开始生成Service ==========");
		String ctime = new SimpleDateFormat("yyyy/M/d").format(new Date());
		String servicePath = basePath+"/src/main/java/"+package_name.replaceAll("\\.", "/")+"/service";			//"src/main/java/com/pbs/ams/web/service"
		String serviceImplPath = basePath+"/src/main/java/"+package_name.replaceAll("\\.", "/")+"/service/impl"; //"src/main/java/com/pbs/ams/web/service/impl"
		File servicePathFile = new File(servicePath);
		File serviceImplPathFile = new File(serviceImplPath);

		if (servicePathFile.exists()) {
			servicePathFile.mkdirs();
		}
		if (!serviceImplPathFile.exists()) {
			serviceImplPathFile.mkdirs();
		}

		for (int i = 0; i < tables.size(); i++) {
			String modelName = StringUtil.lineToHump(ObjectUtils.toString(tables.get(i).get("table_name")));
			String service = 	 servicePath + "/" + modelName + "Service.java";
			String serviceMock = servicePath + "/" + modelName + "ServiceMock.java";
			String serviceImpl = serviceImplPath + "/" + modelName + "ServiceImpl.java";
			// 生成service
			File serviceFile = new File(service);
			if (!serviceFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name); 		 	//"com.pbs.ams.web"
				context.put("model", modelName);
				context.put("ctime", ctime);
				VelocityUtil.generate(service_vm, service, context);//"template/Service.vm"
				System.out.println(service);
			}
			// 生成serviceMock
			File serviceMockFile = new File(serviceMock);
			if (!serviceMockFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", modelName);
				context.put("ctime", ctime);
				VelocityUtil.generate(serviceMock_vm, serviceMock, context);
				System.out.println(serviceMock);
			}
			// 生成serviceImpl
			File serviceImplFile = new File(serviceImpl);
			if (!serviceImplFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", modelName);
				context.put("mapper", StringUtil.toLowerCaseFirstOne(modelName));
				context.put("ctime", ctime);
				VelocityUtil.generate(serviceImpl_vm, serviceImpl, context);	//"template/ServiceImpl.vm"
				System.out.println(serviceImpl);
			}
		}
		System.out.println("========== 结束生成Service ==========");

	}





}
