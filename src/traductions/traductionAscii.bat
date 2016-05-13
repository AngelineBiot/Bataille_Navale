for /f %%fich in ('dir /B *.properties') do (
	native2ascii -encoding UTF-8 base_traductions/%%fich %%fich
)