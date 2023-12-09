package utils;

import bean.Student;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetDataFromExcel {
	public static List<Student> getAllByExcel(String path) throws Exception{
		File filePath;
		System.out.println(path);
		try {
			filePath = new File(path);
			//Workbook wb1 = Workbook.getWorkbook(filePath);
		} catch (Exception e) {
			filePath = new File(path);
		}
		List<Student> list = new ArrayList<Student>();
		System.out.print(filePath);

			Workbook wb = Workbook.getWorkbook(filePath);
			//得到文件中第一个工作表格，若要得到全部用sheet[] sheets = wb.getSheets()
			Sheet sheet = wb.getSheet(0);
			//得到第一个表中的总行数和列数
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			System.out.println(rows+"======"+cols);
			//循环取出表中的所有数据,第一行一般是标题，所有循环从1开始而不是0
			for(int i=1;i<rows;i++){
				for(int j=0;j<cols;j++){
					String stuId = sheet.getCell(j++,i).getContents();
					String stuName = sheet.getCell(j++,i).getContents();
					String stuClass = sheet.getCell(j++,i).getContents();
					list.add(new Student(stuId,stuName,stuClass,"","",""));
				}
			}


		return list;
	}

	public static boolean isExist(String stu_id,String examname){
		String sql = "select * from student where stu_id = '"+stu_id+"' and stu_exam='"+examname+"'";
		ResultSet rs = DBUtil.executeQuery(sql);
		try {
			if(rs.next())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
}
