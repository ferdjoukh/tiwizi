# TIWIZI

is a tool whose the goal is to help meta-model designers, during their work.

TIWIZI is paired with a model generator, named GRIMM.

# Download it

You can clone the project. It is totally open source.

# How it works

This is a *fault localizer* for model generation process. The input of TIWIZI are: an ecore meta-model and candidate values.
Candidate values give the number of instances per class you want to generate. For specifying candidate values, you can use .grimm files exactly as in [grimm](https://adel-ferdjoukh.ovh/grimm/) tool   

# Fixing suggestions

The main goal of TIWIZI is to give you fixing suggestions for your meta-model, when you meet a failure during model generation.

After that, a *pdf-creator* collects all the suggestions computed by TIWIZI and summarise them into one single pdf file with a user-friendly syntax highlighting. Here you can see an [example](https://github.com/ferdjoukh/tiwizi/blob/master/suggestions-list-example.pdf) 

# Run TIWIZI

To run tiwizi, you should download the *tiwizi.jar* file or compile the project 

## Quick start
	
	We provide a toy example (simpleHouse.ecore metamodel and House.grimm configuration file)
	You can run it quickly if you type this command without parameters:

	```
	java -jar tiwizi.jar
	```

## Help

	Type this command to get help:

	```
	java -jar tiwizi.jar -h
	```

## USE
	tiwizi -[options string] meta-model rootClass configFile

## PARAMETERS
	all 4 parameters are mandatory

	[-options]
		-g generate a configuration file (filepath=configFile)
		-c check consistency with given meta-model, rootClass and configFile
		-v Verbose mode. Create .tiwizi file containing all log information
		    and a pdf file gathering all fixing suggestions with syntax highlighting
	meta-model
		Ecore meta-model filepath
	rootClass
		rootClass of given meta-model
	configFile
		.grimm configuration File

## EXAMPLES of possible combinations
	tiwizi -g cooking.ecore Kitchen cooking.grimm
	tiwizi -c cooking.ecore Kitchen cooking.grimm
	tiwizi -cv cooking.ecore Kitchen cooking.grimm
