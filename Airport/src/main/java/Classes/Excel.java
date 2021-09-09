package Classes;

import Entities.EAirplane;
import Entities.EFlight;
import Entities.EstatusFlight;
import com.airport.Principal;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Excel {
    public static void downloadExcelFormat(ArrayList<EAirplane> airplanesArrayList ){
    
        try {

            //ubicacion y nombre de archivo excel a descargar
            String filename = "FormatFlights.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FormatFlights");

            //encabezado en excel
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("AirPlane");
            rowhead.createCell(1).setCellValue("AirLine");
            rowhead.createCell(2).setCellValue("Origin");
            rowhead.createCell(3).setCellValue("Destinity");
            rowhead.createCell(4).setCellValue("Status");

            //Lista los Ids de los vuelos creados
            CellRangeAddressList listAirplane = new CellRangeAddressList(
                    1, 20, 0, 0);
            String ids[] = new String[airplanesArrayList.size()];
            for (int i = 0; i < airplanesArrayList.size(); i++) {
                ids[i] = Integer.toString(airplanesArrayList.get(i).getIdAirplane());
            }

            DVConstraint airplaneList = DVConstraint.createExplicitListConstraint(
                    ids);
            DataValidation dataValidation1 = new HSSFDataValidation(listAirplane, airplaneList);
            dataValidation1.setSuppressDropDownArrow(false);
            sheet.addValidationData(dataValidation1);

            //----- fin ----
            //Lista las aerolineas Disponibles
            CellRangeAddressList listAirline = new CellRangeAddressList(
                    1, 20, 1, 1);

            DVConstraint airlineList = DVConstraint.createExplicitListConstraint(
                    new String[]{"Avianca", "Delta Airlines", "United Airlines", "American Airlines"});
            DataValidation dataValidation2 = new HSSFDataValidation(listAirline, airlineList);
            dataValidation2.setSuppressDropDownArrow(false);
            sheet.addValidationData(dataValidation2);
            //---fin ----

            //Lista las aerolineas Disponibles
            CellRangeAddressList listStatus = new CellRangeAddressList(
                    1, 20, 4, 4);

            DVConstraint statusList = DVConstraint.createExplicitListConstraint(
                    new String[]{"ACTIVATED", "DELAYED", "LANDED", "CANCEL"});
            DataValidation dataValidation3 = new HSSFDataValidation(listStatus, statusList);
            dataValidation3.setSuppressDropDownArrow(false);
            sheet.addValidationData(dataValidation3);
            //---fin ----

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            JOptionPane.showMessageDialog(null, "Excel downloaded successfully!");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public static void importFromExcelDocument(ArrayList<EFlight> flightsArrayList ){
     File excelFile;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        HSSFWorkbook wb = null;
        Sheet sheet = null;

        String defaultCurrentDirectoryPath = "C:\\Users\\Public\\Desktop";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        int excelChooser = excelFileChooser.showOpenDialog(null);

        if (excelChooser == JFileChooser.APPROVE_OPTION) {

            try {
                excelFile = excelFileChooser.getSelectedFile();
                fis = new FileInputStream(excelFile);
                bis = new BufferedInputStream(fis);

                wb = new HSSFWorkbook(bis);
                sheet = wb.getSheetAt(0);

                //  int idsFlights = flightsArrayList.size();
                for (int row = 1; row <= sheet.getLastRowNum(); row++) {
                    Row rowCol = sheet.getRow(row);

                    Cell AirPlane = rowCol.getCell(0);
                    Cell AirLine = rowCol.getCell(1);
                    Cell Origin = rowCol.getCell(2);
                    Cell Destinity = rowCol.getCell(3);
                    Cell Status = rowCol.getCell(4);

                    EFlight oFlight = new EFlight();
                    oFlight.setIdFlight(flightsArrayList.size());
                    oFlight.setAirplane((int) AirPlane.getNumericCellValue());
                    oFlight.setAirline(AirLine.toString());
                    oFlight.setOrigin(Origin.toString());
                    oFlight.setDestiny(Destinity.toString());
                    oFlight.setStatus((EstatusFlight.valueOf(Status.toString()).name()));
                    flightsArrayList.add(oFlight);
                    
                }

            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
