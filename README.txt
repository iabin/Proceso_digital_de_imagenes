ARTEAGA HERNÁNDEZ ALEJANDRO IABIN
313033414
para compilar usar
   	ant build

para generar el jar usar
	ant jar

para limpiar usar 
	ant clean

Práctica 1
incluye los filtros
1. Inverso


2. Alto contraste


3. Tonos de gris en las siguientes modalidades:

a.
	Gris = (R+G+B)/3
b. 
	Gris = R * 0.3 + G * 0.59 + B* 0.11
c. 
	Gris = (Max (R,G,B) * Min(R,G,B) )/ 2
d. 
	Gris = Max(R,G,B)
e. Gris = Min(R,G,B)
f. 
	Gris = (R,R,R)
g. Gris = (G,G,G)
h. 
	Gris = (B,B,B)


4. Brillo (R + Constante, G + Constante, B + Constante) (valores de -127 
a 128. Checar si se salen de rango): si R,G, o B > 255 entonces R, G o B 
= 255. Si R, G o B < 0 entonces R, G o B = 0.


5. Filtro Mosaico