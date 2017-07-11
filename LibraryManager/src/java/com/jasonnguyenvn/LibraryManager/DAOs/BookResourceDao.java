/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.DAOs;

import com.jasonnguyenvn.LibraryManager.DTOs.SearchPagingDto;
import com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos.BookDto;
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
    private List<BookDto> searchResult;

    public List<BookDto> getSearchResult() {
        return searchResult;
    }
    
    
    
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
                        + " WHERE [book].["+this.searchBy+"] LIKE ? "
                        + " ) AS R1 WHERE [R1].[rowNum] BETWEEN ? AND ?;";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%"+this.searchValue+"%");
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
    
    private AbstractDbDao.ProcessResultSetCallback<List<BookDto>> 
            processSearchStmResult = 
                new ProcessResultSetCallback<List<BookDto>>() {

        public List<BookDto> process(ResultSet rs) throws SQLException {
             int firstRow = -1;
            List<BookDto> resultList = new ArrayList<BookDto>();
            
            SearchPagingDto<BookDto> result = null;
            
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
    
    
}
