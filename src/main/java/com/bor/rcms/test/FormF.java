/*
 * package com.bor.rcms.test;
 * 
 * import java.io.FileOutputStream; import java.io.IOException;
 * 
 * import com.itextpdf.html2pdf.HtmlConverter;
 * 
 * public class FormF { public static void main(String[] args) { FormF f = new
 * FormF(); String res = f.noticedgenerate("John Doe", "15th March 2025",
 * "10:00 AM", "20th March 2025"); }
 * 
 * private String noticedgenerate(String userName, String submissionDate, String
 * hearingTime, String hearingDate) { // HTML content as a string with
 * placeholders for dynamic data String htmlContent = "<!DOCTYPE html>" +
 * "<html lang=\"en\">" + "<head>" + "<meta charset=\"UTF-8\">" +
 * "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
 * + "<title>Form E & Form F</title>" + "<style>" +
 * "body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; text-align: justify; }"
 * + ".center { text-align: center; }" + ".large-text { font-size: 20px; }" +
 * ".bold { font-weight: bold; }" + ".underline { text-decoration: underline; }"
 * + ".signature { margin-top: 50px; font-weight: bold; text-align: right; }" +
 * ".editable { border-bottom: 1px dashed #000; display: inline-block; min-width: 100px; }"
 * + "</style>" + "</head>" + "<body>"
 * 
 * + "<!-- FORM E -->" + "<p class=\"center bold\">FORM E</p>" +
 * "<p class=\"center\"><small>(Please See Rule 9 (4))</small></p>" +
 * "<p class=\"center large-text\">Office of the Collector, District <span class=\"editable\"></span></p>"
 * 
 * +
 * "<p class=\"center\"><strong>Miscellaneous Case No.</strong> <span class=\"editable\"></span></p>"
 * + "<p class=\"center bold underline\">Notice</p>"
 * 
 * + "<p>To,<br>" + "(Name of the occupant)<br>" +
 * "<span class=\"editable\"></span><br>" +
 * "<span class=\"editable\"></span></p>"
 * 
 * + "<p><strong>Subject:</strong> Freehold Amount</p>"
 * 
 * + "<p>Sir/Madam,</p>"
 * 
 * +
 * "<p>Please refer to your application seeking freehold conversion of your leasehold property into freehold property.</p>"
 * 
 * +
 * "<p>As per the District Valuation Committee, the market valuation of your property has been valued at <span class=\"editable\"></span>.</p>"
 * 
 * +
 * "<p>Therefore, in terms of Rule 10 of the Vesting of Bettiah Raj Properties Rules, 2025, you are requested to deposit an amount of <span class=\"editable\"></span> as freehold amount. The amount should be paid either in full or in <span class=\"editable\"></span> installments.</p>"
 * 
 * +
 * "<p>The installment requested is to be submitted within <span class=\"editable\"></span> days.</p>"
 * 
 * +
 * "<p>You will receive the updated details of the freehold amount within 30 days. Your application will be finalized and processed with you as per the policy under Bihar Public Land Encroachment Act, 1956.</p>"
 * 
 * +
 * "<p>Once you have submitted the full payment (75% of the freehold amount), you shall be required to sign a conveyance deed and deposit the registration fee/stamp duty on the same.</p>"
 * 
 * + "<p class=\"signature\">Yours Sincerely,<br><br>" +
 * "<span class=\"bold\">(Collector)</span></p>"
 * 
 * + "<p>Date: <span class=\"editable\">" + submissionDate + "</span></p>" +
 * "<p>Place: <span class=\"editable\"></span></p>"
 * 
 * + "</body>" + "</html>";
 * 
 * // Convert HTML to PDF and save to a file try { // Specify the output file
 * path String pdfFilePath = "D:\\FormENotice_" + userName.replaceAll(" ", "_")
 * + ".pdf";
 * 
 * // Convert HTML content to PDF and save it
 * HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(pdfFilePath));
 * 
 * System.out.println("PDF generated and saved successfully at: " +
 * pdfFilePath); } catch (IOException e) {
 * System.out.println("Error generating PDF: " + e.getMessage()); }
 * 
 * return htmlContent; } }
 */