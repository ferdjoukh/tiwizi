#!/bin/bash

if [ "$1" = "--help" ] || [ "$1" = "" ]; then

echo "pdf creator for TIWIZI"
echo ""
echo "	This tool create a pdf file to summarise fixing suggestions by TIWIZI tool."
echo ""
echo "	params"
echo "		tiwizi log file to compile eg. log/log-123456.tiwizi"
echo "		pdf file path to create eg. summary"
echo ""
echo "	Required"
echo ""
echo "		Please make sure that these different software/libs are available on your computer:"
echo "			pdflatex"
echo ""

else

echo "I'm trying to find tiwizi files and to create" $2".pdf"
echo ""

cp base.tex $2".tex"

echo "\\lstinputlisting[language=tiwizi,caption=log of TIWIZI]{"$1"}" >> $2".tex"

echo "" >> $2".tex"
echo "" >> $2".tex"
echo "\\end{document}" >> $2".tex"

echo $2".tex was generated" 

pdflatex $2".tex"
pdflatex $2".tex"

echo $2".pdf was generated" 

fi			

