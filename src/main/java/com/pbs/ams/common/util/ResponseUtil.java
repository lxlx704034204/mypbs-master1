package com.pbs.ams.common.util;

import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/7/7.
 */
public class ResponseUtil {
    public static void write(HttpServletResponse response, Object o)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print(o.toString());
        out.flush();
        out.close();
    }

public static void export(HttpServletResponse response, Workbook wb, String fileName)throws Exception{
        response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        OutputStream out=response.getOutputStream();
        //下载位置和处理
        wb.write(out);
        out.flush();
        out.close();
    }

}
