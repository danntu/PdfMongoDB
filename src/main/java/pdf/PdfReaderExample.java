package pdf;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import java.io.IOException;

public class PdfReaderExample {
    private static final String FILE_NAME = "/home/mdaniyar/Шоломицкая.pdf";

    public static void main(String[] args) {
        PdfReader reader;
        try{
            Mongo mongo = new Mongo("localhost",27017);
            DB db = mongo.getDB("test");
            DBCollection dbCollection = db.getCollection("testCollection");
            BasicDBObject document = new BasicDBObject();

            reader = new PdfReader(FILE_NAME);
            String textFromPage = PdfTextExtractor.getTextFromPage(reader,1);
            document.put("",textFromPage);
            dbCollection.insert(document);
            //System.out.println(textFromPage);
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
