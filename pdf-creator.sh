#!/bin/bash

if [ "$1" = "--help" ] || [ "$1" = "" ]; then

echo "pdf creator for TIWIZI"
echo ""
echo "	This tool create a pdf file to summarise fixing suggestions by TIWIZI tool."
echo ""
echo "	params"
echo "		pdf file path to creat eg. summary"
echo ""
echo "	Required"
echo ""
echo "		Please make sure that these different software/libs are available on your computer:"
echo "			pdflatex"
echo ""

else

echo "I'm trying to find tiwizi files and to create" $1".pdf"
echo ""

cp base.tex $1".tex"
tiwiziFiles=$(ls *.tiwizi)

	for file in $tiwiziFiles; do

		echo "\\lstinputlisting[language=tiwizi,caption=An example of fixing suggestion given by tiwizi]{"$file"}" >> $1".tex"

		echo "" >> $1".tex"

	done

	echo "" >> $1".tex"
	echo "\\end{document}" >> $1".tex"

	echo $1".tex was generated" 

	pdflatex $1".tex"
	pdflatex $1".tex"

	rm $1".log"
	rm $1".aux"

	echo $1".pdf was generated" 

fi			

