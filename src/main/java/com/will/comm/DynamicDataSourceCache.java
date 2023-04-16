package com.will.comm;

import static com.will.comm.Common.defaultDataSourceName;

/**
 * ds cache
 *
 * @author Will
 * @create 2023:04:14 12:03
 **/
public class DynamicDataSourceCache {
  private static ThreadLocal<String> dsCache = new ThreadLocal<>();

  public static String getDsFromCache(){
    return dsCache.get();
  }

  public static void setDsCache(String dsName) {
    if ("".equals(dsName)){
      dsCache.set(defaultDataSourceName);
      return;
    }

    dsCache.set(dsName);
  }
}
