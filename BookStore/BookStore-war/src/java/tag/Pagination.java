/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tag;

import dto.BookDTO;
import java.util.Iterator;
import java.util.TreeSet;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Phat Huy
 */
public class Pagination extends SimpleTagSupport {

    private Integer pageSize;
    private Integer pageNumber;
    private TreeSet myBookSet;

    /**
     * Called by the container to invoke this tag. 
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
            int bookAmount = myBookSet.size();
            int pageAmount = bookAmount / pageSize.intValue();
            if (bookAmount % pageSize.intValue() != 0) {
                pageAmount += 1;
            }

            Iterator it = myBookSet.iterator();
            int size = myBookSet.size();
            BookDTO[] dto = new BookDTO[size];
            while (it.hasNext()) {
                for (int i = 0; i < size; i++) {
                    dto[i] = (BookDTO) it.next();
                }
            }

            if (pageAmount == 1) {
                String s = "<table width='460' border='1'> +"
                        + "         <tr>"
                        + "             <th width='200'>Title</th>"
                        + "             <th width='150'>Author</th>"
                        + "             <th width='60'>Price</th>"
                        + "         </tr>";
                s += "<tr>"
                        + "<td><a href='BookDetail?id=" + dto[0].getId() + "'>" + dto[0].getTitle() + "</a></td>"
                        + "<td>" + dto[0].getAuthorName() + "</td>"
                        + "<td align='center'>" + dto[0].getPrice() + "</td>"
                        + "<td align='right'><a href='AddToCart?id=" + dto[0].getId() + "'><input type='button' class='normal_button' value='Add' /></a></td>"
                        + "</tr>";
                s += "</table>";
                out.println(s);
                out.println("<a href='TurnToPage?pageNumber=" + 1 + "'>" + 1 + "</a>");
            } else {
                String s = "<table width='460' border='1'> +"
                        + "         <tr>"
                        + "             <th width='200'>Title</th>"
                        + "             <th width='150'>Author</th>"
                        + "             <th width='60'>Price</th>"
                        + "         </tr>";
                for (int i = (pageSize.intValue() * (pageNumber.intValue() - 1)); i < pageSize.intValue() * pageNumber.intValue(); i++) {
//                BookDTO dto = (BookDTO) bookList.get(i);
                    s += "<tr>"
                            + "<td><a href='BookDetail?id=" + dto[i].getId() + "'>" + dto[i].getTitle() + "</a></td>"
                            + "<td>" + dto[i].getAuthorName() + "</td>"
                            + "<td align='center'>" + dto[i].getPrice() + "</td>"
                            + "<td align='right'><a href='AddToCart?id=" + dto[i].getId() + "'><input type='button' class='normal_button' value='Add' /></a></td>"
                            + "</tr>";
                }
                s += "</table>";
                out.println(s);
                for (int i = 1; i <= pageAmount; i++) {
                    out.println("<a href='TurnToPage?pageNumber=" + i + "'>" + i + "</a>");
                }
            }

//            Iterator it = myBookSet.iterator();
//            int size = myBookSet.size();
//            BookDTO[] dto = new BookDTO[size];
//            while (it.hasNext()) {
//                for (int i = 0; i < size; i++) {
//                    dto[i] = (BookDTO) it.next();
//                }
//            }

//            String s = "<table width='460' border='1'> +"
//                    + "         <tr>"
//                    + "             <th width='200'>Title</th>"
//                    + "             <th width='150'>Author</th>"
//                    + "             <th width='60'>Price</th>"
//                    + "         </tr>";
//            for (int i = (pageSize.intValue() * (pageNumber.intValue() - 1)); i < pageSize.intValue() * pageNumber.intValue(); i++) {
////                BookDTO dto = (BookDTO) bookList.get(i);
//                s += "<tr>"
//                        + "<td><a href='BookDetail?id=" + dto[i].getId() + "'>" + dto[i].getTitle() + "</a></td>"
//                        + "<td>" + dto[i].getAuthorName() + "</td>"
//                        + "<td align='center'>" + dto[i].getPrice() + "</td>"
//                        + "<td align='right'><a href='AddToCart?id=" + dto[i].getId() + "'><input type='button' class='normal_button' value='Add' /></a></td>"
//                        + "</tr>";
//            }
//            s += "</table>";
//            out.println(s);
//            for (int i = 1; i <= pageAmount; i++) {
//                out.println("<a href='TurnToPage?pageNumber=" + i + "'>" + i + "</a>");
//            }

        } catch (java.io.IOException ex) {
            throw new JspException("Error in Pagination tag", ex);
        }
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setMyBookSet(TreeSet myBookSet) {
        this.myBookSet = myBookSet;
    }
}
