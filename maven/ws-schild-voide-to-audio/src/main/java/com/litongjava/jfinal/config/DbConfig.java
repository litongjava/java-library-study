package com.litongjava.jfinal.config;

import com.jfinal.config.Plugins;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.Sqlite3Dialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.litongjava.jfinal.models.voidetotext.model._MappingKit;
import com.litongjava.modules.jfinal.utils.PropKitUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author create by ping-e-lee on 2021年7月9日 下午1:26:18 
 * @version 1.0 
 * @desc
 */
@Slf4j
public class DbConfig {

  public static void config(Plugins me) {
    DruidPlugin dp = initDruidPlugin();
    ActiveRecordPlugin ar = initActiveRecordPlugin(dp);
    me.add(dp);
    me.add(ar);
    log.info("添加主数据源完成");

  }

  public static ActiveRecordPlugin initActiveRecordPlugin(DruidPlugin dp) {
    ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
    arp.setDialect(new Sqlite3Dialect());
    arp.setShowSql(PropKitUtils.getBoolean("jdbc.showSql"));
    _MappingKit.mapping(arp);
    return arp;
  }

  public static DruidPlugin initDruidPlugin() {
    String jdbcUrl = PropKitUtils.get("jdbc.url");
    String jdbcUser = PropKitUtils.get("jdbc.user");
    String jdbcPswd = PropKitUtils.get("jdbc.pswd");
    /**
     * druid会自动检测数据库驱动
     */
    DruidPlugin dp = new DruidPlugin(jdbcUrl, jdbcUser, jdbcPswd);
    return dp;
  }
}