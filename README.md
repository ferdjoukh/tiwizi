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