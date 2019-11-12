package by.pvt.view;


import by.pvt.pojo.dto.DataChartDto;
import by.pvt.pojo.dto.SensorValueDto;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class PDFDocument extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(
            Map<String, Object> model,
            Document document,
            PdfWriter writer,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        DataChartDto dataChartDto = (DataChartDto) model.get("modelObject");

        Paragraph deviceName = new Paragraph("Device: " + dataChartDto.getDevice().getName(), FontFactory.getFont(
                FontFactory.HELVETICA, 18, Font.BOLDITALIC, new CMYKColor(0,
                        255, 255, 17)));


        StringBuilder sensors = new StringBuilder("Sensors: ");
        for (String str : dataChartDto.getMetaData()) {
            sensors.append(str).append("  ");
        }



        Paragraph sensorsName = new Paragraph(
                String.valueOf(sensors),
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                        new CMYKColor(0, 255, 255, 17)));

        Paragraph date = new Paragraph(
                "Date: from " + dataChartDto.getFrom() + " to " + dataChartDto.getTo(),
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                        new CMYKColor(0, 255, 255, 17)));



        PdfPTable table = new PdfPTable(dataChartDto.getMetaData().size() + 1);
        table.setSpacingBefore(25);
        table.setSpacingAfter(25);

        PdfPCell header1 = new PdfPCell(new Phrase("Date"));
        header1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(header1);
        dataChartDto.getMetaData().forEach(name -> {
            PdfPCell header = new PdfPCell(new Phrase(name));
            header.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(header);
        });

        for (SensorValueDto sensorValueDto : dataChartDto.getSensorValueDtoList()) {
            table.addCell(sensorValueDto.getDate().getTime().toString());
            sensorValueDto.getValues()
                    .forEach(integer -> table.addCell(String.valueOf(integer)));
        }


        document.addTitle("pdf");
        document.add(deviceName);
        document.add(sensorsName);
        document.add(date);
        document.add(table);
    }
}
