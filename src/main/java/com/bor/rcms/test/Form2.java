package com.bor.rcms.test;

import com.itextpdf.html2pdf.HtmlConverter;
import java.io.FileOutputStream;
import java.io.IOException;

public class Form2 {

    public static void main(String[] args) {
        Form2 generator = new Form2();
        String html = generator.generateForm2(
            "Patna",           // District
            "Ram Kumar",       // Debtor name
            "Gandhi Nagar, Patna", // Address
            "12,000",          // Amount
            "Land Revenue",    // Nature of demand
            "10",              // Day
            "April",           // Month
            "25",              // Year (last 2 digits)
            "Tehsildar"        // Officer Designation
        );
    }

    public String generateForm2(
        String district,
        String debtorName,
        String address,
        String amount,
        String nature,
        String day,
        String month,
        String year,
        String designation
    ) {
        String htmlContent = "<!DOCTYPE html>"
            + "<html lang='en'>"
            + "<head>"
            + "<meta charset='UTF-8'>"
            + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
            + "<title>FORM No. 2</title>"
            + "<style>"
            + "body { font-family: Arial, sans-serif; margin: 20px; }"
            + ".center { text-align: center; font-weight: bold; }"
            + "table { width: 100%; border-collapse: collapse; border: 1px solid #000; }"
            + "th, td { border: 1px solid #000; padding: 10px; text-align: left; }"
            + ".section { margin-bottom: 20px; }"
            + "</style>"
            + "</head>"
            + "<body>"

            + "<div style='max-width: 800px; margin: 0 auto;'>"
            + "<div class='center'><h2>FORM No. 2</h2><h3>Requisition for a certificate</h3><p style='font-style: italic;'>(See Section 5)</p></div>"

            + "<div class='section'><p>To</p>"
            + "<p style='margin-left: 40px;'>The Certificate Officer of the District of " + district + "</p></div>"

            + "<hr style='border: 2px solid #000;'>"

            + "<table>"
            + "<tr>"
            + "<th>Name of certificate debtor</th>"
            + "<th>Address of Certificate debtor</th>"
            + "<th>Amount of public demand</th>"
            + "<th>Nature of public demand</th>"
            + "</tr>"
            + "<tr>"
            + "<td>" + debtorName + "</td>"
            + "<td>" + address + "</td>"
            + "<td>Rs. " + amount + "</td>"
            + "<td>" + nature + "</td>"
            + "</tr>"
            + "</table>"

            + "<hr style='border: 2px solid #000; margin-top: 5px; margin-bottom: 30px;'>"

            + "<div class='section'>"
            + "<p>I request you to recover the above-mentioned sum of Rs. " + amount + ", which I am satisfied, after inquiry, is due from the said " + debtorName + " in respect of " + nature + ".</p>"
            + "</div>"

            + "<div class='section' style='margin-left: 40px;'>"
            + "<p>Verified by me on the " + day + " day of " + month + " 19" + year + ".</p>"
            + "</div>"

            + "<div style='text-align: right;'>"
            + "<p>A.B.</p>"
            + "<p style='font-style: italic;'>(" + designation + ")</p>"
            + "</div>"

            + "</div>"
            + "</body></html>";

        try {
            String fileName = "D:\\Form2_" + debtorName.replaceAll(" ", "_") + ".pdf";
            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(fileName));
            System.out.println("✅ PDF successfully saved to: " + fileName);
        } catch (IOException e) {
            System.err.println("❌ PDF generation failed: " + e.getMessage());
        }

        return htmlContent;
    }
}
