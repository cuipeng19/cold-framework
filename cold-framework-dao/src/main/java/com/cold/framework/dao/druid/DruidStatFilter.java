package com.cold.framework.dao.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author cuipeng
 * @date 2019/1/29 17:16
 */
@WebFilter(filterName = "druidStatFilter",
            urlPatterns = "/*",
            initParams = {@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")})
public class DruidStatFilter extends WebStatFilter {
}
