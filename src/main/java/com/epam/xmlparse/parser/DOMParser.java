package com.epam.xmlparse.parser;


import com.epam.xmlparse.bean.Book;
import com.epam.xmlparse.bean.TagName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanpryshchepau on 7/19/16.
 */
public class DOMParser {

    public static List<Book> parse(String src) throws IOException, SAXException {

        com.sun.org.apache.xerces.internal.parsers.DOMParser parser =
                new com.sun.org.apache.xerces.internal.parsers.DOMParser();
        parser.parse(src);
        Document document = parser.getDocument();
        Element root = document.getDocumentElement();
        List<Book> list = new ArrayList<Book>();
        NodeList bookNodes = root.getElementsByTagName(String.valueOf(TagName.BOOK).toLowerCase());
        Book book = null;

        for (int i = 0; i < bookNodes.getLength(); i++) {
            book = new Book();
            Element bookElement = (Element) bookNodes.item(i);
            book.setId(Integer.parseInt(bookElement.getAttribute("ID")));
            book.setTitle(getSingleChild(bookElement, TagName.TITLE.toString().toLowerCase()).getTextContent().trim());
            book.setPrice(Integer.parseInt(getSingleChild(bookElement, TagName.PRICE.toString().toLowerCase())
                    .getTextContent().trim()));
            book.setOwner(getSingleChild(
                    getSingleChild(bookElement, TagName.OWNER.toString().toLowerCase()),
                    TagName.SURNAME.toString().toLowerCase()).getTextContent().trim());
            list.add(book);
        }



        return list;
    }

    private static Element getSingleChild(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        Element child = (Element) nodeList.item(0);
        return child;
    }

}
