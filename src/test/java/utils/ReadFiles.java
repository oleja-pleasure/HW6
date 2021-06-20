package utils;

import com.codeborne.pdftest.PDF;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReadFiles {
    public static String readFile(File file) throws IOException {
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    public static String readFileFromPath(String path) throws IOException {
        File file = new File(path);
        return readFile(file);
    }

    public static PDF readPdfFromPath(String path) throws IOException {
        File file = new File(path);
        return new PDF(file);
    }

    public static String readXlsFromPath(String path) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(path));
        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        HSSFRow row = myExcelSheet.getRow(0);
        String name = row.getCell(0).getStringCellValue();
        myExcelBook.close();
        return name;
    }

    public static String readXlsxFromPath(String path) throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(path));
        XSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        XSSFRow row = myExcelSheet.getRow(0);
        String name = row.getCell(0).getStringCellValue();
        myExcelBook.close();
        return name;
    }

    public static void unpack(String sourceZipFilePath, String extractedZipFilePath, char[] password) throws ZipException {
        ZipFile zipFile = new ZipFile(sourceZipFilePath);
        if (zipFile.isEncrypted()) {
            zipFile.setPassword(password);
        }
        zipFile.extractAll(extractedZipFilePath);
    }

    public static String readDocFromPath(String path) throws IOException {
        File fin = new File(path);
        FileInputStream fis = new FileInputStream(fin);
        HWPFDocument doc = new HWPFDocument(fis);
        return doc.getDocumentText();
    }

public static String readDocxFromPath(String path) throws IOException {
    File fin = new File(path);
    FileInputStream fis = new FileInputStream(fin);
    XWPFDocument docx = new XWPFDocument(fis);
    XWPFWordExtractor wx = new XWPFWordExtractor(docx);
    String text = wx.getText();
    fis.close();
    return text;
    }
}
