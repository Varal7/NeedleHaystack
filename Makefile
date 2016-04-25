all: Main.class

Main.class:
	    javac  *.java

clean:
	rm -f *~ *.class

test: all
	# java -ea -Xmx2G Main $(SOURCE) $(TARGET)
	java Test
