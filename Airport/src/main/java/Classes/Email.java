package Classes;

import Entities.EFlight;
import Entities.EstatusFlight;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.awt.image.ColorConvertOp;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Email {

    public void sendReportbyId(int idFlight, String mail, List<EFlight> flightList) {
        
        flightList.removeIf(p -> p.getIdFlight() != idFlight);
        createFile(flightList);
        SendMail(mail);
    }

    public void sendReportbyDate(String dateFlight, String mail, List<EFlight> flightList) throws ParseException {       
        flightList.removeIf(p -> !p.getDepartureTime().substring(0,10).equals(dateFlight));
        createFile(flightList);
        SendMail(mail);
        
        //09-09-2021 05:00 = 09-09-2021
    }

    public void createFile(List<EFlight> flightList) {

        try {
             String basePathTemplate = new File("").getAbsolutePath();
                basePathTemplate = basePathTemplate + "\\Flight_Report_Template.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(basePathTemplate));


            //XSSFSheet sheet = workbook.createSheet("Report");// creating a blank sheet
            XSSFSheet sheet = workbook.getSheet("Report");
            int rownum = 4;
            for (EFlight flight : flightList) {
                Row row = sheet.createRow(rownum++);
                createList(flight, row);

            }

            FileOutputStream out = new FileOutputStream(new File("Flight_Report.xlsx")); // file name with path
            workbook.write(out);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createList(EFlight flight, Row row) // creating cells for each row
    {
        int IC = 1; //TO IDENFITY WHAT COLUMN THE PRINT WILL START
        
        Cell cell = row.createCell(IC+0);
        cell.setCellValue(flight.getIdFlight());

        cell = row.createCell(IC+1);
        cell.setCellValue(flight.getAirline());

        cell = row.createCell(IC+2);
        cell.setCellValue(flight.getAirplane());

        cell = row.createCell(IC+3);
        cell.setCellValue(flight.getOrigin());

        cell = row.createCell(IC+4);
        cell.setCellValue(flight.getDestiny());

        cell = row.createCell(IC+5);
        cell.setCellValue(flight.getDepartureTime());

        cell = row.createCell(IC+6);
        cell.setCellValue(flight.getArrivalTime());
       
            cell = row.createCell(IC+7);
            cell.setCellValue(flight.getStatus());

            cell = row.createCell(IC+8);
            cell.setCellValue(flight.getBinnacle());
        

    }

    public void SendMail(String mail) {

        // Recipient's email ID needs to be mentioned.
        String to = mail;

        // Sender's email ID needs to be mentioned
        String from = "airtraffic.report@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass 
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("airtraffic.report@gmail.com", "AirTraffic1");

            }

        });
        //session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Report");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {

                String basePath = new File("").getAbsolutePath();
                basePath = basePath + "\\Flight_Report.xlsx";

                File f = new File(basePath);

                attachmentPart.attachFile(f);
                textPart.setText("Este es el documento");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {

                e.printStackTrace();

            }

            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
