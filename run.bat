@echo off
set arg1=%~1
IF %~1==clean ( del *.class )
IF %~1==build ( javac dbload.java )
IF %~1==test1 ( java dbload )
IF %~1==test2 ( java dbload FormattedDataSmal.csv )
IF %~1==run ( java dbload -p 4096 FormattedData.csv )
