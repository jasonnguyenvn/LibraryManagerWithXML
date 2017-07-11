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
import java.util.List;

/**
 *
 * @author Hau
 */
public class BookResourceDao extends AbstractDbDao {
    private SearchPagingDto<BookDto> searchResult;
    
    private AbstractDbDao.PrepareStatementCallback prepareStmForSearchByBookTitle 
            = new PrepareStatementCallback() {
        private String searchValue;
        private int page;
        private int pageSize;

        public PreparedStatement process(Connection con) throws SQLException {
            PreparedStatement stm = null;
            if (con != null) {
                String sql = "SELECT * FROM ( "
                        + " SELECT ROW_NUMBER() OVER (ORDER BY [id]) as rowNum,  "
                        + " [id],[booktitle],[author],[price],[description], "
                        + " [year],[publisher],[tags] "
                        + " FROM [book] "
                        + " WHERE [book].[booktitle] LIKE  ? "
                        + " ) AS R1 WHERE [R1].[rowNum] BETWEEN ? AND ?;";
                stm = con.prepareStatement(sql);
                stm.setString(1, this.searchValue);
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
                    this.searchValue = (String) parameters[0];
                }
            }
            
            if (parameters[1] != null) {
                if (parameters[1] instanceof Integer) {
                    this.pageSize = (Integer) parameters[1];
                }
            }
            
            if (parameters[2] != null) {
                if (parameters[2] instanceof Integer) {
                    this.page = (Integer) parameters[2];
                }
            }
        }
    };
    
    private AbstractDbDao.ProcessResultSetCallback<SearchPagingDto<BookDto>> 
            processSearchByBookTileResult = 
                new ProcessResultSetCallback<SearchPagingDto<BookDto>>() {

        public SearchPagingDto<BookDto> process(ResultSet rs) 
                throws SQLException  {
            while (rs.next()) {
                int id = rs.getInt("id");
                String booktitle = rs.getString("booktitle");
                String author = rs.getString("author");
                Double price = rs.getDouble("price");
                String description = rs.getString("description");
                int year = rs.getInt("year");
                String publisher = rs.getString("publisher");
                String tags = rs.getString("tags");
            }
            
            return null;
        }
    };
    
    
}
