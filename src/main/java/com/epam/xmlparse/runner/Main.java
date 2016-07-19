package com.epam.xmlparse.runner;

import com.epam.xmlparse.bean.Book;
import com.epam.xmlparse.parsers.DOMParser;
import com.epam.xmlparse.parsers.SAXParser;
import com.epam.xmlparse.parsers.StAXParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ivanpryshchepau on 7/19/16.
 */
@SuppressWarnings("Since15")
public class Main {
    public static void main(String[] args) throws SAXException, IOException {

        String src = "src/main/resources/data.xml";

        XMLReader readerSAX = XMLReaderFactory.createXMLReader();
        SAXParser sax = new SAXParser();
        readerSAX.setContentHandler(sax);
        readerSAX.parse(new InputSource(src));

        try {
            System.out.println("Start StAX");
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream input = new FileInputStream(src);
            XMLStreamReader readerStAX = inputFactory.createXMLStreamReader(input);
            for (Book book : StAXParser.process(readerStAX)) {
                System.out.println(book.toString());
            }
            System.out.println("End StAX\n\n");
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        System.out.println("Start DOM");
        for (Book book : DOMParser.parse(src)) {
            System.out.println(book.toString());
        }
        System.out.println("End DOM");



    }
}
