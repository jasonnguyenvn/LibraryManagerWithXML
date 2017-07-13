<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : BookSearch.xsl
    Created on : July 12, 2017, 1:15 PM
    Author     : Hau
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    
    <xsl:template name="doPaging">
        <xsl:param name="i"/>
        <xsl:param name="curPage"/>
        <xsl:param name="totalSize"/>
        <xsl:param name="pageSize"/>
        <xsl:param name="searchBy"/>
        <xsl:param name="searchValue"/>
        
        <xsl:if test="$i &lt;= $totalSize"> 
            <xsl:if test="$i != 1"> 
                |
            </xsl:if>
            <xsl:choose>
                <xsl:when test="$i = $curPage">
                    <b><xsl:value-of select="$i" /></b>
                </xsl:when>
                <xsl:otherwise>
                    <a>
                        <xsl:attribute name="href">searchBook?pagesize=<xsl:value-of select="$pageSize"/>&amp;page=<xsl:value-of select="$i"/>&amp;txtSearchValue=<xsl:value-of select="$searchValue"/>&amp;cbxSearchBy=<xsl:value-of select="$searchBy"/></xsl:attribute>

                        <xsl:value-of select="$i" /> 
                    </a>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:call-template name="doPaging"> 
                <xsl:with-param name="i" select="$i + 1"/> 
                <xsl:with-param name="curPage" select="$curPage"/> 
                <xsl:with-param name="totalSize" select="$totalSize"/> 
                <xsl:with-param name="pageSize" select="$pageSize"/> 
                <xsl:with-param name="searchBy" select="$searchBy"/> 
                <xsl:with-param name="searchValue" select="$searchValue"/> 
            </xsl:call-template> 
        </xsl:if>
    </xsl:template>
    
    <xsl:template match="BookSearchResult">
        có <xsl:value-of select="totalsize"/> kết quả. 
        <div class="main-container">
            <div class="search-result-container">
            
                <xsl:for-each select="books/book" >
                    <div class="book-card">
                        <a class="clear-a">
                            <xsl:attribute name="href">viewBookInfo?id=<xsl:value-of select="id"/></xsl:attribute>
                            <div class="card-head">
                                <xsl:value-of select="booktitle"/>
                            </div>
                            <div class="card-body">
                                <div class="part1">
                                    <div class="row">
                                        <div class="left-col">
                                            Tác giả
                                        </div>
                                        <div class="right-col">
                                            <xsl:value-of select="author"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="left-col">
                                            Nhà xuất bản
                                        </div>
                                        <div class="right-col">
                                            <xsl:value-of select="publisher"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="left-col">
                                            Năm xuất bản
                                        </div>
                                        <div class="right-col">
                                            <xsl:value-of select="year"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="left-col">
                                            Giá bìa
                                        </div>
                                        <div class="right-col">
                                            <xsl:value-of select="price"/> đ
                                        </div>
                                    </div>
                                </div>
                                <div class="description-container">
                                    <div>
                                        <b>Miêu tả:</b>
                                    </div>
                                    <div class="description">
                                        <xsl:value-of select="description"/>
                                    </div>
                                </div>
                            </div>
                            <div class="card-foot">
                                <div class="left-col">
                                    <b> 
                                        Có <xsl:value-of select="count(copies/bookcopy)"/> 
                                        cuốn trong thư viện.
                                    </b>
                                </div>
                                <div class="right-col">
                                </div>
                            </div>
                        </a>
                    </div>
                </xsl:for-each>
            </div>
        </div>
        
        <div class="paging-container">
            <div class="paging-wrapper">
                <xsl:if test="totalsize &gt; pagesize">
                    <xsl:call-template name="doPaging"> 
                            <xsl:with-param name="i" select="number(1)"/> 
                            <xsl:with-param name="curPage" select="page"/> 
                            <xsl:with-param name="totalSize" select="totalsize div pagesize + totalsize mod pagesize"/> 
                            <xsl:with-param name="pageSize" select="pagesize"/> 
                            <xsl:with-param name="searchBy" select="searchby"/> 
                            <xsl:with-param name="searchValue" select="searchvalue"/> 
                    </xsl:call-template> 
                </xsl:if>
            </div>
        </div>
       
        <style>
            .main-container, .paging-container {
                width: 100%;
                min-width: 100%;
                position: relative;
                display: flex;
            }
            
            .paging-container {
                min-height: 100px;
                margin-top: 10px;
            }
            
            .paging-container .paging-wrapper {
                margin: auto;
                width: 80%;
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
                font-size: 14pt;
            }
            .search-result-container {
                max-width: 100%;
            }
            .book-card {
                margin: 10px;
                width: 250px;
                height: 300px;
                display: block;
                padding: 10px;
                float: left;
                position: relative;
                position: relative;
                box-shadow: 0 2px 2px rgba(0, 0, 0, .15);
                background-color: #fff;
                
            }
            .book-card .clear-a {
                width: 100%;
                height: 100%;
                color: black;
                text-decoration: none;
            }
           .book-card .card-body .part1 .row {
                position: relative;
                width: 100%;
            }
            
            .book-card .card-body .part1 {
                position: relative;
                width: 100%;
            }
            .book-card .card-body .part1 .row .left-col {
                position: relative;
                width: 40%;
                min-width: 40%;
                font-weight: bold;
                display: inline;
            }
            .book-card .card-body  .part1 .row .right-col {
                position: relative;
                width: 60%;
                min-width: 60%;
                padding-left: 10px;
                display: inline;
            }
            .book-card .description-container {
                position: relative;
                width: 100%;
                margin-top: 10px;
            }
            .book-card .description-container .description {
                width: 100%;
                max-height: 150px;
                min-height: 90px;
                overflow: hidden;
                white-space: pre-wrap;
                text-overflow: ellipsis;
            }
            .book-card .card-head {
                display: block;
                position: relative;
                width: 100%;
                height: 10%;
                font-size: 12;
                font-weight: bold; 
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
            }
            .book-card .card-body {
                display: block;
                position: relative;
                width: 100%;
                height: 80%;
            }
            .book-card .card-foot {
                margin-top: 5px;
                padding: 10px;
                display: block;
                position: relative;
                width: 100%;
                height: 10%;
            }
            .book-card .card-foot .left-col,
            .book-card .card-foot .right-col  {
                position: relative;
                width: 50%;
                display: inline;
            }
        </style>
    </xsl:template>

</xsl:stylesheet>
