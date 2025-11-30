package opencart.utility;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ExcelUtil {

    public static final String TEST_DATA_SHEET_PATH =
            "./src/main/resources/OpenCartTestData.xlsx";

    public static Workbook book;
    public static Sheet sheet;

    public static Object[][] getTestData(String sheetName) {
        System.out.println("Reading Excel data from sheet: " + sheetName);

        Object[][] data = null;

        try {
            FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
            book = WorkbookFactory.create(ip);
            sheet = book.getSheet(sheetName);

            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i + 1);
                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                    data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(EncryptedDocumentException e){
            e.printStackTrace();
        }catch(IOException e){
                e.printStackTrace();
            }

              return data;
    }
}































//    public static Object[][] getTestData(String sheetName)
//    {
//        System.out.println("Reading the data from sheet" + sheetName);
//        Object data[][] = null;//it holds excelfile data,initially its null
//        try
//        {
//            FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);//creating object of excelfile
//
//            book = WorkbookFactory.create(ip);//asks workbook to create connection with file object which is ip
//            sheet = book.getSheet(sheetName);
//            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
//
//            for (int i = 0; i < sheet.getLastRowNum(); i++)
//            {
//                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++)
//                {
//                    data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
//
//                }
//            }
//
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//
//        }
//        return data;
//    }
//
//   }






