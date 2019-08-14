package com.zikozee.pdf_manipulation.pdf;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@RestController
public class PdfController {
    @GetMapping("/splitter")
    public String splitter() throws IOException {

        //Loading an existing PDF document
        File file = new File("C:/Users/Ezekiel/Desktop/PDF/HB PERSONAL HISTORY FORM.PDF");
        PDDocument document = PDDocument.load(file);

        //Instantiating Splitter class
        Splitter splitter = new Splitter();

        //splitting the pages of a PDF document
        List<PDDocument> Pages = splitter.split(document);

        //Creating an iterator
        Iterator<PDDocument> iterator = Pages.listIterator();

        //Saving each page as an individual document
        int i = 1;
        while(iterator.hasNext()) {
            PDDocument pd = iterator.next();
            pd.save("C:/Users/Ezekiel/Desktop/PDF/split/"+ i++ +".pdf");
        }
        System.out.println("Successful");
        document.close();

        return "Multiple PDF’s created";
    }

    @GetMapping("/merger")
    public String merger() throws IOException {
        File folder = new File("C:/Users/Ezekiel/Desktop/PDF/to_merge");
        File[] fileNames = folder.listFiles();

//        //Loading an existing PDF document
//        File file1 = new File("C:/PdfBox_Examples/sample1.pdf");
//        PDDocument doc1 = PDDocument.load(file1);
//
//        File file2 = new File("C:/PdfBox_Examples/sample2.pdf");
//        PDDocument doc2 = PDDocument.load(file2);
        List<PDDocument> docs = new LinkedList<>(); // holder for docs
        for (File fileName : fileNames) {
            PDDocument doc = PDDocument.load(fileName);
            docs.add(doc);
        }

        //Instantiating PDFMergerUtility class
        PDFMergerUtility PDF_merger = new PDFMergerUtility();

        //Setting the destination file
        PDF_merger.setDestinationFileName("C:/Users/Ezekiel/Desktop/PDF/merged/merged.pdf");

        //adding the source files
        for (File file: fileNames){
            PDF_merger.addSource(file);
        }

        //Merging the two documents
        PDF_merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

        System.out.println("Documents merged");
        //Closing the documents
        for(PDDocument doc : docs){
            doc.close();
        }

        // to verify if pdf merged is accurate, add 1st,2nd and 3rd pdf names
        return "Pdf merged-" + fileNames[0].getName()+ " " + fileNames[1].getName() + " " + fileNames[2].getName();
    }

    @GetMapping("/encrypting")
    public String encrypt() throws IOException{
        //Loading an existing document
        File file = new File("C:/Users/Ezekiel/Desktop/PDF/z_to_B-encrypted/sample.pdf");
        PDDocument document = PDDocument.load(file);

        //Creating access permission object
        AccessPermission ap = new AccessPermission();

        //Creating StandardProtectionPolicy object
        StandardProtectionPolicy spp = new StandardProtectionPolicy("1234", "1234", ap);

        //Setting the length of the encryption key
        spp.setEncryptionKeyLength(128);

        //Setting the access permissions
        spp.setPermissions(ap);

        //Protecting the document
        document.protect(spp);

        System.out.println("Document encrypted");

        //Saving the document
        document.save("C:/Users/Ezekiel/Desktop/PDF/z_encrypted/Encrypted.pdf");
        //Closing the document
        document.close();

        return "Encryption Successful";
    }
}
