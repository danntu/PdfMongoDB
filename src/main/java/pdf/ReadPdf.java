package pdf;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class ReadPdf {
    public static void main(String[] args) throws IOException {
        try(PDDocument pdfDocument = PDDocument.load(new File("/home/mdaniyar/Шоломицкая.pdf"))){
            pdfDocument.getClass();
            Mongo mongo = new Mongo("localhost",27017);
            DB db = mongo.getDB("test");
            DBCollection dbCollection = db.getCollection("testCollection2");
            BasicDBObject document = new BasicDBObject();

            if (!pdfDocument.isEncrypted()){
                PDFTextStripper stripper = new PDFTextStripper();
                String pdfFileInText = stripper.getText(pdfDocument);
                System.out.println(pdfFileInText);
                document.put("",pdfFileInText);
                dbCollection.insert(document);
                //Читает pdf файл построчно
                String lines[] = pdfFileInText.split("\\r?\\n");

//                for (String line : lines) {
//                    System.out.println(line);
//                }

            }
        }
    }
}
