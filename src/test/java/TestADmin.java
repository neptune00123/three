import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.CmfzZzhApplication;
import com.baizhi.dao.*;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Testaa;
import com.baizhi.vo.Header;
import com.baizhi.vo.People;
import io.goeasy.GoEasy;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = CmfzZzhApplication.class)
@RunWith(SpringRunner.class)
public class TestADmin {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private TestaaDao testDao;
    @Autowired
    private EchartsDao echartsDao;
    @Autowired
    private VoDao voDao;


    @Test
    public void test1() throws IOException {
        //创建表格
        HSSFWorkbook workbook = new HSSFWorkbook();
        //时间转换
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        //创建工作区域
        HSSFSheet sheet = workbook.createSheet("测试");
        //设置第几行的宽度
        sheet.setColumnWidth(2,20*256);
        //创建行
        HSSFRow row = sheet.createRow(0);
        //标签行
        String[] names={"ID","NAME","BIRTHDAY"};
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(name);
            cell.setCellStyle(cellStyle);
        }
        //数据
        List<Testaa> query = testDao.query();
        for (int i = 0; i < query.size(); i++) {
            HSSFRow row1 = sheet.createRow(i+1);
            row1.createCell(0).setCellValue(query.get(i).getId());
            row1.createCell(1).setCellValue(query.get(i).getName());
            HSSFCell cell = row1.createCell(2);
            cell.setCellValue(query.get(i).getBirthday());
            cell.setCellStyle(cellStyle);
        }
        workbook.write(new File("D://111.xls"));
        workbook.close();
    }
    @Test
    public void test2(){
        List<Banner> queryall = bannerDao.queryall(0, 10);
        for (Banner banner : queryall) {
            String img1 = banner.getImg();
            String img = img1.split("_")[1];
            banner.setImg("F:\\three\\cmfz_zzh\\src\\main\\webapp\\img1\\"+img);
            System.out.println(queryall);
        }
        Workbook sheets = ExcelExportUtil.exportExcel(new ExportParams("首页", "轮播图"),Banner.class,queryall);
        try {
            sheets.write(new FileOutputStream("D:/easypoi.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3(){
        List<People> querypeople = echartsDao.querypeople();
        for (People queryperson : querypeople) {
            System.out.println(queryperson);
        }
    }

    @Test
    public void getTestDao() {
        Integer querysun = echartsDao.querymon();
        System.out.println(querysun);
    }
    @Test
    public void testgo(){
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-b121a256bfeb4d0e874c71b5a744d092");
        goEasy.publish("180_Channel","你好啊,小老弟!");
    }
    @Test
    public void test(){

    }
}
