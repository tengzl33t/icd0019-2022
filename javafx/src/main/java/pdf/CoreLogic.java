package pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoreLogic {

    public List<PdfPage> findPagesContaining(Path path, String keyWord) {
        try (PDDocument document = PDDocument.load(path.toFile())) {
            return findPagesContaining(document, keyWord)
                    .stream()
                    .map(pageNr -> new PdfPage(path, pageNr))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Integer> findPagesContaining(PDDocument document, String keyWord) throws IOException {

        PDFTextStripper stripper = new PDFTextStripper();

        List<Integer> pageNumbers = new ArrayList<>();

        for (int p = 1; p <= document.getNumberOfPages(); p++) {

            stripper.setStartPage(p);
            stripper.setEndPage(p);

            String text = stripper.getText(document);

            if (text.toLowerCase().contains(keyWord)) {
                pageNumbers.add(p);
            }

        }

        return pageNumbers;
    }

    public List<Path> getPdfFilesList(Path path) {
        try (Stream<Path> stream = Files.list(path)) {
            return stream
                    .filter(file -> file.toString().endsWith(".pdf"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ListEntry> search(Path pdfDir, String searchString) {
        return getPdfFilesList(pdfDir).stream()
                .map(pdfPath -> findPagesContaining(pdfPath, searchString))
                .flatMap(Collection::stream)
                .map(this::createEntry)
                .toList();
    }

    private ListEntry createEntry(PdfPage page) {
        String url = String.format("file://%s#page=%s",
                page.file().toAbsolutePath(), page.pageNr());
        String title = String.format("%s (page %s)",
                page.file().getFileName(), page.pageNr());

        return new ListEntry(title, url);
    }


}
