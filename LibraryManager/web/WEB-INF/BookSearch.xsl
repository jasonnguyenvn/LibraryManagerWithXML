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

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    
    <xsl:template match="bookDtoes">
        
        có <xsl:value-of select="count(bookDto)"/> kết quả.
        
        <div class="search-result-container">
            
            <xsl:for-each select="bookDto" >
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
        
       
        <style>
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
                min-height: 100px;
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
