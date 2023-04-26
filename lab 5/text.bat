@echo off
color 0A
echo Abdelrhman zain 
echo 2101646

echo Testing Normal file case...
java -jar ./XMLParser.jar example.arxml
echo ----------------------------------
echo:

echo Testing NotValid file case...
java -jar ./XMLParser.jar example.txt
echo ----------------------------------
echo:

echo Testing Empty file case...
java -jar ./XMLParser.jar exampleEmpty.arxml
echo:

pause