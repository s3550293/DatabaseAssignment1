@echo off
set arg1=%~1
IF %~1==clean ( del *.class )
IF %~1==build ( javac *.java )
IF %~1==test1 ( java dbload )
IF %~1==test2 ( java dbload FormattedDataSmal.csv )
IF %~1==run ( java dbload -p 4096 FormattedDataSmal.csv )

IF %~1==query1 ( java dbquery 4096 )
IF %~1==query2 ( java dbquery finance Water)
IF %~1==query ( java dbquery FASHIONSTYLEFILE 4096 )
