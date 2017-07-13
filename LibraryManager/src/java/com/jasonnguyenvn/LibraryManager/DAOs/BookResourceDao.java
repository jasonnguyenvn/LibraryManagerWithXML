/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jasonnguyenvn.LibraryManager.DAOs;

import com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos.BookDto;
import com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos.BookcopyDto;
import com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos.BookshelfDto;
import com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos.CeilDto;
import com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos.Copies;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Hau
 */
public class BookResourceDao extends AbstractDbDao { 
    private BookDto bookDto;

    public BookDto getBookDto() {
        return bookDto;
    }
    
    
    
    private List<BookDto> searchResult;

    public List<BookDto> getSearchResult() {
        return searchResult;
    }
    
    private AbstractDbDao.PrepareStatementCallback prepareGetBookshelfByIdStm =
            new PrepareStatementCallback() {
        private int bookshelfId;

        public void setParameters(Object... parameters) {
            if (parameters == null) {
                return;
            }
            if (parameters.length < 1) {
                return;
            }

            if (parameters[0] != null) {
                if (parameters[0] instanceof Integer) {
                    this.bookshelfId = (Integer) parameters[0];
                }
            }
        }

        public PreparedStatement process(Connection con) throws SQLException {
            PreparedStatement stm = null;
            if (con != null) {
                String sql = "SELECT TOP 1000 [id],[title],[description],[tags] "
                        + "FROM [bookshelf] WHERE [bookshelf].[id]=?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, bookshelfId);

            }
            return stm;
        }
    };
    
