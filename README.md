# Data-Analyser-For-Covid19-In-Europe
This project is built in eclipse environment with java code. 
The data analyzer can read csv files and on their data filters can be applied.
The result using swing library and xchart-3.8.1.jar is the output of a table with the filtered data and 2 charts(line,bar).
The project is divided into code packages and the collaboration is done using interfaces. 
The class where the main is located is DataAnalysis.java. 

src->application->naive/client->DataAnalysis.java

The file must be registered in the system , with the file name , dataset path and separator.
Then it is important to create hashmaps of individual filters . Where the individual filters (field,filter) will be placed inside .
At the end the result of the individual filters is generated , which is presented in the way mentioned above.

