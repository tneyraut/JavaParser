JAVAPARSER=./javaparser
JAVAC=javac

.PHONY: compile 

compile:
	$(JAVAC) $(JAVAPARSER)/*.java