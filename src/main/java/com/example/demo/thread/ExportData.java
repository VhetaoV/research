package com.example.demo.thread;



import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: hetao
 * @create: 2019-12-30 13:25
 **/
public class ExportData {

    private static final String url = "jdbc:mysql://10.37.47.28:3306/ami_lora?characterEncoding=utf-8";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String username = "lora_reader";
    private static final String password = "JYhLe[Lb";



    //标题 样式
    private static HSSFCellStyle getHeadStyle(HSSFWorkbook wb) {
        HSSFCellStyle headstyle = wb.createCellStyle();
        HSSFFont headFont = (HSSFFont) wb.createFont();
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
        headFont.setFontName("黑体");
        headFont.setFontHeightInPoints((short) 20);
        headstyle.setFont(headFont);
        headstyle.setWrapText(true); // 设置为自动换行
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直对齐居中
        headstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        headstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        headstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        headstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        return headstyle;
    }

    //内容样式
    private static HSSFCellStyle getcellStyle(HSSFWorkbook wb) {
        HSSFCellStyle cell_Style = (HSSFCellStyle) wb.createCellStyle();// 设置字体样式
        HSSFFont cell_Font = (HSSFFont) wb.createFont();
        cell_Font.setFontHeightInPoints((short) 10);
        cell_Font.setFontName("宋体");
        cell_Style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cell_Style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直对齐居中
        cell_Style.setWrapText(true); // 设置为自动换行
        cell_Style.setFont(cell_Font);
        cell_Style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        cell_Style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        cell_Style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        cell_Style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        return cell_Style;
    }

    public static void main(String[] args){
        //线上库
        DataSourceFactory dataSourceFactory = new DataSourceFactory(name,url,username,password);

        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try { // gmeter.company_id in (61,142) AND
            String sql = "SELECT company.cid,company.company_name FROM " +
                    "(SELECT *, SUM(billing_gas) AS userUsed FROM ami_billing_temporary GROUP BY meter_code HAVING SUM(billing_gas) >= 5) res " +
                    "INNER JOIN ami_gprs_meter gprs ON res.meter_code = gprs.meter_code " +
                    "INNER JOIN ami_company company ON company.cid = gprs.company_id " +
                    "GROUP BY res.company_id HAVING SUM(res.userUsed) >= 200 ";
            connection = dataSourceFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<String> companyList = new ArrayList<>();
            Map<String,String> map = new HashMap<>();
            while (resultSet.next()){
                String companyId = resultSet.getString("cid");
                String companyName = resultSet.getString("company_name");
                companyList.add(companyId);
                map.put(companyId,companyName);
            }
            for(String companyId2 : map.keySet()){
                StringBuilder sql2 = new StringBuilder();
                sql2.append( "SELECT billing.meter_code,SUM(billing.billing_gas) AS userUsed,gprs.valve_status,customer.customer_balance " +
                        "FROM ami_billing_temporary billing INNER JOIN ami_gprs_meter gprs ON billing.meter_code = gprs.meter_code " +
                        "INNER JOIN ami_customer customer ON customer.cid = gprs.customer_id " +
                        "WHERE gprs.company_id = "+companyId2+" GROUP BY meter_code HAVING SUM(billing_gas) >= 5");
                preparedStatement = connection.prepareStatement(sql2.toString());
                resultSet = preparedStatement.executeQuery();
                List<Object> recordnotreadlist = new ArrayList<>();
                while (resultSet.next()){
                    Map<String,String> map2 = new HashMap<>();
                    String meter_code = resultSet.getString("meter_code");
                    String userUsed = resultSet.getString("userUsed");
                    String valve_status = resultSet.getString("valve_status");
                    String customer_balance = resultSet.getString("customer_balance");
                    map2.put("meter_code",meter_code);
                    map2.put("userUsed",userUsed);
                    map2.put("valve_status",valve_status);
                    map2.put("customer_balance",customer_balance);
                    recordnotreadlist.add(map2);
                }
                recordnotreadExport(recordnotreadlist,map.get(companyId2));
            }

        }catch (Exception e){
           e.printStackTrace();
        }finally {
            if(dataSourceFactory!=null){
                dataSourceFactory.closeCon(resultSet,preparedStatement,connection);
            }
        }

    }

    //建档未抄表报表// 未通信报表
    public static void recordnotreadExport(List<Object> recordnotreadlist, String titleName) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(titleName);
//        wb.setSheetName(0, titleName);

        HSSFCellStyle headStyle = getHeadStyle(wb);
        HSSFCellStyle cellStyle = getcellStyle(wb);

        HSSFRow row = sheet.createRow((int) 0);//标题
        row.setHeight((short) 360);
//        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 12));
//        HSSFCell headCell = row.createCell(0);
//        headCell.setCellValue(titleName);
//        headCell.setCellStyle(headStyle);

        row.setHeight((short) 600);
        sheet.setColumnWidth(0, (26 * 256));
        sheet.setColumnWidth(1, (18 * 256));
        sheet.setColumnWidth(2, (18 * 256));
        sheet.setColumnWidth(3, (18 * 256));


        HSSFCell cell = row.createCell(0); //创建表头
        cell.setCellValue("表号");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(1);
        cell.setCellValue("补扣气量");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(2);
        cell.setCellValue("阀门状态");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(3);
        cell.setCellValue("账户余额");
        cell.setCellStyle(cellStyle);


        for (int i = 0; i < recordnotreadlist.size(); i++) {
            row = sheet.createRow((int) i + 1);
            sheet.setColumnWidth(0, (26 * 256));
            sheet.setColumnWidth(1, (18 * 256));
            sheet.setColumnWidth(2, (18 * 256));
            sheet.setColumnWidth(3, (18 * 256));

            Map map = (Map) recordnotreadlist.get(i);


            HSSFCell csCell1 = row.createCell(0);
            csCell1.setCellValue(map.get("meter_code").toString());
            csCell1.setCellStyle(cellStyle);

            HSSFCell csCell2 = row.createCell(1);
            csCell2.setCellValue(map.get("userUsed").toString());
            csCell2.setCellStyle(cellStyle);

            HSSFCell csCell3 = row.createCell(2);
            csCell3.setCellValue(returnDes(map.get("valve_status").toString()));
            csCell3.setCellStyle(cellStyle);

            HSSFCell csCell4 = row.createCell(3);
            csCell4.setCellValue(map.get("customer_balance").toString());
            csCell4.setCellStyle(cellStyle);
        }
        try{

            FileOutputStream fout = new FileOutputStream("D:\\exportexcel/" +titleName+".xls");
            wb.write(fout);
            fout.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //阀门状态； 01开阀  02警告性关阀  03强制性关阀
    public static String returnDes(String statusCode) {
        String valve_status = "";
          if("01".equals(statusCode)){
              valve_status = "开阀";
          }else if ("02".equals(statusCode)){
              valve_status = "警告性关阀";
          }else if ("03".equals(statusCode)){
              valve_status = "强制性关阀";
          }
        return valve_status;
    }

}
