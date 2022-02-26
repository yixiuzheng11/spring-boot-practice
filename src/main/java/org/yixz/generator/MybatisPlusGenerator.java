package org.yixz.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;
import java.util.*;

/**
 * @Author: yixz
 */
public class MybatisPlusGenerator {
    //项目路径
    final static String  dirPath = System.getProperty("user.dir");
    //需要生成代码的表名
    final static String[]  tableNames = new String[] {"t_menu", "t_user_role", "t_role_menu"};

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Pabst@2020");
        dsc.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        mpg.setDataSource(dsc);

        //文件生成路径
        gc.setOutputDir(dirPath + "/src/main/java/");
        System.out.println( "路径为" + gc.getOutputDir());
        gc.setAuthor("yixz");
        //是否覆盖生成的文件
        gc.setFileOverride(true);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("org.yixz");
        //pc.setModuleName("example.mybatisP");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //控制不生成 controller, 空字符串就行
        templateConfig.setController("");
        mpg.setTemplate(templateConfig);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        //XML配置，不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 需要生成的表，如果 setInclude() 不加参数, 会自定查找所有表
        strategy.setInclude(tableNames);
        //strategy.setInclude(scanner("表名"));
        //strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        // 排除生成的表
        // strategy.setExclude(new String[]{"test"});
        //是否为实体类生成lombok注解
        strategy.setEntityLombokModel(true);
        //生成实体时去除表名的前缀
        strategy.setTablePrefix(new String[] { "t_"});
        // 表名生成策略，驼峰命名规则
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //列名规则，驼峰命名规则
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //是否生成lombok注解
        strategy.setEntityLombokModel(true);
        //自动填充的配置
        TableFill created_date = new TableFill("created_date", FieldFill.INSERT);
        TableFill created_by = new TableFill("created_by", FieldFill.INSERT);
        //设置更新时间的生成策略
        TableFill updated_date = new TableFill("updated_date", FieldFill.INSERT_UPDATE);
        //设置更新时间的生成策略
        TableFill updated_by = new TableFill("updated_by", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> list = new ArrayList<>();
        list.add(created_date);
        list.add(created_by);
        list.add(updated_date);
        list.add(updated_by);
        strategy.setTableFillList(list);
        //是否生成@RestController注解的controller
        strategy.setRestControllerStyle(true);
        // 自定义实体父类
        //strategy.setSuperEntityClass("org.yixz.entity.BaseEntity");
        // 自定义实体，公共字段
        //strategy.setSuperEntityColumns(new String[] { "baseId"});
        // 自定义 mapper父类，默认是com.baomidou.mybatisplus.core.mapper.BaseMapper
        // strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        // 自定义 service父类，默认是"com.baomidou.mybatisplus.extension.service.IService
        // strategy.setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService");
        // 自定义 service实现类父类，默认是com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
        //strategy.setSuperServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        strategy.setEntityBuilderModel(true);
        mpg.setStrategy(strategy);

        // 执行生成
        mpg.execute();
    }
}
