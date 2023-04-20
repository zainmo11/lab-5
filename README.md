# Program to reorder Autosar ARXML containers alphabetically by their name sub-container "SHORT-NAME"
- This program is designed to read an Autosar ARXML file containing a list of containers, each with a unique ID, and reorder the containers alphabetically by their name sub-container "SHORT-NAME". The program will then write the reordered containers to a new ARXML file.

## Requirements
- The name of the arxml file shall be an argument which needs to be passed through the command line.
- If the file is not having .arxml extension then the program will trigger a user-defined handled exception “NotValidAutosarFileException”.
- If the file is empty, then the program will trigger a user-defined unhandled exception “EmptyAutosarFileException”.
- The output file shall be named the same as the input file concatenated with “_mod.arxml”. For example, if the input file is named "Rte_Ecuc.arxml", the output file should be "Rte_Ecuc_mod.arxml".
- Usage
-The program is written in Java and can be compiled using a Java compiler. The compiled Java file should be run with the input ARXML file passed as an argument through the command line. The output file will be created in the same directory as the input file.

## Batch File
- To make running the program easier, a batch file has been provided that allows you to test three different scenarios:

## Normal case:
   provide the input file and specify its name inside the batch script.
## Not valid Autosar file case:
   provide the file that is not valid Autosar file.
## Empty file case: 
   provide the empty file.
##To use the batch file, simply run the command below in the command line:
- sh run_autosar_reorder.sh
## Exceptions
- NotValidAutosarFileException: This exception is thrown when the input file does not have the .arxml extension.
- EmptyAutosarFileException: This exception is thrown when the input file is empty.
