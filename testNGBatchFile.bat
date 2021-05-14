set projectLocation=D:\IdeaProjects\Facial
cd %projectLocation%
set classpath=%projectLocation%\target\classes;%projectLocation%\src\libs\*
java org.testng.TestNG %projectLocation%\testng.xml
pause