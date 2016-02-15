JAVA  = /usr/bin/java
# JAVAFLAGS = -version # -classpath $(LIBS)
JAVAC = /usr/bin/javac
JFLAGS = -g # -classpath $(LIBS)

SRCS = uk/org/mafoo/wordbubbles/Cell.java \
	uk/org/mafoo/wordbubbles/ImpossibleException.java \
	uk/org/mafoo/wordbubbles/Lexicon.java \
	uk/org/mafoo/wordbubbles/Prison.java

OBJS = ${SRCS:.java=.class}

.SUFFIXES: .java .class

all: build wordbubbles.jar

run: all
	$(JAVA) main

.java.class:
	$(JAVAC) $(JFLAGS) $<

build: $(OBJS) main.class

clean:
	rm -f $(OBJS)

wordbubbles.jar: build
	jar cf $@ $(OBJS)
