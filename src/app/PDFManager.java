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

    public int splitPdfDocument(File originalFilePath, File outputFilePath, String monthYear) throws IOException {
        int pageNum = 0;

        PDDocument document = PDDocument.load(originalFilePath);

        // Se crea el directorio si no existe
            if (!outputFilePath.exists()) {
                outputFilePath.mkdirs();
            }
            
        if (document.getNumberOfPages() > 1) {
            // Divide el PDF en paginas individuales
            Splitter splitter = new Splitter();
            List<PDDocument> listPages = splitter.split(document);

            // Se instancia el Iterador para recorrer las Paginas separadas
            Iterator<PDDocument> iterator = listPages.listIterator();

            while (iterator.hasNext()) {
                pageNum++;

                PDDocument pd = iterator.next();
                String nameWorker = getWorkerNameFromPDDocument(pd);

                if (nameWorker.equals("")) {
                    nameWorker = "Sin Nombre - pagina_" + pageNum;
                }

                pd.save(outputFilePath.getAbsolutePath() + "\\" + nameWorker + monthYear + ".pdf");
            }
        } else if (document.getNumberOfPages() == 1) {
            // Se ejecuta si el PDF solo tiene una pagina
            pageNum++;

            // Instancia del nuevo documento a partir del original
            PDDocument pd = document;
            
            // Se lee el documento para recuperar el nombre
            String nameWorker = getWorkerNameFromPDDocument(pd);

            // En caso de no poder recuperar el Nombre del trabajador
            // Se asigna el nombre generico 'Sin Nombre' y el numero de la pagina
            if (nameWorker.equals("")) {
                nameWorker = "Sin Nombre - pagina_" + pageNum;
            }

            // Genera el Path absoluto del nuevo archivo PDF y guarda el archivo
            String newPath = outputFilePath.getAbsolutePath() + "\\" + nameWorker + monthYear + ".pdf";
            pd.save(newPath);
        }

        document.close();

        return pageNum;
    }

    // Devuelve el Nombre del Trabajador al que pertenece la Nomina
    public String getWorkerNameFromPDDocument(PDDocument doc) throws IOException {
        // Lee el PDF y lo guarda en un String 'textPdf'
        PDFTextStripper pdfStripper = new PDFTextStripper();
        pdfStripper.setStartPage(0);
        pdfStripper.setEndPage(doc.getNumberOfPages());
        String textPdf = pdfStripper.getText(doc);

        // Recupera el Nombre del trabajador desde el String generado anteriormente
        String name = "";
        String[] lines = textPdf.split("\n");

        for (String line : lines) {
            if (line.contains("Trabajador")) {
                String[] arrString = line.split("Trabajador: ");
                name = arrString[1];
                name = name.replace("\r", "");
                break;
            }
        }

        return name;
    }

    // Busca la linea que contiene la cadena 'Trabajador' 
    // y extrae el nombre del trabajador que viene seguido
    public String getWorkerNameFromFile(File filePath) throws IOException {
        String textPdf = pdfToStringFromFile(filePath);
        String name = "";
        String[] lines = textPdf.split("\n");

        for (String line : lines) {
            if (line.contains("Trabajador")) {
                String[] arrString = line.split("Trabajador: ");
                name = arrString[1];
                name = name.replace("\r", "");
            }
        }

        return name;
    }

    // Lee el PDF a partir de un Archivo y devuelve un String
    public String pdfToStringFromFile(File file) throws IOException {
        String text = "";

        /* Constructor: RandomAccessFile(File file, String mode) */
        PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
        parser.parse();

        COSDocument cosDoc = parser.getDocument();
        PDFTextStripper pdfStripper = new PDFTextStripper();
        PDDocument pdDoc = new PDDocument(cosDoc);
        pdfStripper.setStartPage(0);
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());

        text = pdfStripper.getText(pdDoc);

        return text;
    }

}
