<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : UserInfo.xsl
    Created on : July 14, 2017, 2:35 AM
    Author     : Hau
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="user">
        Xin chào
        <xsl:value-of select="fullname"/>
    </xsl:template>

</xsl:stylesheet>
