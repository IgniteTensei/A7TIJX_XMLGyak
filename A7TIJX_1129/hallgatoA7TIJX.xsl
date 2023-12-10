<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" version = "1.0" >
    
    <xsl:template match="/">
        <html>
            <body>
                <h2>Student</h2>
                
                <table border = "3">
                    <tr bgcolor = "#88dbf2">
                        <th>STUDENTID</th>
                        <th>Keresztnév</th>
                        <th>Vezeteknév</th>
                        <th>Becenév</th>
                        <th>Kor</th>
                    </tr>
                    
                    <xsl:for-each select="class/student">
                        <tr>
                            <td>
                                <xsl:value-of select = "@studentid"/>
                            </td>
                            <td>
                                <xsl:value-of select = "keresztnev"/>
                            </td>
                            <td>
                                <xsl:value-of select = "vezeteknev"/>
                            </td>
                            <td>
                                <xsl:value-of select = "becenev"/>
                            </td>
                            <td>
                                <xsl:value-of select = "kor"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                    
                </table>
            </body>
        </html>
        
    </xsl:template>	
    <xsl:output method="html" encoding="utf-8" indent="yes"></xsl:output>
    
</xsl:stylesheet>