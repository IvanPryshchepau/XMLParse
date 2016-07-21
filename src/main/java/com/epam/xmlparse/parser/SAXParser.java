package com.epam.xmlparse.parser;

import com.epam.xmlparse.bean.Book;
import com.epam.xmlparse.bean.TagName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by ivanpryshchepau on 7/19/16.
 */
public class SAXParser extends DefaultHandler{

    private ArrayList<Book> list = new ArrayList<Book>();
    private StringBuilder text;
    private Book book;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start SAX");
    }

    @Override
    public void endDocument() throws SAXException {
        for (Book book : list){
            System.out.println(book.toString());
        }
        System.out.println("End SAX\n\n");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if (qName.equals("book")){
            book = new Book();
            book.setId(Integer.parseInt(attributes.getValue("ID")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        TagName tagName = TagName.valueOf(qName.toUpperCase());
        switch (tagName){
            case TITLE: {
                book.setTitle(text.toString());
                break;
            }
            case PRICE: {
                book.setPrice(Integer.parseInt(text.toString()));
                break;
            }
            case SURNAME: {
                book.setOwner(text.toString());
                break;
            }
            case BOOK: {
                list.add(book);
                book = null;
                break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length);
    }
}
