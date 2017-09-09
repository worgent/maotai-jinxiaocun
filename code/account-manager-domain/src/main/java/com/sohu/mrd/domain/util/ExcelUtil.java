package com.sohu.mrd.domain.util;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * Describe:excel 工具类
 * User: chenghaixing
 * Date: 2013-10-20
 * Time: 上午9:15:35
 */
public class ExcelUtil {
    private Log logger = LogFactory.getLog(ExcelUtil.class);
     private Map<String, String> excelTemplateMap;
    private static final ExcelUtil instance = new ExcelUtil();

    private ExcelUtil() {
    }

    public static ExcelUtil getInstance() {
        return instance;
    }


    /**
     * 公用的导出xcel的方法
     *
     * @param response
     * @param exportName：生成下载的文件名
     * @param templateName：位于指定的路径下的模板文件名
     * @param dataMap：需要导出的数据
     * @throws Exception
     */

    public void exportExcel(HttpServletRequest request, HttpServletResponse response, String exportName, String templateName, Map<String, Object> dataMap) throws Exception {
        response.reset();  //清空输出流
        response.setCharacterEncoding ("UTF-8");//设置编码
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment;filename=\"" + new String(exportName.getBytes("UTF-8"),"iso-8859-1") + "\"");

        String templateFullPath = request.getSession().getServletContext().getRealPath("/") + excelTemplateMap.get("TEMPLATE_PATH") + templateName;
        XLSTransformer transformer = new XLSTransformer();
        //InputStream is = new BufferedInputStream(new FileInputStream("c:\\template.xls"));
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new BufferedInputStream(new FileInputStream(templateFullPath));
            HSSFWorkbook workbook = transformer.transformXLS(is, dataMap);
            os = response.getOutputStream();
            workbook.write(os);
            os.flush();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("ExcelUtil -> exportExcel() -> is.close() error!", e);
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error("ExcelUtil -> exportExcel() -> os.close() error!", e);
                }
            }
        }

    }

    public void setExcelTemplateMap(Map<String, String> excelTemplateMap) {
        this.excelTemplateMap = excelTemplateMap;
    }
}