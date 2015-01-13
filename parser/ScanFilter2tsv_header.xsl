<?xml version="1.0" encoding="UTF-8"?> 
<xsl:stylesheet version="1.0"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns="http://www.w3.org/1999/xhtml"> 	

	<xsl:output method="text" />
	<xsl:output encoding="UTF-8" />

	<xsl:template match="SCAN"> 
		<xsl:call-template name="Header" />
	</xsl:template> 

	<xsl:template name="Header">
		<xsl:apply-templates select="//HEADER"/>
	</xsl:template> 

	<xsl:template match="HEADER">
    		<xsl:for-each select="KEY">
      			<xsl:if test="@value='DATE'">
				<xsl:text>Date&#09;</xsl:text>
      				<xsl:value-of select="translate(text(), ',', '&#09;')"/>
      				<xsl:text>&#09;&#13;</xsl:text>
    			</xsl:if>
      			<xsl:if test="@value='TARGET'">
        			<xsl:text>Host range&#09;</xsl:text>
        			<xsl:value-of select="translate(text(), ',', '&#09;')"/>
        			<xsl:text>&#09;;&#13;</xsl:text>
      			</xsl:if>
      			<xsl:if test="@value='EXCLUDED_TARGET'">
        			<xsl:text>Host excluded&#09;</xsl:text>
        			<xsl:value-of select="translate(text(), ',', '&#09;')"/>
        			<xsl:text>&#09;;&#13;</xsl:text>
      			</xsl:if>
      			<xsl:if test="@value='NBHOST_TOTAL'">
        			<xsl:text>Total number of host&#09;</xsl:text>
        			<xsl:value-of select="text()"/>
        			<xsl:text>&#09;&#13;</xsl:text>
      			</xsl:if>
      			<xsl:if test="@value='NBHOST_ALIVE'">
        			<xsl:text>Number of responding host&#09;</xsl:text>
        			<xsl:value-of select="text()"/>
        			<xsl:text>&#09;&#13;</xsl:text>
      			</xsl:if>
      			<xsl:if test="@value='SCAN_HOST'">
        			<xsl:text>Vulnerability Signature Version&#09;</xsl:text>
        			<xsl:value-of select="text()"/>
        			<xsl:text>&#09;&#13;</xsl:text>
      			</xsl:if>
      			<xsl:if test="@value='OPTIONS'">
        			<xsl:text>Configuration option&#09;</xsl:text>
        			<xsl:value-of select="translate(text(), ',', '&#09;')"/>
        			<xsl:text>&#09;;&#13;</xsl:text>
      			</xsl:if>
			<xsl:if test="@value='TITLE'">
        			<xsl:text>Title&#09;</xsl:text>
        			<xsl:value-of select="text()"/>
        			<xsl:text>&#09;;&#13;</xsl:text>
      			</xsl:if>
			<xsl:if test="@value='DURATION'">
        			<xsl:text>Duration&#09;</xsl:text>
        			<xsl:value-of select="text()"/>
        			<xsl:text>&#09;;&#13;</xsl:text>
      			</xsl:if>
				<xsl:if test="@value='STATUS'">
        			<xsl:text>Status&#09;</xsl:text>
        			<xsl:value-of select="text()"/>
        			<xsl:text>&#09;;&#13;&#13;</xsl:text>
      			</xsl:if>
    		</xsl:for-each>
	</xsl:template> 
	
</xsl:stylesheet> 