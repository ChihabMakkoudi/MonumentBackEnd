<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>
    <xsl:param name="version" select="'1.0'"/>
    <xsl:template match="Monument">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm"
                                       margin-left="2cm" margin-right="2cm">
                    <fo:region-body margin-top="0.5cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body">

                    <!-- <fo:block font-size="10pt">
                        <fo:table table-layout="fixed" width="90%" border-collapse="separate">
                            <fo:table-column column-width="2cm"/>
                            <fo:table-column column-width="4cm"/>
                            <fo:table-column column-width="5cm"/>
                            <fo:table-column column-width="3cm"/>
                            <fo:table-header>
                                <fo:table-row>
                                    <fo:table-cell border="solid black 1px" padding="2px" font-weight="bold"
                                                   text-align="center">
                                        <fo:block>Id</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border="solid black 1px" padding="2px" font-weight="bold"
                                                   text-align="center">
                                        <fo:block>Model</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border="solid black 1px" padding="2px" font-weight="bold"
                                                   text-align="center">
                                        <fo:block>Manufacturer</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border="solid black 1px" padding="2px" font-weight="bold"
                                                   text-align="center">
                                        <fo:block>Year</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-header>

                            <fo:table-body>
                                <xsl:apply-templates select="nom"/>
                            </fo:table-body>
                            <fo:table-body>
                                <xsl:apply-templates select="description"/>
                            </fo:table-body>
                            <fo:table-body>
                                <xsl:apply-templates select="coordinate"/>
                            </fo:table-body>
                        </fo:table>
                    </fo:block> -->
                    <fo:block font-size="30pt" font-weight="bold" text-decoration="underline">
                        <xsl:value-of select="nom"/>
                    </fo:block>

                    <fo:block>
                        <fo:leader leader-pattern="space" leader-length="0pt"/>
                    </fo:block>

                    <fo:block>
                        <fo:external-graphic src="url('{liensImage/liensImage}')" content-height="scale-to-fit"  content-width="5.00in" />
                    </fo:block>
                    
                    <fo:block>
                        <fo:leader leader-pattern="space" leader-length="0pt"/>
                    </fo:block>

                    <fo:block font-size="15pt" font-weight="bold">Description</fo:block>
                    
                    <fo:block>
                        <xsl:value-of select="description"/>
                    </fo:block>
                    
                    <fo:block>
                        <fo:leader leader-pattern="space" leader-length="0pt"/>
                    </fo:block>
                    <fo:block>
                        <fo:leader leader-pattern="space" leader-length="0pt"/>
                    </fo:block>

                    <fo:block>
                        <fo:inline font-size="15pt" font-weight="bold">Ville : </fo:inline>
                        <fo:inline>
                            <xsl:value-of select="ville/nom"/>
                        </fo:inline>
                    </fo:block>

                    <fo:block>
                    <fo:leader leader-pattern="space" leader-length="0pt"/>
                    </fo:block>
                    
                    
                    <fo:block>
                        <fo:leader leader-pattern="space" leader-length="0pt"/>
                    </fo:block>
                    
                    <fo:block font-size="15pt" font-weight="bold">
                        <fo:inline>Note :  </fo:inline>
                        <fo:inline>
                            <xsl:value-of select="rating"/>
                        </fo:inline>
                        <fo:inline> / 5 </fo:inline>
                    </fo:block>

                    <fo:block>
                    <fo:leader leader-pattern="space" leader-length="0pt"/>
                    </fo:block>
                    <fo:block>
                        <fo:leader leader-pattern="space" leader-length="0pt"/>
                    </fo:block>
                    
                    <fo:block font-size="15pt" font-weight="bold">Coordinate :</fo:block>  
                    <fo:block>
                        <fo:inline>
                            <xsl:value-of select="coordinate/latitude"/>
                        </fo:inline>
                        <fo:inline> , </fo:inline>
                        <fo:inline>
                            <xsl:value-of select="coordinate/longitude"/>
                        </fo:inline>
                    </fo:block>

                    <fo:block>
                    <fo:leader leader-pattern="space" leader-length="0pt"/>
                    </fo:block>


                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    
    <xsl:template match="coordinate">
        <fo:table-row>
            <fo:table-cell>
                <fo:block>
                    <xsl:value-of select="latitude"/>
                </fo:block>
            </fo:table-cell>
            <fo:table-cell>
                <fo:block>
                    <xsl:value-of select="longitude"/>
                </fo:block>
            </fo:table-cell>
        </fo:table-row>
    </xsl:template>
    
    
    <xsl:template match="ville">
        <fo:block>
            <xsl:value-of select="nom"/>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>