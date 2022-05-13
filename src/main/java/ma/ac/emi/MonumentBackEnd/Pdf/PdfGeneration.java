package ma.ac.emi.MonumentBackEnd.Pdf;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class PdfGeneration
{
    public static final String RESOURCES_DIR;
    public static final String OUTPUT_FILE;
    public static final String DATA_DIR;

    static {
        DATA_DIR = "monuments//";
        RESOURCES_DIR = "src//main//resources//";
        OUTPUT_FILE = "src//main//resources//temp//output.pdf";
    }

    public static void main( String[] args )
    {
        try {
            convertToPDF("5");
        } catch (FOPException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void convertToPDF(String filename) throws IOException, FOPException, TransformerException {
        // the XSL FO file
        File xsltFile = new File(RESOURCES_DIR + "//pdf_template.xsl");
        // the XML file which provides the input
        StreamSource xmlSource = new StreamSource(new File(DATA_DIR + "//"+filename+".xml"));
        // create an instance of fop factory
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        // a user agent is needed for transformation
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        // Setup output
        OutputStream out;
        out = new java.io.FileOutputStream(OUTPUT_FILE);

        try {
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            // Resulting SAX events (the generated FO) must be piped through to
            // FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then
            // PDF is created
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }
}
