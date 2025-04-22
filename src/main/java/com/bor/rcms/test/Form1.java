package com.bor.rcms.test;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.html2pdf.HtmlConverter;

public class Form1 {

    public static void main(String[] args) {
        Form1 generator = new Form1();
        String html = generator.generateForm1("Patna", "Certificate Holder Name and Address", "Debtor Name and Address",
            "Rs. 12,000 for the year 2024", "Illegal possession of land", "25", "April", "25", "Certificate Officer of Patna");
    }

    public String generateForm1(
        String district,
        String certificateHolder,
        String certificateDebtor,
        String demandDetails,
        String furtherDetails,
        String day,
        String month,
        String year,
        String officerDesignation
    ) {
        String htmlContent = "<!DOCTYPE html>"
            + "<html lang='en'>"
            + "<head>"
            + "<meta charset='UTF-8'>"
            + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
            + "<title>FORM No. 1</title>"
            + "<style>"
            + "body { font-family: Arial, sans-serif; margin: 20px; }"
            + ".center { text-align: center; }"
            + ".bold { font-weight: bold; }"
            + "table { width: 100%; border-collapse: collapse; border: 1px solid #000; margin-top: 20px; }"
            + "th, td { border: 1px solid #000; padding: 10px; vertical-align: top; }"
            + ".signature { margin-top: 50px; font-weight: bold; display: flex; justify-content: space-between; }"
            + "</style>"
            + "</head>"
            + "<body>"

            + "<div style='max-width: 800px; margin: 0 auto;'>"
            + "<div class='center'>"
            + "<h2 class='bold'>FORM No. 1</h2>"
            + "<h3 class='bold'>Certificate of Public Demands</h3>"
            + "<p>(See Section 4 and 6)</p>"
            + "<p style='font-style: italic;'>Filed in the Office of the Certificate Officer of " + district + ".</p>"
            + "</div>"

            + "<hr style='border-top: 2px solid #000;'>"

            + "<table>"
            + "<tr>"
            + "<th>Number of Certificate</th>"
            + "<th>Name and address of certificate holder</th>"
            + "<th>Name and address of certificate debtor</th>"
            + "<th>Amount of public demand</th>"
            + "<th>Further particulars of the demand</th>"
            + "</tr>"
            + "<tr>"
            + "<td></td>"
            + "<td>" + certificateHolder + "</td>"
            + "<td>" + certificateDebtor + "</td>"
            + "<td>" + demandDetails + "</td>"
            + "<td>" + furtherDetails + "</td>"
            + "</tr>"
            + "</table>"

            + "<hr style='border-top: 2px solid #000;'>"

            + "<p>I hereby certify that the above-mentioned sum of Rs ....................................... is due from the above-named person.</p>"
            + "<p><i>[If the certificate is signed or represented under section 5, add-]</i></p>"
            + "<p>I further certify that the above-mentioned sum is justly recoverable, and that its recovery by suit is not barred by law.</p>"
            + "<p>Dated this " + day + " day of " + month + " 19" + year + ".</p>"

            + "<div class='signature'>"
            + "<div><p>!Certificate Holder</p></div>"
            + "<div style='text-align: right;'>"
            + "<p>A.B.</p>"
            + "<p>" + officerDesignation + "</p>"
            + "</div>"
            + "</div>"

            + "</div>"
            + "</body></html>";

        try {
            String fileName = "D:\\Form1_" + district.replaceAll(" ", "_") + ".pdf";
            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(fileName));
            System.out.println("✅ PDF successfully saved to: " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error generating PDF: " + e.getMessage());
        }

        return htmlContent;
    }
}
