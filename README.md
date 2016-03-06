# JavaParser
Projet de réingénierie du logiciel

Ce programme permet d'analyser n'importe quel programme java.
Voici les commandes utiles : 
- java javaparser/JavaParser1_7 nomFichier.java 0 csv -> permet de récupérer les métriques (nombre de if, for, break, vairables locales...) du fichier sous format csv
- java javaparser/JavaParser1_7 nomFichier.java 0 json -> permet de récupérer les métriques (nombre de if, for, break, vairables locales...) du fichier sous format json 
- java javaparser/JavaParser1_7 nomFichier.java 1 -> permet de générer le diagramme de classes (installer GraphViz)
- java javaparser/JavaParser1_7 nomFichier.java 2 -> permet de générer le diagramme de flux de contrôle (installer GraphViz)

Au lieu de donner fichier java (nomFichier.java) en paramètre il est possible de donner une liste de fichiers .java comme ceci : 
- java javaparser/JavaParser1_7 @filelist.txt 0 csv -> permet de récupérer les métriques (nombre de if, for, break, vairables locales...) sous format csv
- java javaparser/JavaParser1_7 @filelist.txt 0 json -> permet de récupérer les métriques (nombre de if, for, break, vairables locales...) sous format json 
- java javaparser/JavaParser1_7 @filelist.txt 1 -> permet de générer le diagramme de classes (installer GraphViz)
- java javaparser/JavaParser1_7 @filelist.txt 2 -> permet de générer le diagramme de flux de contrôle (installer GraphViz)

Utiliser la commande suivante pour générer un fichier .txt comportant la liste des fichiers .java de votre projet : 
- java FileList cheminAbsolueDeLaRacineDeVotreProjet
