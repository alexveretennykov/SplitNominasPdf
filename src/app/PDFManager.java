package app;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFManager {

    public PDFManager() {

    }

    public int splitPdfDocument(File originalFilePath, File outputFilePath, String monthYear, String searchPatternBefore, String searchPatternAfter) throws IOException {
        int pageNum = 0;

        PDDocument document = PDDocument.load(originalFilePath);

        // Crea el directorio si no existe
        if (!outputFilePath.exists()) {
            outputFilePath.mkdirs();
        }

        if (document.getNumberOfPages() > 1) {
            // Divide el PDF en paginas individuales
            Splitter splitter = new Splitter();
            List<PDDocument> listPages = splitter.split(document);

            // Instancia el Iterador para recorrer las Paginas separadas
            Iterator<PDDocument> iterator = listPages.listIterator();
                    
            while (iterator.hasNext()) {
                pageNum++;
                PDDocument pd = null;

                try {
                    pd = iterator.next();
                    String nameWorker = getWorkerNameFromPDDocument(pd, searchPatternBefore, searchPatternAfter);

                    if (nameWorker.equals("")) {
                        nameWorker = "Sin Nombre - pagina_" + pageNum;
                    }

                    pd.save(outputFilePath.getAbsolutePath() + "/" + nameWorker + monthYear + ".pdf");
                } finally {
                    if (pd != null) {
                        pd.close();
                    }
                }
            }
        } else if (document.getNumberOfPages() == 1) {
            // Se ejecuta si el PDF solo tiene una pagina
            pageNum++;

            // Instancia del nuevo documento a partir del original
            PDDocument pd = document;

            // Se lee el documento para recuperar el nombre
            String nameWorker = getWorkerNameFromPDDocument(pd, searchPatternBefore, searchPatternAfter);

            // En caso de no poder recuperar el Nombre del trabajador
            // Se asigna el nombre generico 'Sin Nombre' y el numero de la pagina
            if (nameWorker.equals("")) {
                nameWorker = "Sin Nombre - pagina_" + pageNum;
            }

            // Genera el Path absoluto del nuevo archivo PDF y guarda el archivo
            String newPath = outputFilePath.getAbsolutePath() + "/" + nameWorker + monthYear + ".pdf";
            pd.save(newPath);
        }

        document.close();

        return pageNum;
    }

    // Devuelve el Nombre del Trabajador al que pertenece la Nomina
    public String getWorkerNameFromPDDocument(PDDocument doc, String searchPatternBefore, String searchPatternAfter) throws IOException {
        // Lee el PDF y lo guarda en un String 'textPdf'
        PDFTextStripper pdfStripper = new PDFTextStripper();
        pdfStripper.setStartPage(0);
        pdfStripper.setEndPage(doc.getNumberOfPages());
        // Fuerza la lectura del PDF en orden de izquierda a derecha y de arriba abajo
        pdfStripper.setSortByPosition(true);
        String textPdf = pdfStripper.getText(doc);

        // Recupera el Nombre del trabajador desde el String generado anteriormente
        String name = "";
        String[] lines = textPdf.split("\n");

        for (String line : lines) {
            if (line.contains(searchPatternBefore.trim())) {
                // Separa El nombre de la cadena que tiene al principio
                String[] arrString = line.trim().split(searchPatternBefore.trim());
                if (arrString.length > 1) {
                    name = arrString[1];
                    // Elimina caracteres especiales
                    name = removeSpecialCharacters(name);
                }

                // En caso de que haya un patron que indique una cadena que hay que quitar de la derecha
                // Separa el Nombre de la cadena que tiene al final
                if (!searchPatternAfter.trim().equals("") && name.contains(searchPatternAfter.trim())) {
                    String[] arrString2 = name.trim().split(searchPatternAfter.trim());
                    name = arrString2[0];
                }

                break;
            }
        }

        return removeSpecialCharacters(name);
    }

    // Elimina caracteres especiales
    private String removeSpecialCharacters(String text) {
        String editText = "";

        editText = text.replace("\r", "")
                .replace("\t", "")
                .replace("\n", "")
                .replace("\b", "")
                .replace("\f", "")
                .replace("/", "")
                .replace("\\", "")
                .replace(":", "doblepunto")
                .replace("?", "")
                .replace("\"", "")
                .replace("<", "")
                .replace(">", "")
                .replace("|", "");

        return editText;
    }

    // Busca la linea que contiene la cadena 'Trabajador' 
    // y extrae el nombre del trabajador que viene seguido
    public String getWorkerNameFromFile(File filePath, String searchPatternBefore, String searchPatternAfter) throws IOException {
        String textPdf = pdfToStringFromFile(filePath, 0);
        String name = "";
        String[] lines = textPdf.split("\n");

        for (String line : lines) {
            if (line.contains(searchPatternBefore.trim())) {
                // Separa El nombre de la cadena que tiene al principio
                String[] arrString = line.trim().split(searchPatternBefore.trim());
                if (arrString.length > 1) {
                    name = arrString[1];
                    // Elimina caracteres especiales
                    name = removeSpecialCharacters(name);
                }

                // En caso de que haya un patron que indique una cadena que hay que quitar de la derecha
                // Separa el Nombre de la cadena que tiene al final
                if (!searchPatternAfter.trim().equals("") && name.contains(searchPatternAfter.trim())) {
                    String[] arrString2 = name.trim().split(searchPatternAfter.trim());
                    name = arrString2[0];
                }

                break;
            }
        }

        return name;
    }

    // Lee el PDF a partir de un Archivo y devuelve un String
    public String pdfToStringFromFile(File file, int endPage) throws IOException {
        String text = "";

        /* Constructor: RandomAccessFile(File file, String mode) */
        PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
        parser.parse();

        COSDocument cosDoc = parser.getDocument();
        PDFTextStripper pdfStripper = new PDFTextStripper();
        // Fuerza la lectura del PDF en orden de izquierda a derecha y de arriba abajo
        pdfStripper.setSortByPosition(true);
        PDDocument pdDoc = new PDDocument(cosDoc);
        pdfStripper.setStartPage(0);

        if (endPage == 0) {
            pdfStripper.setEndPage(pdDoc.getNumberOfPages());
        } else {
            pdfStripper.setEndPage(endPage);
        }

        text = pdfStripper.getText(pdDoc);

        pdDoc.close();

        return text;
    }

}
