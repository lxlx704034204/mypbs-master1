package com.pbs.ams.common.util;

/**
 * Created by TiAmo on 17/7/4.
 */
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.contrib.HSSFCellUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import java.io.*;

/**
 * 描述：Excel写操作帮助类
 *
 *
 * */
public class ExcelUtil {

    /**
     * 功能：创建HSSFSheet工作簿
     * @param     wb    HSSFWorkbook
     * @param     sheetName    String
     * @return    HSSFSheet
     */
    public static HSSFSheet createSheet(HSSFWorkbook wb,String sheetName){
        HSSFSheet sheet=wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth(12);   //设置默认行高
        sheet.setGridsPrinted(false);      //是否显示边框
        sheet.setDisplayGridlines(false);   //是否在查看器中显示网格线
        return sheet;
    }




    /**
     * 功能：创建HSSFRow
     * @param     sheet    HSSFSheet
     * @param     rowNum    int
     * @param     height    int
     * @return    HSSFRow
     */
    public static HSSFRow createRow(HSSFSheet sheet,int rowNum,int height){
        HSSFRow row=sheet.createRow(rowNum);
        row.setHeight((short)height);
        return row;
    }



    public static HSSFCell createCell0(HSSFRow row,int cellNum){
        HSSFCell cell=row.createCell(cellNum);
        return cell;
    }


    /**
     * 功能：创建CELL
     * @param     row        HSSFRow
     * @param     cellNum    int
     * @param     style    HSSFStyle
     * @return    HSSFCell
     */
    public static HSSFCell createCell(HSSFRow row,int cellNum,CellStyle style){
        HSSFCell cell=row.createCell(cellNum);
        cell.setCellStyle(style);
        return cell;
    }



    /**
     * 功能：创建CellStyle样式
     * @param     wb                HSSFWorkbook
     * @param     backgroundColor    背景色
     * @param     foregroundColor    前置色
     * @param    font            字体
     * @return    CellStyle
     */
    public static CellStyle createCellStyle(HSSFWorkbook wb,short backgroundColor,short foregroundColor,short halign,Font font){
        CellStyle cs=wb.createCellStyle();
        cs.setAlignment(halign);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cs.setFillBackgroundColor(backgroundColor);
        cs.setFillForegroundColor(foregroundColor);
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cs.setFont(font);
        return cs;
    }


    /**
     * 功能：创建带边框的CellStyle样式
     * @param     wb                HSSFWorkbook
     * @param     backgroundColor    背景色
     * @param     foregroundColor    前置色
     * @param    font            字体
     * @return    CellStyle
     */
    public static CellStyle createBorderCellStyle(HSSFWorkbook wb,short backgroundColor,short foregroundColor,short halign,Font font){
        CellStyle cs=wb.createCellStyle();
        cs.setAlignment(halign);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cs.setFillBackgroundColor(backgroundColor);
        cs.setFillForegroundColor(foregroundColor);
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cs.setFont(font);
        cs.setBorderLeft(CellStyle.BORDER_DASHED);
        cs.setBorderRight(CellStyle.BORDER_DASHED);
        cs.setBorderTop(CellStyle.BORDER_DASHED);
        cs.setBorderBottom(CellStyle.BORDER_DASHED);
        return cs;
    }





    /**
     * 功能：多行多列导入到Excel并且设置标题栏格式
     */
    public static void writeArrayToExcel(HSSFSheet sheet, int rows, int cells, Object [][]value){

        Row row[]=new HSSFRow[rows];
        Cell cell[]=new HSSFCell[cells];


        for(int i=0;i<row.length;i++){
            row[i]=sheet.createRow(i);


            for(int j=0;j<cell.length;j++){
                cell[j]=row[i].createCell(j);
                cell[j].setCellValue(convertString(value[i][j]));

            }

        }
    }

    public static HSSFSheet arrayToExcel(HSSFSheet sheet, int rows, int cells, Object [][]value){

        Row row[]=new HSSFRow[rows];
        Cell cell[]=new HSSFCell[cells];

        for(int i=0;i<row.length;i++){
            row[i]=sheet.createRow(i);


            for(int j=0;j<cell.length;j++){
                cell[j]=row[i].createCell(j);
                cell[j].setCellValue(convertString(value[i][j]));

            }

        }
        return sheet;
    }

