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
        <table>
            <xsl:for-each select="bookDto">
                <tr>
                    <td>
                        <xsl:value-of select="booktitle"/>
                    </td>
                    
                    <td>
                        <xsl:value-of select="author"/>
                    </td>
                    
                    
                    <td>
                        <xsl:value-of select="description"/>
                    </td>
                    
                    
                    <td>
                        <xsl:value-of select="publisher"/>
                    </td>
                    
                    
                    <td>
                        <xsl:value-of select="year"/>
                    </td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>

</xsl:stylesheet>
