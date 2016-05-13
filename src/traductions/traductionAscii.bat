dir /B "*.properties" >sauv.txt

for /f %%i IN (sauv.txt) DO (
	native2ascii -encoding UTF-8 base_traductions/%%i %%i
)

del sauv.txt