package sample;

import pdf.CoreLogic;
import pdf.ListEntry;
import pdf.PdfPage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CoreLogicUsageExample {

    public static void main(String[] args) {

        CoreLogic coreLogic = new CoreLogic();

        List<Path> pdfPaths = coreLogic.getPdfFilesList(Paths.get("./slides"));

        System.out.println(pdfPaths);

        Path firstFile = pdfPaths.get(0);

        List<PdfPage> pages = coreLogic.findPagesContaining(firstFile, "eksam");

        System.out.println(pages);

        List<ListEntry> entries = coreLogic.search(Paths.get("./slides"), "eksam");

        System.out.println(entries);
    }

}
