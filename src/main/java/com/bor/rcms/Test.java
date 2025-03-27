package com.bor.rcms;
import org.apache.pdfbox.multipdf.PDFMergerUtility; 
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        // Create a PDFMergerUtility object
        PDFMergerUtility mergerUtility = new PDFMergerUtility();

        try {
            // Add the source PDF files to be merged
            mergerUtility.addSource(new File("‪C:\\Users\\Admin\\Pictures\\Screenshot (3).png"));  // Corrected path for first PDF
            mergerUtility.addSource(new File("C:/Users/Admin/Downloads/Vimlesh Kumar Yadav (P8300).pdf"));  // Corrected path for second PDF

            // Specify the output PDF file
            mergerUtility.setDestinationFileName("D:/MergedFile.pdf");

            // Merge the PDFs
            mergerUtility.mergeDocuments(null); // null here means no additional merge options
            System.out.println("PDFs merged successfully!");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error merging PDFs: " + e.getMessage());
        }
    }

}
