package com.bor.rcms.test;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.html2pdf.HtmlConverter;

public class Form3 {

    public static void main(String[] args) {
        Form3 generator = new Form3();
        String html = generator.generateForm3(
            "Bhojraj Singh",     // Certificate debtor name
            "land tax dues",     // Reason for demand
            "7",                 // Section of the Act
            "25",                // Day
            "April",             // Month
            "25",                // Year (last 2 digits)
            "Patna"              // Officer designation/location
        );
    }

    public String generateForm3(
        String debtorName,
        String reason,
        String section,
        String day,
        String month,
        String year,
        String officerLocation
    ) {
        String htmlContent = "<!DOCTYPE html>"
            + "<html lang='en'>"
            + "<head>"
            + "<meta charset='UTF-8'>"
            + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
            + "<title>FORM No. 3</title>"
            + "<style>"
            + "body { font-family: Arial, sans-serif; margin: 20px; }"
            + ".center { text-align: center; font-weight: bold; }"
            + ".italic { font-style: italic; }"
            + ".container { max-width: 800px; margin: 0 auto; padding: 20px; }"
            + "p { text-align: justify; line-height: 1.6; }"
            + "table { width: 100%; margin-top: 40px; }"
            + "td { vertical-align: top; }"
            + "</style>"
            + "</head>"
            + "<body>"

            + "<div class='container'>"
            + "<div class='center'><h2>FORM No. 3</h2><h3>Nature of Certificate-debtor</h3><p class='italic'>(See Section 7)</p></div>"

            + "<p>To</p>"
            + "<p style='margin-left: 40px;' class='italic'>" + debtorName + "</p>"

            + "<p>You are hereby informed that a certificate against you for Rs............................ due from you on account of " + reason + ", has this day been filed in my office, under section " + section + " of the Bihar and Orissa Public Demands Recovery Act, 1914. If you deny your liability to pay the said sum of Rs............................................... you may, within thirty days from the service of this notice, file in my office a petition denying liability, in whole or in part. If, within the said thirty days, you fail to file such a petition, or if you fail to show cause, or do not show sufficient cause, why such certificate should not be executed, it will be executed, under the provisions of the said Act, unless you pay Rs................................ (Rs......................... on account of the demand and Rs.................................... on account of costs of realization) into my office. Until the said amount is so paid you are hereby prohibited from alienating your immovable property, or any part of it, by sale, gift, mortgage or otherwise. If you in the meantime conceal, remove or dispose of any part of your movable property, the certificate will be executed immediately.</p>"

            + "<p>A copy of the certificate above-mentioned is hereto annexed.</p>"
            + "<p>You may remit the amount by money order, quoting the number and year of the certificate.</p>"

            + "<p>Dated this " + day + " Day of " + month + " 19" + year + ".</p>"

            + "<table>"
            + "<tr>"
            + "<td></td>"
            + "<td style='text-align: right;'>"
            + "<p>A.B.</p>"
            + "<p class='italic'>(Certificate Officer of " + officerLocation + ")</p>"
            + "</td>"
            + "</tr>"
            + "</table>"

            + "</div>"
            + "</body></html>";

        try {
            String fileName = "D:\\Form3_" + debtorName.replaceAll(" ", "_") + ".pdf";
            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(fileName));
            System.out.println("✅ PDF successfully saved to: " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error generating PDF: " + e.getMessage());
        }

        return htmlContent;
    }
}
