package tests;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.codeborne.pdftest.PDF.containsText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static utils.ReadFiles.*;

public class CheckFiles {
    String txtFile = "Test.txt",
            docFile = "Test.doc",
            docxFile = "Test.docx",
            xlsFile = "Test.xls",
            xlsxFile = "Test.xlsx",
            pdfFile = "Test.pdf",
            zipFile = "Test.zip",
            path = "src/test/resources/files/";

    @Test
    void checkTxtFile() throws IOException {
        String expectedData = "Проверка содержимого txt файла";
        String txt = readFileFromPath(path + txtFile);
        assertThat(txt, containsString(expectedData));
    }

    @Test
    void checkPdfFile() throws IOException {
        String expectedData = "Проверка содержимого pdf файла";
        PDF pdf = readPdfFromPath(path + pdfFile);
        assertThat(pdf, containsText(expectedData));
    }

    @Test
    void checkXlsFile() throws IOException {
        String expectedData = "Проверка содержимого xls файла";
        String spreadsheet = readXlsFromPath(path + xlsFile);
        assertThat(spreadsheet, containsString(expectedData));
    }

    @Test
    void checkXlsxFile() throws IOException {
        String expectedData = "Проверка содержимого xlsx файла";
        String spreadsheet = readXlsxFromPath(path + xlsxFile);
        assertThat(spreadsheet, containsString(expectedData));
    }

    @Test
    void checkTxtFileFromZip() throws IOException {
        String expectedData = "Проверка содержимого txt файла";
        String sourceZipFilePath = path + zipFile;
        String extractedZipFilePath = "src/test/resources/files/output/";
        char[] password = {'1', 'a', '3', 'c'};
        unpack(sourceZipFilePath, extractedZipFilePath, password);
        String txt = readFileFromPath(extractedZipFilePath + txtFile);
        assertThat(txt, containsString(expectedData));
    }

    @Test
    void checkDocFile() throws IOException {
        String expectedData = "Проверка содержимого doc файла";
        String doc = readDocFromPath(path + docFile);
        assertThat(doc, containsString(expectedData));
    }

    @Test
    void checkDocxFile() throws IOException {
        String expectedData = "Проверка содержимого docx файла";
        String docx = readDocxFromPath(path + docxFile);
        assertThat(docx, containsString(expectedData));

    }
}