    private AbstractDbDao.ProcessResultSetCallback<BookshelfDto> 
            processGetBookshelfByIdRs = new ProcessResultSetCallback<BookshelfDto>() {

        public BookshelfDto process(ResultSet rs) throws SQLException, NamingException {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String tags = rs.getString("tags");
                return new BookshelfDto(id, title);
            }
            return null;
        }
    };
    
    private AbstractDbDao.PrepareStatementCallback prepareGetCeilByIdStm = 
            new PrepareStatementCallback() {
        private int ceilId;
        

        public void setParameters(Object... parameters) {
            if (parameters == null) {
                return;
            }
            if (parameters.length < 1) {
                return;
            }

            if (parameters[0] != null) {
                if (parameters[0] instanceof Integer) {
                    this.ceilId = (Integer) parameters[0];
                }
            }

        }

        public PreparedStatement process(Connection con) throws SQLException {
            PreparedStatement stm = null;
            if (con != null) {
                String sql = "SELECT TOP 1000 [id],[bookshelfid],[row],[col],"
                        + "[description],[size],[tags] "
                        + "FROM [ceil] WHERE [ceil].[id]= ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, ceilId);

            }

            return stm;
        }
    };
    
    private AbstractDbDao.ProcessResultSetCallback<CeilDto> processGetCeilByIdRs
            = new ProcessResultSetCallback<CeilDto>() {

        public CeilDto process(ResultSet rs) throws SQLException, NamingException {
            while (rs.next()) {
                int id = rs.getInt("id");
                int row = rs.getInt("row");
                String col = rs.getString("col");
                String description = rs.getString("description");
                int size = rs.getInt("size");
                String tags = rs.getString("tags");
                CeilDto dto = new CeilDto(id, row, col);
                
                int bookshelfid = rs.getInt("bookshelfid");
                dto.setCeilof(
                        executeSelect(prepareGetBookshelfByIdStm, 
                                processGetBookshelfByIdRs, bookshelfid)
                );
                
                return dto;
            }
            return null;
        }
    };
            

    private AbstractDbDao.PrepareStatementCallback prepareGetBookCopiesStm
            = new PrepareStatementCallback() {
                private int bookId;

                public void setParameters(Object... parameters) {
                    if (parameters == null) {
                        return;
                    }
                    if (parameters.length < 1) {
                        return;
                    }

                    if (parameters[0] != null) {
                        if (parameters[0] instanceof Integer) {
                            this.bookId = (Integer) parameters[0];
                        }
                    }

                }

                public PreparedStatement process(Connection con) throws SQLException {
                    PreparedStatement stm = null;
                    if (con != null) {
                        String sql = "SELECT TOP 1000 [id],[bookid],[code],[ceilid] FROM [bookcopy] "
                        + " WHERE [bookcopy].[bookid] = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setInt(1, bookId);

                    }

                    return stm;
                }
            };

    private AbstractDbDao.ProcessResultSetCallback<List<BookcopyDto>> 
            processGetBookCopiesStmRS = new ProcessResultSetCallback<List<BookcopyDto>>() {

        public List<BookcopyDto> process(ResultSet rs) 
                throws SQLException, NamingException {
            List<BookcopyDto> resultList = null;

            while (rs.next()) {
                if (resultList == null) {
                    resultList = new ArrayList<BookcopyDto>();
                }
                int id = rs.getInt("id");
                String code = rs.getString("code");
                BookcopyDto dto = new BookcopyDto(id, code);
                
                int ceilId = rs.getInt("ceilid");
                dto.setStoredin(
                        executeSelect(prepareGetCeilByIdStm, processGetCeilByIdRs, 
                                ceilId)
                );
                
                resultList.add(dto);
            }

            return resultList;
        }
    };

    private AbstractDbDao.PrepareStatementCallback prepareStmForSearchStm
            = new PrepareStatementCallback() {
                private String searchValue;
                private int page;
                private int pageSize;
                private String searchBy;

                public PreparedStatement process(Connection con) throws SQLException {
                    PreparedStatement stm = null;
                    if (con != null) {
                        String sql = "SELECT * FROM ( "
                        + " SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY [id]) as rowNum,  "
                        + " [id],[booktitle],[author],[price],[description], "
                        + " [year],[publisher],[tags] "
                        + " FROM [book] "
                        + " WHERE [book].[" + this.searchBy + "] LIKE ? "
                        + " ) AS R1 WHERE [R1].[rowNum] BETWEEN ? AND ?;";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, "%" + this.searchValue + "%");
                        stm.setInt(2, page);
                        stm.setInt(3, pageSize);

                    }

                    return stm;
                }

                public void setParameters(Object... parameters) {
                    if (parameters == null) {
                        return;
                    }
                    if (parameters.length < 1) {
                        return;
                    }

                    if (parameters[0] != null) {
                        if (parameters[0] instanceof String) {
                            this.searchBy = (String) parameters[0];
                        }
                    }

                    if (parameters[1] != null) {
                        if (parameters[1] instanceof String) {
                            this.searchValue = (String) parameters[1];
                        }
                    }

                    if (parameters[2] != null) {
                        if (parameters[2] instanceof Integer) {
                            this.pageSize = (Integer) parameters[2];
                        }
                    } else {
                        this.pageSize = 100;
                    }

                    if (parameters[3] != null) {
                        if (parameters[3] instanceof Integer) {
                            this.page = (Integer) parameters[3];
                            if (this.page < 1) {
                                this.page = 1;
                            }
                        }
                    } else {
                        this.page = 1;
                    }
                }
            };

    private AbstractDbDao.ProcessResultSetCallback<List<BookDto>> processSearchStmResult
            = new ProcessResultSetCallback<List<BookDto>>() {

                public List<BookDto> process(ResultSet rs) throws SQLException, NamingException {
                    int firstRow = -1;
                    List<BookDto> resultList = new ArrayList<BookDto>();

                    while (rs.next()) {
                        if (firstRow == -1) {
                            firstRow = rs.getInt("rowNum");
                        }
                        int id = rs.getInt("id");
                        String booktitle = rs.getString("booktitle");
                        String author = rs.getString("author");
                        Double price = rs.getDouble("price");
                        String description = rs.getString("description");
                        int year = rs.getInt("year");
                        String publisher = rs.getString("publisher");
                        String tags = rs.getString("tags");

                        BookDto dto = new BookDto(id, booktitle, author, price,
                                description, year, publisher, tags);
                        dto.setCopies(
                              new Copies(
                              executeSelect(prepareGetBookCopiesStm,
                                        processGetBookCopiesStmRS, id)
                              )
                        );
                        resultList.add(dto);
                    }
                    return resultList;
                }

            };

    public void searchByBookTitle(String searchValue, Integer pageSize,
            Integer page) throws SQLException, NamingException {
        this.searchResult = this.executeSelect(prepareStmForSearchStm,
                processSearchStmResult, "booktitle", searchValue, pageSize, page);
    }

    public void searchByAuthor(String searchValue, Integer pageSize,
            Integer page) throws SQLException, NamingException {
        this.searchResult = this.executeSelect(prepareStmForSearchStm,
                processSearchStmResult, "author", searchValue, pageSize, page);
    }

    public void searchByPublisher(String searchValue, Integer pageSize,
            Integer page) throws SQLException, NamingException {
        this.searchResult = this.executeSelect(prepareStmForSearchStm,
                processSearchStmResult, "publisher", searchValue, pageSize, page);
    }
    
    public void searchByYear(String searchValue, Integer pageSize,
            Integer page) throws SQLException, NamingException {
        this.searchResult = this.executeSelect(prepareStmForSearchStm,
                processSearchStmResult, "year", searchValue, pageSize, page);
    }
    
    // CODE to get BOOK detail :
    private AbstractDbDao.PrepareStatementCallback prepareStmGetBook = 
            new PrepareStatementCallback() {
        private int id;
        public void setParameters(Object... parameters) {
            if (parameters == null) {
                return;
            }
            if (parameters.length < 1) {
                return;
            }

            if (parameters[0] != null) {
                if (parameters[0] instanceof Integer) {
                    this.id = (Integer) parameters[0];
                }
            }
        }

        public PreparedStatement process(Connection con) throws SQLException {
            PreparedStatement stm = null;
            if (con != null) {
                String sql = " SELECT   "
                        + " [id],[booktitle],[author],[price],[description], "
                        + " [year],[publisher],[tags] "
                        + " FROM [book] "
                        + " WHERE [book].[id]=?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);

            }
            return stm;
        }
        
    };
    
    private AbstractDbDao.ProcessResultSetCallback<BookDto> processGetBookRs = 
            new ProcessResultSetCallback<BookDto>() {

        public BookDto process(ResultSet rs) throws SQLException, NamingException {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String booktitle = rs.getString("booktitle");
                    String author = rs.getString("author");
                    Double price = rs.getDouble("price");
                    String description = rs.getString("description");
                    int year = rs.getInt("year");
                    String publisher = rs.getString("publisher");
                    String tags = rs.getString("tags");

                    BookDto dto = new BookDto(id, booktitle, author, price,
                            description, year, publisher, tags);
                    dto.setCopies(
                          new Copies(
                          executeSelect(prepareGetBookCopiesStm,
                                    processGetBookCopiesStmRS, id)
                          )
                    );
                    return dto;
                }
                return null;
        }
    };
    
    public void getBookById(int id) throws SQLException, NamingException {
        this.bookDto = this.executeSelect(prepareStmGetBook, processGetBookRs, id);
    }

}
