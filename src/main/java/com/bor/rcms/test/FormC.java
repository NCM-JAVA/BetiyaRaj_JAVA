package com.bor.rcms.test;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.html2pdf.HtmlConverter;

public class FormC {
    public static void main(String[] args) {
        FormC c = new FormC();
        String res = c.noticedgenerate("John Doe", "15th March 2025", "10:00 AM", "20th March 2025");
    }

    private String noticedgenerate(String userName, String submissionDate, String hearingTime, String hearingDate) {
        // HTML content as a string with placeholders for dynamic data
        String htmlContent = "<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                + "<title>FORM C Notice</title>"
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

                + "<p class=\"center bold\">FORM C</p>"
                + "<p class=\"center\"><small>(Please See Rule 5 (3))</small></p>"

                + "<p class=\"center large-text\">Office of the Collector, District <span class=\"editable\"></span></p>"

                + "<p class=\"center\">Miscellaneous Case No. <span class=\"editable\"></span></p>"

                + "<p class=\"center bold underline\">Notice</p>"

                + "<p>To,<br>"
                + "(Name of the occupant)<br>"
                + "<span class=\"editable\">" + userName + "</span><br>"
                + "<span class=\"editable\"></span></p>"

                + "<p><strong>Subject:</strong> Possession of the property <span class=\"editable\"></span> (mention details of the property)</p>"

                + "<p>Sir,</p>"

                + "<p>With respect to the aforementioned property, the undersigned is in receipt of the Order dated <span class=\"editable\"></span> "
                + "of the Special Officer rejecting your objections filed under Section 7 of The Vesting of Bettiah Raj Properties Act, 2024 "
                + "read with Rule 3 and Rule 4 of the Vesting of Bettiah Raj Properties Rules, 2025 and whereas he has requested the undersigned "
                + "to take possession of the aforementioned property;</p>"

                + "<p><strong>Or</strong></p>"

                + "<p>Whereas no objections have been filed with regard to the aforementioned property within 60 days of the publication of the "
                + "Notification under Section 3 of The Vesting of Bettiah Raj Properties Act, 2024 and as such the said property stands vested "
                + "with the State Government in terms of Section 3 of The Vesting of Bettiah Raj Properties Act, 2024 read with Rule 3 and Rule 5 "
                + "(1) of the Vesting of Bettiah Raj Properties Rules, 2025;</p>"

                + "<p>This notice is being issued to you to submit a reply within 15 days as to why the possession of the said property be not taken.</p>"

                + "<p>Your reply should reach the undersigned within the time frame, else the undersigned shall be free to proceed further ex-parte.</p>"

                + "<p class=\"signature\">Yours Sincerely,<br><br>"
                + "<span class=\"bold\">(Collector)</span></p>"

                + "<p>Date: <span class=\"editable\">" + submissionDate + "</span></p>"
                + "<p>Place: <span class=\"editable\"></span></p>"

                + "</body>"
                + "</html>";

        // Convert HTML to PDF and save to a file
        try {
            // Specify the output file path
            String pdfFilePath = "D:\\FormCNotice_" + userName.replaceAll(" ", "_") + ".pdf";

            // Convert HTML content to PDF and save it
            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(pdfFilePath));

            System.out.println("PDF generated and saved successfully at: " + pdfFilePath);
        } catch (IOException e) {
            System.out.println("Error generating PDF: " + e.getMessage());
        }

        return htmlContent;
    }
}
