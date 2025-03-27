package com.bor.rcms.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import com.bor.rcms.entity.DocumentEntity;
import com.bor.rcms.entity.NewObjection;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.HtmlConverter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FormB {
    public static void main(String[] args) {
        FormB b = new FormB();
        String res = b.noticedgenerate("John Doe", "15th March 2025", "10:00 AM", "20th March 2025");
    }

    private String noticedgenerate(String userName, String submissionDate, String hearingTime, String hearingDate) {
        // HTML content as a string with placeholders for dynamic data
        String htmlContent = "<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                + "<title>FORM B Notice</title>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; text-align: justify; }"
                + ".center { text-align: center; }"
                + ".bold { font-weight: bold; }"
                + ".large-text { font-size: 30px; }"
                + ".underline { text-decoration: underline; }"
                + ".signature { margin-top: 50px; font-weight: bold; text-align: right; }"
                + ".editable { border-bottom: 1px dashed #000; display: inline-block; min-width: 100px; }"
                + "</style>"
                + "</head>"
                + "<body>"

                + "<p class=\"center bold\">FORM B</p>"
                + "<p class=\"center\"><small>(Please See Rule 4)</small></p>"

                + "<p class=\"center large-text\">Office of the Special Officer,<br> District <span class=\"editable\"></span></p>"

                + "<p class=\"center\">Miscellaneous Case No. <span class=\"editable\"></span></p>"

                + "<p>To,<br>"
                + "The Collector,<br>"
                + "<span class=\"editable\"></span></p>"

                + "<p>Sir/Madam,</p>"

                + "<p>I Shri/Smt <span class=\"editable\">" + userName + "</span>, in my capacity as the Special Officer, "
                + "have rejected the objection petition filed by the occupant under Rule 3 of The Vesting of Bettiah Raj Properties Rules, 2025 "
                + "in case of the following person:</p>"

                + "<ol>"
                + "<li>Name of the occupant: <span class=\"occupant\"></span></li>"
                + "<li>Correspondence address of the occupant: <span class=\"occupant\"></span></li>"
                + "<li>Phone Number and Email of the occupant: <span class=\"occupant\"></span></li>"
                + "<li>Details of the property of the occupant whose possession is to be taken: <span class=\"occupant\"></span></li>"
                + "</ol>"

                + "<p>In terms of Rule 4 (7), you are requested to direct the concerned officer to take possession of the aforementioned property "
                + "as per the prescribed procedure.</p>"

                + "<p>I am also enclosing the copy of the detailed Order vide which the objection of the occupant has been rejected.</p>"

                + "<p class=\"signature\">Yours Sincerely,<br><br>"
                + "<span class=\"bold\">(Special Officer)</span></p>"

                + "<p>Date: <span class=\"editable\">" + submissionDate + "</span></p>"
                + "<p>Place: <span class=\"editable\"></span></p>"

                + "</body>"
                + "</html>";

        // Convert HTML to PDF and save to a file
        try {
            // Specify the output file path
            String pdfFilePath = "D:\\FormBNotice_" + userName.replaceAll(" ", "_") + ".pdf";

            // Convert HTML content to PDF and save it
            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(pdfFilePath));

            System.out.println("PDF generated and saved successfully at: " + pdfFilePath);
        } catch (IOException e) {
            System.out.println("Error generating PDF: " + e.getMessage());
        }

        return htmlContent;
    }
}

