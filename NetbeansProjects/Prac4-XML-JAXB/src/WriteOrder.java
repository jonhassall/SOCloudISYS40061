import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taha-m
 */
public class WriteOrder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        itemOrder.Book quickXML = new itemOrder.Book();
        quickXML.setAuthor("Jonathan Hassall");
        quickXML.setTitle("How to Throw Snowballs");
        quickXML.setDescription("A brief guide to throwing snowballs");
        quickXML.setISBN(123456789);
        quickXML.setPublicationYear(2015);
        quickXML.setPrice(1.99f);
        
        //Open file
        Path currentRelativePath = Paths.get("");
        String filename = currentRelativePath.toAbsolutePath().toString() + File.separator + "temp" + File.separator + "output.xml";
        System.out.println(filename);
        File file = new File(filename);
        OutputStream fos = null;

        try {
                fos = new FileOutputStream(file, false);
        } catch (IOException e) {
                //e.printStackTrace();
        } finally {
        }
        
        try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(quickXML.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            marshaller.marshal(quickXML, System.out);
            marshaller.marshal(quickXML, fos);
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
        
        try {
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(WriteOrder.class.getName()).log(Level.SEVERE, null, ex);
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
                        if (fis != null)
                                fis.close();
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
                        if (fos != null)
                                fos.close();
                } catch (IOException ex) {
                        //ex.printStackTrace();
                    System.out.println("Error writing file");
                }
        }

        return true;
    }

}
