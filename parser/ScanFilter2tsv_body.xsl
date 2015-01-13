<?xml version="1.0" encoding="UTF-8"?>  
<xsl:stylesheet version="1.0"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns="http://www.w3.org/1999/xhtml"> 
	
	<xsl:output method="text"/> 
	<xsl:output encoding="UTF-8"/>


	<xsl:template match="SCAN"> 
		<xsl:call-template name="VulnsByServer" />
	</xsl:template> 

	<xsl:template name="VulnsByServer">
		<xsl:text> Server &#09; IP &#09; Type &#09; Protocol &#09; CVSS_BASE &#09; PCI_FLAG &#09; Number &#09; CVE ID &#09; Title &#09; Service Name&#09;Port&#09;OS&#10;</xsl:text>
		<xsl:apply-templates select="//IP"/>
	</xsl:template> 
	
	<xsl:template match="IP">
		<xsl:for-each select="VULNS/CAT/VULN">
			<xsl:sort order="descending" select="@severity"/>
			<xsl:value-of select="../../../@name"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../../../@value"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="name()"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../@protocol"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:if test="CVSS_BASE&gt;0">
				<xsl:value-of select="CVSS_BASE"/>
			</xsl:if>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="PCI_FLAG"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="@number" />
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="@cveid"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="TITLE"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../@value"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../@port"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../../../OS"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:text>&#13;</xsl:text>
		</xsl:for-each>
	
		<xsl:for-each select="INFOS/CAT/INFO">
			<xsl:sort order="descending" select="@severity"/>
			<xsl:value-of select="../../../@name"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../../../@value"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="name()"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../@protocol"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:if test="CVSS_BASE&gt;0">
				<xsl:value-of select="CVSS_BASE"/>
			</xsl:if>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="PCI_FLAG"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="@number" />
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="@cveid"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="TITLE"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../@value"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../@port"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../../../OS"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:text>&#13;</xsl:text>
		</xsl:for-each>
	
		<xsl:for-each select="SERVICES/CAT/SERVICE">
			<xsl:sort order="descending" select="@severity"/>
			<xsl:value-of select="../../../@name"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../../../@value"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="name()"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../@protocol"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:if test="CVSS_BASE&gt;0">
				<xsl:value-of select="CVSS_BASE"/>
			</xsl:if>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="PCI_FLAG"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="@number" />
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="@cveid"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="TITLE"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../@value"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../@port"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../../../OS"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:text>&#13;</xsl:text>
		</xsl:for-each>
	
		<xsl:for-each select="PRACTICES/CAT/PRACTICE">
			<xsl:sort order="descending" select="@severity"/>
			<xsl:value-of select="../../../@name"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../../../@value"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="name()"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../@protocol"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:if test="CVSS_BASE&gt;0">
				<xsl:value-of select="CVSS_BASE"/>
			</xsl:if>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="PCI_FLAG"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="@number" />
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="@cveid"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="TITLE"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../@value"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../@port"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:value-of select="../../../OS"/>
			<xsl:text>&#09;</xsl:text>
			<xsl:text>&#13;</xsl:text>
			
		</xsl:for-each>
	
	</xsl:template> 
</xsl:stylesheet> 