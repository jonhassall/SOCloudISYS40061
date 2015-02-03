
import itemOrder.Book;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author taha-m
 */
public class ReadOrder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        itemOrder.Book quickXML = new itemOrder.Book();

        //Open file / unmarshall XML into object
        try {
            Path currentRelativePath = Paths.get("");
            String filename = currentRelativePath.toAbsolutePath().toString() + File.separator + "temp" + File.separator + "output.xml";
            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(quickXML.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            quickXML = (Book) unmarshaller.unmarshal(new java.io.File(filename)); //NOI18N
            System.out.println("ISBN: " + quickXML.getISBN());
            System.out.println("Author: " + quickXML.getAuthor());
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }

    }

    /**
     *
     * @param filename String
     * @return boolean
     */
    public static boolean readFile(String filename) {
        File file = new File(filename);
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);

            System.out.println("Total file size to read (in bytes) : " + file.length());

            int content;
            while ((content = fis.read()) != -1) {
                // convert to char and display it
                System.out.print((char) content);
            }

        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                //ex.printStackTrace();
            }
        }

        return true;
    }

    /**
     *
     * @param filename String
     * @param data
     * @return boolean
     */
    public static boolean writeFileBytes(String filename, byte[] data) {
        File file = new File(filename);
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file, false);
            fos.write(data);
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                //ex.printStackTrace();
                System.out.println("Error writing file");
            }
        }

        return true;
    }

}
