JJTREE=./jjtree
JAVACC=./javacc
JJDOC=./jjdoc
JAVAC=javac
GRAMMAR=java1_7
OUTPUT=javaparser

.PHONY: compile clean mrproper

compile:
	mkdir -p $(OUTPUT)
	$(JJTREE) $(GRAMMAR).jjt
	$(JAVACC) $(GRAMMAR).jj
	$(JJDOC) $(GRAMMAR).jj
	cp -rf visitors/*.java .
	$(JAVAC) *.java
	mv $(GRAMMAR).jj $(OUTPUT)
	mv *.class $(OUTPUT)
	mv *.java $(OUTPUT)

clean:
	rm -rf $(OUTPUT)

mrproper: clean
	rm -rf *~ $(GRAMMAR).html
