package com.cold.framework.api.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author cuipeng
 * @date 2019/1/29 17:21
 */
@WebServlet(urlPatterns = "/druid/*",
            initParams = {
                @WebInitParam(name = "loginUsername", value = "dev"),
                @WebInitParam(name = "loginPassword", value = "dev"),
                @WebInitParam(name = "resetEnable", value = "false")
            })
public class DruidStatServlet extends StatViewServlet {
}
