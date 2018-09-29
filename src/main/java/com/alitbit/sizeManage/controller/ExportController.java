package com.alitbit.sizeManage.controller;

import com.alitbit.sizeManage.bean.CustomerInfo;
import com.alitbit.sizeManage.bean.excel.Size;
import com.alitbit.sizeManage.service.CustomerInfoService;
import com.alitbit.sizeManage.util.PoiUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private CustomerInfoService customerInfoService;

//    @GetMapping("/size")
//    public void size(HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        List<CustomerInfo> customerInfos = customerInfoService.findAll();
//        List<Size> sizes = new ArrayList<>();
//
//        for (CustomerInfo customerInfo : customerInfos){
//            Size size = new Size();
//            BeanUtils.copyProperties(customerInfo, size);
//            sizes.add(size);
//        }
//
//        String filename = "客户尺码表.xls";
//
//        PoiUtil.exportExcel(sizes, null,"sheet1", Size.class, filename, response);
//    }

    @GetMapping("/size")
    public ResponseEntity<byte[]> size() throws IOException {
        List<CustomerInfo> customerInfos = customerInfoService.findAllProcess();
        List<Size> sizes = new ArrayList<>();

        // create a new workbook
        HSSFWorkbook wb = new HSSFWorkbook();
        // create a sheet
        HSSFSheet sheet = wb.createSheet();
        // Row Cell CellStyle
        Row row = null;
        Cell cell = null;
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cellStyle2 = wb.createCellStyle();
        Font font = null;

        int rowNum = 0;
        int colNum = 0;

        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle2.setAlignment(HorizontalAlignment.CENTER);
        font =  wb.createFont();
        font.setColor(HSSFColor.RED.index);
        cellStyle2.setFont(font);

        // 固定第一行
        sheet.createFreezePane(0, 1, 0, 1);

        sheet.setColumnWidth(1, 30*256);
        sheet.setColumnWidth(2, 25*256);
        sheet.setColumnWidth(3, 16*256);
        sheet.setColumnWidth(6, 15*256);
        sheet.setColumnWidth(7, 15*256);
        sheet.setColumnWidth(34, 15*256);
        sheet.setColumnWidth(35, 20*256);

        row = sheet.createRow(rowNum++);

        createCell(row, colNum++, cellStyle).setCellValue("编号");
        createCell(row, colNum++, cellStyle).setCellValue("码数");
        createCell(row, colNum++, cellStyle).setCellValue("淘宝名");
        createCell(row, colNum++, cellStyle).setCellValue("手机号");
        createCell(row, colNum++, cellStyle).setCellValue("平时码数");
        createCell(row, colNum++, cellStyle).setCellValue("身高");
        createCell(row, colNum++, cellStyle).setCellValue("颈侧点-膝盖");
        createCell(row, colNum++, cellStyle).setCellValue("1/2肩宽+袖长");
        createCell(row, colNum++, cellStyle).setCellValue("胸围");
        createCell(row, colNum++, cellStyle).setCellValue("腰围");
        createCell(row, colNum++, cellStyle).setCellValue("臀围");
        createCell(row, colNum++, cellStyle).setCellValue("袖肥");
        createCell(row, colNum++, cellStyle).setCellValue("颈围");
        createCell(row, colNum++, cellStyle).setCellValue("肩宽");
        createCell(row, colNum++, cellStyle).setCellValue("胸距");
        createCell(row, colNum++, cellStyle).setCellValue("上胸围");
        createCell(row, colNum++, cellStyle).setCellValue("前胸宽");
        createCell(row, colNum++, cellStyle).setCellValue("后背宽");
        createCell(row, colNum++, cellStyle).setCellValue("背长");
        createCell(row, colNum++, cellStyle).setCellValue("臂长");
        createCell(row, colNum++, cellStyle).setCellValue("上臂长");
        createCell(row, colNum++, cellStyle).setCellValue("袖长");
        createCell(row, colNum++, cellStyle).setCellValue("臂围");
        createCell(row, colNum++, cellStyle).setCellValue("袖笼围");
        createCell(row, colNum++, cellStyle).setCellValue("肘围");
        createCell(row, colNum++, cellStyle).setCellValue("手腕围");
        createCell(row, colNum++, cellStyle).setCellValue("通档");
        createCell(row, colNum++, cellStyle).setCellValue("大腿根围");
        createCell(row, colNum++, cellStyle).setCellValue("膝围");
        createCell(row, colNum++, cellStyle).setCellValue("小腿围");
        createCell(row, colNum++, cellStyle).setCellValue("脚踝围");
        createCell(row, colNum++, cellStyle).setCellValue("胸高");
        createCell(row, colNum++, cellStyle).setCellValue("腰高");
        createCell(row, colNum++, cellStyle).setCellValue("后中长");
        createCell(row, colNum++, cellStyle).setCellValue("中腰-膝盖距离");
        createCell(row, colNum++, cellStyle).setCellValue("第七颈椎点-大腿跟围");

        for (CustomerInfo customerInfo : customerInfos){
            colNum = 0;
            row = sheet.createRow(rowNum++);
            createCell(row, colNum++, cellStyle).setCellValue(rowNum-1);

            // 尺码异常抛红
            cell = createCell(row, colNum++, cellStyle);
            cell.setCellValue(customerInfo.getBigSize());
            try {
                Integer.parseInt(customerInfo.getBigSize());
            }catch (NumberFormatException e){
                cell.setCellStyle(cellStyle2);
            }

            createCell(row, colNum++, cellStyle).setCellValue(customerInfo.getTbName());
            createCell(row, colNum++, cellStyle).setCellValue(customerInfo.getPhone());
            createCell(row, colNum++, cellStyle).setCellValue(customerInfo.getSmallSize());
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getHeight()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getNeckKnee()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getShoulderSleeve()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getChest()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getWaist()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getAss()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getSleeveFat()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getNeck()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getShoulder()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getChestDistance()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getChestUp()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getChestFront()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getBackWidth()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getBackLength()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getArmLength()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getArmUpLength()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getSleeveLength()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getArmWidth()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getSleeveWidth()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getElbowWidth()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getWristWidth()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getPants()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getBigLagWidth()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getKneeWidth()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getSmallLagWidth()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getAnkleWidth()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getChestHeight()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getWristHeight()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getBackMidLength()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getWristKnee()));
            createCell(row, colNum++, cellStyle).setCellValue(getValue(customerInfo.getSeventhLag()));
        }

        HttpHeaders headers = new HttpHeaders();
        String filename = "客户尺码表";
        headers.setContentDispositionFormData("attachment", new String(filename.getBytes(), "iso-8859-1")+".xls");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        return new ResponseEntity<byte[]>(outByteStream.toByteArray(), headers, HttpStatus.OK);
    }
    
    private String getValue(BigDecimal value){
        if (value == null){
            return "";
        }else {
            return value.toPlainString();
        }
    }
    
    private Cell createCell(Row row, int colNum, CellStyle cellStyle){
        Cell cell = row.createCell(colNum);
        cell.setCellStyle(cellStyle);
        return cell;
    }
}
