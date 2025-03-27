package com.bor.rcms.test;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.html2pdf.HtmlConverter;

public class FormD {
    public static void main(String[] args) {
        FormD d = new FormD();
        String res = d.noticedgenerate("John Doe", "15th March 2025", "10:00 AM", "20th March 2025");
    }

    private String noticedgenerate(String userName, String submissionDate, String hearingTime, String hearingDate) {
        // HTML content as a string with placeholders for dynamic data
        String htmlContent = "<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                + "<title>FORM D - Willingness to Convert Leasehold Property</title>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; text-align: justify; }"
                + ".center { text-align: center; }"
                + ".large-text { font-size: 20px; }"
                + ".bold { font-weight: bold; }"
                + ".underline { text-decoration: underline; }"
                + ".signature { margin-top: 50px; font-weight: bold; text-align: right; }"
                + ".editable { border-bottom: 1px dashed #000; display: inline-block; min-width: 100px; }"
                + "</style>"
                + "</head>"
                + "<body>"

                + "<p class=\"center bold\">FORM D</p>"
                + "<p class=\"center\"><small>(Please See Rule 9)</small></p>"

                + "<p>To,<br>"
                + "The Collector,<br>"
                + "<span class=\"editable\"></span></p>"

                + "<p><strong>Subject:</strong> Willingness to convert leasehold property into freehold property.</p>"

                + "<p>Sir/Madam,</p>"

                + "<p>I am the rightful occupant of the property mentioned below and I am willing to deposit the freehold amount in such installments as may be fixed by you.</p>"

                + "<ul>"
                + "<li><strong>My Name:</strong> <span class=\"Name\">" + userName + "</span></li>"
                + "<li><strong>Correspondence Address:</strong> <span class=\"Address\"></span></li>"
                + "<li><strong>Age:</strong> (Enclose the copy of Aadhaar Card in support) <span class=\"Age\"></span></li>"
                + "<li><strong>Phone Number:</strong> <span class=\"Number\"></span></li>"
                + "<li><strong>WhatsApp Number:</strong> <span class=\"Number\"></span></li>"
                + "<li><strong>Email:</strong> <span class=\"Email\"></span></li>"
                + "<li><strong>Detailed address of the property to be converted into freehold:</strong> <span class=\"Email\"></span></li>"
                + "</ul>"

                + "<p>I am enclosing all the documents as required under Rule 7 of the Vesting Bettiah Raj Properties Rules, 2025.</p>"

                + "<p>Kindly inform me the freehold amount so that I can deposit the same. I am also enclosing the affidavit to this effect.</p>"

                + "<p class=\"signature\">Yours Sincerely,<br><br>"
                + "<span class=\"bold\">(Name and Signature of the occupant)</span></p>"

                + "<p><strong>Enclose:</strong> Affidavit by the occupant.</p>"

                + "<p>Date: <span class=\"editable\">" + submissionDate + "</span></p>"
                + "<p>Place: <span class=\"editable\"></span></p>"

                + "</body>"
                + "</html>";

        // Convert HTML to PDF and save to a file
        try {
            // Specify the output file path
            String pdfFilePath = "D:\\FormDNotice_" + userName.replaceAll(" ", "_") + ".pdf";

            // Convert HTML content to PDF and save it
            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(pdfFilePath));

            System.out.println("PDF generated and saved successfully at: " + pdfFilePath);
        } catch (IOException e) {
            System.out.println("Error generating PDF: " + e.getMessage());
        }

        return htmlContent;
    }
}
