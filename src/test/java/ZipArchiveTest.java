import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ZipArchiveTest {

    private ClassLoader cl = ZipArchiveTest.class.getClassLoader();

    @Test
    @DisplayName("Проверка наличия нужного pdf файла в архиве")
    void pdfFileParsingTest() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("All Doc.zip"))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("Doc 1.pdf")) {
                    PDF pdf = new PDF(zis);

                    Assertions.assertEquals(1, pdf.numberOfPages);

                    return;
                }
            }
            Assertions.fail("Файл не найден");
        }
    }

    @Test
    @DisplayName("Проверка наличия нужного xlsx файла в архиве")
    void xlsxFileParsingTest() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("All Doc.zip"))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("Doc 2.xlsx")) {
                    XLS xls = new XLS(zis);

                    Assertions.assertEquals("Год", xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue());
                    Assertions.assertEquals("Название", xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue());
                    Assertions.assertEquals("Руководитель разработки, компания", xls.excel.getSheetAt(0).getRow(0).getCell(2).getStringCellValue());
                    Assertions.assertEquals("Предшественник(и)", xls.excel.getSheetAt(0).getRow(0).getCell(3).getStringCellValue());

                    return;
                }
            }
            Assertions.fail("Файл не найден");
        }
    }

    @Test
    @DisplayName("Проверка наличия нужного csv файла в архиве")
    void csvFileParsingTest() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("All Doc.zip"))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("Doc 3.csv")) {
                    CSVReader csv = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csv.readAll();

                    Assertions.assertEquals(5, data.size());
                    Assertions.assertTrue(Arrays.equals(data.get(0), new String[]{"Year", "Name", "Team leads", "Predecessors"}));
                    return;
                }
            }

            Assertions.fail("Файл не найден");
        }
    }
}