    /**
     * 功能：多行多列导入到Excel并且设置标题栏格式
     */
    public static void writeArrayToExcel(String[] title,HSSFWorkbook wb,HSSFSheet sheet,int rows,int cells,Object [][]value){

        Row row[]=new HSSFRow[rows];
        Cell cell[]=new HSSFCell[cells];
        HSSFCellStyle ztStyle =  (HSSFCellStyle)wb.createCellStyle();

        Font ztFont = wb.createFont();
        ztFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        //ztFont.setItalic(true);                     // 设置字体为斜体字
        // ztFont.setColor(Font.COLOR_RED);            // 将字体设置为“红色”
        ztFont.setFontHeightInPoints((short)10);    // 将字体大小设置为18px
        //ztFont.setFontName("华文行楷");             // 将“华文行楷”字体应用到当前单元格上
        // ztFont.setUnderline(Font.U_DOUBLE);
        ztStyle.setFont(ztFont);

        for(int i = 0; i < row.length; i ++){
            row[i] = sheet.createRow(i+1);
            HSSFRow row0 = sheet.createRow(0);
            for (int j = 0; j < title.length; j++) {    //标题
                HSSFCell c0 = row0.createCell(j);
                c0.setCellValue(title[j]);
                c0.setCellStyle(ztStyle);
            }

            for(int j=0;j<cell.length;j++){
                cell[j]=row[i].createCell(j);
                cell[j].setCellValue(convertString(value[i][j]));
            }

        }
    }




    /**
     * 功能：合并单元格
     * @param     sheet        HSSFSheet
     * @param     firstRow    int
     * @param     lastRow        int
     * @param     firstColumn    int
     * @param     lastColumn    int
     * @return    int            合并区域号码
     */
    public static int mergeCell(HSSFSheet sheet,int firstRow,int lastRow,int firstColumn,int lastColumn){
        return sheet.addMergedRegion(new CellRangeAddress(firstRow,lastRow,firstColumn,lastColumn));
    }



    /**
     * 功能：创建字体
     * @param     wb            HSSFWorkbook
     * @param     boldweight    short
     * @param     color        short
     * @return    Font
     */
    public static Font createFont(HSSFWorkbook wb,short boldweight,short color,short size){
        Font font=wb.createFont();
        font.setBoldweight(boldweight);
        font.setColor(color);
        font.setFontHeightInPoints(size);
        return font;
    }


    /**
     * 设置合并单元格的边框样式
     * @param    sheet    HSSFSheet
     * @param     ca        CellRangAddress
     * @param     style    CellStyle
     */
    public static void setRegionStyle(HSSFSheet sheet, CellRangeAddress ca,CellStyle style) {
        for (int i = ca.getFirstRow(); i <= ca.getLastRow(); i++) {
            HSSFRow row = HSSFCellUtil.getRow(i, sheet);
            for (int j = ca.getFirstColumn(); j <= ca.getLastColumn(); j++) {
                HSSFCell cell = HSSFCellUtil.getCell(row, j);
                cell.setCellStyle(style);
            }
        }
    }

    /**
     * 功能：将HSSFWorkbook写入Excel文件
     * @param     wb        HSSFWorkbook
     * @param     fileName    文件名
     */
    public static void writeWorkbook(HSSFWorkbook wb,String fileName){
        FileOutputStream fos=null;
        File f=new File(fileName);
        try {
            fos=new FileOutputStream(f);
            wb.write(fos);
        } catch (Exception e) {

        }
         finally{
            try {
                if(fos!=null){
                    fos.close();
                }
            } catch (IOException e) {
            }
        }
    }



    public static String convertString(Object value) {
        if (value == null) {
            return "";
        } else {
            return value.toString();
        }
    }

    /**
     *将数据从输入流写到输出流
     * @param os 输出流
     * @param is 输入流
     * @throws IOException
     */
    public static void writeExceltoOutpurStream(InputStream is, ServletOutputStream os) throws IOException{
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(os);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }
}