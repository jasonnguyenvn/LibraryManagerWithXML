<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : BookInfo.xsl
    Created on : July 13, 2017, 4:54 PM
    Author     : Hau
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="book">
        <xsl:param name="userFullname" />
        
        <html>
            <head>
                <title>
                    Thông tin chi tiết:
                    <xsl:value-of select="booktitle" />
                </title>
                
                <link rel="stylesheet" media="screen" href="main.css" />

            </head>
            <body>
                <style>
                    
                    .book-info {
                    width: 50%;
                    }
                    .book-info .row {
                    width: 100%;
                    }
                    .book-info .row .left-col {
                    width: 40%;
                    min-width: 40%;
                    display: inline;
                    }
                    .book-info .row .right-col {
                    width: 60%;
                    min-width: 60%;
                    display: inline;
                    }
                    
                    .book-title {
                    font-size: 14pt;
                    font-weight: bold;
                    }
                    
                    .book-action {
                    display:block;
                    float: right;
                    }
                
                    
                </style>
                <div id="nav-bar">
                    <div class="nav-bar-wrapper">
                        <div id="logo">
                            <a href="./">
                                Trang chủ
                            </a>
                        </div>
                        <div id="userInfo">
                            
                            <xsl:if test="$userFullname != ''">
                                Xin chào, 
                                <b>
                                    <xsl:value-of select="$userFullname" />
                                </b>
                            </xsl:if>
                        </div>
                    </div>
                </div>
                
                <div class="book-info">
                    <div class="book-info-head">
                        <span class="book-title">
                            <xsl:value-of select="booktitle" />
                        </span>
                        
                        <xsl:if test="$userFullname != ''">
                            <xsl:variable name="testAvailable" select="isavailabletoborrow/result" />
                            <xsl:if test="$testAvailable = &quot;true&quot;" >
                                <div class="book-action" >
                                    <form action="registerBorrow" method="post">
                                        <input type="hidden" name="txtBookid">
                                            <xsl:attribute name="value">
                                                <xsl:value-of select="id"/>
                                            </xsl:attribute>
                                        </input>
                                        <input type="submit" name="btnAction" value="Đăng ký mượn" />
                                    </form>
                                </div>
                            </xsl:if>
                        </xsl:if>
                    
                    </div>
                    <div class="row">
                        <div class="left-col">
                            Tác giả:
                        </div>
                        <div class="right-col">
                            <xsl:value-of select="author"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="left-col">
                            Nhà xuất bản:
                        </div>
                        <div class="right-col">
                            <xsl:value-of select="publisher"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="left-col">
                            Năm xuất bản:
                        </div>
                        <div class="right-col">
                            <xsl:value-of select="year"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="left-col">
                            Giá bìa:
                        </div>
                        <div class="right-col">
                            <xsl:value-of select="price"/> đ
                        </div>
                    </div>
                    <hr/>
                    <div class="description-container">
                        <div>
                            <b>Miêu tả:</b>
                        </div>
                        <div class="description">
                            <xsl:value-of select="description"/>
                        </div>
                    </div>
                </div>
                
                
                <div class="list-of-copies">
                    <p>
                        <b>Có <xsl:value-of select="count(copies/bookcopy)"/>
                            bản trong thư viện.</b>
                    </p>
                    <xsl:if test="count(copies/bookcopy) &gt; 0" >
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Mã</th>
                                    <th>Vị trí</th>
                                </tr>
                            </thead>
                            <tbody>
                                <xsl:for-each select="copies/bookcopy" >
                                    <tr>
                                        <td>
                                            <xsl:value-of select="code"/>
                                        </td>
                                        <td>
                                            Ô <b>
                                                <xsl:value-of select="storedin/col"/>
                                                <xsl:value-of select="storedin/row"/>
                                            </b>
                                            thộc kệ sách:
                                            <b>
                                                <xsl:value-of select="storedin/ceilof/title"/>
                                            </b>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </tbody>
                        </table>
                    </xsl:if>
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
