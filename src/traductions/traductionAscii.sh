listeFichiers=$(ls *.properties)
for fich in $listeFichiers
do
	native2ascii -encoding UTF-8 base_traductions/$fich $fich
done
