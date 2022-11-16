package pdf;

import java.nio.file.Path;

public record PdfPage(Path file, Integer pageNr) {

    @Override
    public String toString() {
        return "(%s, %s)".formatted(file, pageNr);
    }
}
