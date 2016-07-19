package com.epam.xmlparse.parsers;

import com.epam.xmlparse.bean.Book;
import com.epam.xmlparse.bean.TagName;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.epam.xmlparse.bean.TagName.BOOK;

/**
 * Created by ivanpryshchepau on 7/19/16.
 */
@SuppressWarnings("Since15")
public class StAXParser {

    public static List<Book> process(XMLStreamReader reader) throws XMLStreamException {
        List<Book> list = new ArrayList<Book>();
        Book book = null;
        TagName tag = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT: {
                    tag = TagName.valueOf(reader.getLocalName().toUpperCase());
                    switch (tag) {
                        case BOOK: {
                            book = new Book();
                            break;
                        }
                    }
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (tag) {
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
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    tag = TagName.valueOf(reader.getLocalName().toUpperCase());
                    if (tag.equals(BOOK)) {
                        list.add(book);
                    }
                    break;
                }
            }
        }
        return list;
    }
}


